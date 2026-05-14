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
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class TablutDecider extends Decider {

    private static final Random loto = new Random(Calendar.getInstance().getTimeInMillis());

    public TablutDecider(Model model, Controller control) {
        super(model, control);
    }

    @Override
    public ActionList decide() {
        // do a cast get a variable of the real type to get access to the attributes of HoleStageModel
        TablutStageModel stage = (TablutStageModel)model.getGameStage();
        TablutBoard board = stage.getBoard(); // get the board
        GameElement pawn = null;
        int rowDest = 0;
        int colDest = 0;

        //à compléter, ps je sais pas ce que ça fait

        return null;
    }


}
