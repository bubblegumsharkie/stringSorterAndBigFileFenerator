package org.qualificationassignment.services;

import org.apache.commons.io.FileUtils;
import org.qualificationassignment.config.AppConfig;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class FileSorter {
    private final String pathToTemp = AppConfig.getTempFilePath();
    private final FileService fileService;

    private final String pathToResultFile = AppConfig.getFinalFilePath();

    public FileSorter(FileService fileService) {
        this.fileService = fileService;
    }

    public void sortFile(File file) {
        String news = AppConfig.getFinalFilePath().split("/")[AppConfig.getFinalFilePath().split("/").length - 1];
        System.out.println("[INFO] Deleting previous results of running this application: " + news);
        fileService.delete(AppConfig.getFinalFilePath());
        HashMap<String, Integer> passedLetters = new HashMap<>();
        int amountOfSupportedLinesInFile = AppConfig.getMaximumAmountOfSupportedLinesInFile();
        int amountLine = AppConfig.getAmountOfGeneratedLines();
        int sizePartLine = AppConfig.getAmountOfCharsToGroupBy();
        String pathToFile = file.getAbsolutePath();

        if (amountLine < amountOfSupportedLinesInFile) {
            System.out.println("[FILE SORTER] The file is size is small enough to process. Starting sorting process");
            sortLineToFile(pathToFile);
        } else {
            System.out.println("[FILE SORTER] The file size exceeds limit of processing. Trying to divide it to smaller files");
            fileSeparator(pathToFile, passedLetters, sizePartLine);
        }
        System.out.println("[INFO] Dividing all files to be acceptable size");
        while (true) {
            sizePartLine = sizePartLine + 2;
            int counter = passedLetters.keySet().size();
            for (Map.Entry<String, Integer> partLine : passedLetters.entrySet()) {
                if (partLine.getValue() > amountOfSupportedLinesInFile) {
                    String fileName = partLine.getKey().toLowerCase(Locale.ROOT);
                    fileSeparator(pathToTemp + fileName, passedLetters, sizePartLine);
                } else {
                    counter--;
                }
            }
            if (counter == 0) {
                System.out.println("[INFO] File is separated, all the temp files are go");
                break;
            }
        }
        System.out.println("[INFO] Sorting files in small files");
        passedLetters.keySet().forEach(s ->
                sortLineToFile(pathToTemp + s + ".txt"));
        System.out.println("[INFO] Creating the final file");
        fileService.createFile(pathToResultFile);
        System.out.println("[INFO] Sorting and putting to the final file");
        passedLetters.keySet().stream().sorted().forEach(tmp -> {
            try {
                writeResultsToFile(tmp);
            } catch (Exception e) {
                System.out.println("[ERROR] Couldn't create the final file");
                e.printStackTrace();
            }
            fileService.delete(pathToTemp + tmp + ".txt");
        });
        try {
            FileUtils.copyFile(new File(pathToTemp + AppConfig.getGeneratedFileName()), new File(pathToResultFile));
            fileService.delete(pathToTemp + AppConfig.getGeneratedFileName());
        } catch (IOException e) {
            System.out.println("[INFO] Temp folder deleted");
        }
        fileService.deleteFolder(AppConfig.getTempFilePath());
    }

    private void writeResultsToFile(String tempFileName) {
        System.out.println("[INFO] Putting strings into final file");
        String tempFile = pathToTemp + tempFileName + ".txt";
        try (
                FileReader fileReader = new FileReader(tempFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
//            fileService.write(pathToResultFile, bufferedReader.readLine(), true);
            bufferedReader.lines().forEach(l -> fileService.write(pathToResultFile, l, true));
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERROR] [FILE WRITER] [READ] An unhandled error occurred while creating file");
            e.printStackTrace();
        }
    }

    private void fileSeparator(String pathToFile, HashMap<String, Integer> passedLetters, Integer sizePartLine) {
        System.out.println("[INFO] Separating the big file into chunks");
        try (
                FileReader fileReader = new FileReader(pathToFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            System.out.println("[INFO] Comparing the lines");
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.length() - 1 < sizePartLine) {
                    passedLetters.put(line.toLowerCase(Locale.ROOT), +1);
                    String fileName = line.toLowerCase(Locale.ROOT) + ".txt";
                    fileService.createFile(pathToTemp + fileName);
                    fileService.write(pathToTemp + fileName, line, true);
                    continue;
                }
                String startLine = line.substring(0, sizePartLine);
                if (passedLetters.containsKey(startLine.toLowerCase(Locale.ROOT))) {
                    System.out.println("[INFO] Added part of the string to the file");
                    fileService.write(pathToTemp + startLine.toLowerCase(Locale.ROOT) + ".txt", line, true);
                    passedLetters.put(startLine.toLowerCase(Locale.ROOT), passedLetters.get(startLine.toLowerCase(Locale.ROOT)) + 1);
                } else {
                    System.out.println("[INFO] Renewed amount of unique strings");
                    passedLetters.put(startLine.toLowerCase(Locale.ROOT), 1);
                    startLine = startLine.toLowerCase(Locale.ROOT) + ".txt";
                    System.out.println("[INFO] Creating a new file: " + startLine);
                    fileService.createFile(pathToTemp + startLine);
                    System.out.println("[INFO] Putting a string into temp file");
                    fileService.write(pathToTemp + startLine, line, true);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERROR] [READ] An unhandled error occurred while reading file");
            e.printStackTrace();
        }

    }

    private void sortLineToFile(String pathToFile) {
        List<String> listForSort = null;
        System.out.println("[INFO] Reading file to sort");
        try (
                FileReader fileReader = new FileReader(pathToFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader)
        ) {
            listForSort = bufferedReader.lines().sorted(String.CASE_INSENSITIVE_ORDER).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            System.out.println("[ERROR] File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("[ERROR] [READ] An unhandled error occurred while reading file");
            e.printStackTrace();
        }
        int length = pathToFile.split("/").length;
        String fileName = pathToFile.split("/")[length - 1];
        fileService.createFile(pathToTemp + fileName);
        if (listForSort != null) {
            fileService.write(pathToTemp + fileName, listForSort.get(0), false);
            for (int i = 1; i < listForSort.size(); i++) {
                fileService.write(pathToTemp + fileName, listForSort.get(i), true);
            }
        } else {
            System.out.println("[ERROR] [READ] An unhandled error occurred while creating file");
        }
    }
}
