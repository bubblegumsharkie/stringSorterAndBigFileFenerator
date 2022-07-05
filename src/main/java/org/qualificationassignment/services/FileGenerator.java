package org.qualificationassignment.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.qualificationassignment.config.AppConfig;

public class FileGenerator {
    FileService fileService = new FileService();

    public String generateRandomString(int maxLineLength) {
        return RandomStringUtils.randomAlphanumeric(0, maxLineLength).toLowerCase();
    }

    public void generateFile() {
        fileService.createFile();
        for (int i = 0; i < AppConfig.getAmountOfLines(); i++) {
            fileService.write(
                    AppConfig.getSourceFileName(),
                    generateRandomString(AppConfig.getMaxLineLength()),
                    true);
        }
    }

}
