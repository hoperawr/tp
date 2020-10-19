package seedu.address.ui;

import java.util.Arrays;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.day.Day;

/**
 * The controller for the weight statistics window.
 *
 * @@author Marco Jakob - Adapted
 * Adapted from https://code.makery.ch/library/javafx-tutorial/part6/
 * with modifications for this app context.
 */
public class WeightStatsWindow extends UiPart<Stage> {

    private static final Logger logger = LogsCenter.getLogger(WeightStatsWindow.class);
    private static final String FXML = "WeightStatsWindow.fxml";

    @FXML
    private LineChart<String, Integer> lineChart;

    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> dates = FXCollections.observableArrayList();

    /**
     * Creates a new WeightStatsWindow.
     *
     * @param root Stage to use as the root of the WeightStatsWindow.
     */
    public WeightStatsWindow(Stage root) {
        super(FXML, root);
    }

    /**
     * Creates a new WeightStatsWindow.
     */
    public WeightStatsWindow(ObservableList<Day> dayList) {
        this(new Stage());
        initialize(dayList);
        setWeightData(dayList);
    }

    /**
     * Initializes the Line Chart
     */
    @FXML
    private void initialize(ObservableList<Day> dayList) {
        String[] datesString = dayList.stream().map(x -> x.getDate().get().toString())
                .toArray(String[]::new);

        dates.addAll(Arrays.asList(datesString));

        // Assign the dates as categories for the horizontal axis.
        xAxis.setCategories(dates);
    }

    /**
     * Sets the weight data points to show the statistics for.
     *
     * @param dayList
     */
    public void setWeightData(ObservableList<Day> dayList) {

        XYChart.Series<String, Integer> weights = new XYChart.Series<>();
        weights.setName("Weight");

        //Get the weight data of all the days.
        for (int i = 0; i < dayList.size(); i++) {
            weights.getData().add(new XYChart.Data<>(dates.get(i), Integer.parseInt(dayList.get(i).getWeight().value)));
        }

        lineChart.getData().add(weights);
    }

    /**
     * Shows the weight statistics window.
     * @throws IllegalStateException
     * <ul>
     *     <li>
     *         if this method is called on a thread other than the JavaFX Application Thread.
     *     </li>
     *     <li>
     *         if this method is called during animation or layout processing.
     *     </li>
     *     <li>
     *         if this method is called on the primary stage.
     *     </li>
     *     <li>
     *         if {@code dialogStage} is already showing.
     *     </li>
     * </ul>
     */
    public void show() {
        logger.fine("Showing weight statistics.");
        getRoot().show();
        getRoot().centerOnScreen();
    }

    /**
     * Returns true if the help window is currently being shown.
     */
    public boolean isShowing() {
        return getRoot().isShowing();
    }

    /**
     * Hides the help window.
     */
    public void hide() {
        getRoot().hide();
    }

    /**
     * Focuses on the help window.
     */
    public void focus() {
        getRoot().requestFocus();
    }


}
