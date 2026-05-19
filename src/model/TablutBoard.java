package model;

import boardifier.model.ContainerElement;
import boardifier.model.Coord2D;
import boardifier.model.GameElement;
import boardifier.model.GameStageModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class TablutBoard extends ContainerElement {
    private Point kingPos;


    public TablutBoard(int x, int y, GameStageModel gameStageModel) {
        // call the super-constructor to create a 3x3 grid, named "holeboard", and in x,y in space
        super("tablutboard", x, y, 9 , 9, gameStageModel);
        this.kingPos=new Point(5,5);

    }

    public void setKingPos(int i, int j) {
        kingPos.x=j;
        kingPos.y=i;
    }

    public Point getKingPos() {
        return kingPos;
    }

    public int[][] create2DArrayOfMaxValue(int i, int j, int value) {
        int[][] result = new int[i][j];
        for (int[] array : result) {
            for (int k=0; k<array.length;k++ ) {
                array[k]=value;
            }
        }
        return result;
    }

    public int minMovementKing() {

        int[][] store = create2DArrayOfMaxValue(9,9,Integer.MAX_VALUE);







        return 0;
    }

    public void minMovementKingRecursive(int[][] grid,int i, int j, int oldI, int oldJ) {

    }





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
        if (!validPosition(i,j)) {return true;}
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
        if (!validPosition(i,j)) {return false;}

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

        while ( validPosition(i,j) && grid[i][j].isEmpty() ) {
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


    public Point[] getNeighborsCoordinates(int i, int j) {
        return new Point[] {new Point(j, i-1),new Point(j+1, i),new Point(j, i+1),new Point(j-1, i)};
    }

    /*/
    return an array of the neighbors of a cell in the grid,
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
