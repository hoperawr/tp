package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE_COUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CALORIE_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EXERCISE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FOOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_DAYS;

import java.time.LocalDate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.day.Date;
import seedu.address.model.day.Day;
import seedu.address.model.day.calorie.Calorie;
import seedu.address.model.day.calorie.Input;
import seedu.address.model.day.calorie.Output;




public class CalorieCommand extends Command {

    public static final String COMMAND_WORD = "calorie";
    public static final String NO_AVAILABLE_DAY =
            "Please add a new day entry for the date intended before adding calorie input/output";
    public static final String INVALID_DATE = "Please input a valid Date";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a calorie to a particular day. "
            + "Parameters: "
            + PREFIX_CALORIE_TYPE + "IN/OUT"
            + PREFIX_DATE + "(OPTIONAL) 2020-10-14"
            + PREFIX_TIME + "TIME"
            + PREFIX_EXERCISE + "(IN) EXERCISE"
            + PREFIX_FOOD + "(OUT) FOOD"
            + PREFIX_CALORIE_COUNT + "CALORIE COUNT"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CALORIE_TYPE + "out "
            + PREFIX_TIME + "1230 "
            + PREFIX_EXERCISE + "RUNNING "
            + PREFIX_CALORIE_COUNT + "123";
    private Calorie calorie;
    private String type;
    private String date;

    /**
     * Creates an CalorieCommand to add the specified {@code Calorie}
     */
    public CalorieCommand(Calorie calorie, String type, String date) {
        this.calorie = calorie;
        this.type = type;
        this.date = date;
    }

    private LocalDate getDate(String date) throws CommandException {
        LocalDate addDate;
        if (date.isEmpty()) {
            addDate = LocalDate.now();
        } else {
            if (Date.isValidDate(date)) {
                addDate = LocalDate.parse(date);
            } else {
                throw new CommandException(INVALID_DATE);
            }
        }
        return addDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LocalDate date = getDate(this.date);
        if (model.hasDay(date)) {
            Day editDay = model.getDay(date);
            if (type.equals("in")) {
                editDay.getCalorieManager().addCalorieInput((Input) calorie);
            } else {
                editDay.getCalorieManager().addCalorieOutput((Output) calorie);
            }
            model.setDay(model.getDay(date), editDay);
            model.updateFilteredDayList(PREDICATE_SHOW_ALL_DAYS);
        } else {
            throw new CommandException(NO_AVAILABLE_DAY);
        }
        return new CommandResult(calorie.toString());
    }
}