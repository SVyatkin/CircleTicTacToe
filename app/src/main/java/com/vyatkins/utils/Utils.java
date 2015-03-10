package com.vyatkins.utils;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import com.vyatkins.circletictactoe.Game;

import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

/**
 * Created by vyatkins on 3/2/15.
 */
public class Utils {

    private static Game game = new Game();

    public static Point getCenter(Point a, Point b, Point c){
        double mr;
        double mt;
        double x;
        double y;

        if (b.y == a.y) {
            x = (b.x + a.x) / 2;
            if (c.x == b.x) {
                y = (b.y + c.y) / 2;
            } else {
                mt = (double) (c.y - b.y) / (double) (c.x - b.x);
                y = -1 / mt * (x - (c.x + b.x) / 2) + (c.y + b.y) / 2;
            }
            return new Point((int) x, (int) y);
        }

        if (b.x == a.x){
            y = (b.y + a.y)/2;
            if (b.y == c.y){
                x = (c.x + b.x)/2;
            } else {
                mt =(double)(c.y - b.y)/(double)(c.x - b.x);
                x = ((c.y + b.y) / 2 - y) * mt + (c.x + b.x) / 2;


            }
            return new Point((int) x, (int) y);
        }

        if (c.x == b.x){
            y = (b.y + c.y)/2;
            if (b.y == a.y){
                x = (a.x + b.x)/2;
            } else {
                mr = (double)(b.y - a.y)/(double)(b.x - a.x);
                x = (- y + (a.y + b.y) / 2) * mr + (a.x + b.x)/2;
            }
            return new Point((int) x, (int) y);
        }

        mr = (double)(b.y - a.y)/(double)(b.x - a.x);
        mt =(double)(c.y - b.y)/(double)(c.x - b.x);
        x = mr * mt * (c.y - a.y) + mr * (b.x + c.x) - mt * (a.x + b.x);
        x = x / (2 * (mr - mt));
        y = -1/mr * (x - (a.x + b.x)/2) + (a.y + b.y) / 2;
        return new Point((int) x, (int) y);
    }

    public static float getRadius (Point center, Point a) {
        return (float) sqrt((center.x - a.x) * (center.x - a.x)
                + (center.y - a.y) * (center.y - a.y));
    }

    public static void allCirclePaint(int len, int start,
                                      List<Point> arr,
                                      Canvas canvas,
                                      Paint paintC,
                                      int shiftBorder,
                                      float l_x) {

        for (int i=0; i < arr.size()-2; i++)
            for (int j = i + 1; j<arr.size() -1; j++)
                for (int k = j + 1; k< arr.size(); k++)
                    paintCircle (canvas,
                                 paintC,
                                 arr.get(i),
                                 arr.get(j),
                                 arr.get(k),
                                 shiftBorder,
                                        l_x);
    }

    public static void paintCircle(Canvas canvas,
                                   Paint paintC,
                                   Point a,
                                   Point b,
                                   Point c,
                                   int shiftBorder,
                                   float l_x) {


        Point center = Utils.getCenter(
                new Point ((int)(a.x * l_x + l_x/2),(int) (a.y * l_x + l_x/2)),
                new Point ((int)(b.x * l_x + l_x/2),(int) (b.y * l_x + l_x/2)),
                new Point ((int)(c.x * l_x + l_x/2),(int) (c.y * l_x + l_x/2)));

//        Log.v("center  x:y", String.valueOf(center.x) + " : " + String.valueOf(center.y) + " : " + shiftBorder + " : " + l_x );

        if (!isRightCircle(a, b, c)) return;


        float radius = Utils.getRadius (center, new Point ((int)(a.x * l_x + l_x/2),(int) (a.y * l_x + l_x/2)));

        if (isLongRadius(center, radius, shiftBorder, l_x)) return;

        canvas.drawCircle(shiftBorder + center.x, center.y, radius, paintC);
        Log.v("a : ", String.valueOf(a.x) + " : " + String.valueOf(a.y));
        Log.v("b : ", String.valueOf(b.x) + " : " + String.valueOf(b.y));
        Log.v("c : ", String.valueOf(c.x) + " : " + String.valueOf(c.y));
        Log.v("center  x:y", String.valueOf(center.x/l_x) + " : " + String.valueOf(center.y/l_x) + " : " + radius );
    }

    private static boolean isRightCircle(Point a, Point b, Point c) {
        float ab = getRadius(a, b);
        float ac = getRadius(a, c);
        float bc = getRadius(b, c);
        if (abs(ab - bc) < 0.001) {
            return abs(ab * ab + bc * bc - ac * ac) < 0.001;
        }

        if (abs(ab - ac) < 0.001) {
            return abs(ab * ab - bc * bc + ac * ac) < 0.001;
        }

        if (abs(ac - bc) < 0.001) {
            return abs(ac * ac + bc * bc - ab * ab) < 0.001;
        }

        return false;
    }

    private static boolean isLongRadius(Point center, float radius, int shiftBorder, float l_x) {
        return
                (center.x - radius < 0)
                        ||
                ((center.x + radius) > (game.SIZE_OF_BOARD * l_x))
                        ||
                (center.y - radius < 0)
                        ||
                ((center.y + radius) > game.SIZE_OF_BOARD * l_x)
                ;
    }

    private static boolean isNotCenter(Point center, float l_x) {
        float fx = center.x/l_x;
        float fy = center.y/l_x;
        int x = (int) fx;
        int y = (int) fy;
        return (fx - x) != 0.5 ||  (fy - y) != 0.5;
    }

}
