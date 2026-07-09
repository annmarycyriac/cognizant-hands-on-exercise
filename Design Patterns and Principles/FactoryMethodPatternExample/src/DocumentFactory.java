package com.example.factory;


public abstract class DocumentFactory {

  
    public abstract Document createDocument();

    public Document openNewDocument() {
        Document doc = createDocument();
        System.out.println("Factory produced a: " + doc.getType());
        doc.open();
        return doc;
    }
}
