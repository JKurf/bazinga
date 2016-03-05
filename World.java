import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.List;

public class World {

    String name; //Map Name
    int[][] map; //Map's Integer Array for Tiling
    boolean[][] clip; //Map's Collisions
    int rows; //Rows of Map's Array
    int cols; //Columns of Map's Array
    float xStart = 1.5f;
    float yStart = 1.5f;
    Mob[] mobs;

    /**
     * This Constructor only needs the World Name, and will use the method 'getMapData' to load the world
     * @param name Name of the World
     */
    public World(String name) {
        this.name = name;
        getMapData();
    }

    public void getMapData() {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("WorldFiles/" + name + ".json"));
            JSONObject json = (JSONObject) obj;
            rows = Integer.valueOf((String) json.get("Rows"));
            cols = Integer.valueOf((String) json.get("Cols"));
            JSONArray tiles = (JSONArray) json.get("TileMap");
            map = new int[rows][cols];
            int count = 0;
            for (Object i : tiles) {
                for (Object j : (List) i) {
                    map[count / cols][count++ % cols] = Integer.valueOf((String) j);
                }
            }
            JSONArray collision = (JSONArray) json.get("ClipMap");
            clip = new boolean[rows][cols];
            count = 0;
            for (Object i : collision) {
                for (Object j : (List) i) {
                    clip[count / cols][count++ % cols] = (j.equals("1"));
                }
            }
            JSONArray mobArray = (JSONArray) json.get("Mobs");
            int a = mobArray.toArray().length;
            mobs = new Mob[mobArray.toArray().length];
            count = 0;
            for (Object i : (List) mobArray) {
                int count2 = 0;
                String[] entry = new String[5];
                for (Object j : (List) i) {
                    entry[count2++] = j.toString();
                }
                Direction d;
                switch (entry[4]) {
                    case "D":
                        d = Direction.DOWN;
                        break;
                    case "U":
                        d = Direction.UP;
                        break;
                    case "L":
                        d = Direction.LEFT;
                        break;
                    case "R":
                        d = Direction.RIGHT;
                        break;
                    default:
                        d = Direction.DOWN;
                        break;
                }
                mobs[count++] = new Mob(entry[0], Integer.valueOf(entry[1]), Integer.valueOf(entry[2]), Integer.valueOf(entry[3]), d);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
