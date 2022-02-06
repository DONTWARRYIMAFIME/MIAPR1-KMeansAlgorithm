package by.miapr.kmeans.scenes.controllers;

import by.miapr.kmeans.alghoritm.KMeansAlgorithm;
import by.miapr.kmeans.alghoritm.KMeansAlgorithmConfig;
import by.miapr.kmeans.alghoritm.data.Cluster;
import by.miapr.kmeans.alghoritm.data.Point;
import javafx.fxml.FXML;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.List;

public class MainSceneController {
    public static double POINT_SCALE = 3;
    private KMeansAlgorithm kMeansAlgorithm;

    @FXML
    private Button btnStart;

    @FXML
    private Button btnComplete;

    @FXML
    private Button btnX1;

    @FXML
    private Button btnX10;

    @FXML
    private Button btnX100;

    @FXML
    private BubbleChart<?, ?> bubbleChart;

    @FXML
    private TextField tfNumberOfClusters;

    @FXML
    private TextField tfNumberOfIterations;

    @FXML
    private TextField tfNumberOfPoints;

    @FXML
    void initialize() {
        btnStart.setOnAction(event -> {
            final KMeansAlgorithmConfig config = new KMeansAlgorithmConfig();

            config.setNumberOfPoints(Integer.parseInt(tfNumberOfPoints.getText()));
            config.setNumberOfIterations(Integer.parseInt(tfNumberOfIterations.getText()));
            config.setNumberOfClusters(Integer.parseInt(tfNumberOfClusters.getText()));

            kMeansAlgorithm = new KMeansAlgorithm(config);

            kMeansAlgorithm.iterate();
            drawPoints(bubbleChart);
            enableIterationButtons();
        });

        btnComplete.setOnAction(event -> {
            kMeansAlgorithm.iterate(Integer.parseInt(tfNumberOfIterations.getText()));
            drawPoints(bubbleChart);

            if (kMeansAlgorithm.isCompleted()) {
                disableIterationButtons();
            }
        });

        btnX1.setOnAction(event -> {
            kMeansAlgorithm.iterate();
            drawPoints(bubbleChart);

            if (kMeansAlgorithm.isCompleted()) {
                disableIterationButtons();
            }
        });

        btnX10.setOnAction(event -> {
            kMeansAlgorithm.iterate(10);
            drawPoints(bubbleChart);

            if (kMeansAlgorithm.isCompleted()) {
                disableIterationButtons();
            }
        });

        btnX100.setOnAction(event -> {
            kMeansAlgorithm.iterate(100);
            drawPoints(bubbleChart);

            if (kMeansAlgorithm.isCompleted()) {
                disableIterationButtons();
            }
        });
    }

    private void disableIterationButtons() {
        btnComplete.setDisable(true);
        btnX1.setDisable(true);
        btnX10.setDisable(true);
        btnX100.setDisable(true);
    }

    private void enableIterationButtons() {
        btnComplete.setDisable(false);
        btnX1.setDisable(false);
        btnX10.setDisable(false);
        btnX100.setDisable(false);
    }

    private void drawPoints(BubbleChart<?,?> bubbleChart) {
        bubbleChart.getData().clear();
        List<Cluster> clusterList = kMeansAlgorithm.getClusters();

        for (Cluster cluster : clusterList) {
            XYChart.Series series = new XYChart.Series();

            for (Point point : cluster.getPoints()) {
                series.getData().add(new XYChart.Data(point.getX(), point.getY(), POINT_SCALE));
            }
            bubbleChart.getData().add(series);
        }

        XYChart.Series series = new XYChart.Series();
        for (Cluster cluster : clusterList) {
            series.getData().add(new XYChart.Data(cluster.getCenterX(), cluster.getCenterY(), POINT_SCALE + 5));

        }

        bubbleChart.getData().add(series);
    }

}
