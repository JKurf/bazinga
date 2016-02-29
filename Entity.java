import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Entity {

    private String name; //Entity's Name
    private String ID; //Entity's ID
    private int x; //Entity's X-Coordinate
    private int y; //Entity's Y-Coordinate
    private String dir; //Entity's Direction

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
        this.ID = ID;
        this.x = x;
        this.y = y;
        this.dir = dir;
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

    /**
     * This Method will call the Entity's X-Coordinate
     * @return Current X-Coordinate
     */
    public int xPos() {
        return x;
    }

    /**
     * This Method will call the Entity's Y-Coordinate
     * @return Current Y-Coordinate
     */
    public int yPos() {
        return y;
    }

    /**
     * This Method will call the Entity's Direction
     * @return Current Direction
     */
    public String direction() {
        return dir;
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

    /**
     * This Method will update the Entity for movement
     * @param direction The direction in which the Entity is facing
     */
    public void move(String direction) {
        if (dir.equals(direction.toUpperCase())) {
            switch (direction.toUpperCase()) {
                case "U":
                    y++;
                    break;
                case "D":
                    y--;
                    break;
                case "R":
                    x++;
                    break;
                case "L":
                    x--;
                    break;
            }
        }
        dir = direction.toUpperCase();
    }
}
