package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DAYS;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.calorie.Calorie;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.calorie.Exercise;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Output;
import seedu.address.model.calorie.Time;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.Weight;

/**
 * Edit a certain calorie input/output for a particular day
 */
public class ChangeCommand extends Command {

    public static final String COMMAND_WORD = "change";

    public static final String MESSAGE_NO_DAY_DETERMINANT = " Either input a date or an index to specify which "
            + "date the calorie to be edited is present but not both ";

    public static final String MESSAGE_NO_CALORIE_DETERMINANT =
            " Index field to determine calorie to change cannot be empty ";

    public static final String MESSAGE_NO_TYPE_DETERMINANT = " Calorie type field cannot be empty";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the calorie identified "
            + "by the index number used in the displayed calorie list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TIME + "TIME] "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_EDIT_CALORIE_SUCCESS = "Edited Calorie: %1$s";

    public static final String MESSAGE_NOT_EDITED = "Please edit at least one of the calorie field";


    private Index index;
    private LocalDate date;
    private Index calorieIndex;
    private ChangeCalorieDescriptor changeCalorieDescriptor;

    /**
     * @param index of the calorie in the list to edit.
     * @param changeCalorieDescriptor details to edit the calorie with.
     */
    public ChangeCommand(Index index, ChangeCommand.ChangeCalorieDescriptor changeCalorieDescriptor,
                         Index calorieIndex) {
        requireAllNonNull(index, changeCalorieDescriptor, calorieIndex);

        this.index = index;
        this.changeCalorieDescriptor = new ChangeCommand.ChangeCalorieDescriptor(changeCalorieDescriptor);
        this.calorieIndex = calorieIndex;
    }

    /**
     * @param date of the calorie is in.
     * @param changeCalorieDescriptor details to edit the calorie with.
     */
    public ChangeCommand(LocalDate date, ChangeCommand.ChangeCalorieDescriptor changeCalorieDescriptor,
                         Index calorieIndex) {
        requireAllNonNull(date, changeCalorieDescriptor, calorieIndex);

        this.date = date;
        this.changeCalorieDescriptor = new ChangeCommand.ChangeCalorieDescriptor(changeCalorieDescriptor);
        this.calorieIndex = calorieIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Day> lastShownList = model.getMyFitnessBuddy().getDayList();
        Day editDay;

        if (index != null) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX);
            } else {
                editDay = lastShownList.get(index.getZeroBased());
            }
        } else {
            if (!model.hasDay(date)) {
                throw new CommandException(Messages.MESSAGE_INVALID_DAY_DISPLAYED_INDEX);
            } else {
                editDay = model.getDay(date);
            }
        }
        Boolean isOut = changeCalorieDescriptor.getIsOut();
        CalorieManager calorieManager = editDay.getCalorieManager();

        Calorie calorieToEdit = null;
        try {
            calorieToEdit = calorieManager.getCalorie(isOut, calorieIndex);
        } catch (IllegalValueException e) {
            throw new CommandException(Messages.MESSAGE_INVALID_CALORIE_DISPLAYED_INDEX);
        }
        Calorie editedCalorie = createEditedCalorie(calorieToEdit, changeCalorieDescriptor);

        if (isOut) {
            Output toEdit = (Output) calorieToEdit;
            Output edited = (Output) editedCalorie;
            if (toEdit.isSameOutput(edited)) {
                throw new CommandException(MESSAGE_NOT_EDITED);
            }
        }

        if (!isOut) {
            Input toEdit = (Input) calorieToEdit;
            Input edited = (Input) editedCalorie;
            if (toEdit.isSameInput(edited)) {
                throw new CommandException(MESSAGE_NOT_EDITED);
            }
        }

        CalorieManager cm = null;
        try {
            cm = calorieManager.setCalorie(calorieIndex, changeCalorieDescriptor.getIsOut(), editedCalorie);
        } catch (IllegalValueException e) {
            throw new CommandException(e.getMessage());
        }
        Date date = editDay.getDate();
        Weight weight = editDay.getWeight();

        Day editedDay = new Day(date, weight, cm);
        editedDay.setHeight(editDay.getHeight());
        editedDay.setStartingWeight(editDay.getStartingWeight());
        editDay.setAge(editDay.getAge());

        model.setDay(editDay, editedDay);
        model.updateFilteredDayList(PREDICATE_SHOW_ALL_DAYS);

        return new CommandResult(String.format(MESSAGE_EDIT_CALORIE_SUCCESS, editedCalorie));
    }

    /**
     * Creates and returns a {@code Calorie} with the details of {@code dayToEdit}
     * edited with {@code editCalorieDescriptor}.
     */
    private static Calorie createEditedCalorie(Calorie calorieToEdit,
                                               ChangeCommand.ChangeCalorieDescriptor changeCalorieDescriptor) {
        assert changeCalorieDescriptor != null;

        Time updatedTime = changeCalorieDescriptor.getTime().orElse(calorieToEdit.getTime());

        CalorieCount updatedCalorieCount = changeCalorieDescriptor.getCalorieCount()
                .orElse(calorieToEdit.getCalorieCount());

        Boolean isOut = changeCalorieDescriptor.getIsOut();
        if (isOut) {
            Exercise updatedExercise = changeCalorieDescriptor.getExercise()
                    .orElse(((Output) calorieToEdit).getExercise());
            return new Output(updatedTime, updatedExercise, updatedCalorieCount);
        } else {
            Food updatedFood = changeCalorieDescriptor.getFood()
                    .orElse(((Input) calorieToEdit).getFood());
            return new Input(updatedTime, updatedFood, updatedCalorieCount);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChangeCommand)) {
            return false;
        }

        // state check
        ChangeCommand e = (ChangeCommand) other;

        boolean temp;
        if (index != null) {
            temp = index.equals(e.index);
        } else {
            temp = date.equals(e.date);
        }

        return temp
                && calorieIndex.equals(e.calorieIndex)
                && changeCalorieDescriptor.equals(e.changeCalorieDescriptor);
    }
    /**
     * Stores the details to edit the calorie with. Each non-empty field value will replace the
     * corresponding field value of the calorie.
     */
    public static class ChangeCalorieDescriptor {

        private Time time;
        private Food food;
        private Exercise exercise;
        private CalorieCount calorieCount;
        private Boolean isOut;

        public ChangeCalorieDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public ChangeCalorieDescriptor(ChangeCommand.ChangeCalorieDescriptor toCopy) {
            setTime(toCopy.time);
            setFood(toCopy.food);
            setExercise(toCopy.exercise);
            setCalorieCount(toCopy.calorieCount);
            setIsOut(toCopy.isOut);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(time, calorieCount, food, exercise);
        }

        public void setIsOut(Boolean isOut) {
            this.isOut = isOut;
        }

        public Boolean getIsOut() {
            return isOut;
        }

        public void setTime(Time time) {
            this.time = time;
        }

        public Optional<Time> getTime() {
            return Optional.ofNullable(time);
        }

        public void setFood(Food food) {
            this.food = food;
        }

        public Optional<Food> getFood() {
            return Optional.ofNullable(food);
        }

        public void setExercise(Exercise exercise) {
            this.exercise = exercise;
        }

        public Optional<Exercise> getExercise() {
            return Optional.ofNullable(exercise);
        }

        public void setCalorieCount(CalorieCount calorieCount) {
            this.calorieCount = calorieCount;
        }
        public Optional<CalorieCount> getCalorieCount() {
            return Optional.ofNullable(calorieCount);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof ChangeCommand.ChangeCalorieDescriptor)) {
                return false;
            }

            // state check
            ChangeCommand.ChangeCalorieDescriptor e = (ChangeCommand.ChangeCalorieDescriptor) other;

            return getTime().equals(e.getTime())
                    && getIsOut().equals(e.getIsOut())
                    && getCalorieCount().equals(e.getCalorieCount())
                    && getExercise().equals(e.getExercise())
                    && getFood().equals(e.getFood());
        }
    }
}

