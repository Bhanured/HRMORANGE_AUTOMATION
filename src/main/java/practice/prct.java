package practice;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFName;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class prct {
	
	public static void main(String[] args) throws IOException {
		
		FileInputStream input = null;
		XSSFWorkbook WB = null;
		XSSFSheet sheet;
		
		System.out.println("Starting .....");
		
		try {
			input = new FileInputStream("C:\\Users\\bhanu\\eclipse-myworkspace\\OrangeHRM\\TestData\\LoginTestData.xlsx");
			WB=new XSSFWorkbook(input);
			sheet=WB.getSheet("Sheet1");
			System.out.println(sheet.getLastRowNum());
			System.out.println(sheet.getRow(0).getLastCellNum());
			
			System.out.println(sheet.getRow(1).getCell(1).getCellType());
			System.out.println(sheet.getRow(2).getPhysicalNumberOfCells());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println("❌ Error: File not found -> " + e.getMessage());

			e.printStackTrace();
		}
		WB.close();
		input.close();
		
		

	}
	
	
	
	

}
