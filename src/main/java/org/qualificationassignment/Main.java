package org.qualificationassignment;

import org.qualificationassignment.config.AppConfig;
import org.qualificationassignment.services.FileGenerator;
import org.qualificationassignment.services.FileService;
import org.qualificationassignment.services.FileSorter;

import java.io.File;

public class Main {
    static FileService fileService = new FileService();
    static FileGenerator fileGenerator = new FileGenerator(fileService);
    static FileSorter fileSorter = new FileSorter(fileService);

    public static void main(String[] args) {
        System.out.println("[INFO] Launching the application");
        fileGenerator.generateFile(AppConfig.isGenerated());
        fileSorter.sortFile(new File(AppConfig.getSourceFileNamePath()));
    }
}