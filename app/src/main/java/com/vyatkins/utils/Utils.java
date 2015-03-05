package com.vyatkins.utils;

import android.graphics.Point;

import static java.lang.Math.sqrt;

/**
 * Created by vyatkins on 3/2/15.
 */
public class Utils {

    public static Point getCenter(Point a, Point b, Point c){
//        if (b.x == a.x || c.x == b.x) {
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
}
