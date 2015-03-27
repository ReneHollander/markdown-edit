package at.rene8888.markdownedit.markdown.document;

import at.rene8888.markdownedit.exception.SaveException;

import java.io.File;

public abstract class MarkdownDocument {

    private File path;
    private String content;

    public MarkdownDocument(File path, String content) {
        this.content = content;
        this.path = path;
    }

    public File getPath() {
        return path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public abstract DocumentType getDocType();

    public abstract void save() throws SaveException;

    @Override
    public String toString() {
        return "MarkdownDocument{" +
                "content='" + getContent() + '\'' +
                ", docType=" + getDocType() +
                ", path=" + getPath() +
                '}';
    }
}
