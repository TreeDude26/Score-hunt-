package root.content;

import com.badlogic.gdx.Input;

public enum Direction
{
    UP(new Position(0,1), Input.Keys.W),
    DOWN(new Position(0,-1), Input.Keys.S),
    LEFT(new Position(-1,0), Input.Keys.A),
    RIGHT(new Position(1,0), Input.Keys.D);

    private final Position position;
    private final int key;

    Direction(Position position, int keyCode) {
        this.position = position;
        this.key = keyCode;
    }
    public Position getPosition(){
        return position;
    }

    public int getKey() {
        return key;
    }


}
