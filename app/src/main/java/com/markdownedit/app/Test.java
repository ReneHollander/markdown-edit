package com.markdownedit.app;

import com.markdownedit.api.plugin.Registry;

import java.io.File;

public class Test {

    public static void main(String[] args) {
        Registry registry = Registry.instance();
        Registry.instance().registerDocument(AwesomeDocument.class);
        System.out.println(Registry.instance().getDocumentTypeManager().getTypeForEnding("ad"));
        System.out.println(new AwesomeDocument(new File("test.ad"), "empty"));
    }

}
