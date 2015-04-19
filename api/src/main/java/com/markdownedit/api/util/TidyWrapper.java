package com.markdownedit.api.util;

import org.w3c.tidy.Tidy;

public class TidyWrapper {

    private static final Tidy tidy;

    static {
        tidy = new Tidy();
        tidy.setShowErrors(0);
        tidy.setQuiet(true);
    }

    public static Tidy tidy() {
        return tidy;
    }

}
