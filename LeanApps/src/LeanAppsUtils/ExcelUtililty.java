package LeanAppsUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;
import LeanAppsUtils.Constants;

public class ExcelUtililty {
	private static XSSFSheet ExcelWSheet;	 
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;

public static Object[][] getCredentials(String FilePath, String SheetName) throws Exception {   
   String[][] credentials = null;
   try {
	   FileInputStream ExcelFile = new FileInputStream(FilePath);
	   ExcelWBook = new XSSFWorkbook(ExcelFile);
	   ExcelWSheet = ExcelWBook.getSheet(SheetName);
	   int startRow = 1;
	   int startCol = 1;
	   int ci,cj;
	   int totalRows = ExcelWSheet.getLastRowNum();
	   int totalCols = 2;
	   credentials=new String[totalRows][totalCols];
	   ci=0;
	   for (int i=startRow;i<=totalRows;i++, ci++) { 
		  cj=0;
		   for (int j=startCol;j<=totalCols;j++, cj++){
			   credentials[ci][cj]=getCellData(i,j);
			   //System.out.println(credentials[ci][cj]);  
				}
			}
		}
	catch (FileNotFoundException e){
		System.out.println("Could not read the Excel sheet");
		e.printStackTrace();
		}
	catch (IOException e){
		System.out.println("Could not read the Excel sheet");
		e.printStackTrace();
		}
	return(credentials);
	}
public static String getCellData(int RowNum, int ColNum) throws Exception {
	try{
		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		String CellData = Cell.getStringCellValue();
			return CellData;
		}catch (Exception e){
		System.out.println(e.getMessage());
		throw (e);
		}
	}
}