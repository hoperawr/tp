package seedu.address.ui;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.day.calorie.Input;

public class CalorieInputCard extends UiPart<Region> {

    private static final String FXML = "CalorieInputListCard.fxml";

    public final Input calorieInput;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label calorieCount;
    @FXML
    private Label time;
    @FXML
    private Label food;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CalorieInputCard(Input calorieInput, int displayedIndex) {
        super(FXML);
        this.calorieInput = calorieInput;
        id.setText(displayedIndex + ". ");
        calorieCount.setText(calorieInput.getCalorieCount().toString() + " calories");
        time.setText(calorieInput.getTime().toString());
        food.setText(calorieInput.getFood().toString());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CalorieInputCard)) {
            return false;
        }

        // state check
        CalorieInputCard card = (CalorieInputCard) other;
        return id.getText().equals(card.id.getText())
                && calorieInput.equals(card.calorieInput);
    }

}
