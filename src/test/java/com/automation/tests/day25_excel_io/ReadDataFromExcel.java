package com.automation.tests.day25_excel_io;

import com.automation.utilities.ExcelUtil;
import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;


public class ReadDataFromExcel {

    @Test
    public void readExcelFileTest() throws IOException {
        //we need to get a file as an object
        File file = new File("VytrackTestUsers.xlsx");// this File can be any file, its generic
        //object that represents excel file  //Workbook workbook is specific for excel object.Its coming from apache.poi
        Workbook workbook = WorkbookFactory.create(file);
        //get QA1-short QA1-short is the sheet name which is one the bottom of the excel file page.
        Sheet workSheet = workbook.getSheet("QA1-short");
        //get first row
        Row firstRow = workSheet.getRow(0);
        //get first cell
        Cell firstCell = firstRow.getCell(0);
        //get string value
        String value = firstCell.getStringCellValue();

        String secondCellValue = firstRow.getCell(1).getStringCellValue();

        System.out.println(value);
        System.out.println(secondCellValue);

        int lastCell = firstRow.getLastCellNum();
        System.out.println("######################");

        for (int i = 0; i < lastCell; i++) {
            System.out.print(firstRow.getCell(i) + " | ");
        }

        // last row is 16the--->index is 15
        //index of last row
        int numberOfRows = workSheet.getLastRowNum(); // rows start with 0
        //returns how many rows at all
        int numberOfRows2 = workSheet.getPhysicalNumberOfRows();

        System.out.println("\nIndex of last row " + numberOfRows);

        System.out.println("number Of Rows = " + numberOfRows2);

        System.out.println("#############################");

        // cell is just like column

        for (int row = 0; row < workSheet.getPhysicalNumberOfRows(); row++) {
            for (int cell = 0; cell < workSheet.getRow(row).getLastCellNum(); cell++) {
                String cellValue = workSheet.getRow(row).getCell(cell).getStringCellValue();
                System.out.print(cellValue + " | ");

            }
            System.out.println();
        }
    }

      @Test
      public void excelUtilityTest(){
           String path = "VytrackTestUsers.xlsx";
           String spreadSheet = "QA1-all";
           ExcelUtil excelUtil = new ExcelUtil(path,spreadSheet);
           //https://docs.oracle.com/javase/tutorial/java/javaOO/methodreferences.html
 //          excelUtil.getDataList().forEach(p->System.out.println(p));

           for(Map<String,String> record : excelUtil.getDataList()){
               System.out.println(record);
           }


      }
       @Test
       public void getColumnNamesTest(){
           String path = "VytrackTestUsers.xlsx";
           String spreadSheet = "QA1-short";
           ExcelUtil excelUtil = new ExcelUtil(path,spreadSheet);

           System.out.println(excelUtil.getColumnsNames());
      }


}


/*
April 7,2020

  Agenda :

      Reading data from excel files.
      ######################################################

      To open excel files we use Microsoft Office Excel or we can open them in google driver,
      but you cannot open excel file as a simple text file.

      In Java , there is a library that calls Apache POI that stands for reading /writing operations with excel files.

      It's just a jar file that we need to add. Since we are using maven ,it's gonna be dependency.

      Apache POI doesn't rely on Microsoft Office Excel. It's purely standalone tool.

      Add this to your pom.xml :

      <!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
                       <dependency>
                          <groupId>org.apache.poi</groupId>
                          <artifactId>poi-ooxml</artifactId>
                          <version>4.1.2</version>
                       </dependency>

       There are 2 types of excel files : .xls and .xlsx, last one is newer.

       Workbook --excel file itself
       WorkSheet--excel spreadsheet
       Row-- row in excel spreadsheet
       Cell--cell witin excel spreadsheet row

       NotOLE2FileException : Invalid header signature; --no excel file found

       java.io.IOException: org.apache.poi.xssf.usermodel.SXXFWorkbookFactory not found---
       check if poi--ooxml-*.jar is on the classpath. --library wasnt downloaded. Refresh maven dependencies.

       mvn dependency: tree--to download all libraries that are specified in pom.xml file
 */