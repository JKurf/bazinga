package src.Ent;

public class Animation {
    public double time = 0.0;
    public int currentFrame;
    public int delay = 100;
    public int nFrames = 8;
    public Frame[] frames;

    public static int IDLE = 0;
    public static int UP = 1;
    public static int DOWN = 2;
    public static int LEFT = 3;
    public static int RIGHT = 4;

    Animation(int N) {
        nFrames = N;
        frames = new Frame[nFrames];

        for(int n = 0; n < nFrames; n ++) {
            frames[n] = new Frame(16*n,0,16*(n+1),16);
        }
    }

    public void Update(double elapsedTime) {
        time += elapsedTime*1000.0f;

        if(time >= delay) {
            time = 0;
            currentFrame++;
        }

        if(currentFrame >= nFrames) {
            currentFrame = 0;
        }
    }

    public Frame getCurrent() {
        return frames[currentFrame];
    }
}
