package officeHours;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelIO {

    @Test
    public void createExcelFileTest() throws IOException {
        String filePath = System.getProperty("user.dir") + File.separator +"regression_test_result.xlsx";
        File file = new File(filePath);
//        file.createNewFile();// to create new file
        Workbook workbook = WorkbookFactory.create(file);
        Sheet sheet = workbook.createSheet("test_output");

        //to write changes into the file
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);
        fileOutputStream.close();
        workbook.close();

    }
}
