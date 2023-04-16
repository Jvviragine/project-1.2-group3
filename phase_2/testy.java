package phase_2;

import java.io.IOException;
import java.net.http.HttpRequest.BodyPublisher;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;

public class testy {
    
    public static void main(String[] args) throws IOException {
        
        ExcelFileReader.main(args);
        ArrayList<ArrayList<Cell>> bodies1  = ExcelFileReader.bodies;
        ArrayList<CelestialBody> celBodies = new ArrayList<>();

        for(int i = 0; i<bodies1.size();i++){
            String name = bodies1.get(i).get(0).getStringCellValue();
            Vector pos = new Vector();
            Vector vel = new Vector();
            pos.setX(bodies1.get(i).get(1).getNumericCellValue());
            pos.setY(bodies1.get(i).get(2).getNumericCellValue());
            pos.setZ(bodies1.get(i).get(3).getNumericCellValue());
            vel.setX(bodies1.get(i).get(4).getNumericCellValue());
            vel.setY(bodies1.get(i).get(5).getNumericCellValue());
            vel.setZ(bodies1.get(i).get(6).getNumericCellValue());
            double mass = bodies1.get(i).get(7).getNumericCellValue();
            CelestialBody k = new CelestialBody(pos, vel, mass, name);
            celBodies.add(k);
            System.out.println(i);
        }

        System.out.println(celBodies);
    }
}
