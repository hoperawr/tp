package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.calorie.CalorieCount;
import seedu.address.model.calorie.Food;
import seedu.address.model.calorie.Input;
import seedu.address.model.calorie.Time;

/**
 * Jackson-friendly version of {@link seedu.address.model.calorie.Input}.
 */
class JsonAdaptedInput {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Input's %s field is missing!";
    private final String calorieCount;
    private final String time;
    private final String food;

    /**
     * Constructs a {@code JsonAdaptedInput} with the given Input details.
     */
    @JsonCreator
    public JsonAdaptedInput(@JsonProperty("time") String time,
                            @JsonProperty("food") String food,
                            @JsonProperty("calorieCount") String calorieCount) {
        this.time = time;
        this.food = food;
        this.calorieCount = calorieCount;
    }

    /**
     * Converts a given {@code Input} into this class for Jackson use.
     */
    public JsonAdaptedInput(Input source) {
        calorieCount = source.getCalorieCount().calorieCount;
        time = source.getTime().time;
        food = source.getFood().food;
    }


    /**
     * Converts this Jackson-friendly adapted Input object into the model's {@code Input} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Input.
     */
    public Input toModelType() throws IllegalValueException {
        if (time == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Time.class.getSimpleName()));
        }

        if (!Time.isValidTime(time)) {
            throw new IllegalValueException(String.format(Time.MESSAGE_CONSTRAINTS));
        }
        final Time modelTime = new Time(time);

        if (food == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Food.class.getSimpleName()));
        }

        if (!Food.isValidFood(food)) {
            throw new IllegalValueException(String.format(Food.MESSAGE_CONSTRAINTS));
        }
        final Food modelFood = new Food(food);

        if (calorieCount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    CalorieCount.class.getSimpleName()));
        }
        if (!CalorieCount.isValidCalorieCount(calorieCount)) {
            throw new IllegalValueException(String.format(CalorieCount.MESSAGE_CONSTRAINTS));
        }
        final CalorieCount modelCalorieCount = new CalorieCount(calorieCount);

        return new Input(modelTime, modelFood, modelCalorieCount);
    }

}
