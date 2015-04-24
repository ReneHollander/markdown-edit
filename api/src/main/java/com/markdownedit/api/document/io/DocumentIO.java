package com.markdownedit.api.document.io;

import com.markdownedit.api.document.Document;

import java.io.File;
import java.io.IOException;

public interface DocumentIO<D extends Document> {

    public D readDocument(File file) throws IOException;

    public void updateDocument(D document) throws IOException;

    public void writeDocument(D document, File file) throws IOException;

}
