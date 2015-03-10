package com.vyatkins.circletictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.vyatkins.utils.Utils;


public class GameView extends View {

    private int shiftBorder = 20;

    private final Game game;
    private final Paint paint;
    private final Paint paintC;
    private Bitmap backgroundGrid;
    private int aiWins;
    private int userWins;
    private int draws;
    private String youLabel;
    private String aiLabel;
    private Paint paintBoard;

    public GameView(Context context) {
        super(context);
        this.game = (Game) context;
        paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
//        paint.setStyle(Paint.Style.STROKE);

        paintC = new Paint();
        paintC.setColor(Color.GREEN);
        paintC.setStrokeWidth(5);
        paintC.setStyle(Paint.Style.STROKE);

        paintBoard = new Paint();
        paintBoard.setColor(Color.BLUE);
        paintBoard.setStrokeWidth(3);

        aiWins = 0;
        userWins = 0;
        draws = 0;
        youLabel = game.getString(R.string.youLabel);
        aiLabel = game.getString(R.string.aiLabel);

        backgroundGrid = BitmapFactory.decodeResource(game.getResources(),
                R.drawable.grid);

        setEnabled(true);
        setClickable(true);
    }

    protected void onDraw(Canvas canvas) {
        Paint background = new Paint();
        Paint text = new Paint();
        background.setColor(Color.WHITE);
        canvas.drawRect(0, 0, getWidth(), getHeight(), background);
        shiftBorder = (getWidth() - backgroundGrid.getWidth()) / 2;
        canvas.drawBitmap(backgroundGrid, shiftBorder, 0, null);
        float l_x = backgroundGrid.getWidth() / game.SIZE_OF_BOARD;
        float l_y = backgroundGrid.getHeight() / game.SIZE_OF_BOARD;

        Utils.allCirclePaint(3, 0,
                game.getPlayerPoints(),
                canvas,
                paintC,
                shiftBorder,
                l_x);

        for (int i = 0; i < game.SIZE_OF_BOARD; i++)
            for (int j = 0; j < game.SIZE_OF_BOARD; j++) {
                int pos = game.getGrid(i, j);
                if (pos == 1) {
                    canvas.drawCircle(l_x * i + shiftBorder + l_x / 2, l_y * j + l_y / 2, l_y / 4, paint);
                }
                if (pos == 2) {
                    canvas.drawCircle(l_x * i + shiftBorder + l_x / 2, l_y * j + l_y / 2, l_y / 4, paintC);
                }
                if (pos == 0) {
                    canvas.drawCircle(l_x * i + shiftBorder + l_x / 2, l_y * j + l_y / 2, l_y / 10, paintBoard);
                }
            }
        //current rank
        text.setAntiAlias(true);
        text.setColor(Color.BLACK);
        text.setTextSize(30);
        canvas.drawText(youLabel, 30, backgroundGrid.getHeight() + 50, text);
        canvas.drawText(String.valueOf(userWins), 210, backgroundGrid.getHeight() + 50, text);

        canvas.drawText(aiLabel, 30, backgroundGrid.getHeight() + 90, text);
        canvas.drawText(String.valueOf(aiWins), 210, backgroundGrid.getHeight() + 90, text);

        canvas.drawText("Draw", 30, backgroundGrid.getHeight() + 130, text);
        canvas.drawText(String.valueOf(draws), 210, backgroundGrid.getHeight() + 130, text);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        int winner = -1;
        boolean isValidMove = false;

        if (action == MotionEvent.ACTION_DOWN) {
            return true;

        } else if (action == MotionEvent.ACTION_UP) {
            int x = (int) event.getX();
            int y = (int) event.getY();
//            Log.v("real  x:y", String.valueOf(x) + " : " + String.valueOf(y) + " : " + shiftBorder + " : " + getWidth());

            // if game over reset grid, and begin new game
            if (game.checkGameFinished() != 0) {
                game.cleanGrid();
                invalidate();
                return false;
            }

            int bgWidth = backgroundGrid.getWidth() / game.SIZE_OF_BOARD;
            int bgHeight = backgroundGrid.getHeight() / game.SIZE_OF_BOARD;

            x = (x - shiftBorder) / bgWidth;
            y = y / bgHeight;
//            Log.v("test x:y", String.valueOf(x) + " : " + String.valueOf(y));

            game.addPoint(new Point(x, y));

            if (x < game.SIZE_OF_BOARD && x >= 0 && y < game.SIZE_OF_BOARD && y >= 0) {
                isValidMove = game.setGrid(x, y, game.getPlayer());
            }
            if (isValidMove == true) {
                winner = game.checkGameFinished();

                if (winner == 0) {
                    game.computerMove();
                }
                winner = game.checkGameFinished();

                if (winner == 1) {
                    if (game.getPlayer() == 1) {
                        userWins++;
                    } else {
                        aiWins++;
                    }
                }
                if (winner == 2) {
                    if (game.getPlayer() == 2) {
                        userWins++;
                    } else {
                        aiWins++;
                    }
                } else if (winner == 3) {
                    draws++;
                }
            }
        }
        invalidate();
        return false;
    }

}
