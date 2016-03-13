package src.Ent;

public class Frame {
    public int u1;
    public int u2;
    public int v1;
    public int v2;

    public Frame() {
        this(16, 16, 32, 32);
    }

    public Frame(int U1, int V1, int U2, int V2) {
        u1 = U1;
        u2 = U2;
        v1 = V1;
        v2 = V2;
    }
}
