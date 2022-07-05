package org.qualificationassignment.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.qualificationassignment.config.AppConfig;

public class FileGenerator {
    private final FileService fileService;

    public FileGenerator(FileService fileService) {
        this.fileService = fileService;
    }

    public String generateRandomString(int maxLineLength) {
        return RandomStringUtils.randomAlphanumeric(AppConfig.getAmountOfCharsToGroupBy(), maxLineLength).toLowerCase();
    }

    public void generateFile(boolean generate) {
        if (generate) {
            System.out.println("[INFO] Deleting the previous generated input file");
            fileService.delete(AppConfig.getSourceFileNamePath());
            fileService.createFile(AppConfig.getSourceFileNamePath());
            for (int i = 0; i < AppConfig.getAmountOfGeneratedLines(); i++) {
                fileService.write(
                        AppConfig.getSourceFileNamePath(),
                        generateRandomString(AppConfig.getMaxGeneratedLineLength()),
                        true);
            }
        } else {
            System.out.println("Proceeding with the provided input file");
        }
    }

}
