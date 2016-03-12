/*
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Audio {

    public static final String KAZOO = "Data/Xenoblade_Kazoo.wav";
    public static final String GIRUGAMESH = "Data/Girugamesh.wav";
    private static InputStream inputWAV;
    private static AudioStream playWAV;

    public static void play(String songPath) {
        try {
            inputWAV = new FileInputStream(songPath);
            playWAV = new AudioStream(inputWAV);
            AudioPlayer.player.start(playWAV);
        } catch (IOException e) {
            System.out.print("Could not find Song: " + songPath);
        }
    }

    public static boolean isOver() {
        try {
            return (playWAV.available() == 0);
        } catch (IOException e) {
            return true;
        }
    }

    public static void end() {
        AudioPlayer.player.stop(playWAV);
        try {
            playWAV.close();
        } catch (IOException e) {
            System.out.print("Error Resetting Track");
        }
    }
}
*/