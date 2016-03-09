/**
 * Created by graf on 3/8/2016.
 */
public class Frame {
    int u1, u2, v1, v2;

    public Frame() {
        this(16, 16, 32, 32);
    }

    public Frame(int U1, int V1, int U2, int V2) {
        /*
        u1 = (float) U1 / GraphicsClass.getPow2(GraphicsClass.WIDTH);
        u2 = (float) U2 / GraphicsClass.getPow2(GraphicsClass.WIDTH);
        v1 = (float) V1 / GraphicsClass.getPow2(GraphicsClass.HEIGHT);
        v2 = (float) V2 / GraphicsClass.getPow2(GraphicsClass.HEIGHT);
        */
        u1 = U1;
        u2 = U2;
        v1 = V1;
        v2 = V2;
    }
}
