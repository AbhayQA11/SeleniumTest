package LeanAppsUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import LeanAppsUtils.Constants;
public class ExcelRead {
	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFCell Cell;
	public static XSSFRow rw;
	@SuppressWarnings("deprecation")
	public static String getCellData(int rownum,int colnum) throws Exception{
		File path=new File(Constants.testDataFile);
		FileInputStream fis=new FileInputStream(path);	
		wb=new XSSFWorkbook(fis);
		sheet=wb.getSheet("Sheet1");
	    int totalRows = sheet.getLastRowNum();
		System.out.println(totalRows);
		rw=sheet.getRow(rownum);
    	Cell = rw.getCell(colnum);
    	String CellData = "";
            if (Cell.getCellType() == XSSFCell.CELL_TYPE_STRING) {
            	CellData = Cell.getStringCellValue();
            } else if (Cell.getCellType() == XSSFCell.CELL_TYPE_NUMERIC) {
            	CellData = "" + Cell.getNumericCellValue();
            } else if (Cell.getCellType() == XSSFCell.CELL_TYPE_BOOLEAN) {
            	CellData = "" + Cell.getBooleanCellValue();
            } else if (Cell.getCellType() == XSSFCell.CELL_TYPE_FORMULA) {
            	CellData = "" + Cell.getCellFormula();
            } else if (Cell.getCellType() == XSSFCell.CELL_TYPE_BLANK) {
            	CellData = "";
            }
            return CellData;
		}
}
