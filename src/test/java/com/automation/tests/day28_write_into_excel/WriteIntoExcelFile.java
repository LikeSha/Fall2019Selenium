package com.automation.tests.day28_write_into_excel;

import org.apache.poi.ss.usermodel.*;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

public class WriteIntoExcelFile {

    @Test
    public void writeIntoFileTest() throws IOException {
        FileInputStream inputStream = new FileInputStream("VytrackTestUsers.xlsx");
        Workbook workbook = WorkbookFactory.create(inputStream);
        inputStream.close();

        Sheet sheet= workbook.getSheet("QA3-short");
        Row row = sheet.getRow(1);//second row
        Cell cell = row.getCell(5);//create new cell
//        Row row = sheet.getRow(0);// first row
//        Cell cell = row.getCell(row.getLastCellNum()-1); this line of code is
//          getting physical number of cell
//        System.out.println(cell.getStringCellValue());

        System.out.println("Before : " + cell.getStringCellValue());
        cell.setCellValue("PASSED");//I am changing from n/a to passeds
        System.out.println("After : " + cell.getStringCellValue());

        Row firstRow = sheet.getRow(0);//get first row
        Cell newCell = firstRow.createCell(6);//CREATE NEW CELL
//        Cell newCell = firstRow.createCell(row.getLastCellNum());
        newCell.setCellValue("Date of execution");//give the name to this cell

        //write date and time info into second row, last column

        Row secondRow = sheet.getRow(1);
        Cell newCell2 = secondRow.createCell(6);
//        Cell newCell2 = secondRow.createCell(row.getLastCellNum());
        newCell2.setCellValue(LocalDate.now().toString());//I will set current date and time info into new cell
        //newCell2.setCellValue(DateTimeUtilities.getCurrentDate("MMM d, yyyy"));

        //whenever you create a cell, it does not mean that all cell under neat will created automatically
       //So first we created object of second row then we created new cell and write date and time information


        //I create if I want to write something into the file
        //don't forget to close excel file if you opened it
        FileOutputStream outputStream = new FileOutputStream("VytrackTestUsers.xlsx");
        workbook.write(outputStream);//write changes
        workbook.close();//close when everything is done

    }
}
/*
April 14 classNote  class video  04/14/2020

Agenda: write data into excel file.

FileInputStream inputStream = new FileInputStream("VytrackTestUsers.xlsx");
FileInputStream - is needed to open the excel file, and turn into into java object.
//I create a  FileOutputStream object if I want to write something into the file
//don't forget to close excel file if you opened it

FileOutputStream outputStream = new FileOutputStream("VytrackTestUsers.xlsx");
workbook.write(outputStream);//write changes
workbook.close();//close when everything is done
outputStream.close();
Row secondRow = sheet.getRow(1);
Cell newCell2 = secondRow.createCell(6);//create a cell
Before writing something into new cell, we have create it first.
newCell.setCellValue("Date of execution");//give the name to this cell
setCellValue() - to write something into cell.

main goal is :
1- getting all data from excel while testing login with different user.
    every row of data represents separate test case.

2- then writing updated test status into excel file.
    If something went wrong during test execution, with this set of test data, we can set test status as FAILED.
    Same test will be running as many times as many rows of data we have in excel file
    (except first of one that is reserved for column names).
3- if fails it will go to after method and there we would make a record to excel file as Fail.
    Test status cannot be determined before test execution. To determine test status,
    we use ITestResult in the @AfterMethod. Because, in case of failure,
    exception will be thrown and test execution will be terminated.
    There is no way to write failure test result at the end of test method itself.
    That's why we have to move this line into @AfterMethod. To make excel objected shared,
    we created reference variable in the AbstractTestBase class.
    Thus, this variable can be used by test class, test set passed and skipped test status,
    and AbstractTestBase class, more specifically, @AfterMethod to set test failure into excel file.
    Also, we created static (global) variable row in the base class.
    In this way, row index will be same for test and after method.
We were performing Data Driven testing. Same test (login), different test data (credentials).
Every set of credentials, specified in the individual row.

 */