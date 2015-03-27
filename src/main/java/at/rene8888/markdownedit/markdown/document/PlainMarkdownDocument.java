package at.rene8888.markdownedit.markdown.document;

import at.rene8888.markdownedit.exception.SaveException;

import java.io.FileWriter;
import java.io.IOException;

public class PlainMarkdownDocument extends MarkdownDocument {

    @Override
    public void save() throws SaveException {
        if (!this.getPath().mkdirs()) {
            throw new SaveException("Unable to create directories to save Markdown Document");
        }
        try {
            FileWriter writer = new FileWriter(this.getPath());
            writer.write(this.getContent());
            writer.close();
        } catch (IOException e) {
            throw new SaveException("Error writing Markdown Document to disk", e);
        }
    }
}
