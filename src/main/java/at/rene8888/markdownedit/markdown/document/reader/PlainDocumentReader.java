package at.rene8888.markdownedit.markdown.document.reader;

import at.rene8888.markdownedit.exception.ReadException;
import at.rene8888.markdownedit.markdown.document.MarkdownDocument;
import at.rene8888.markdownedit.markdown.document.PlainMarkdownDocument;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PlainDocumentReader implements DocumentReader {
    @Override
    public MarkdownDocument readFromFile(File file) throws ReadException {
        try {
            String content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            return new PlainMarkdownDocument(file, content);
        } catch (IOException e) {
            throw new ReadException("Error reading Markdown Document", e);
        }
    }
}
