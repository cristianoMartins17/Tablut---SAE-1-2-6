package control;

import boardifier.control.ActionFactory;
import boardifier.control.ActionPlayer;
import boardifier.control.Controller;
import boardifier.model.GameElement;
import boardifier.model.ContainerElement;
import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.model.action.ActionList;
import boardifier.view.View;
import model.TablutStageModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TablutController extends Controller{

    BufferedReader consoleIn;
    Boolean firstPlayer;

    public TablutController(Model model, View view){
        super(model, view);
        firstPlayer = true;
    }


    public void stageLoop(){
        consoleIn = new BufferedReader(new InputStreamReader(System.in));
        update();
        while(! model.isEndStage()) {
            playTurn();
            endOfTurn();
            update();
        }
        endGame();
    }

    private void playTurn(){
        // surment a modifier car choix des points probablement faux
        Player p = model.getCurrentPlayer();
        if (p.getType() == Player.COMPUTER){
            System.out.println("Computer Plays");
            TablutDecider decider = new TablutDecider(model,this);
            ActionPlayer play = new ActionPlayer(model, this, decider, null);
            play.start();
        } else {
            boolean ok = false;
            while(!ok){
                System.out.print(p.getName() + " -> ");
                try {
                    String line = consoleIn.readLine();
                    if ( line.length() == 4 ){
                        ok = analyseAndPlay(line);
                    }
                    if(!ok){
                        System.out.println("rentrez une valeur correct, réessayez");
                    }
                }
                catch (IOException e){}
            }
        }
    }

    public void endOfTurn(){
        model.setNextPlayer();
        Player p = model.getCurrentPlayer();
        TablutStageModel stageModel = (TablutStageModel) model.getGameStage();
        stageModel.getPlayerName().setText(p.getName());
    }
    private boolean analyseAndPlay(String line){
        // analyse si un coup et correct
        TablutStageModel gameStage = (TablutStageModel) model.getGameStage();
        int colPawn = (int) (line.charAt(0)- 'A');
        int rowPawn = (int) (line.charAt(1)- '1');
        if ((colPawn<0)||(colPawn>2) return false;
        if ((rowPawn<0)||(rowPawn>2)) return false;
        int col = (int) (line.charAt(2) - 'A');
        int row = (int) (line.charAt(3) - '1');
        if ((col<0)||(col>2)) return false;
        if ((row<0)||(row>2)) return false;
        ContainerElement pot = null;
        if (model.getIdPlayer() ==0){
            // black pawn
        } else {
            // white pawn
        }
        if(pot.isEmptyAt(colPawn,0)) return false;
        if(pot.isEmptyAt(rowPawn,0)) return false;
        GameElement pawn = pot.getElement(colPawn,rowPawn);
        // a finir
        return true;
    }

}
