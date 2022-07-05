package org.qualificationassignment.config;

public class AppConfig {
    static private final String PROGRAM_FILES_PATH = "./src/files/";
    static private final String SOURCE_FILE_NAME = PROGRAM_FILES_PATH + "file.txt";
    static private final String TEMP_FILE_PATH = PROGRAM_FILES_PATH + "tmp/";
    static private final String FINAL_FILE_PATH = PROGRAM_FILES_PATH + "final.txt";
    static private final int maxGeneratedLineLength = 300;
    static private final int amountOfGeneratedLines = 10000;
    static private final int maximumAmountOfSupportedLinesInFile = 5000;
    static private final int amountOfCharsToSort = 5;

    public static String getProgramFilesPath() {
        return PROGRAM_FILES_PATH;
    }

    public static String getSourceFileName() {
        return SOURCE_FILE_NAME;
    }

    public static String getTempFilePath() {
        return TEMP_FILE_PATH;
    }

    public static String getFinalFilePath() {
        return FINAL_FILE_PATH;
    }

    public static int getMaxGeneratedLineLength() {
        return maxGeneratedLineLength;
    }

    public static int getAmountOfGeneratedLines() {
        return amountOfGeneratedLines;
    }

    public static int getMaximumAmountOfSupportedLinesInFile() {
        return maximumAmountOfSupportedLinesInFile;
    }

    public static int getAmountOfCharsToSort() {
        return amountOfCharsToSort;
    }
}
