package com.markdownedit.api.document.annotation;

import com.markdownedit.api.document.io.DocumentReader;
import com.markdownedit.api.document.io.DocumentWriter;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DocumentType {

    String name();

    String[] fileTypes();

    Class<? extends DocumentReader> documentReader();

    Class<? extends DocumentWriter> documentWriter();

}
