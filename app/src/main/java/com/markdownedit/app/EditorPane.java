package com.markdownedit.app;

import com.markdownedit.app.helper.FXMLHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorPane extends BorderPane implements Initializable {

    @FXML
    private TabPane tpMain;
    @FXML
    private Label lRenderTime;
    @FXML
    private MenuItem miSave;
    @FXML
    private MenuItem miAbout;
    @FXML
    private MenuItem miOpen;
    @FXML
    private MenuItem miClose;
    @FXML
    private MenuItem miExport;

    public EditorPane() {
        FXMLHelper.loadFxml(this, Reference.GUI.FXML.EDITOR);
    }

    @FXML
    public void miOpenClick(ActionEvent event) {

    }

    @FXML
    public void miSaveClick(ActionEvent event) {

    }

    @FXML
    public void miExportClick(ActionEvent event) {

    }

    @FXML
    public void miCloseClick(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public void miAboutClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.tpMain.getTabs().add(new EditorTab());
    }

}
