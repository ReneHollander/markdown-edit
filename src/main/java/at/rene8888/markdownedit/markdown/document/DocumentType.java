package at.rene8888.markdownedit.markdown.document;

public enum DocumentType {

    PLAIN("Markdown File", "md");

    private String name;
    private String fileType;

    DocumentType(String name, String fileType) {
        this.name = name;
        this.fileType = fileType;
    }

    public String getName() {
        return name;
    }

    public String getFileType() {
        return fileType;
    }

}
