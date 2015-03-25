package at.rene8888.markdownedit;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.LineNumberFactory;
import org.pegdown.Extensions;
import org.pegdown.PegDownProcessor;
import org.w3c.dom.Document;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.function.IntFunction;

public class Test extends Application {

    private static final File DATA_FOLDER = new File("data");
    private static final File STYLE_FOLDER = new File(DATA_FOLDER, "style");

    private static final String DOCTYPE = "<?xml version=\"1.0\" ?>\n<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">";
    private static final String HTML_FRAMEWORK_FIRST_PART;
    private static final String HTML_FRAMEWORK_SECOND_PART;

    private static final PegDownProcessor PEGDOWN_PROCESSOR = new PegDownProcessor(Extensions.ALL - Extensions.ANCHORLINKS);

    static {
        String[] cssFiles = {"gfm.css", "highlighting.css"};

        String head = "";
        head += "<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />\n";
        head += "<title>HTML</title>\n";
        for (String cssFileString : cssFiles) {
            File cssFile = new File(STYLE_FOLDER, cssFileString);
            head += "<link rel=\"stylesheet\" href=\"file:///" + cssFile.getAbsolutePath() + "\" />\n";
        }
        HTML_FRAMEWORK_FIRST_PART = DOCTYPE + "\n<html xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n" + head + "\n</head>\n<body>\n";
        HTML_FRAMEWORK_SECOND_PART = "\n</body>\n</html>\n";

        System.out.println(HTML_FRAMEWORK_FIRST_PART + HTML_FRAMEWORK_SECOND_PART);

    }

    private Scene scene;

    @Override
    public void start(Stage stage) throws IOException, InterruptedException {

        com.threecrickets.jygments.grammar.LexerHelper lh = new com.threecrickets.jygments.grammar.LexerHelper();

        BorderPane bp = new BorderPane();

        HBox hbox = new HBox();
        bp.setTop(hbox);

        SplitPane sp = new SplitPane();
        bp.setCenter(sp);

        WebView browser = new WebView();
        browser.getEngine().setJavaScriptEnabled(true);
        CodeArea codeArea = new CodeArea();

        IntFunction<String> format = (digits -> " %" + digits + "d ");
        codeArea.setParagraphGraphicFactory(LineNumberFactory.get(codeArea, format));
        sp.getItems().add(codeArea);

        codeArea.setOnKeyTyped((e) -> {
            System.out.println(e);
        });

        codeArea.textProperty().addListener((obs, oldText, newText) -> {
            updateContents(browser.getEngine(), newText);
        });

        sp.getItems().add(browser);

        Button update = new Button("Update");
        update.setOnAction((event) -> {
            try {
                updateContents(browser.getEngine(), FileUtils.readFileToString(new File("in.md")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        hbox.getChildren().add(update);

        Button toPdf = new Button("To PDF");
        toPdf.setOnAction((event) -> {
            try {
                String htmlMarkdown = PEGDOWN_PROCESSOR.markdownToHtml(codeArea.getText());
                String content = HTML_FRAMEWORK_FIRST_PART + htmlMarkdown + HTML_FRAMEWORK_SECOND_PART;

                Tidy tidy = new Tidy(); // obtain a new Tidy instance
                tidy.setXHTML(false);
                Document doc = tidy.parseDOM(new StringReader(content), null);
                System.out.println(doc);
                ITextRenderer renderer = new ITextRenderer();
                renderer.setDocument(doc, null);
                renderer.layout();
                OutputStream os = new FileOutputStream("test.pdf");
                renderer.createPDF(os);
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        hbox.getChildren().add(toPdf);

        // create the scene
        stage.setTitle("Web View");
        scene = new Scene(bp, 750, 500);
        stage.setScene(scene);
        stage.show();

        // updateContents(browser.getEngine(), FileUtils.readFileToString(new File("in.md")));
    }

    public void updateContents(WebEngine engine, String newMarkdown) {
        long start = System.nanoTime();
        String htmlMarkdown = PEGDOWN_PROCESSOR.markdownToHtml(newMarkdown);
        String content = HTML_FRAMEWORK_FIRST_PART + htmlMarkdown + HTML_FRAMEWORK_SECOND_PART;
        engine.loadContent(content);
        long stop = System.nanoTime();
        long diff = stop - start;
        System.out.println(diff + "ns, " + (diff / 1000000) + "ms");
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }
}