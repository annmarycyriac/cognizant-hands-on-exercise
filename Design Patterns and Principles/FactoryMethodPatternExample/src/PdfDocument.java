package com.example.factory;

public class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a PDF (.pdf) document...");
    }

    @Override
    public void save() {
        System.out.println("Saving document in .pdf format.");
    }

    @Override
    public String getType() {
        return "PDF Document";
    }
}
