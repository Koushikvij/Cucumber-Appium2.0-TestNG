package dataHandlers;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.*;
import org.testng.annotations.Test;

import utilities.common.Log;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class ExcelHandler
{
    public final String currentDir = System.getProperty("user.dir");

    //Location of Test data excel file
    public String testDataExcelPath = currentDir + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "TestData";
    public String testDataExcelFileName = "member_upload_sample.xlsx";
    private XSSFWorkbook excelWBook = null; //Excel WorkBook
    private XSSFSheet excelWSheet = null;//Excel Sheet
    private XSSFCell cell = null;//Excel cell
    private XSSFRow row = null;//Excel row
    public int rowNumber; //Row Number
    public int columnNumber;//Column Number
    private FormulaEvaluator formulaEvaluator;

    @Test
    public void ExcelTest()
    {
        int rowNum = getRowNum("TC02");
        int colNum = getColumnNum("Password");
        System.out.println(rowNum);
        System.out.println(colNum);
        System.out.println(getCellData("TC02", "Username"));
        System.out.println(getCellData("TC02", "Password"));
        setCellData("TC02", "Username", "Username");
        setCellData("TC02", "Password", "Password");
        System.out.println(getCellData("TC02", "Username"));
        System.out.println(getCellData("TC02", "Password"));
    }
    /**
     * @Test_Method_Description : To Load Excel File
     * @Method_Name : loadExcelFile
     * @Input_Parameters : String
     * @Output_Parameters : Na
     **/
    public synchronized void loadExcelFile(String FilePath, int SheetNumber)
    {

        try(FileInputStream ExcelFile = new FileInputStream(FilePath))
        {
            testDataExcelFileName = new File(FilePath).getName();
            excelWBook = new XSSFWorkbook(ExcelFile);
            XSSFFormulaEvaluator.evaluateAllFormulaCells(excelWBook);
            formulaEvaluator = excelWBook.getCreationHelper().createFormulaEvaluator();
            formulaEvaluator.evaluateAll();
            excelWSheet = excelWBook.getSheetAt(SheetNumber);
        }
        catch(IOException e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }


    /**
     * @Test_Method_Description : To get Row Count
     * @Method_Name : getRowCount
     * @Input_Parameters : Na
     * @Output_Parameters : Na
     **/
    public synchronized int getRowCount()
    {
        int rowCount = excelWSheet.getLastRowNum() + 1;
        return rowCount;
    }


    /**
     * @Test_Method_Description : To get Coloumn Count
     * @Method_Name : getColumnCount
     * @Input_Parameters : Na
     * @Output_Parameters : Na
     **/
    public synchronized int getColumnCount()
    {
        int colCount = excelWSheet.getRow(0).getLastCellNum() + 1;
        return colCount;
    }

    public synchronized int getRowNum(String rowIdentifier)
    {
        int rowNum = 0;
        int i = 0;
        try
        {
            int rowCount = getRowCount();
            boolean foundFlag = false;
            for(i = 0; i < rowCount; i++)
            {
                cell = excelWSheet.getRow(i).getCell(0);
                DataFormatter formatter = new DataFormatter();
                String cellValue = formatter.formatCellValue(cell);
                if(cellValue.trim().equalsIgnoreCase(rowIdentifier.trim()))
                {
                    foundFlag = true;
                    break;
                }
            }
            if(!foundFlag)
            {
                rowNum = -1;
            }
            else
            {
                rowNum = i;
            }
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return rowNum;
    }

    public synchronized int getColumnNum(String colIdentifier)
    {
        int colNum = 0;
        int i = 0;
        try
        {
            int colCount = getColumnCount();
            boolean foundFlag = false;
            for(i = 0; i < colCount; i++)
            {
                cell = excelWSheet.getRow(0).getCell(i);
                DataFormatter formatter = new DataFormatter();
                String cellValue = formatter.formatCellValue(cell);
                if(cellValue.trim().equalsIgnoreCase(colIdentifier.trim()))
                {
                    foundFlag = true;
                    break;
                }
            }
            if(!foundFlag)
            {
                colNum = -1;
            }
            else
            {
                colNum = i;
            }
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return colNum;
    }


    public synchronized String getColumnIdentifier(int i)
    {
        String cellValue = "";
        try
        {
            cell = excelWSheet.getRow(0).getCell(i);
            DataFormatter formatter = new DataFormatter();
            cellValue = formatter.formatCellValue(cell);
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return cellValue;
    }


    public synchronized String getCellData(String rowIdentifier, String colIdentifier)
    {
        String cellValue = "";
        if(excelWBook != null)
        {
            try
            {
                int rowNum = getRowNum(rowIdentifier);
                int colNum = getColumnNum(colIdentifier);
                if(rowNum < 0)
                {
                    System.out.println("The Row Identifier is either invalid or not found");
                }
                else if(colNum < 0)
                {
                    System.out.println("The Column Identifier is either invalid or not found");
                }
                else
                {
                    XSSFFormulaEvaluator.evaluateAllFormulaCells(excelWBook);
                    cell = excelWSheet.getRow(rowNum).getCell(colNum);
                    //		        CellValue c=formulaEvaluator.evaluate(cell);
                    //		        if(c!=null)
                    //		        	cellValue=c.getStringValue();
                    DataFormatter formatter = new DataFormatter();
                    cellValue = formatter.formatCellValue(cell, formulaEvaluator);
                }
            }
            catch(Exception e)
            {
                Log.error(ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
        }
        return cellValue;
    }

    /**
     * @Test_Method_Description : To Get Row Data
     * @Method_Name : getAllRowData
     * @Input_Parameters : String
     * @Output_Parameters :  Map
     **/
    public synchronized Map<String, String> getAllRowData(String rowIdentifier)
    {
        Map<String, String> allColData = new HashMap<>();
        try
        {
            int colCount = getColumnCount();
            for(int i = 0; i < 7; i++)
            {
                String colIdentifier = getColumnIdentifier(i);
                if(!colIdentifier.isEmpty())
                {
                    String cellData = getCellData(rowIdentifier, colIdentifier);
                    if(cellData.isEmpty())
                    {
//                        allColData.put(colIdentifier, "");
                        continue;
                    }
                    else
                        allColData.put(colIdentifier, cellData);
                }
            }
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return allColData;
    }

    /**
     * @Test_Method_Description : To Get Column Data
     * @Method_Name : getAllColumnData
     * @Input_Parameters : String
     * @Output_Parameters : List
     **/
    public synchronized List<String> getAllColumnData(String colIdentifier)
    {
        List<String> allColData = new ArrayList<>();
        try
        {
            int RowCount = getRowCount();
            int colNumber = getColumnNum(colIdentifier);

            for(int i = 1; i < RowCount; i++)
            {
                String cellValue = "";

                cell = excelWSheet.getRow(i).getCell(colNumber);
                DataFormatter formatter = new DataFormatter();
                cellValue = formatter.formatCellValue(cell);

                if(!colIdentifier.isEmpty())
                {
                    if(!cellValue.isEmpty())
                        allColData.add(cellValue);
                }
            }
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
        return allColData;
    }
    /**
     * @Test_Method_Description : To Set Column Data
     * @Method_Name : setCellData
     * @Input_Parameters : String,String,String
     * @Output_Parameters : Na
     **/
    public synchronized void setCellData(String rowIdentifier, String colIdentifier, String cellValue)
    {
        try
        {
            int rowNum = getRowNum(rowIdentifier);
            int colNum = getColumnNum(colIdentifier);
            if(rowNum < 0)
            {
                System.out.println("The Row Identifier is either invalid or not found");
            }
            else if(colNum < 0)
            {
                System.out.println("The Column Identifier is either invalid or not found");
            }
            else
            {
                row = excelWSheet.getRow(rowNum);
                cell = row.getCell(colNum);
                if(cell == null)
                {
                    cell = row.createCell(colNum);
                    cell.setCellValue(cellValue);
                }
                else
                {
                    cell.setCellValue(cellValue);
                }
            }
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }

    /**
     * @Test_Method_Description : To Set Column Data
     * @Method_Name : setCellData
     * @Input_Parameters : String,String,String
     * @Output_Parameters : Na
     **/
    public synchronized void setCellData(String rowIdentifier, String colIdentifier, int cellValue)
    {
        try
        {
            int rowNum = getRowNum(rowIdentifier);
            int colNum = getColumnNum(colIdentifier);
            if(rowNum < 0)
            {
                System.out.println("The Row Identifier is either invalid or not found");
            }
            else if(colNum < 0)
            {
                System.out.println("The Column Identifier is either invalid or not found");
            }
            else
            {
                row = excelWSheet.getRow(rowNum);
                cell = row.getCell(colNum);
                if(cell == null)
                {
                    cell = row.createCell(colNum);
                    cell.setCellValue(cellValue);
                }
                else
                {
                    cell.setCellValue(cellValue);
                }
                saveExcelFile();
            }
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }
    /**
     * @Test_Method_Description : To Save Excel File
     * @Method_Name : saveExcelFile
     * @Input_Parameters : String,String,String
     * @Output_Parameters : Na
     **/
    public synchronized void saveExcelFile()
    {
        if(excelWBook != null)
        {
            try(FileOutputStream fileOut = new FileOutputStream(testDataExcelPath + File.separator + testDataExcelFileName))
            {
                excelWBook.write(fileOut);
                fileOut.flush();
            }
            catch(Exception e)
            {
                Log.error(ExceptionUtils.getStackTrace(e));
                e.printStackTrace();
            }
        }
    }
    /**
     * @Test_Method_Description : To Save and Close Excel File
     * @Method_Name : saveCloseExcelFile
     * @Input_Parameters : String,String,String
     * @Output_Parameters : Na
     **/
    public synchronized void saveCloseExcelFile()
    {

        try(OutputStream fileOut = new FileOutputStream(testDataExcelPath + File.separator + testDataExcelFileName))
        {
            excelWBook.write(fileOut);
            excelWBook.close();
            fileOut.close();
        }
        catch(Exception e)
        {
            Log.error(ExceptionUtils.getStackTrace(e));
            e.printStackTrace();
        }
    }
}