import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.util.List;

public class World {

    String name; //Map Name
    int[][] map; //Map's Integer Array for Tiling
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
