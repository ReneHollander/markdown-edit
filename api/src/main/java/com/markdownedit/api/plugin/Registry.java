package com.markdownedit.api.plugin;

import com.markdownedit.api.document.Document;
import com.markdownedit.api.document.DocumentTypeManager;
import com.markdownedit.api.highlighter.Highlighter;
import com.markdownedit.api.parser.Parser;

public class Registry {

    private static Registry instance;

    private DocumentTypeManager documentTypeManager;

    private Registry() {
        if (instance != null) throw new RuntimeException("there can only be one registry");
        documentTypeManager = new DocumentTypeManager();
    }

    public void registerDocument(Class<? extends Document> documentClass) {
        documentTypeManager.registerDocument(documentClass);
    }

    public void registerParser(Parser parser) {

    }

    public void registerHighlighter(Highlighter highlighter) {

    }

    public DocumentTypeManager getDocumentTypeManager() {
        return documentTypeManager;
    }

    public static Registry instance() {
        if (instance == null) {
            instance = new Registry();
        }
        return instance;
    }

}
