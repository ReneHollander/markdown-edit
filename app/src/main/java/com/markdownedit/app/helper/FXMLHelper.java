package com.markdownedit.app.helper;

import com.markdownedit.app.Reference;
import com.markdownedit.app.exception.FXMLLoadException;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class FXMLHelper {

    public static void loadFxml(Initializable controller, String fxmlName) {
        FXMLLoader fxmlLoader = new FXMLLoader(controller.getClass().getResource(Reference.GUI.LAYOUT_FOLDER + fxmlName + ".fxml"));
        fxmlLoader.setRoot(controller);
        fxmlLoader.setController(controller);

        try {
            fxmlLoader.load();
        } catch (Exception exception) {
            throw new FXMLLoadException(exception);
        }
    }

}
