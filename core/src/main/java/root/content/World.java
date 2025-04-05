package root.content;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Vector3;

import java.util.HashMap;
import java.util.Random;

import static root.content.Direction.*;

public class World {
    private final HashMap<Position, Field> fields = new HashMap<>();
    private final Random random = new Random();
    private Position playerPosition = new Position(0, 0);
    private int points = 100;




    public Field getField(Position position) {
        Field field = fields.get(position);
        if (field == null) {
            field = Field.getRandomField(random, position);

            fields.put(position, field);
        }
        return field;
    }

    private void move(Direction direction) {
        playerPosition = playerPosition.add(direction.getPosition());
        points = Math.min(points + getField(playerPosition).getPoints(), 100);
        fields.put(playerPosition, Field.NEUTRAL);
    }

    public boolean update(Vector3 vector) {
        boolean playSound = false;
        if (!isPlayerAlive()) return false;
        for (Direction direction : Direction.values())
            if (Gdx.input.isKeyJustPressed(direction.getKey())) {
                move(direction);
                playSound = true;
            }

        if (vector != null) {
            if((vector.x < 0.5 && vector.x > -0.5) && (vector.y < 0.5 && vector.y > -0.5)) return false;
            move(Math.abs(vector.x) > Math.abs(vector.y) ? vector.x < 0 ? LEFT : RIGHT : vector.y < 0 ? DOWN : UP);
            playSound = true;
        }
        return playSound;
    }

    public boolean isPlayerAlive() {
        return points > 0;
    }

    public int getPoints() {
        return points;
    }

    public Position getPlayerPosition() {
        return playerPosition;
    }
}


