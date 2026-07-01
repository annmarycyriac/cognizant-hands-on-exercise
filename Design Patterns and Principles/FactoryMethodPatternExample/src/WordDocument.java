package com.example.factory;

public class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a Word (.docx) document...");
    }

    @Override
    public void save() {
        System.out.println("Saving Word document in .docx format.");
    }

    @Override
    public String getType() {
        return "Word Document";
    }
}
