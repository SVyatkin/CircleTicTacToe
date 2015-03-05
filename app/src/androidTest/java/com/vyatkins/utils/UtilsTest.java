package com.vyatkins.utils;

import android.graphics.Point;

import junit.framework.TestCase;

public class UtilsTest extends TestCase {

    public void testGetCenter() throws Exception {
        int l_x = 10;
        int l_y = 10;
        Point center = Utils.getCenter(
                new Point ((int)(l_x * 4 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 4 + l_x/2), (int)(l_y * 3 + l_y/2)),
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 2 + l_y/2))
        );

        Point center1 = Utils.getCenter(
                new Point ((int)(l_x * 4 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 3 + l_y/2))
        );

        Point center2 = Utils.getCenter(
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 4 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 3 + l_y/2))
        );
        assertTrue(center1.equals(center2.x, center2.y));

        Point center3 = Utils.getCenter(
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 3 + l_y/2)),
                new Point ((int)(l_x * 4 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 2 + l_y/2))
        );

        assertTrue(center3.equals(center2.x, center2.y));

        Point center4 = Utils.getCenter(
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 3 + l_y/2)),
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 4 + l_x/2), (int)(l_y * 2 + l_y/2))
        );

        assertTrue(center3.equals(center4.x, center4.y));

    }

    public void testGetRadius() throws Exception {
        int l_x = 10;
        int l_y = 10;
        Point center = Utils.getCenter(
                new Point ((int)(l_x * 4 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 2 + l_y/2)),
                new Point ((int)(l_x * 5 + l_x/2), (int)(l_y * 3 + l_y/2))
        );

        float r = Utils.getRadius (center,
                new Point ((int)(l_x * 4 + l_x/2), (int)(l_y * 2 + l_y/2)));

        assertTrue((r-7.0) > 0.0);
    }
}