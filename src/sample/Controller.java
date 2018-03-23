package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button selectFile;
    private Button selectDirectory;

    @FXML
    private TextField pathToFile;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // TODO (don't really need to do anything here).

    }

    public void selectFile(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
      //  fileChooser.showOpenDialog();

      pathToFile.setText("press key");
        System.out.println("Button Clicked!");
    }
}