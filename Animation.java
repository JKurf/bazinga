
public class Animation {
    double time = 0.0;
    int currentFrame;
    int delay = 100;
    int nFrames = 8;
    Frame[] frames;

    static int IDLE = 0;
    static int UP = 1;
    static int DOWN = 2;
    static int LEFT = 3;
    static int RIGHT = 4;

    Animation(int N) {
        nFrames = N;
        frames = new Frame[nFrames];

        for(int n = 0; n < nFrames; n ++) {
            frames[n] = new Frame(16*n,0,16*(n+1),16);
        }
    }

    void Update(double elapsedTime) {
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
