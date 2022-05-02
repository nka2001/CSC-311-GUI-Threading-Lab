package com.mycompany.threadinglab;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;

public class PrimaryController {

    @FXML
    private Button primaryButton;
    @FXML
    private Button longRun;
    @FXML
    private Label label;
    @FXML
    private ProgressBar progress;

    @FXML
    private void showMessage(ActionEvent event) {

        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setContentText("I Love JAVAFX");
        a.show();

    }

    /**
     * this method shows how tying up a thread will cause a major issue for the
     * main GUI if you want to use this method change the onaction in the fxml
     * file
     *
     * @param event
     */
    @FXML
    private void longRunningOperation(ActionEvent event) {

        //lockedGUI();
        Thread t = new Thread(() -> lockedGUI());
        t.start();
    }

    public void lockedGUI() {

        System.out.println("long operation started");
        Platform.runLater(() -> {
            label.setText("long operation started");
            longRun.setDisable(true);
        });

        for (int i = 0; i < 5; i++) {

            try {
                Thread.sleep(1000);
                progress.setProgress(i);

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
        Platform.runLater(() -> {
            label.setText("long operation ended");
            longRun.setDisable(false);
        });
        System.out.println("long operation ended");

    }
}
