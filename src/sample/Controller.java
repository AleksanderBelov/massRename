package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import sample.Code;


public class Controller implements Initializable {
    private File listClips;
    private File dirWithClips;

    @FXML
    private TextField pathToFile;

    @FXML
    private TextField pathToDirectory;

    @FXML
    private Button renameStart;

    @FXML
    private Button renameStop;

    @FXML
    private TabPane tabPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        renameStart.setDisable(true);
        renameStop.setDisable(true);
        // TODO (don't really need to do anything here).

    }

    public void selectFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Resource File");
        listClips = fileChooser.showOpenDialog(null);
        if (listClips != null){
            pathToFile.setText(listClips.getPath());
            if (dirWithClips != null) {
                renameStart.setDisable(false);
            }
        }
        System.out.println("Button Clicked!");
    }

    public void selectDirectory(ActionEvent actionEvent) {

        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("select directory");
        dirWithClips = directoryChooser.showDialog(null);
        if (dirWithClips != null) {
            pathToDirectory.setText(dirWithClips.getAbsolutePath());
            if (listClips != null) {
                renameStart.setDisable(false);
            }
        }
    }

    public void renameStart(ActionEvent actionEvent) {

        renameStop.setDisable(false);
        Code code = new Code();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {

            if (code.startRename(listClips, dirWithClips)) {

                alert.setTitle("rename status");
                alert.setHeaderText(null);
                alert.setContentText("Done");
                alert.showAndWait();

            } else {

                alert.setTitle("rename status");
                alert.setHeaderText(null);
                alert.setContentText("Done");
                alert.showAndWait();
            }
        }
        if (tabPane.getSelectionModel().getSelectedIndex() == 1) {

            Lib.startAggregator();

        }

    }
}