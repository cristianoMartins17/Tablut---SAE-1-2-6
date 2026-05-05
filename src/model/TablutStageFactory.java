package model;

import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;

public class TablutStageFactory extends StageElementsFactory {


    private TablutStageModel stageModel;

    public TablutStageFactory (GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (TablutStageModel) gameStageModel;
    }

    public void setup() {

        TablutBoard board = new TablutBoard(0,0 , stageModel);
        stageModel.setBoard(board);

        int[][] posGrays = new int[][] { {2,4}, {3,4}, {4,2}, {4,3}, {4,5}, {4,6}, {5,4}, {6, 4}};
        TablutPawn[] grays = new TablutPawn[8];

        for (int i=0; i<posGrays.length; i++) {
            int[] pos = posGrays[i];
            TablutPawn pawn = new TablutPawn(stageModel, 2, 2);
            board.addElement(pawn, pos[0], pos[1]);
            grays[i]=pawn;
        }

        int[][] posBlacks = { {0,3},{0,4},{0,5}, {1,4}, {3,0},{4,0},{5,0}, {8,3},{8,4},{8,5}, {7,4}, {3,8},{4,8},{5,8}, {4,1},{4,7}};
        TablutPawn[] blacks=new TablutPawn[16];

        for (int i=0;i<posBlacks.length;i++) {
            int[] pos = posBlacks[i];
            TablutPawn pawn = new TablutPawn(stageModel, 1, 1);
            board.addElement(pawn, pos[0], pos[1]);
            blacks[i]=pawn;
        }

        TablutPawn king = new TablutPawn(stageModel, 3, 2);
        board.addElement(king, 4,4);

        stageModel.setKing(king);
        stageModel.setBlackPawns(blacks);
        stageModel.setGrayPawns(grays);


    }
}
