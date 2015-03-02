package com.vyatkins.circletictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Game extends Activity {

    public final int SIZE_OF_BOARD = 8;

    private int player = 1;
    private int[][] board = new int[SIZE_OF_BOARD][SIZE_OF_BOARD];
    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cleanGrid();
        gameView = new GameView(this);
        setContentView(gameView);
        gameView.requestFocus();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public int getGrid(int i, int j) {
        return board[i][j];
    }

    public int checkGameFinished() {
        return 0;
    }

    public boolean setGrid(int i, int j, int value) {
        board[i][j] = value;
        return false;
    }

    public int getPlayer() {
        return player;
    }

    public void setPlayer(int player) {
        this.player = player;
    }

    public void cleanGrid() {
        for (int i = 0; i < SIZE_OF_BOARD; i++)
            for (int j = 0; j < SIZE_OF_BOARD; j++) board[i][j] = 0;
    }

        public void computerMove() {

        }
}
