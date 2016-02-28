import jdk.nashorn.internal.parser.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class World {

    public static void main(String[] args) {
        World w = new World("TestMap");
    }

    int[][] map;

    public World(String name) {
        File file = new File("WorldFiles/" + name + ".txt");
        Scanner fileScan = null;
        try {
            fileScan = new Scanner(file);
            int c;
            while (fileScan.hasNext()) {
                c = fileScan.nextInt();
                System.out.println(c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void getMapData(String name) {

        JSONParser jsonParser = new JSONParser("WorldFiles/" + name + ".JSON", null, true);

        jsonParser.parse();

    }
}
