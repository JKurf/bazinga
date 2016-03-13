package src;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.util.Scanner;

/**
 * @author Jack Kurfman, jkurfman@purdue.edu
 * @version 3/3/2016.
 */
public class txt2json {

    public static void main(String[] args) throws IOException {
        Scanner scanInput = new Scanner(new FileReader("Data/WorldFiles/Input.txt"));
        String name = scanInput.nextLine();
        int rows = scanInput.nextInt();
        int cols = scanInput.nextInt();
        int[][] map = new int[rows][cols];
        int[][] clip = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    map[i][j] = scanInput.nextInt();
                } catch (Exception e) {
                    System.out.println("File Not In Correct Format.");
                    return;
                }
            }
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try {
                    clip[i][j] = scanInput.nextInt();
                } catch (Exception e) {
                    System.out.println("File Not In Correct Format.");
                    return;
                }
            }
        }
        JSONArray tilemap1 = new JSONArray();
        JSONArray tilemap2;
        for (int[] i : map) {
            tilemap2 = new JSONArray();
            for (int j : i) {
                tilemap2.add(Integer.toString(j));
            }
            tilemap1.add(tilemap2);
        }
        JSONArray clipmap1 = new JSONArray();
        JSONArray clipmap2;
        for (int[] i : clip) {
            clipmap2 = new JSONArray();
            for (int j : i) {
                clipmap2.add(Integer.toString(j));
            }
            clipmap1.add(clipmap2);
        }
        JSONObject obj = new JSONObject();
        File f = new File("Data/WorldFiles/" + name + ".json");
        FileWriter fos = new FileWriter(f);
        obj.put("Name", name);
        obj.put("Rows", Integer.toString(rows));
        obj.put("Cols", Integer.toString(cols));
        obj.put("TileMap", tilemap1);
        obj.put("ClipMap", clipmap1);
        fos.write(obj.toJSONString());
        fos.close();
        System.out.println("Successfully created JSON World File for " + name);
    }

}
