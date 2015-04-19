package com.markdownedit.api.parser;

import com.markdownedit.api.util.TidyWrapper;
import org.w3c.dom.Document;

import java.io.StringReader;

public abstract class Parser {

    private String name;

    public Parser(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract String parseToHtml(String markdown);

    public Document parseToDocument(String markdown) {
        return TidyWrapper.tidy().parseDOM(new StringReader(parseToHtml(markdown)), null);
    }

}
