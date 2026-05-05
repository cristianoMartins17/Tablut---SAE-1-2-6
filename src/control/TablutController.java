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
                    if ( line.length() == 3 ){
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
    }
    private boolean analyseAndPlay(String line){
        // analyse si un coup et correct
        return true;
    }

}
