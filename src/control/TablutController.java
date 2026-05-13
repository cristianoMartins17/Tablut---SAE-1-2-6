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

import java.awt.*;
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
                    ok = analyseAndPlay(line);
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

        if (!syntaxCheck(line)) {return false;}

        int n = line.length();

        Point begin = getIndexs(line.charAt(0), line.charAt(1));
        Point dest = getIndexs(line.charAt(n-2), line.charAt(n-1));

        ContainerElement board = gameStage.getBoard();


        GameElement pawn = board.getElement(begin.y, begin.x);

        System.out.println(begin.y+" "+ begin.x);
        // a finir
        return true;
    }

    public boolean syntaxCheck(String line) {

        line = line.replace(" ", "");
        if (line.length()<4) {return false;}
        if (line.length()==4) {
            return validCellName(line.charAt(0), line.charAt(1)) && validCellName(line.charAt(2), line.charAt(3));
        }
        else {
            int n=line.length();
            return validCellName(line.charAt(0), line.charAt(1)) && validCellName(line.charAt(n-2), line.charAt(n-1));
        }
    }

    public boolean validCellName(char c1, char c2) {
        int letterCount=0;
        if (Character.isAlphabetic(c1)) {letterCount+=1;}
        if (Character.isAlphabetic(c2)) {letterCount++;}

        int digitCount=0;
        if (Character.isDigit(c1)) {digitCount++;}
        if (Character.isDigit(c2)) {digitCount++;}

        if (letterCount!=digitCount) {return false;}

        char letter = (Character.isAlphabetic(c1)) ? c1 : c2;
        char digit = (Character.isDigit(c1)) ? c1 : c2;

        return (digit>'0' && digit<='9' && letter>='A' && letter<='I');
    }

    public Point getIndexs(char c1, char c2) {
        char letter=Character.isAlphabetic(c1) ? c1 : c2;
        char digit = Character.isDigit(c1) ? c1 : c2;
        return new Point(letter-'A',digit-'1');

    }

}
