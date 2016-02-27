import java.util.Scanner;

public class World {

    //int[][] map;

    public static void main(String[] args) {
        Scanner fileScan = new Scanner("WorldFiles/TestMap");
        int c;
        while (fileScan.hasNextInt()) {
            c = fileScan.nextInt();
            System.out.println(c);
        }
    }
}
