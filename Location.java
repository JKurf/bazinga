public class Location {
    private float x;
    private float y;
    private float xLast;
    private float yLast;
    private Direction direction;

    public Location(float x, float y, Direction direction) {
        this.x = x;
        this.y = y;
        this.xLast = x;
        this.yLast = y;
        this.direction = direction;
    }

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

    public Direction getDirection() {
        return direction;
    }

    public void setX(float coordinate) {
        x = coordinate;
    }

    public void setY(float coordinate) {
        y = coordinate;
    }

    public void setDirection(Direction dir) {
        direction = dir;
    }

    public void move(float dx, float dy, World world) {
        if(canMove(dx, dy, world)) {
            xLast = x;
            yLast = y;
            x += dx;
            y += dy;
        }
    }

    public boolean canMove(float dx, float dy, World world) {
        if(world.clip[-1 + world.rows - (int)((y + dy + 1.0f/16.0f))][(int)((x + dx + 1.0f/16.0f))])   {return false;}
        if(world.clip[-1 + world.rows - (int)((y + dy + 15.0f/16.0f))][(int)((x + dx + 1.0f/16.0f))])   {return false;}
        if(world.clip[-1 + world.rows - (int)((y + dy + 15.0f/16.0f))][(int)((x + dx + 15.0f/16.0f))])  {return false;}
        if(world.clip[-1 + world.rows - (int)((y + dy + 1.0f/16.0f))][(int)((x + dx + 15.0f/16.0f))])  {return false;}
        return true;
    }

    public boolean inEntity(float dx, float dy, Location entityLocation, Location playerLocation) {

        return true;
    }
}