package com.markdownedit.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        EditorPane editorPane = new EditorPane();
        primaryStage.setScene(new Scene(editorPane));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(ApplicationMain.class, args);
    }
}
