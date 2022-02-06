package by.miapr.kmeans.alghoritm;

import by.miapr.kmeans.alghoritm.data.Cluster;
import by.miapr.kmeans.alghoritm.data.Point;
import by.miapr.kmeans.alghoritm.generator.impl.ClusterGenerator;
import by.miapr.kmeans.alghoritm.generator.impl.PointGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class KMeansAlgorithm {
    private final List<Cluster> clusters;
    private final List<Point> points;

    private final KMeansAlgorithmConfig config;


    private int currentIteration;
    private boolean isCompleted;

    public KMeansAlgorithm(KMeansAlgorithmConfig config) {
        this.config = config;

        clusters = new ClusterGenerator().generate(config.getNumberOfClusters());
        points = new PointGenerator().generate(config.getNumberOfPoints());
    }

    public void iterate() {
        if (!isCompleted && currentIteration >= config.getNumberOfIterations()) {
            isCompleted = true;
            return;
        }

        List<Cluster> previewsClusters = (List<Cluster>) ObjectCloner.deepCopy(clusters);

        if (currentIteration > 0) {
            recalculateClustersCenters(clusters);
        }

        for (Point point : points) {
            definePointCluster(point, clusters);
        }

        if (Objects.equals(previewsClusters, clusters)) {
            isCompleted = true;
            return;
        }

        currentIteration++;
    }

    public void iterate(int amount) {
        IntStream.range(0, amount).forEach((ind) -> iterate());
    }

    public List<Cluster> getClusters() {
        return Collections.unmodifiableList(clusters);
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    private void definePointCluster(Point point, List<Cluster> clusters) {
        Cluster neededCluster = null;
        double minDist = -1;

        for (Cluster cluster : clusters) {
            double dist = calculateDistance(cluster.getCenterX(), cluster.getCenterY(), point.getX(), point.getY());
            if (dist < minDist || minDist == -1) {
                minDist = dist;
                neededCluster = cluster;
            }
        }

        try {
            neededCluster.addPoint(point);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void recalculateClustersCenters(List<Cluster> clusters) {
        clusters.forEach(cluster -> {
            List<Point> pointList = cluster.getPoints();

            if (pointList != null && !pointList.isEmpty()) {

                int sumX = 0, sumY = 0;

                for (Point point : pointList) {
                    sumX += point.getX();
                    sumY += point.getY();
                }

                int newX = sumX / pointList.size();
                int newY = sumY / pointList.size();

                cluster.setCenter(newX, newY);
            }
        });
    }

    private double calculateDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}