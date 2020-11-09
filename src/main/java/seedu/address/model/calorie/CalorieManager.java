package seedu.address.model.calorie;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;


/**
 * Stores and handles operation related to calories
 */
public class CalorieManager {

    public static final String MESSAGE_INVALID_INDEX = "Index is out of range";
    public static final String INSANE_INPUT_CALORIE = "why are you eat so much calories?\n"
            + "It is physically impossible to consume more than 2147483647KCal";
    public static final String INSANE_OUTPUT_CALORIE = "Good you are exercising so much!!\n"
            + "But it is physically impossible to expend more than 2147483647KCal";

    public static final String INVALID_INDEX = "please input a correct index";
    private ObservableList<Input> calorieInputList;
    private ObservableList<Output> calorieOutputList;
    private int totalCalorieIn;
    private int totalCalorieOut;
    private final Logger logger = LogsCenter.getLogger(CalorieManager.class);

    /**
     * Constructor for Manager that manage calorie input/output
     */
    public CalorieManager() {
        this.calorieOutputList = FXCollections.observableArrayList();
        this.calorieInputList = FXCollections.observableArrayList();
    }

    /**
     * Constructor for Manager that manage calorie input/output
     */
    public CalorieManager(ObservableList<Input> inputList, ObservableList<Output> outputList) {
        requireAllNonNull(inputList, outputList);
        this.calorieInputList = inputList;
        this.calorieOutputList = outputList;
        updateTotalCalorieCounts(inputList, outputList);
    }

    /**
     * returns the total input calorie
     */
    public int getTotalInputCalorie() {
        return totalCalorieIn;
    }

    /**
     * returns the total input calorie
     */
    public int getTotalOutputCalorie() {
        return totalCalorieOut;
    }

    /**
     * A method to update all the counters
     * @param inputList list of inputs
     * @param outputList list of outputs
     */
    public void updateTotalCalorieCounts(List<Input> inputList, List<Output> outputList) {
        requireAllNonNull(inputList, outputList);
        for (Input x: inputList) {
            totalCalorieIn += Integer.parseInt(x.getCalorieCount().calorieCount);
        }
        for (Output y: outputList) {
            totalCalorieOut += Integer.parseInt(y.getCalorieCount().calorieCount);
        }
    }

    /**
     * A method to update TotalCalorieOut
     * @param calorieOutput amount of calorie output to be updated
     */
    public void addTotalCalorieOut(CalorieCount calorieOutput) throws IllegalValueException {
        requireAllNonNull(calorieOutput);
        int previousTotalCalorieOut = totalCalorieOut;
        int calorieToAdd = Integer.parseInt(calorieOutput.toString());
        if (totalCalorieOut + calorieToAdd < 0) {
            throw new IllegalValueException(INSANE_OUTPUT_CALORIE);
        }
        totalCalorieOut += calorieToAdd;
        //        logger.info("----------update total calorie output----------");
        //        logger.info(previousTotalCalorieOut + "+" + calorieOutput.toString() + "=" + totalCalorieOut);
    }

    /**
     * A method to update TotalCalorieIn
     * @param calorieInput amount of calorie Input to be updated
     */
    public void addTotalCalorieInput(CalorieCount calorieInput) throws IllegalValueException {
        requireAllNonNull(calorieInput);
        int previousTotalCalorieIn = totalCalorieIn;
        int calorieToAdd = Integer.parseInt(calorieInput.toString());
        if (totalCalorieIn + calorieToAdd < 0) {
            throw new IllegalValueException(INSANE_INPUT_CALORIE);
        }
        totalCalorieIn += calorieToAdd;
        //        logger.info("----------update total calorie Input----------");
        //        logger.info(previousTotalCalorieIn + "+" + calorieInput.toString() + "=" + totalCalorieIn);
    }

