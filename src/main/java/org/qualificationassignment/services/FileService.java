package org.qualificationassignment.services;

import org.apache.commons.io.FileUtils;
import org.qualificationassignment.config.AppConfig;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileService {

    public void createFile(String filePath) {
        try {
            Files.createDirectories(Paths.get(AppConfig.getProgramFilesPath()));
            Files.createDirectories(Paths.get(AppConfig.getTempFilePath()));
            File myFile = new File(filePath);
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

    public void write(String filePath, String line, boolean append) {
        try (
                FileWriter file = new FileWriter(filePath, append);
                BufferedWriter bufferedWriter = new BufferedWriter(file)
        ) {
            bufferedWriter.write(line + "\n");
        } catch (IOException e) {
            System.out.println("[ERROR] [WRITE] An unhandled error occurred while writing file");
            e.printStackTrace();
        }
    }

    public void delete(String filePath) {
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            System.out.println("[ERROR] [DELETE] An unhandled error occurred while deleting file");
            e.printStackTrace();
        }
    }

    public void deleteFolder(String folderPath) {
        try {
            FileUtils.deleteDirectory(new File(folderPath));
        } catch (IOException e) {
            System.out.println("[ERROR] [DELETE] An unhandled error occurred while deleting file");
            e.printStackTrace();
        }
    }

}
