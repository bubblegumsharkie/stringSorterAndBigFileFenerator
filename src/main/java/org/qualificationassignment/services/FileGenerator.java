package org.qualificationassignment.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.qualificationassignment.config.AppConfig;

public class FileGenerator {
    private final FileService fileService;

    public FileGenerator(FileService fileService) {
        this.fileService = fileService;
    }

    public String generateRandomString(int maxLineLength) {
        return RandomStringUtils.randomAlphanumeric(AppConfig.getAmountOfCharsToSort(), maxLineLength).toLowerCase();
    }

    public void generateFile() {
        System.out.println("[INFO] Deleting the previous generated input file");
        fileService.delete(AppConfig.getSourceFileName());
        fileService.createFile(AppConfig.getSourceFileName());
        for (int i = 0; i < AppConfig.getAmountOfGeneratedLines(); i++) {
            fileService.write(
                    AppConfig.getSourceFileName(),
                    generateRandomString(AppConfig.getMaxGeneratedLineLength()),
                    true);
        }
    }

}
