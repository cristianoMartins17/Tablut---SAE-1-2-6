package control;

import boardifier.control.ActionFactory;
import boardifier.control.ActionPlayer;
import boardifier.control.Controller;
import boardifier.model.Model;
import boardifier.model.Player;
import boardifier.model.action.ActionList;
import boardifier.model.action.RemoveFromStageAction;
import boardifier.view.View;
import model.TablutBoard;
import model.TablutPawn;
import model.TablutStageModel;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;


public class TablutController extends Controller{

    BufferedReader consoleIn;
    Boolean soldat;

    public TablutController(Model model, View view){
        super(model, view);
        soldat = new Random().nextBoolean();
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
                String tour = (soldat) ? " (soldat) " : " (moscovite) ";

                System.out.print(p.getName() + tour + " -> ");
                try {
                    String line = consoleIn.readLine();
                    line = line.replace(" ", "");
                    line=line.toUpperCase();
                    ok = analyseAndPlay(line);
                    if(!ok){
                        System.out.println("rentrez une valeur correct, réessayez");
                    }
                }
                catch (IOException e){
                    System.out.println("Error");
                }
            }
        }
    }

    public void endOfTurn(){
        model.setNextPlayer();
        soldat =!soldat;
        Player p = model.getCurrentPlayer();
        TablutStageModel stageModel = (TablutStageModel) model.getGameStage();
        stageModel.getPlayerName().setText(p.getName());
    }

    private boolean analyseAndPlay(String line){
        // analyse si un coup et corrert
        TablutStageModel gameStage = (TablutStageModel) model.getGameStage();
        TablutBoard board = gameStage.getBoard();
        if (!syntaxCheck(line)) {return false;}
        int n = line.length();
        Point start = getIndex(line.charAt(0), line.charAt(1));
        Point dest = getIndex(line.charAt(n-2), line.charAt(n-1));
//        System.out.println(start+" : "+dest);
        ArrayList<Point> validsDirections = new ArrayList<>(gameStage.getBoard().computeValidMoves(start.y,start.x));
        if (!validsDirections.contains(dest) || board.getElements(start.y,start.x).isEmpty()) {return false;}
        TablutPawn pawn = (TablutPawn) board.getElement(start.y, start.x);
        if (pawn.getColor()==TablutPawn.MOSCOVITE && soldat || !soldat &&  pawn.getColor()!=TablutPawn.MOSCOVITE  ) {
            System.out.println("Vous n'êtes pas autorisés à jouer ce pion, il ne fait pas partie de votre équipe.");
            return false;
        }
        ActionList movement= ActionFactory.generateMoveWithinContainer(model, pawn, dest.y , dest.x);
        movement.setDoEndOfTurn(false);
        ActionPlayer play = new ActionPlayer(model, this, movement);
        play.start();
        if (pawn.getColor()==TablutPawn.ROI) {
            board.setKingPos(dest.y, dest.x);
        }

        ActionList captures = getCaptures(dest.y, dest.x);
        captures.setDoEndOfTurn(true);
        ActionPlayer capturesPlayer = new ActionPlayer(model, this, captures);
        capturesPlayer.start();


        return true;


    }

    public boolean syntaxCheck(String line) {
        if (line.length()<4) {return false;}
        int n=line.length();
        String word = line.substring(2,n-2).toUpperCase();
        switch (word) {
            case "","TO","VERS","À":
                break;
            default:
                return false;
        }
        return validCellName(line.charAt(0), line.charAt(1)) && validCellName(line.charAt(n-2), line.charAt(n-1));
    }

    public boolean validCellName(char c1, char c2) {
        int letterCount=0;
        if (Character.isAlphabetic(c1)) {letterCount+=1;}
        if (Character.isAlphabetic(c2)) {letterCount++;}

        int digitCount=0;
        if (Character.isDigit(c1)) {digitCount++;}
        if (Character.isDigit(c2)) {digitCount++;}

        if (letterCount!= 1 || digitCount!=1) {return false;}

        char letter = (Character.isAlphabetic(c1)) ? c1 : c2;
        char digit = (Character.isDigit(c1)) ? c1 : c2;

        return (digit>'0' && digit<='9' && letter>='A' && letter<='I');
    }

    public Point getIndex(char c1, char c2) {
        char letter=Character.isAlphabetic(c1) ? c1 : c2;
        char digit = Character.isDigit(c1) ? c1 : c2;
        return new Point(letter-'A',digit-'1');
    }


    public ActionList getCaptures(int i, int j) {
        ActionList removes = new ActionList();
        TablutStageModel gameStage = (TablutStageModel) model.getGameStage();
        TablutBoard board = gameStage.getBoard();
        Point[] neighborsCoordinates = board.getNeighborsCoordinates(i,j);
        for (Point coordinate : neighborsCoordinates) {
            int row=coordinate.y;
            int col=coordinate.x;
            if (board.isCaptured(row, col)) {
                RemoveFromStageAction action = new RemoveFromStageAction(model, board.getFirstElement(row, col));
                removes.addSingleAction(action);
            }
        }
        return removes;


    }

}
