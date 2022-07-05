package org.qualificationassignment.services;

import org.qualificationassignment.config.AppConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {

    public void createFile() {
        try {
            Files.createDirectories(Paths.get(AppConfig.getSourceFilePath()));
            File myFile = new File(AppConfig.getSourceFileName());
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("[ERROR] [READ] An unhandled error occurred while creating file");
            e.printStackTrace();
        }
    }

    public void write(String filename, String line, boolean append) {
        try (
                FileWriter file = new FileWriter(filename, append);
                BufferedWriter bufferedWriter = new BufferedWriter(file)
        ) {
            bufferedWriter.write(line + "\n");
            System.out.println("The \"" + line + "\" string is successfully written to the " + filename);
        } catch (IOException e) {
            System.out.println("[ERROR] [WRITE] An unhandled error occurred while writing file");
            e.printStackTrace();
        }
    }
}
