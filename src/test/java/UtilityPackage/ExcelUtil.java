package UtilityPackage;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
public class ExcelUtil
{
    private Workbook workbook;
    private Sheet sheet;
    public ExcelUtil(String filePath, String sheetName) 
    {
        try 
        {
            FileInputStream file = new FileInputStream(filePath);
            workbook = WorkbookFactory.create(file);
            sheet = workbook.getSheet(sheetName);
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public String getCellData(int row, int col) 
    {
        Row currentRow = sheet.getRow(row);
        if (currentRow != null)
        {
            Cell cell = currentRow.getCell(col);
            if (cell != null) 
            {
                return cell.toString();
            }
        }
        return "";
    }

    public int getRowCount()
    {
        return sheet.getPhysicalNumberOfRows();
    }

    public void closeWorkbook()
    {
        try 
        {
            if (workbook != null)
            {
                workbook.close();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