    /**
     * delete calorie output from the calorieOutputList and update the total calorie output.
     * @param index of the output calorie to be deleted
     */
    public void deleteTotalCalorieOutput(int index) throws IllegalValueException {
        requireNonNull(index);
        try {
            CalorieCount calorieCount = getCalorieOutputList().get(index).getCalorieCount();
            totalCalorieOut -= Integer.parseInt(calorieCount.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalValueException(MESSAGE_INVALID_INDEX);
        }
    }

    /**
     * delete calorie input from the calorieInputList and update the total calorie input.
     * @param index of the input calorie to be deleted
     */
    public void deleteTotalCalorieInput(int index) throws IllegalValueException {
        requireNonNull(index);
        try {
            CalorieCount calorieCount = getCalorieInputList().get(index).getCalorieCount();
            totalCalorieIn -= Integer.parseInt(calorieCount.toString());
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalValueException(MESSAGE_INVALID_INDEX);
        }
    }

    public ObservableList<Output> getCalorieOutputList() {
        return calorieOutputList;
    }

    public ObservableList<Input> getCalorieInputList() {
        return calorieInputList;
    }

    /**
     * add a calorie input into an already sorted calorieInputList and update the total calorie input
     * @paran calorieInput to be added
     */
    public void addCalorieInput(Input calorieInput) throws IllegalValueException {
        requireNonNull(calorieInput);
        assert calorieInput instanceof Input : "calorieInput must be an input";
        addTotalCalorieInput(calorieInput.getCalorieCount());
        boolean isAdded = false;
        for (int index = calorieInputList.size() - 1; index >= 0; index -= 1) {
            if (calorieInput.happenAfter(calorieInputList.get(index))) {
                calorieInputList.add(index + 1, calorieInput);
                isAdded = true;
                break;
            }
        }
        if (!isAdded) {
            calorieInputList.add(0, calorieInput);
        }
    }

    /**
     * add a calorie output into an already sorted calorieOutputList and update the total calorie output
     * @param calorieOutput to be added
     */
    public void addCalorieOutput(Output calorieOutput) throws IllegalValueException {
        requireNonNull(calorieOutput);
        addTotalCalorieOut(calorieOutput.getCalorieCount());
        boolean isAdded = false;
        for (int index = calorieOutputList.size() - 1; index >= 0; index -= 1) {
            if (calorieOutput.happenAfter(calorieOutputList.get(index))) {
                calorieOutputList.add(index + 1, calorieOutput);
                isAdded = true;
                break;
            }
        }
        if (!isAdded) {
            calorieOutputList.add(0, calorieOutput);
        }
    }

    /**
     * removes a calorie from the List and update the total calorie input
     * @param isOut to determine which type of calorie to be removed
     * @param targetIndex of the calorie to be removed
     */
    public void removeCalorie(Boolean isOut, Index targetIndex) throws IllegalValueException {
        requireAllNonNull(isOut, targetIndex);
        int index = targetIndex.getZeroBased();
        if (!isOut) {
            deleteTotalCalorieInput(index);
            calorieInputList.remove(index);
        } else {
            deleteTotalCalorieOutput(index);
            calorieOutputList.remove(index);
        }
    }

    /**
     * Get size of list depending on type
     * @param isOut is the calorie type Output?
     */
    public int getListSize(Boolean isOut) {
        requireNonNull(isOut);
        if (!isOut) {
            return calorieInputList.size();
        } else {
            return calorieOutputList.size();
        }
    }

    /**
     * Get a calorie of a certain type and index
     * @param isOut is the calorie type Output?
     * @param index of the calorie to be retrieved
     */
    public Calorie getCalorie(Boolean isOut, Index index) throws IllegalValueException {
        requireAllNonNull(isOut, index);
        try {
            if (!isOut) {
                return calorieInputList.get(index.getZeroBased());
            } else {
                return calorieOutputList.get(index.getZeroBased());
            }
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalValueException(INVALID_INDEX);
        }
    }

    /**
     * replace a calorie with the respective index in the corresponding list with editedCalorie
     * @param isOut is the calorie type Output?
     * @param index of the calorie to be retrieved
     * @param editedCalorie the calorie to be edited
     */
    public CalorieManager setCalorie(Index index, Boolean isOut, Calorie editedCalorie) throws IllegalValueException {
        requireAllNonNull(index, isOut, editedCalorie);
        if (isOut) {
            calorieOutputList.remove(index.getZeroBased());
            addCalorieOutput((Output) editedCalorie);
        } else {
            calorieInputList.remove(index.getZeroBased());
            addCalorieInput((Input) editedCalorie);
        }
        return new CalorieManager(getCalorieInputList(), getCalorieOutputList());
    }

    /**
     * Check if there is a calorie in the list with the same time as param
     * @param toCheck if the list contains this calorie entry
     * @param isOut determines which list to check
     */
    public boolean contains(Calorie toCheck, Boolean isOut) {
        requireAllNonNull(toCheck, isOut);
        if (isOut && toCheck instanceof Input) {
            return false;
        }

        if (!isOut && toCheck instanceof Output) {
            return false;
        }

        if (isOut) {
            return calorieOutputList.stream().anyMatch(((Output) toCheck)::equals);
        } else {
            return calorieInputList.stream().anyMatch(((Input) toCheck)::equals);
        }
    }

    /**
     * Returns true if both CalorieManager have the same lists.
     * This defines a stronger notion of equality between two calorie managers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof CalorieManager)) {
            return false;
        }

        CalorieManager otherCalorieManager = (CalorieManager) other;
        return otherCalorieManager.getCalorieInputList().equals(getCalorieInputList())
                && otherCalorieManager.getCalorieOutputList().equals(getCalorieOutputList());
    }

}

