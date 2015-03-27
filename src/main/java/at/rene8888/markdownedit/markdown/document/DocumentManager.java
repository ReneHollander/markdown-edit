package at.rene8888.markdownedit.markdown.document;

import at.rene8888.markdownedit.exception.SaveException;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DocumentManager {

    private List<MarkdownDocument> activeDocuments;

    public DocumentManager() {
        this.activeDocuments = new ArrayList<>();
    }

    public List<MarkdownDocument> getActiveDocuments() {
        return activeDocuments;
    }

    public MarkdownDocument openDocument(File file) {
        // TODO open doc
        return null;
    }

    public boolean closeDocument(MarkdownDocument markdownDocument, boolean save) throws SaveException {
        if (save) {
            markdownDocument.save();
        }
        this.getActiveDocuments().remove(markdownDocument);
        return true;
    }

    public void saveAll() throws SaveException {
        for (MarkdownDocument doc : this.getActiveDocuments()) {
            doc.save();
        }
    }

    public void closeAll(boolean save) throws SaveException {
        if (save) {
            this.saveAll();
        }
        this.getActiveDocuments().clear();
    }
}
