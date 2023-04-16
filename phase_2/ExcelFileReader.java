package phase_2;

import java.io.File;  
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.FormulaEvaluator;  
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class ExcelFileReader{

    public static ArrayList<ArrayList<Cell>> bodies  = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        FileInputStream file = new FileInputStream(new File("/Users/ulasawczuk/Documents/GitHub/project-1.2-group3/phase_2/IC.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file); 
        org.apache.poi.ss.usermodel.Sheet sheet = wb.getSheetAt(0);  
        FormulaEvaluator formulaEvaluator = wb.getCreationHelper().createFormulaEvaluator(); 

       for(int i = 1; i<12; i++){
        Row row = sheet.getRow(i);
        bodies.add(new ArrayList<>());
        for(int j = 0; j<=7; j++){
            Cell cell = row.getCell(j);
            bodies.get(i-1).add(cell);
        }
        
       }
       //System.out.println(bodies);
        
    }
}
