package at.rene8888.markdownedit.markdown.document;

import at.rene8888.markdownedit.markdown.document.reader.DocumentReader;
import at.rene8888.markdownedit.markdown.document.reader.PlainDocumentReader;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum DocumentType {

    PLAIN("Markdown File", new String[]{"md"}, new PlainDocumentReader());

    private static final DocumentType DEFAULT_DOCUMENT_TYPE = DocumentType.PLAIN;
    private static final Map<String, DocumentType> FILE_TYPE_MAPPING = new HashMap<>();

    static {
        for (DocumentType dt : DocumentType.values()) {
            for (String ft : dt.getFileTypes()) {
                FILE_TYPE_MAPPING.put(ft, dt);
            }
        }
    }

    private String name;
    private String[] fileTypes;
    private DocumentReader documentReader;

    DocumentType(String name, String[] fileTypes, DocumentReader documentReader) {
        this.name = name;
        this.fileTypes = fileTypes;
        this.documentReader = documentReader;
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

    @Override
    public String toString() {
        return "DocumentType{" +
                "name='" + name + '\'' +
                ", fileTypes=" + Arrays.toString(fileTypes) +
                ", documentReader=" + documentReader +
                '}';
    }

    public static DocumentType getDocumentTypeByFileType(String type) {
        DocumentType dt = FILE_TYPE_MAPPING.get(type);
        if (dt == null) {
            dt = DEFAULT_DOCUMENT_TYPE;
        }
        return dt;
    }
}
