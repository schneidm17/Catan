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

        thickStroke = new Paint();
        thinStroke = new Paint();
        redPlayer = new Paint();
        orangePlayer = new Paint();
        bluePlayer = new Paint();
        whitePlayer = new Paint();

        thickStroke.setColor(Color.BLACK);
        thinStroke.setColor(Color.BLACK);
        redPlayer.setColor(Color.RED);
        orangePlayer.setColor(Color.YELLOW);
        bluePlayer.setColor(Color.BLUE);
        whitePlayer.setColor(Color.WHITE);

        thickStroke.setStyle(Paint.Style.STROKE);
        thinStroke.setStyle(Paint.Style.STROKE);
        thickStroke.setStrokeWidth(3);
        thinStroke.setStrokeWidth(2);

        redPlayer.setStyle(Paint.Style.FILL);
        orangePlayer.setStyle(Paint.Style.FILL);
        bluePlayer.setStyle(Paint.Style.FILL);
        whitePlayer.setStyle(Paint.Style.FILL);
    }

    private Paint squareColor[] = {stone, wool, wood, wheat, brick, wool, brick,
            wheat, wood, sand, wood, stone, wood, stone, wheat, wool, brick, wheat, wool};
    int scale = 350;

    Paint thickStroke;
    Paint thinStroke;
    Paint redPlayer;
    Paint orangePlayer;
    Paint bluePlayer;
    Paint whitePlayer;

    public void drawBoard(Canvas canvas) {
        drawSquares(canvas);

        drawRoad(canvas, redPlayer, 13);
        drawRoad(canvas, redPlayer, 41);
        drawRoad(canvas, redPlayer, 40);
        drawRoad(canvas, redPlayer, 42);
        drawSet(canvas, redPlayer, 10);
        drawSet(canvas, redPlayer, 29);

        drawRoad(canvas, whitePlayer, 25);
        drawRoad(canvas, whitePlayer, 37);
        drawRoad(canvas, whitePlayer, 24);
        drawSet(canvas, whitePlayer, 19);
        drawSet(canvas, whitePlayer, 35);

        drawRoad(canvas, bluePlayer, 52);
        drawRoad(canvas, bluePlayer, 56);
        drawRoad(canvas, bluePlayer, 45);
        drawSet(canvas, bluePlayer, 40);
        drawSet(canvas, bluePlayer, 44);

        drawRoad(canvas, orangePlayer, 15);
        drawRoad(canvas, orangePlayer, 58);
        drawRoad(canvas, orangePlayer, 14);
        drawSet(canvas, orangePlayer, 13);
        drawSet(canvas, orangePlayer, 42);
    }

    public void drawRoad(Canvas canvas, Paint playerColor, int r) {
        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;

        double x = sites[roads[r][0]][0];
        double y = sites[roads[r][0]][1];
        double i = sites[roads[r][1]][0] - x;
        double j = sites[roads[r][1]][1] - y;

        Path road = new Path();

        road.moveTo(centerX + (float) (scale * getx(x + i / 6 - j / 12, y + j / 5 + i / 12, 0)),
                centerY + (float) (scale * gety(x + i / 6 - j / 16, y + j / 5 + i / 12, 0)));
        road.lineTo(centerX + (float) (scale * getx(x + i / 6 + j / 12, y + j / 5 - i / 12, 0)),
                centerY + (float) (scale * gety(x + i / 6 + j / 16, y + j / 5 - i / 12, 0)));
        road.lineTo(centerX + (float) (scale * getx(x + 5 * i / 6 + j / 12, y + 4 * j / 5 - i / 12, 0)),
                centerY + (float) (scale * gety(x + 5 * i / 6 + j / 16, y + 4 * j / 5 - i / 12, 0)));
        road.lineTo(centerX + (float) (scale * getx(x + 5 * i / 6 - j / 12, y + 4 * j / 5 + i / 12, 0)),
                centerY + (float) (scale * gety(x + 5 * i / 6 - j / 16, y + 4 * j / 5 + i / 12, 0)));
        road.close();

        canvas.drawPath(road, playerColor);
        canvas.drawPath(road, thinStroke);
    }

    public void drawSet(Canvas canvas, Paint playerColor, int n) {
        float x = canvas.getWidth() / 2 + (float) (scale * getx(sites[n][0], sites[n][1], 0));
        float y = canvas.getHeight() / 2 + (float) (scale * gety(sites[n][0], sites[n][1], 0));

        Path set = new Path();
        set.moveTo(x - scale / 15, y + scale / 15);
        set.lineTo(x - scale / 15, y - scale / 30);
        set.lineTo(x, y - scale / 15);
        set.lineTo(x + scale / 15, y - scale / 30);
        set.lineTo(x + scale / 15, y + scale / 15);
        set.close();

        canvas.drawPath(set, playerColor);
        canvas.drawPath(set, thinStroke);
    }

    public void drawSquares(Canvas canvas) {
        int centerX = canvas.getWidth() / 2;
        int centerY = canvas.getHeight() / 2;

        //draw the coastline
        Path coast = new Path();
        coast.moveTo(centerX + (float) (scale * getx(coastline[0][0], coastline[0][1], 0)),
                centerY + (float) (scale * gety(coastline[0][0], coastline[0][1], 0)));
        for (double[] aCoastline : coastline) {
            double x = aCoastline[0];
            double y = aCoastline[1];
            coast.lineTo(centerX + (float) (scale * getx(x, y, 0)), centerY + (float) (scale * gety(x, y, 0)));
        }
        coast.close();
        canvas.drawPath(coast, sand);
        canvas.drawPath(coast, thinStroke);

        //draw the squares on the board
        for (int i = 0; i < tiles.length; i++) {
            double x = tiles[i][0];
            double y = tiles[i][1];

            Path hex = new Path();
            hex.moveTo(centerX + (float) (scale * getx(x + 2, y, 0)), centerY + (float) (scale * gety(x + 2, y, 0)));
            hex.lineTo(centerX + (float) (scale * getx(x + 1, y + Math.sqrt(3), 0)), centerY + (float) (scale * gety(x + 1, y + Math.sqrt(3), 0)));
            hex.lineTo(centerX + (float) (scale * getx(x - 1, y + Math.sqrt(3), 0)), centerY + (float) (scale * gety(x - 1, y + Math.sqrt(3), 0)));
            hex.lineTo(centerX + (float) (scale * getx(x - 2, y, 0)), centerY + (float) (scale * gety(x - 2, y, 0)));
            hex.lineTo(centerX + (float) (scale * getx(x - 1, y - Math.sqrt(3), 0)), centerY + (float) (scale * gety(x - 1, y - Math.sqrt(3), 0)));
            hex.lineTo(centerX + (float) (scale * getx(x + 1, y - Math.sqrt(3), 0)), centerY + (float) (scale * gety(x + 1, y - Math.sqrt(3), 0)));
            hex.close();

            canvas.drawPath(hex, squareColor[i]);
            canvas.drawPath(hex, thickStroke);
        }
    }
}