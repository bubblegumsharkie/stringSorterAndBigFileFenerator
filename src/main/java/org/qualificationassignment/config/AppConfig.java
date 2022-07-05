package org.qualificationassignment.config;

public class AppConfig {
    static private final String PROGRAM_FILES_PATH = "./src/files/";
    static private final String GENERATED_FILE_NAME = "generated.txt";
    static private final String SORTED_FILE_NAME = "result.txt";
    static private final String SOURCE_FILE_NAME_PATH = PROGRAM_FILES_PATH + GENERATED_FILE_NAME;
    static private final String TEMP_FILE_PATH = PROGRAM_FILES_PATH + "tmp/";
    static private final String FINAL_FILE_PATH = PROGRAM_FILES_PATH + SORTED_FILE_NAME;
    static private final boolean IS_GENERATED = true;
    static private final int maxGeneratedLineLength = 300;
    static private final int amountOfGeneratedLines = 10000;
    static private final int maximumAmountOfSupportedLinesInFile = 5000;
    static private final int amountOfCharsToGroupBy = 2;

    public static String getProgramFilesPath() {
        return PROGRAM_FILES_PATH;
    }

    public static String getGeneratedFileName() {
        return GENERATED_FILE_NAME;
    }

    public static boolean isGenerated() {
        return IS_GENERATED;
    }

    public static String getSourceFileNamePath() {
        return SOURCE_FILE_NAME_PATH;
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

    public static int getAmountOfCharsToGroupBy() {
        return amountOfCharsToGroupBy;
    }


}
