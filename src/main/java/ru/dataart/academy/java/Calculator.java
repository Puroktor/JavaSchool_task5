package ru.dataart.academy.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.zip.ZipInputStream;

public class Calculator {
    /**
     * @param zipFilePath -  path to zip archive with text files
     * @param character   - character to find
     * @return - how many times character is in files
     */
    public Integer getNumberOfChar(String zipFilePath, char character) {
        int numberOfChar = 0;
        try (ZipInputStream zipStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            while (zipStream.getNextEntry() != null) {
                int ch;
                while ((ch = zipStream.read()) != -1) {
                    if (ch == character) {
                        numberOfChar++;
                    }
                }
                zipStream.closeEntry();
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found", e);
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't work with zip stream", e);
        }
        return numberOfChar;
    }

    /**
     * @param zipFilePath - path to zip archive with text files
     * @return - max length
     */

    public Integer getMaxWordLength(String zipFilePath) {
        int maxLength = 0;
        try (ZipInputStream zipStream = new ZipInputStream(new FileInputStream(zipFilePath))) {
            while (zipStream.getNextEntry() != null) {
                int length = 0;
                int ch;
                while ((ch = zipStream.read()) != -1) {
                    if (ch == ' ' || ch == '\n') {
                        length = 0;
                    } else {
                        length++;
                    }
                    maxLength = Math.max(maxLength, length);
                }
                zipStream.closeEntry();
            }
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("File not found", e);
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't work with zip stream", e);
        }
        return maxLength;
    }
}
