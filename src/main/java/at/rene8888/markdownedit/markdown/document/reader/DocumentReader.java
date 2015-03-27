package at.rene8888.markdownedit.markdown.document.reader;

import at.rene8888.markdownedit.exception.ReadException;
import at.rene8888.markdownedit.markdown.document.MarkdownDocument;

import java.io.File;

public interface DocumentReader {

    public MarkdownDocument readFromFile(File file) throws ReadException;

}
