package edu.up.cs301.catan;

import android.graphics.*;

import static edu.up.cs301.catan.R.id.displayArea;

/**
 * This is the base class for the entire game board
 * <p/>
 * Created by schneidm17 on 9/13/2015.
 */
public class GameBoard extends Graphics {
    public GameBoard(int phi, int theta) {
        super(phi, theta);
    }

    public void drawBoard(Canvas canvas) {
        Paint coastColor = new Paint();
        coastColor.setColor(0xFFC2B280);
        coastColor.setStyle(Paint.Style.FILL);

        Paint fillColor = new Paint();
        fillColor.setColor(Color.GREEN);
        fillColor.setStyle(Paint.Style.FILL);

        Paint strokeColor = new Paint();
        strokeColor.setColor(Color.BLACK);
        strokeColor.setStyle(Paint.Style.STROKE);
        strokeColor.setStrokeWidth(3);

        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;
        int scale = 350;

        Path coast = new Path();
        coast.moveTo(centerX + (float) (scale * getx(coastline[0][0], coastline[0][1], 0)),
                centerY + (float) (scale * gety(coastline[0][0], coastline[0][1], 0)));
        for (double[] aCoastline : coastline) {
            double x = aCoastline[0];
            double y = aCoastline[1];
            coast.lineTo(centerX + (float) (scale * getx(x, y, 0)), centerY + (float) (scale * gety(x, y, 0)));
        }
        coast.close();
        canvas.drawPath(coast, coastColor);
        canvas.drawPath(coast, strokeColor);

        for (double[] tile : tiles) {
            double x = tile[0];
            double y = tile[1];

            Path hex = new Path();
            hex.moveTo(centerX + (float) (scale * getx(x + 2, y, 0)), centerY + (float) (scale * gety(x + 2, y, 0)));
            hex.lineTo(centerX + (float) (scale * getx(x + 1, y + Math.sqrt(3), 0)), centerY + (float) (scale * gety(x + 1, y + Math.sqrt(3), 0)));
            hex.lineTo(centerX + (float) (scale * getx(x - 1, y + Math.sqrt(3), 0)), centerY + (float) (scale * gety(x - 1, y + Math.sqrt(3), 0)));
            hex.lineTo(centerX + (float) (scale * getx(x - 2, y, 0)), centerY + (float) (scale * gety(x - 2, y, 0)));
            hex.lineTo(centerX + (float) (scale * getx(x - 1, y - Math.sqrt(3), 0)), centerY + (float) (scale * gety(x - 1, y - Math.sqrt(3), 0)));
            hex.lineTo(centerX + (float) (scale * getx(x + 1, y - Math.sqrt(3), 0)), centerY + (float) (scale * gety(x + 1, y - Math.sqrt(3), 0)));
            hex.close();

            canvas.drawPath(hex, fillColor);
            canvas.drawPath(hex, strokeColor);
        }

    }
}
