package com.markdownedit.app;

import com.markdownedit.api.document.io.DocumentIO;

import java.io.File;
import java.io.IOException;

public class AwesomeDocumentIO implements DocumentIO<AwesomeDocument>{

    @Override
    public AwesomeDocument readDocument(File file) throws IOException {
        return null;
    }

    @Override
    public void updateDocument(AwesomeDocument document) throws IOException {

    }

    @Override
    public void writeDocument(AwesomeDocument document, File file) throws IOException {

    }
}
