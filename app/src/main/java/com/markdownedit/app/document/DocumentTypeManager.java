package com.markdownedit.app.document;

import com.markdownedit.api.document.DocumentType;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DocumentTypeManager {

    private final Set<DocumentType> documentTypeSet;
    private final Map<String, DocumentType> documentTypeFileEndingMapping;

    public DocumentTypeManager() {
        this.documentTypeSet = new HashSet<DocumentType>();
        documentTypeFileEndingMapping = new HashMap<String, DocumentType>();
    }

    public void register(DocumentType documentType) {
        if (!documentTypeSet.contains(documentType)) {
            throw new IllegalStateException("document already registered!");
        } else {
            for (String fileEnding : documentType.getFileTypes()) {
                if (documentTypeFileEndingMapping.containsKey(fileEnding))
                    throw new IllegalStateException("fileending " + fileEnding + " is already regisired!");
            }
            documentTypeSet.add(documentType);
            for (String fileEnding : documentType.getFileTypes()) {
                documentTypeFileEndingMapping.put(fileEnding, documentType);
            }
        }
    }

    public DocumentType getTypeForEnding(String fileEnding) {
        return documentTypeFileEndingMapping.get(fileEnding);
    }

}

