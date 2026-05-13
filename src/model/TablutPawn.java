package model;


import boardifier.model.ElementTypes;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

public class TablutPawn extends GameElement {
    private int number;
    private int color;
    public static final int SOLDAT = 0;
    public static final int MOSCOVITE = 1;
    public static final int ROI=2;


    public TablutPawn(GameStageModel gameStage, int number, int color) {
        super(gameStage);
        this.number=number;
        this.color=color;
        this.gameStageModel=gameStage;
        this.container=gameStage.getContainers().get(0);
        System.out.println(this.getContainer());


        ElementTypes.register("pawn", 51);
        setType(51);
    }

    public int getNumber() {
        return number;
    }

    public int getColor() {
        return color;
    }
}
