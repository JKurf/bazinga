import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class World {

    String name; //Map Name
    int[][] map; //Map's Integer Array for Tiling
    boolean[][] clip;  //Map of collision boundaries
    int rows; //Rows of Map's Array
    int cols; //Columns of Map's Array

    /**
     * This Constructor only needs the World Name, and will use the method 'getMapData' to load the world
     * @param name Name of the World
     */
    public World(String name) {
        this.name = name;
        getMapData();
    }

    /**
     * This Method will take the World text file in WorldFiles, and from it retrieve the Maps's 2D Integer Array
     */
    public void getMapData() {
        File file = new File("WorldFiles/" + name + ".txt");
        try {
            Scanner fileScan = new Scanner(file);

            //Get Map Dimensions
            this.rows = fileScan.nextInt();
            this.cols = fileScan.nextInt();

            //Create the Map's Array [row][column]
            this.map = new int[rows][cols];
            int count = 0; //Simple counter
            while (count < rows*cols) {
                //if(fileScan.hasNextInt())
                    this.map[(count / cols)][count++ % (cols)] = fileScan.nextInt();
                //else
                //    this.map[(count / cols)][count++ % (cols)] = 0;
            }

            this.clip = new boolean[rows][cols];
            count = 0;
            while (count < rows*cols) {
                //if(fileScan.hasNextInt())
                    this.clip[(count / cols)][count++ % (cols)] = fileScan.nextInt() == 1;
                //else
                //    this.clip[(count / cols)][count++ % (cols)] = fileScan.nextInt() == 1;
            }

        } catch (FileNotFoundException e) {
            System.out.printf("\nWorld File for '%s' Not Found\n", name);
        }
    }
}
