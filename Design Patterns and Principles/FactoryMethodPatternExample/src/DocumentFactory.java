package com.example.factory;

/**
 * The Creator in the Factory Method pattern.
 * Declares the factory method createDocument(), which subclasses
 * override to decide WHICH concrete Document class gets instantiated.
 */
public abstract class DocumentFactory {

    // The factory method itself.
    public abstract Document createDocument();

    /**
     * A template-style helper that uses the factory method without
     * knowing the concrete Document type it will receive.
     */
    public Document openNewDocument() {
        Document doc = createDocument();
        System.out.println("Factory produced a: " + doc.getType());
        doc.open();
        return doc;
    }
}
