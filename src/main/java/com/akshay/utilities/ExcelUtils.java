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

    private static Workbook workbook;
    private static Sheet sheet;


    private ExcelUtils() {
        // Prevent instantiation
    }

    /**
     * Opens the Excel workbook.
     *
     * @param filePath Excel file path
     */
    private static Workbook getWorkbook(String filePath) {

        try {

            FileInputStream fis = new FileInputStream(filePath);

            return new XSSFWorkbook(fis);

        } catch (IOException e) {

            logger.error("Unable to open excel file : {}", filePath, e);

            throw new RuntimeException("Unable to open excel file.", e);
        }
    }

    /**
     * Returns sheet object.
     */
    private static Sheet getSheet(String filePath, String sheetName) {

        workbook = getWorkbook(filePath);

        sheet = workbook.getSheet(sheetName);

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
        Sheet sheet = getSheet(filePath, sheetName);
        return sheet.getLastRowNum();
    }

    /**
     * Returns total columns.
     */
    public static int getColumnCount(String filePath, String sheetName) {
        Sheet sheet = getSheet(filePath, sheetName);
        return sheet.getRow(0).getLastCellNum();
    }

    /**
     * Returns cell value as String.
     */
    public static String getCellData(String filePath, String sheetName, int rowNumber, int columnNumber) {

        Sheet sheet = getSheet(filePath, sheetName);

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
    }

    /**
     * Returns test data from Excel sheet in Object[][] format.
     *
     * @param sheetName Excel sheet name
     * @return Object[][]
     */
    public static Object[][] getTestData(String fileName,String sheetName) {

        int rowCount = getRowCount(fileName, sheetName);
        int columnCount = getColumnCount(fileName, sheetName);

        // Skip first column (Sr.No)
        Object[][] data = new Object[rowCount][columnCount - 1];

        for (int row = 1; row <= rowCount; row++) {

            for (int column = 1; column < columnCount; column++) {

                data[row - 1][column - 1] =
                        getCellData(fileName, sheetName, row, column);
            }
        }

        return data;
    }
}
