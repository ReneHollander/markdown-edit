package at.rene8888.markdownedit.markdown.document;

import at.rene8888.markdownedit.exception.SaveException;

import java.io.File;

public abstract class MarkdownDocument {

    private String content;
    private DocumentType docType;
    private File path;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DocumentType getDocType() {
        return docType;
    }

    public void setDocType(DocumentType docType) {
        this.docType = docType;
    }

    public File getPath() {
        return path;
    }

    public abstract void save() throws SaveException;

    @Override
    public String toString() {
        return "MarkdownDocument{" +
                "content='" + content + '\'' +
                ", docType=" + docType +
                ", path=" + path +
                '}';
    }
}
