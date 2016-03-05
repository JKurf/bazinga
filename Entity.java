import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Entity {

    public static void main(String[] args) {
        Entity test = new Entity("0000", 0, 0, Direction.DOWN, false);
    }

    public  String lineEntry; //Entity's Line in Entities.txt
    private String name; //Entity's Name
    Location location;
    Texture tex;

    /**
     * This Constructor will Give the Entity a Cartesian Position, an ID Number, and a Name
     * @param ID Identification number (4 digit String)
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    public Entity(String ID, int x, int y, Direction dir, boolean mob) {
        this.location = new Location(x, y, dir);
        String line;
        File file = new File("Data/Entities.txt");
        try {
            Scanner fileScan = new Scanner(file);
            while (fileScan.hasNextLine()) {
                line = fileScan.nextLine();
                if (line.contains(ID)) {
                    lineEntry = line;
                    if (mob) {
                        return;
                    } else {
                        this.name = line.substring(5);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.printf("\nEntity File not Found\n");
        }
    }

    public void loadTexture(String filename) {
        tex = GraphicsClass.loadTexture(filename);
    }
}
