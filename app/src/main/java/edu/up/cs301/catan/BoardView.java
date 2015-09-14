package edu.up.cs301.catan;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by schneidm17 on 9/13/2015.
 */
public class BoardView extends SurfaceView{

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        /*
        Paint myColor = new Paint();
            myColor.setColor(Color.YELLOW);
            myColor.setStyle(Paint.Style.FILL);

        Path hex = new Path();
            hex.moveTo(200,200);
            hex.rLineTo(200,0);
            hex.rLineTo(0,200);
            hex.rLineTo(-200,0);
            hex.rLineTo(0,-200);

        canvas.drawPath(hex,myColor);
        */

        GameBoard myGameBoard = new GameBoard(45,0);
        myGameBoard.drawBoard(canvas);
    }
}