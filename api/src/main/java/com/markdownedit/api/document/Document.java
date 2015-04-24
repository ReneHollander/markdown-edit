package com.markdownedit.api.document;

import com.markdownedit.api.plugin.Registry;

import java.io.File;

public abstract class Document {

    private File path;
    private DocumentType documentType;
    private String content;

    public Document(File path, String content) {
        this.content = content;
        this.path = path;
        this.inject();
    }

    @SuppressWarnings("unchecked")
    private void inject() {
        this.documentType = Registry.instance().getDocumentTypeManager().getForDocumentClass((Class<Document>) this.getClass());
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    @Override
    public String toString() {
        return "Document{" +
                "path=" + path +
                ", documentType=" + documentType +
                ", content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Document document = (Document) o;

        if (path != null ? !path.equals(document.path) : document.path != null) return false;
        if (documentType != null ? !documentType.equals(document.documentType) : document.documentType != null)
            return false;
        return !(content != null ? !content.equals(document.content) : document.content != null);

    }

    @Override
    public int hashCode() {
        int result = path != null ? path.hashCode() : 0;
        result = 31 * result + (documentType != null ? documentType.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }
}
