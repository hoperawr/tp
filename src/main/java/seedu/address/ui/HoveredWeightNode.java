package seedu.address.ui;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * @@author jewelsea-Reused
 * Reused from https://gist.github.com/jewelsea/4681797 with modifications
 *
 * A node which displays a value when the mouse cursor hovers over it for weight chart.
 */
public class HoveredWeightNode extends StackPane {

    /**
     * Creates a new Hovered Node for the data point
     * @param priorValue the value of the previous data point
     * @param value the current value of the data point
     */
    public HoveredWeightNode(int priorValue, int value) {
        setPrefSize(10, 10);

        final Label label = createDataThresholdLabel(priorValue, value);

        //Displays the data value label when the mouse cursor hovers at the data node of the chart.
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                getChildren().setAll(label);
                toFront();
            }
        });

        //Removes the data value label when the mouse cursor leaves at the data node of the chart.
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                getChildren().clear();
            }
        });
    }

    /**
     * Creates a label to display the value of the data node for weight.
     *
     * @param priorValue the data value of the previous node.
     * @param value the data value of the current node in focus.
     */
    private Label createDataThresholdLabel(int priorValue, int value) {
        final Label label = new Label(value + "");
        label.getStyleClass().addAll("default-color0", "chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 20; -fx-font-weight: bold");

        if (priorValue == 0 || priorValue == value) {
            //Value will be displayed in grey if there is no change between
            //the prior and current value or the current node is the first node
            //To indicate no change in weight
            label.setTextFill(Color.GRAY);
        } else if (value < priorValue) {
            //Value will be displayed in green if current value is smaller than prior value
            //To indicate weight loss
            label.setTextFill(Color.FORESTGREEN);
        } else {
            //Value will be displayed in red if current value is greater than prior value
            //To indicate weight gain
            label.setTextFill(Color.FIREBRICK);
        }

        label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
        return label;
    }
}
//@@author
