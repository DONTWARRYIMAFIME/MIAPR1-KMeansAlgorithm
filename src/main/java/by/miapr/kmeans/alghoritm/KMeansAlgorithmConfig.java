package by.miapr.kmeans.alghoritm;

public class KMeansAlgorithmConfig {
    private int numberOfPoints = 10_000;
    private int numberOfClusters = 19;
    private int numberOfIterations = 300;

    public KMeansAlgorithmConfig() {
    }

    public KMeansAlgorithmConfig(int numberOfPoints, int numberOfClusters, int numberOfIterations) {
        this.numberOfPoints = numberOfPoints;
        this.numberOfClusters = numberOfClusters;
        this.numberOfIterations = numberOfIterations;
    }

    public int getNumberOfPoints() {
        return numberOfPoints;
    }

    public void setNumberOfPoints(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
    }

    public int getNumberOfClusters() {
        return numberOfClusters;
    }

    public void setNumberOfClusters(int numberOfClusters) {
        this.numberOfClusters = numberOfClusters;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }
}
