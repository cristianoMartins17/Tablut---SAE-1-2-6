package control;


import View.TablutStageLook;
import boardifier.control.ActionFactory;
import boardifier.control.Controller;
import boardifier.control.Decider;
import boardifier.model.GameElement;
import boardifier.model.Model;
import boardifier.model.action.ActionList;
import model.TablutBoard;
import model.TablutPawn;
import model.TablutStageModel;

import java.awt.*;
import java.util.*;
import java.util.List;


public class TablutDeciderSmart extends Decider {

    private static final Random loto = new Random(Calendar.getInstance().getTimeInMillis());

    public TablutDeciderSmart(Model model, Controller control) {
        super(model, control);
    }

    @Override
    public ActionList decide() {
        TablutStageModel stage = (TablutStageModel) model.getGameStage();
        TablutBoard board = stage.getBoard();

        //regrouper les pions du joueur courant dans un tableau
        TablutPawn[] pawns;
        if (model.getIdPlayer() == 0) {
            pawns = stage.getBlackPawns();
        } else {
            //suédois : on regroupe soldats + roi dans un tableau
            TablutPawn[] grayPawns = stage.getGrayPawns();
            pawns = new TablutPawn[grayPawns.length + 1];
            for (int i = 0; i < grayPawns.length; i++) {
                pawns[i] = grayPawns[i];
            }
            pawns[grayPawns.length] = stage.getKing();
        }

        //choisir un pion au hasard et un coup au hasard
        //on tente jusqu'à trouver un pion qui peut bouger
        TablutPawn selectedPawn = null;
        Point dest = null;

        //on génère un ordre de parcours aléatoire avec un indice de départ random
        int start = loto.nextInt(pawns.length);
        for (int i = 0; i < pawns.length; i++) {
            TablutPawn candidate = pawns[(start + i) % pawns.length];
            Point pos = findPawnPosition(board, candidate);
            if (pos == null) continue; //pion déjà capturé, pas sur le plateau

            List<Point> moves = board.computeValidMoves(pos.y, pos.x); //(y=row, x=col)
            if (!moves.isEmpty()) {
                selectedPawn = candidate;
                //choisir une destination au hasard parmi les coups valides
                dest = moves.get(loto.nextInt(moves.size()));
                break;
            }
        }

        if (selectedPawn == null) return null; //aucun coup possible = défaite

        //dest est un Point(x=col, y=row)
        return ActionFactory.generateMoveWithinContainer(model, selectedPawn, dest.y, dest.x);
    }
    //méthode utilitaire : retrouver la position (row, col) d'un pion sur le board
    private Point findPawnPosition(TablutBoard board, TablutPawn pawn) {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (board.getElement(row, col) == pawn) return new Point(col, row);
            }
        }
        return null;//pion capturé / absent du plateau
    }

}
