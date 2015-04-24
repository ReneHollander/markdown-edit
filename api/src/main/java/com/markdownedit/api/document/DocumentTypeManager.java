package com.markdownedit.api.document;

import com.markdownedit.api.document.io.DocumentIO;
import com.markdownedit.api.exception.RegisterException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DocumentTypeManager {

    private final Set<DocumentType> documentTypeSet;
    private final Map<String, DocumentType> documentTypeFileEndingMapping;
    private final Map<Class<? extends Document>, DocumentType> documentClassTypeMapping;

    public DocumentTypeManager() {
        this.documentTypeSet = new HashSet<DocumentType>();
        documentTypeFileEndingMapping = new HashMap<String, DocumentType>();
        documentClassTypeMapping = new HashMap<Class<? extends Document>, DocumentType>();
    }

    private void registerType(Class<? extends Document> documentClass, DocumentType documentType) {
        if (documentTypeSet.contains(documentType)) {
            throw new RegisterException("document already registered!");
        } else {
            for (String fileEnding : documentType.getFileTypes()) {
                if (documentTypeFileEndingMapping.containsKey(fileEnding))
                    throw new RegisterException("fileending " + fileEnding + " is already regisired!");
            }
            documentTypeSet.add(documentType);
            documentClassTypeMapping.put(documentClass, documentType);
            for (String fileEnding : documentType.getFileTypes()) {
                documentTypeFileEndingMapping.put(fileEnding, documentType);
            }
        }
    }

    public void registerDocument(Class<? extends Document> documentClass) {
        com.markdownedit.api.document.annotation.DocumentType documentTypeAnnotation = documentClass.getAnnotation(com.markdownedit.api.document.annotation.DocumentType.class);
        if (documentTypeAnnotation == null) {
            throw new RegisterException("supplied document doesn't have document type annotation!");
        }
        if (documentClassTypeMapping.containsKey(documentClass)) {
            throw new RegisterException("documentClass already registered");
        }
        Class<? extends DocumentIO> documentIOClass = documentTypeAnnotation.documentIO();
        DocumentIO documentIO;
        try {
            documentIO = documentIOClass.getConstructor().newInstance();
        } catch (Exception e) {
            throw new RegisterException("error instanciating documentReader!", e);
        }
        DocumentType documentType = new DocumentType(documentTypeAnnotation.name(), documentTypeAnnotation.fileTypes(), documentIO);
        registerType(documentClass, documentType);
    }

    public DocumentType getTypeForEnding(String fileEnding) {
        return documentTypeFileEndingMapping.get(fileEnding);
    }

    public DocumentType getForDocumentClass(Class<Document> documentClass) {
        DocumentType documentType = documentClassTypeMapping.get(documentClass);
        if (documentType == null) {
            throw new IllegalStateException("documentClass is not mapped to a type!");
        }
        return documentType;
    }

}

