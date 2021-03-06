package edu.up.cs301.catan;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

/**
 * Created by schneidm17 on 9/13/2015.
 */
public class BoardView extends SurfaceView {

    protected GameBoard myGameBoard;

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        myGameBoard = new GameBoard(0, 0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        myGameBoard.drawBoard(canvas);
    }
}