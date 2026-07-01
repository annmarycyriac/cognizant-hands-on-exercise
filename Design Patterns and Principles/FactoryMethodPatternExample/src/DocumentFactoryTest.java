package com.example.factory;

/**
 * Demonstrates creating different kinds of documents through the
 * Factory Method pattern, WITHOUT the client code ever calling
 * `new WordDocument()`, `new PdfDocument()`, etc. directly.
 */
public class DocumentFactoryTest {

    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Demo: Document Management System ===\n");

        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        System.out.println("-- Creating a Word document --");
        Document word = wordFactory.openNewDocument();
        word.save();

        System.out.println("\n-- Creating a PDF document --");
        Document pdf = pdfFactory.openNewDocument();
        pdf.save();

        System.out.println("\n-- Creating an Excel document --");
        Document excel = excelFactory.openNewDocument();
        excel.save();

        System.out.println("\n-- Creating documents from an array of factories (polymorphism) --");
        DocumentFactory[] factories = { wordFactory, pdfFactory, excelFactory };
        for (DocumentFactory factory : factories) {
            Document doc = factory.createDocument();
            System.out.println("Created: " + doc.getType());
        }
    }
}
