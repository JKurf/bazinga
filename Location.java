public class Location {
    private float x;
    private float y;
    private float xLast;
    private float yLast;
    private String direction;

    public Location() {
        this(0, 0, "DOWN");
    }

    public Location(float x, float y) {
        this(x, y, "DOWN");
    }

    public Location(float x, float y, String direction) {
        this.x = x;
        this.y = y;
        this.xLast = x;
        this.yLast = y;
        this.direction = direction;
    }

    public Location(Location loc) {this(loc.xPos(), loc.yPos(), "DOWN");}

    public float xPos() {
        return x;
    }

    public float yPos() {
        return y;
    }

    public float xPrev() {
        return xLast;
    }

    public float yPrev() {
        return yLast;
    }

    public String getDirection() {
        return direction;
    }

    public void setX(float coordinate) {
        x = coordinate;
    }

    public void setY(float coordinate) {
        y = coordinate;
    }

    public void setDirection(String dir) {
        direction = dir;
    }

    public void move(float dx, float dy, World world) {
        /*try {
            if (canMoveX(dx, world)) { x += dx; }
        } catch (Exception ignored) {
            System.out.print("");
        }
        try {
            if (canMoveY(dy, world)) { y += dy; }
        } catch (Exception ignored) {
            System.out.print("");
        }*/
        if(canMove(dx, dy, world)) {
            x += dx;
            y += dy;
        }
    }

    public boolean canMove(float dx, float dy, World world) {
        /*
        if(world.clip[(int)((x + dx) / 16)][(int)((y + dy) / 16)]) {return false;}
        if(world.clip[(int)((x + dx) / 16)][(int)((y + dy + 10) / 16)]) {return false;}
        if(world.clip[(int)((x + dx + 10) / 16)][(int)((y + dy + 10) / 16)]) {return false;}
        if(world.clip[(int)((x + dx + 10) / 16)][(int)((y + dy) / 16)]) {return false;}
        */

        /*for(int j = 0; j < world.rows; j ++) {
            for (int i = 0; i < world.cols; i ++) {
                if((world.clip[j][i])) {
                    System.out.print(1);
                }
                else {
                    System.out.print(0);
                }
            }
            System.out.println();
        }*/

        if(world.clip[-1 + world.rows - (int)((y + dy + 4) / 16)][(int)((x + dx + 4) / 16)]) {return false;}
        if(world.clip[-1 + world.rows - (int)((y + dy + 12) / 16)][(int)((x + dx + 4) / 16)]) {return false;}
        if(world.clip[-1 + world.rows - (int)((y + dy + 12) / 16)][(int)((x + dx + 12) / 16)]) {return false;}
        if(world.clip[-1 + world.rows - (int)((y + dy + 4) / 16)][(int)((x + dx + 12) / 16)]) {return false;}

        return true;
    }

    public boolean canMoveX(float dx, World world) {
        /*if (dx < 0) {
            return !world.clip[(int)Math.floor(y / 16)][(int)Math.floor((x + dx) / 16)];
        } else {
            return !world.clip[(int)Math.floor(y / 16)][(int)Math.floor((x + dx + 16) / 16)];
        }*/
        /*int I = (int)Math.floor(x / 16);
        int J = (int)Math.floor(y / 16);

        boolean left, right;
        left = world.clip[I-1][J];
        right = world.clip[I+1][J];

        /*if(dx > 0) {
            //return right;
            System.out.println("Can't move Right");
            return true;
        }
        else {
            //return left;
            System.out.println("Can't move Left");
            return true;
        }*/
        return true;
    }

    public boolean canMoveY(float dy, World world) {
        /*if (dy < 0) {
            return !world.clip[(int)Math.floor((y + dy) / 16)][(int)Math.floor((x) / 16)];
        } else {
            return !world.clip[(int)Math.floor((y + dy + 16) / 16)][(int)Math.floor((x) / 16)];
        }*/
        /*int I = (int)Math.floor(x / 16);
        int J = (int)Math.floor(y / 16);

        boolean up, down;
        up = world.clip[I][J+1];
        down = world.clip[I][J-1];

        /*if(dy > 0) {
            //return up;
            System.out.println("Can't move Up");
            return true;
        }
        else {
            //return down;
            System.out.println("Can't move Down");
            return true;
        }*/
        return true;
    }
}