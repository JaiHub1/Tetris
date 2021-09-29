package com.example.proyecto;

import android.graphics.Color;
import android.graphics.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Tablero {

    private final int boardHeight =24;
    private final int boardWidth=12;
    private int tablajuego[][]=new int[boardHeight][boardWidth];
    private final Random random = new Random();
    private ArrayList<figuras> pieceList = new ArrayList<figuras>();
    private  final int number_of_Pieces = 6;

    public Tablero() {
        pieceList.add(new figuras(random.nextInt(number_of_Pieces)+1));
        pieceList.add(new figuras(random.nextInt(number_of_Pieces)+1));
    }

    /*
    color de las figuras y la tablajuego cell
     */

    public int codeToColor(int x, int y) {

        if(tablajuego[x][y]==0) return Color.parseColor("#000000");
        if(tablajuego[x][y]==1) return Color.parseColor("#ECFF00");
        if(tablajuego[x][y]==2) return Color.parseColor("#49FF00");
        if(tablajuego[x][y]==3) return Color.parseColor("#00FFF0");
        if(tablajuego[x][y]==4) return Color.parseColor("#FF00FB");
        if(tablajuego[x][y]==5) return Color.parseColor("#49FF00");
        if(tablajuego[x][y]==6) return Color.parseColor("#FF0000");
        if(tablajuego[x][y]==7) return Color.parseColor("#FF0000");

        return -1;
    }

    public void clearGameBoard() {
        for(int i=0; i<boardHeight; i++) {
            for(int j=0; j<boardWidth; j++) {
                tablajuego[i][j]= 0;
            }
        }
    }

    public  ArrayList<figuras> getPieceList(){
        return pieceList;
    }

    public figuras getCurrentPiece()  {
        return pieceList.get(pieceList.size() - 2);
    }

    public figuras getNextPiece() {
        return pieceList.get(pieceList.size()-1);
    }

    private void placePiece(figuras currentPiece) {
        tablajuego[currentPiece.x1][currentPiece.y1] = currentPiece.figura;
        tablajuego[currentPiece.x2][currentPiece.y2] = currentPiece.figura;
        tablajuego[currentPiece.x3][currentPiece.y3] = currentPiece.figura;
        tablajuego[currentPiece.x4][currentPiece.y4] = currentPiece.figura;
    }

    private void deletePiece(figuras currentPiece) {
        tablajuego[currentPiece.x1][currentPiece.y1] = 0;
        tablajuego[currentPiece.x2][currentPiece.y2] = 0;
        tablajuego[currentPiece.x3][currentPiece.y3] = 0;
        tablajuego[currentPiece.x4][currentPiece.y4] = 0;
    }

    /*
    checks if Piece can move in direction x|y
    copy Piece and try to move it, return true
    if it can move
     */

    private boolean piece_Can_Move(figuras currentPiece, int x, int y) {
        int tmp =0;
        /*
        copy piece coordinates
         */
        Point p1 = new Point(currentPiece.x1, currentPiece.y1);
        Point p2 = new Point(currentPiece.x2, currentPiece.y2);
        Point p3 = new Point(currentPiece.x3, currentPiece.y3);
        Point p4 = new Point(currentPiece.x4, currentPiece.y4);

        Point tmp1 = new Point(currentPiece.x1+x, currentPiece.y1+y);
        Point tmp2 = new Point(currentPiece.x2+x, currentPiece.y2+y);
        Point tmp3 = new Point(currentPiece.x3+x, currentPiece.y3+y);
        Point tmp4 = new Point(currentPiece.x4+x, currentPiece.y4+y);

        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        tmpPieceCoordinates.add(tmp1);
        tmpPieceCoordinates.add(tmp2);
        tmpPieceCoordinates.add(tmp3);
        tmpPieceCoordinates.add(tmp4);


        for(Point p : tmpPieceCoordinates ) {

            if(p.x< boardHeight && p.y>=0 && p.y< boardWidth && tablajuego[p.x][p.y]==0) {
                tmp++;
            }

            else if(p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        if(tmp==4) {
            return true;
        }
        return false;
    }

     /*
     copy current Piece and check if it can rotate
     if true return true
      */

    private boolean piece_Can_Rotate(figuras currentPiece) {
        int tmp =0;
        ArrayList<Point> tmpPieceCoordinates = new ArrayList<Point>();

        figuras tmpStein = new figuras(currentPiece);

        Point p1 = new Point(currentPiece.x1, currentPiece.y1);
        Point p2 = new Point(currentPiece.x2, currentPiece.y2);
        Point p3 = new Point(currentPiece.x3, currentPiece.y3);
        Point p4 = new Point(currentPiece.x4, currentPiece.y4);

        tmpStein.turnPiece();

        Point tmp1 = new Point(tmpStein.x1, tmpStein.y1);
        Point tmp2 = new Point(tmpStein.x2, tmpStein.y2);
        Point tmp3 = new Point(tmpStein.x3, tmpStein.y3);
        Point tmp4 = new Point(tmpStein.x4, tmpStein.y4);

        tmpPieceCoordinates .add(tmp1);
        tmpPieceCoordinates .add(tmp2);
        tmpPieceCoordinates .add(tmp3);
        tmpPieceCoordinates .add(tmp4);

        for(Point p : tmpPieceCoordinates  ) {

            if(p.x< boardHeight && p.x>=0 && p.y>=0 && p.y< boardWidth && tablajuego[p.x][p.y]==0) {
                tmp++;
            }

            else if(p.equals(p1) || p.equals(p2) || p.equals(p3) || p.equals(p4)) {
                tmp++;
            }
        }

        if(tmp==4) {  /* all four little squares are correct*/
            return true;
        }
        return false;
    }

    private  boolean can_Move_Left(figuras currentPiece) {
        if(piece_Can_Move(currentPiece, 0, -1)==true) {
            return true;
        }
        return false;
    }

    private boolean can_Move_Right(figuras currentPiece){
        if(piece_Can_Move(currentPiece, 0,1) == true) {
            return true;
        }
        return false;
    }

    public boolean can_Move_Down(figuras currentPiece) {
        if(piece_Can_Move(currentPiece, 1,0)==true) {
            return true;
        }
        return false;
    }


    private void movePiece(figuras currentPiece, int x, int y)   {
        deletePiece(currentPiece);
        currentPiece.move(x, y);
        placePiece(currentPiece);
    }

    public void moveRight(figuras currentPiece){
        if(can_Move_Right(currentPiece)==true) {
            movePiece(currentPiece, 0, 1);
        }
    }

    public  void moveLeft(figuras currentPiece){
        if(can_Move_Left(currentPiece)==true) {
            movePiece(currentPiece, 0, -1);
        }
    }

    public  void moveDown(figuras currentPiece) {
        if(can_Move_Down(currentPiece)==true) {
            movePiece(currentPiece, 1, 0);
        }
    }

    public void fastDrop(figuras currentPiece) {
        deletePiece(currentPiece);

        while(can_Move_Down(currentPiece)==true) {
            moveDown(currentPiece);
        }
        placePiece(currentPiece);
    }

    /*
    turn all pieces until square piece
     */

    public void rotatePiece(figuras currentPiece) {

        if(piece_Can_Rotate(currentPiece)==true && currentPiece.figura !=1) {
            deletePiece(currentPiece);
            currentPiece.turnPiece();
            placePiece(currentPiece);
        }
        placePiece(currentPiece);
    }

    public int clearRows() {

        int deletedRowIndex;
        int deletedRows=0;
        ArrayList<Integer> arrayList = new ArrayList<Integer>();

        for (int i = 0; i < boardHeight; i++) {
            for (int j = boardWidth - 1; j >= 0; j--) {

                if (tablajuego[i][j]==0) { // Row not full
                    break;
                }
                if (j == 0) {
                    deletedRowIndex = i;
                    arrayList.add(deletedRowIndex);
                    deletedRows++;
                    deleteRow(deletedRowIndex);
                }
            }
        }

        if (deletedRows >= 1) {
            int highestRow = Collections.min(arrayList); // highest Row which can be cleared
            int[][] gameBoardCopy = new int[highestRow][boardWidth];

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    gameBoardCopy[i][j] = tablajuego[i][j];
                }
            }

            for (int i = 0; i < gameBoardCopy.length; i++) {
                for (int j = 0; j < gameBoardCopy[1].length; j++) {
                    tablajuego[i+deletedRows][j] = gameBoardCopy[i][j];
                }
            }
        }
        return deletedRows;
    }

    public void deleteRow(int r){
        for (int i = 0; i < boardWidth; i++) {
            tablajuego[r][i] =0;
        }
    }

    public boolean checklose(figuras spielStein) {
        if(can_Move_Down(spielStein) == false && spielStein.getMinXCoordinate(
                spielStein.x1, spielStein.x2, spielStein.x3, spielStein.x4)<=1) {
            return true;
        }
        return false;
    }

    public  int getBoardHeight() {
        return this.boardHeight;
    }

    public int getBoardWidth() {
        return this.boardWidth;
    }
}