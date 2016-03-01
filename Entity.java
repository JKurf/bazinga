import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Entity {

    private static int entityCount = 0;
    private int entityNumber;
    private String name; //Entity's Name
    private String ID; //Entity's ID
    Location location;

    Texture tex;

    //Constructor if nothing (Defaults to TestEntity)
    public Entity() {
        this("0000", 0, 0, "D");
    }

    //Constructor if only ID & Direction
    public Entity(String ID, String dir) {
        this(ID, 0, 0, dir);
    }

    //Constructor if only ID
    public Entity(String ID) {
        this(ID, 0, 0, "D");
    }

    /**
     * This Constructor will Give the Entity a Cartesian Position, an ID Number, and a Name
     * @param ID Identification number (4 digit String)
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Entity(String ID, int x, int y, String dir) {
        this.entityNumber = entityCount++;
        this.ID = ID;
        this.location = new Location(x, y, dir);
        String line;
        File file = new File("Data/Entities.txt");
        try {
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                line = fileScan.nextLine();
                if (line.contains(ID)) name = line.substring(5);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("\nEntity File not Found\n");
        }
    }

    public void loadTexture(String filename, TextureLoader texLoad) {

        try {
            tex = texLoad.getTexture(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This Method will call the Entity's Name
     * @return Name
     */
    public String getEntityName() {
        return name;
    }

    /**
     * This Method will call the Entity's ID
     * @return ID
     */
    public String getID() {
        return ID;
    }

    public int getEntityNumber() {
        return entityNumber;
    }

    public static int getEntityCount() {
        return entityCount;
    }
}