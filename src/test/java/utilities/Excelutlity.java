package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

public class Excelutlity {

    private XSSFWorkbook wb;
    private XSSFSheet sheet;
    private FileInputStream input;
    private final String path;

    public Excelutlity(String path) {
        this.path = path;
    }

    // ✅ Safely get cell data (with null checks)
    public String getcelldata(String sheetname, int rowNum, int colNum) throws IOException {
        input = new FileInputStream(path);
        wb = new XSSFWorkbook(input);
        sheet = wb.getSheet(sheetname); // Use correct sheet by name

        if (sheet == null) {
            throw new IOException("Sheet '" + sheetname + "' not found.");
        }

        XSSFRow row = sheet.getRow(rowNum);
        if (row == null) {
            return ""; // No such row
        }

        XSSFCell cell = row.getCell(colNum);
        if (cell == null) {
            return ""; // No such cell
        }

        // Handle different cell types
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue().trim();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf(cell.getNumericCellValue());
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else {
            return ""; // Blank or unsupported type
        }
    }

    // ✅ Get number of rows in a sheet
    public int getrowcount(String sheetname) throws IOException {
        input = new FileInputStream(path);
        wb = new XSSFWorkbook(input);
        sheet = wb.getSheet(sheetname);

        if (sheet == null) {
            throw new IOException("Sheet '" + sheetname + "' not found.");
        }

        return sheet.getLastRowNum(); // 0-based index (e.g., 0 means 1 row)
    }

    // ✅ Get number of cells in the first row (usually headers)
    public int getcellcount(String sheetname) throws IOException {
        input = new FileInputStream(path);
        wb = new XSSFWorkbook(input);
        sheet = wb.getSheet(sheetname);

        if (sheet == null) {
            throw new IOException("Sheet '" + sheetname + "' not found.");
        }

        XSSFRow row = sheet.getRow(0); // Header row
        if (row == null) {
            return 0;
        }

        return row.getLastCellNum(); // Returns count of cells
    }
}
