package org.qualificationassignment.services;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {
    String sourceFilePath = "./src/files";
    String sourceFileName = sourceFilePath + "/file.txt";

    public void createFile() {
        try {
            Files.createDirectories(Paths.get(sourceFilePath));
            File myFile = new File(sourceFileName);
            if (myFile.createNewFile()) {
                System.out.println("File created: " + myFile.getName());
            } else {
                System.out.println("File already exists");
            }
        } catch (IOException e) {
            System.out.println("[ERROR] An unhandled error occurred while creating file");
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
            System.out.println("[ERROR] An unhandled error occurred while creating file");
            e.printStackTrace();
        }
    }
}
