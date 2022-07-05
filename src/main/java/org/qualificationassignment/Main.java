package org.qualificationassignment;

import org.qualificationassignment.services.FileGenerator;

public class Main {
    static FileGenerator fileGenerator = new FileGenerator();
    public static void main(String[] args) {
        System.out.println("Hello world!");
        fileGenerator.generateFile();

    }
}