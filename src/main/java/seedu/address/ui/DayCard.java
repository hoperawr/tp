package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.calorie.CalorieManager;
import seedu.address.model.day.Day;





/**
 * An UI component that displays information of a {@code Day}.
 */
public class DayCard extends UiPart<Region> {

    private static final String FXML = "DayListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Day day;
    private MainWindow mainWindow;

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label id;
    @FXML
    private Label weight;
    @FXML
    private Label totalCalorieIn;
    @FXML
    private Label totalCalorieOut;
    @FXML
    private Label surplus;
    @FXML
    private ImageView isLosing;
    @FXML
    private HBox profile;
    @FXML
    private ProgressBar progress;

    private Image cross = new Image(this.getClass().getResourceAsStream("/images/Cross.png"));
    private Image tick = new Image(this.getClass().getResourceAsStream("/images/Tick.png"));

    /**
     * Creates a {@code DayCard} with the given {@code Day} and index to display and the given {@code MainWindow}.
     */
    public DayCard(Day day, int displayedIndex, MainWindow mainWindow) {
        super(FXML);
        this.day = day;
        CalorieManager cm = day.getCalorieManager();
        id.setText(displayedIndex + ". ");
        date.setText("Date: " + day.getDate().get());
        weight.setText("Weight: " + day.getWeight().value + " kg");
        totalCalorieIn.setText("Total Calories In: " + cm.getTotalInputCalorie() + " calories");
        totalCalorieOut.setText("Total Calories Out: " + cm.getTotalOutputCalorie() + " calories");
        surplus.setText("Balance : " + day.getBalance());
        isLosing.setImage(day.isLosing() ? tick : cross);
        progress.setProgress(day.getProgress());
        this.mainWindow = mainWindow;

        // Fills the calorie placeholders and updates the status bar when a DayCard is double clicked
        cardPane.setOnMouseClicked(x -> {
            displayCalories(displayedIndex - 1);
            displayDate(day.getDate().get().toString());
        });
    }

    /**
     * Displays the list of calorie inputs and outputs.
     *
     * @param index the index of the day that was clicked.
     */
    private void displayCalories(int index) {
        this.mainWindow.updateCaloriePanelsWhenViewed(index);
    }

    /**
     * Displays the date selected in the status bar.
     *
     * @param date the date string of the day that was clicked.
     */
    private void displayDate(String date) {
        this.mainWindow.setDateLabel(date);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DayCard)) {
            return false;
        }

        // state check
        DayCard card = (DayCard) other;
        return id.getText().equals(card.id.getText())
                && day.equals(card.day);
    }
}
