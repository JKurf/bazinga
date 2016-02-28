import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class World {

    int[][] map; //Map's Integer Array for Tiling
    int rows; //Rows of Map's Array
    int cols; //Columns of Map's Array

    public World(String name) {
        getMapData(name);
    }

    public void getMapData(String name) {
        File file = new File("WorldFiles/" + name + ".txt");
        Scanner fileScan = null;
        try {
            fileScan = new Scanner(file);

            //Get Map Dimensions
            this.rows = fileScan.nextInt();
            this.cols = fileScan.nextInt();

            //Create the Map's Array
            this.map = new int[rows][cols];
            int count = 0;
            while (fileScan.hasNext()) this.map[(count / cols)][count++ % (cols)] = fileScan.nextInt();

        } catch (FileNotFoundException e) {
            System.out.printf("\nWorld File for '%s' Not Found\n", name);
        }
    }
}
