package model;

import boardifier.model.ContainerElement;
import boardifier.model.Coord2D;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class TablutBoard extends ContainerElement {
    public TablutBoard(int x, int y, GameStageModel gameStageModel) {
        // call the super-constructor to create a 3x3 grid, named "holeboard", and in x,y in space
        super("tablutboard", x, y, 9 , 9, gameStageModel);

    }



//    public List<Point> computeValidCells(int number) {
//        List<Point> lst = new ArrayList<>();
//        TablutPawn p = null;
//        // if the grid is empty, is it the first turn and thus, all cells are valid
//        if (isEmpty()) {
//            // i are rows
//            for(int i=0;i<3;i++) {
//                // j are cols
//                for (int j = 0; j < 3; j++) {
//                    // cols is in x direction and rows are in y direction, so create a point in (j,i)
//                    lst.add(new Point(j,i));
//                }
//            }
//            return lst;
//        }
//
//    }

    public boolean isCenter(int i, int j) {
        return i==4 && j==4;
    }

    public boolean isCorner(int i, int j) {
        return i==0 && j==0 || i==0 && j==8 || i==8 && j==0 || i==8 && j==8;
    }

    public boolean isOccuped(int i, int j) {
        return (validPosition(i,j) && grid[i][j].isEmpty());
    }

    public boolean safeCell(TablutPawn pawn, int i, int j ) {
        if (!validPosition(i,j)) {return false;}
        List<GameElement> cell = getElements(i,j);
        if (cell.isEmpty()) {return true;}
        else {
            TablutPawn pawn2 = (TablutPawn) cell.get(0);
            return pawn.sameTeam(pawn2);
        }
    }


    public boolean isNeighborOccuped(int i, int j, Point vector) {
        int sumVector = vector.x+vector.y;
        if (sumVector!=-1 && sumVector!=1) {
            throw new IllegalArgumentException("Error : vector must only move 1 componant in 1 axe");
        }
        return isOccuped(i+vector.y,j+vector.x );
    }

    public boolean isCaptured(int i, int j) {
        if (!validPosition(i,j)) {throw new IllegalArgumentException("Error : invalid position");}

        if (grid[i][j]==null || grid[i][j].isEmpty()) {
            return false;
        }
        TablutPawn pawn = (TablutPawn) grid[i][j].get(0);
        if (pawn.getColor()!=TablutPawn.ROI) {
            return !safeCell(pawn, i+1,j) && !safeCell(pawn, i-1, j)  || !safeCell(pawn, i,j+1) && !safeCell(pawn,i,j-1);
        }
        else {
            return !safeCell(pawn, i+1,j) && !safeCell(pawn, i-1, j)  && !safeCell(pawn, i,j+1) && !safeCell(pawn,i,j-1);

        }
    }

    public boolean validPosition(int i, int j) {
        return  (i<9 && i>=0 && j<9 && j>=0);
    }

    public void computeValidMoves1Direction(int i, int j, List<Point> lst, Point vector) {
        i+=vector.y;
        j+=vector.x;

        while ( validPosition(i,j) && grid[i][j].isEmpty() ) { // grid is an array that contains an array that contains a list so we use .isEmpty to check if there is nothing at this position
            lst.add(new Point(j,i));
            i+=vector.y;
            j+=vector.x;
        }
    }

    public List<Point> computeValidMoves(int i, int j) {
        List<Point> result = new ArrayList<>();

        // up
        computeValidMoves1Direction(i,j,result, new Point(0,1));

        // right
        computeValidMoves1Direction(i,j,result, new Point(1,0));

        //bottom
        computeValidMoves1Direction(i,j,result,new Point(0,-1));

        //left
        computeValidMoves1Direction(i,j, result, new Point(-1 , 0));

        return result;

    }

    /*/
    return an array of the neighbors of a tile in the grid,
    [ NORTH , EST, SOUTH, WEST ]
    */
    public TablutPawn[] getNeighbors(int i, int j) {
        TablutPawn[] neighbors = new TablutPawn[4];

        // Nord
        if (validPosition(i-1, j)) neighbors[0] = (TablutPawn) getElement(i-1, j);

        // Est
        if (validPosition(i, j+1)) neighbors[1] = (TablutPawn) getElement(i, j+1);

        // Sud
        if (validPosition(i+1, j)) neighbors[2] = (TablutPawn) getElement(i+1, j);

        // Ouest
        if (validPosition(i, j-1)) neighbors[3] = (TablutPawn) getElement(i, j-1);

        return neighbors;
    }






}
