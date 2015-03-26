package at.rene8888.markdownedit.gui;

import at.rene8888.markdownedit.Reference;
import at.rene8888.markdownedit.gui.helper.FXMLHelper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import org.fxmisc.richtext.CodeArea;

import java.net.URL;
import java.util.ResourceBundle;

public class EditorTab extends Tab {

    public EditorTab() {
        this.setText("Hello World!");
        this.setContent(new EditorTabContent());
    }

    private class EditorTabContent extends SplitPane implements Initializable {

        @FXML
        public CodeArea caMarkdown;
        @FXML
        public WebView wvLivePreview;

        public EditorTabContent() {
            FXMLHelper.loadFxml(this, Reference.GUI.FXML.EDITOR_TAB);
        }

        @Override
        public void initialize(URL location, ResourceBundle resources) {
            wvLivePreview.getEngine().loadContent("Hello World!");
        }
    }
}
