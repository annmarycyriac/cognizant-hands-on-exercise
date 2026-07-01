package com.example.factory;

public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening an Excel (.xlsx) document...");
    }

    @Override
    public void save() {
        System.out.println("Saving document in .xlsx format.");
    }

    @Override
    public String getType() {
        return "Excel Document";
    }
}
