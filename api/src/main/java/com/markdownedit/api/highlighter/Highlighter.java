package com.markdownedit.api.highlighter;

import java.util.Arrays;
import java.util.List;

public abstract class Highlighter {

    private String name;
    private List<String> supportedLanguages;

    public Highlighter(String name, List<String> supportedLanguages) {
        this.name = name;
        this.supportedLanguages = supportedLanguages;
    }

    public Highlighter(String name, String... supportedLanguages) {
        this(name, Arrays.asList(supportedLanguages));
    }

    public String getName() {
        return name;
    }

    public List<String> getSupportedLanguages() {
        return supportedLanguages;
    }

    public boolean supportsLanguage(final String language) {
        return supportedLanguages.stream().filter(l -> l.equals(language)).findAny().isPresent();
    }

    public abstract String highlight(String language, String content);

}
