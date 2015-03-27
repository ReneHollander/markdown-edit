package at.rene8888.markdownedit.markdown.document;

import at.rene8888.markdownedit.exception.SaveException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PlainMarkdownDocument extends MarkdownDocument {

    public PlainMarkdownDocument(File path, String content) {
        super(path, content);
    }

    @Override
    public void save() throws SaveException {
        if (!this.getPath().mkdirs()) {
            throw new SaveException("Unable to create directories to save Markdown Document");
        }
        try {
            FileUtils.writeStringToFile(this.getPath(), this.getContent(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new SaveException("Error writing Markdown Document to disk", e);
        }
    }

    @Override
    public DocumentType getDocType() {
        return DocumentType.PLAIN;
    }
}
