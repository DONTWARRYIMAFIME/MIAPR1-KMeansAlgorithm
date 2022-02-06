module by.miapr.kmeans {
    requires javafx.controls;
    requires javafx.fxml;

    opens by.miapr.kmeans.scenes.controllers to javafx.fxml;

    exports by.miapr.kmeans;
    exports by.miapr.kmeans.alghoritm;
    exports by.miapr.kmeans.alghoritm.data;
}
