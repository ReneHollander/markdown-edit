package com.markdownedit.api.document.io;

import com.markdownedit.api.document.Document;

import java.io.File;
import java.io.IOException;

public interface DocumentReader<D extends Document> {

    public D readDocument(File file) throws IOException;

    public void updateDocument(Document document) throws IOException;

}
