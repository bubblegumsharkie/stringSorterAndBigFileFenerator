package org.qualificationassignment.config;

public class AppConfig {
    static private String sourceFilePath = "./src/files";
    static private String sourceFileName = sourceFilePath + "/file.txt";
    static private int maxLineLength = 100;
    static private int amountOfLines = 20;

    public static String getSourceFilePath() {
        return sourceFilePath;
    }

    public static void setSourceFilePath(String sourceFilePath) {
        AppConfig.sourceFilePath = sourceFilePath;
    }

    public static String getSourceFileName() {
        return sourceFileName;
    }

    public static void setSourceFileName(String sourceFileName) {
        AppConfig.sourceFileName = sourceFileName;
    }

    public static int getMaxLineLength() {
        return maxLineLength;
    }

    public static void setMaxLineLength(int maxLineLength) {
        AppConfig.maxLineLength = maxLineLength;
    }

    public static int getAmountOfLines() {
        return amountOfLines;
    }

    public static void setAmountOfLines(int amountOfLines) {
        AppConfig.amountOfLines = amountOfLines;
    }
}
