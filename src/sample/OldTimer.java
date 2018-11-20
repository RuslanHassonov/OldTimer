package sample;
//Main Class

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OldTimer extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("OldTimers.fxml"));
        primaryStage.setTitle("Old Timers");
        primaryStage.setScene(new Scene(root, 718, 675));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
