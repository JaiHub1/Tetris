package com.example.proyecto;

public class figuras {

   public int figura;
   public int x1, y1;
   public int x2, y2;
   public int x3, y3;
   public int x4, y4;
   private figuras forma;



     public figuras(figuras forma) {
      this.forma = forma;
      this.x1= forma.x1; this.x2= forma.x2;
      this.x3= forma.x3; this.x4= forma.x4;
      this.y1= forma.y1; this.y2= forma.y2;
      this.y3= forma.y3; this.y4= forma.y4;
  }



    public figuras(int formas) {
        switch (formas) {
       //cuadrado
                case 1:
                x1 = 0; y1 = 7;
                x2 = 0; y2 = 8;
                x3 = 1; y3 = 7;
                x4 = 1; y4 = 8;

                figura = 1;
                break;
            //z
            case 2:
                x1 = 0;y1 = 7;
                x2 = 0;y2 = 8;
                x3 = 1;y3 = 8;
                x4 = 1;y4 = 9;

                figura = 2;
                break;
            //l
            case 3:
                x1 = 0;y1 = 6;
                x2 = 0;y2 = 7;
                x3 = 0;y3 = 8;
                x4 = 0;y4 = 9;

                figura = 3;
                break;
            // T
            case 4:
                x1 = 0;y1 = 8;
                x2 = 1;y2 = 7;
                x3 = 1;y3 = 8;
                x4 = 1;y4 = 9;

                figura = 4;
                break;

            //j
            case 6:
                x1 = 0;y1 = 7;
                x2 = 0;y2 = 8;
                x3 = 0;y3 = 9;
                x4 = 1;y4 = 9;

                figura = 6;
                break;
            //L
            case 7:
                x1 = 0;y1 = 7;
                x2 = 0;y2 = 8;
                x3 = 0;y3 = 9;
                x4 = 1;y4 = 7;

                figura = 7;
                break;
        }
    }

    public void move(int x, int y) {
        x1 = x1 + x;
        y1 = y1 + y;
        x2 = x2 + x;
        y2 = y2 + y;
        x3 = x3 + x;
        y3 = y3 + y;
        x4 = x4 + x;
        y4 = y4 + y;
    }

   //gira alrededor de x y Y
    public void girarfigura() {
        int tmp_x1, tmp_y1;
        int tmp_x2, tmp_y2;
        int tmp_x3, tmp_y3;

        tmp_x1 = girarx(y2);
        tmp_y1 = girary(x2);
        x2 = tmp_x1;
        y2 = tmp_y1;

        tmp_x2 = girarx(y3);
        tmp_y2 = girary(x3);
        x3 = tmp_x2;
        y3 = tmp_y2;

        tmp_x3 = girarx(y4);
        tmp_y3 = girary(x4);
        x4 = tmp_x3;
        y4 = tmp_y3;
    }

    public int girarx(int y) {
        return x1 + y - y1;
    }

    public int girary(int x) {
        return y1 - x + x1;
    }

    public int getMinXCoordinate(int x1, int x2, int x3, int x4) {
        return Math.min(Math.min(x1,x2),Math.min(x3,x4));
    }
}