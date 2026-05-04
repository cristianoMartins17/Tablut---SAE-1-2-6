package model;


import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class Pawn extends GameElement {
    private int number;
    private int color;
    public static int MOSCOVITE= 0;
    public static int SUEDOIS = 1;
    public static int ROI=2;


    public Pawn(GameStageModel gameStage, int number, int color) {
        super(gameStage);
        this.number=number;
        this.color=color;

        ElementTypes.register("pawn", 31);
        setType(31);
    }

    public int getNumber() {
        return number;
    }

    public int getColor() {
        return color;
    }


}
