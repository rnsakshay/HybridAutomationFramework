package com.akshay.utilities;

import com.akshay.constants.FrameworkConstants;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    private static final Logger logger =
            LoggerFactory.getLogger(ExcelUtils.class);

    private ExcelUtils() {
        // Prevent instantiation
    }

    /**
     * Opens the Excel workbook.
     *
     * @param filePath Excel file path
     */
    private static Workbook openWorkbook(String filePath) {

        try {

            return new XSSFWorkbook(new FileInputStream(filePath));

        } catch (IOException e) {

            logger.error("Unable to open excel file : {}", filePath, e);

            throw new RuntimeException("Unable to open excel file.", e);
        }
    }

    /**
     * Returns sheet object.
     */
    private static Sheet getSheet(Workbook workbook, String sheetName) {

        Sheet sheet = workbook.getSheet(sheetName);

        if (sheet == null) {
            logger.error("Sheet '{}' not found.", sheetName);
            throw new RuntimeException("Sheet not found : " + sheetName);
        }

        return sheet;
    }

    /**
     * Returns total number of data rows.
     */
    public static int getRowCount(String filePath, String sheetName) {
        try (Workbook workbook = openWorkbook(filePath)) {
            Sheet sheet = getSheet(workbook, sheetName);
            return sheet.getLastRowNum();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close excel workbook.", e);
        }
    }

    /**
     * Returns total columns.
     */
    public static int getColumnCount(String filePath, String sheetName) {
        try (Workbook workbook = openWorkbook(filePath)) {
            Sheet sheet = getSheet(workbook, sheetName);
            return sheet.getRow(0).getLastCellNum();
        } catch (IOException e) {
            throw new RuntimeException("Unable to close excel workbook.", e);
        }
    }

    /**
     * Returns cell value as String.
     */
    public static String getCellData(String filePath, String sheetName, int rowNumber, int columnNumber) {

        try (Workbook workbook = openWorkbook(filePath)) {
            Sheet sheet = getSheet(workbook, sheetName);

            DataFormatter formatter = new DataFormatter();

            Row row = sheet.getRow(rowNumber);

            if (row == null) {
                logger.warn("Row {} not found in sheet '{}'.", rowNumber, sheetName);
                return "";
            }

            Cell cell = row.getCell(columnNumber);

            if (cell == null) {
                logger.warn("Cell {} not found in row {} of sheet '{}'.",
                        columnNumber, rowNumber, sheetName);
                return "";
            }

            return formatter.formatCellValue(cell);
        } catch (IOException e) {
            throw new RuntimeException("Unable to close excel workbook.", e);
        }
    }

    /**
     * Returns test data from Excel sheet in Object[][] format.
     *
     * @param sheetName Excel sheet name
     * @return Object[][]
     */
    public static Object[][] getTestData(String fileName, String sheetName) {

        try (Workbook workbook = openWorkbook(fileName)) {
            Sheet sheet = getSheet(workbook, sheetName);

            int rowCount = sheet.getLastRowNum();
            int columnCount = sheet.getRow(0).getLastCellNum();

            // Skip Sr.No column
            Object[][] data = new Object[rowCount][columnCount - 1];

            DataFormatter formatter = new DataFormatter();

            for (int row = 1; row <= rowCount; row++) {

                Row currentRow = sheet.getRow(row);

                if (currentRow == null) {
                    logger.warn("Row {} not found in sheet '{}'.", row, sheetName);
                    continue;
                }

                for (int column = 1; column < columnCount; column++) {

                    Cell cell = currentRow.getCell(column);

                    if (cell == null) {
                        logger.warn(
                                "Cell {} not found in row {} of sheet '{}'.",
                                column,
                                row,
                                sheetName
                        );

                        data[row - 1][column - 1] = "";
                    } else {
                        data[row - 1][column - 1] =
                                formatter.formatCellValue(cell);
                    }
                }
            }

            return data;
        } catch (IOException e) {
            throw new RuntimeException("Unable to close excel workbook.", e);
        }
    }
}
