package model;

import boardifier.model.ContainerElement;
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
//        Pawn p = null;
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

//    public boolean isCaptured(int i, int j) {
//        if (grid[i][j]==null) {
//            return false;
//        }
//        if (grid[i][j] instanceof Pawn pawn) {
//
//        }
//    }

    public boolean validPosition(int i, int j) {
        return  (i<9 && i>=0 && j<9 && j>=0);
    }


    /*/
    return an array of the neighbors of a tile in the grid,
    [ NORTH , EST, SOUTH, WEST ]
    */
    public Pawn[] getNeighbors(int i, int j) {

        return null;




    }




}
