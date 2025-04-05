package root.content;

import com.badlogic.gdx.graphics.Color;

import java.util.Random;

import static root.content.Colors.*;

public enum Field {
    NEUTRAL("dot0", 0, 0),
    ONE_POSITIVE("dot1", 1, 200),
    TWO_POSITIVE("dot2", 2, 150),
    THREE_POSITIVE("dot3", 3, 100),
    FOUR_POSITIVE("dot4", 4, 50),
    ONE_NEGATIVE("dot1", -1, 4),
    TWO_NEGATIVE("dot2", -2, 3),
    THREE_NEGATIVE("dot3", -3, 2),
    FOUR_NEGATIVE("dot4", -4, 1);

    private int getChance(Position position){
       return points<0?(position.x() * position.x() + position.y() * position.y()) * chance: chance;

    }


    public static Field getRandomField(Random random, Position position) {
        Field[] allFields = Field.values();
        int summe = 0;
        for (Field field : allFields) {
            summe += field.getChance(position);

        }
        int zufallszahl = random.nextInt(summe);
        summe = 0;
        for (Field field : allFields) {
            summe += field.getChance(position);
            if (summe > zufallszahl)
                return field;
        }
        throw new IllegalStateException();
    }

    Field(String textureName, int points, int chance) {
        this.textureName = textureName;
        this.points = points;
        this.color = points < 0 ? NEGATIVE_COLOR : points > 0 ? POSITIVE_COLOR : NEUTRAL_COLOR;
        this.chance = chance;
    }

    private final String textureName;
    private final Color color;
    private final int points;
    private final int chance;

    public String getTextureName() {
        return textureName + ".png";
    }

    public Color getColor() {
        return color;
    }

    public int getPoints() {
        return points;
    }
}

