package com.markdownedit.app;

import com.markdownedit.api.document.Document;
import com.markdownedit.api.document.annotation.DocumentType;

import java.io.File;

@DocumentType(name = "Awesome Document", fileTypes = {"ad"}, documentIO = AwesomeDocumentIO.class)
public class AwesomeDocument extends Document {
    public AwesomeDocument(File path, String content) {
        super(path, content);
    }
}
