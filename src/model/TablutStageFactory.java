package model;

import boardifier.model.GameStageModel;
import boardifier.model.StageElementsFactory;
import boardifier.model.TextElement;

public class TablutStageFactory extends StageElementsFactory {


    private TablutStageModel stageModel;

    public TablutStageFactory (GameStageModel gameStageModel) {
        super(gameStageModel);
        stageModel = (TablutStageModel) gameStageModel;
    }

    public void setup() {


        TablutBoard board = new TablutBoard(0,0 , stageModel);
        stageModel.setBoard(board);

        int[][] posMoscovite = { {0,3},{0,4},{0,5}, {1,4}, {3,0},{4,0},{5,0}, {8,3},{8,4},{8,5}, {7,4}, {3,8},{4,8},{5,8}, {4,1},{4,7}};
        TablutPawn[] moscovite=new TablutPawn[16];

        for (int i=0;i<posMoscovite.length;i++) {
            int[] pos = posMoscovite[i];
            TablutPawn pawn = new TablutPawn(stageModel, 0, 0);
            board.addElement(pawn, pos[0], pos[1]);
            moscovite[i]=pawn;
        }



        int[][] posSoldats = new int[][] { {2,4}, {3,4}, {4,2}, {4,3}, {4,5}, {4,6}, {5,4}, {6, 4}};
        TablutPawn[] soldats = new TablutPawn[8];

        for (int i=0; i<posSoldats.length; i++) {
            int[] pos = posSoldats[i];
            TablutPawn pawn = new TablutPawn(stageModel, 1, 1);
            board.addElement(pawn, pos[0], pos[1]);
            soldats[i]=pawn;
        }



        TablutPawn king = new TablutPawn(stageModel, 2, 2);
        board.addElement(king, 4,4);
        stageModel.setPlayerName(new TextElement(" ", stageModel));
        stageModel.setKing(king);
        stageModel.setBlackPawns(moscovite);
        stageModel.setGrayPawns(soldats);
    }
}
