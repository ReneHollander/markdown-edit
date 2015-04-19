package com.markdownedit.api.document.io;

import com.markdownedit.api.document.Document;

import java.io.File;
import java.io.IOException;

public interface DocumentWriter<D extends Document> {

    public void writeDocument(D document, File file) throws IOException;

}
