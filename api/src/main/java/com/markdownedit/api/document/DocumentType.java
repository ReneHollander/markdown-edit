package com.markdownedit.api.document;

import com.markdownedit.api.document.io.DocumentReader;
import com.markdownedit.api.document.io.DocumentWriter;

import java.util.Arrays;

public class DocumentType {

    private String name;
    private String[] fileTypes;
    private DocumentReader documentReader;
    private DocumentWriter documentWriter;

    public DocumentType(String name, String[] fileTypes, DocumentReader documentReader, DocumentWriter documentWriter) {
        this.name = name;
        this.fileTypes = fileTypes;
        this.documentReader = documentReader;
        this.documentWriter = documentWriter;
    }

    public String getName() {
        return name;
    }

    public String[] getFileTypes() {
        return fileTypes;
    }

    public DocumentReader getDocumentReader() {
        return documentReader;
    }

    public DocumentWriter getDocumentWriter() {
        return documentWriter;
    }

    @Override
    public String toString() {
        return "DocumentType{" +
                "name='" + name + '\'' +
                ", fileTypes=" + Arrays.toString(fileTypes) +
                ", documentReader=" + documentReader +
                ", documentWriter=" + documentWriter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentType that = (DocumentType) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(fileTypes, that.fileTypes)) return false;
        if (documentReader != null ? !documentReader.equals(that.documentReader) : that.documentReader != null)
            return false;
        return !(documentWriter != null ? !documentWriter.equals(that.documentWriter) : that.documentWriter != null);

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (fileTypes != null ? Arrays.hashCode(fileTypes) : 0);
        result = 31 * result + (documentReader != null ? documentReader.hashCode() : 0);
        result = 31 * result + (documentWriter != null ? documentWriter.hashCode() : 0);
        return result;
    }
}
