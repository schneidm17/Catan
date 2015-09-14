package edu.up.cs301.catan;

import android.graphics.*;

import static edu.up.cs301.catan.R.id.displayArea;

/**
 * This is the base class for the entire game board
 *
 * Created by schneidm17 on 9/13/2015.
 */
public class GameBoard {
    public GameBoard(int phi, int theta)
    {
        this.phi=phi;
        this.theta=theta;
        updateABC();
    }

    /*
     * The camera is stored in spherical coordinates as degrees {d, phi, theta}, which
     * make it easier for the user to rotate the board and prevents rounding errors, but
     * the function that maps the xyz coordinates of the board to the xy of the screen requires
     * cartesian coordinates {a, b, c} that are only calculated once after moving the camera
     */
    private int phi; //angle of the camera from the z axis (in spherical coordinates)
    private int theta; //angle of the camera from the x axis (in spherical coordinates)
    private final double d=30; //distance of the camera from the origin (constant)

    private double a; //the x coordinate of the view plane (in cartesian coordinates)
    private double b; //the y coordinate of the view plane (in cartesian coordinates)
    private double c; //the z coordinate of the view plane (in cartesian coordinates)
    private final double p=25; //distance of the view plane from the origin (constant)

    public static final double deg = 0.017453292519943295; //conversion factor for deg to rad

    /**
     * this is the xy coordinates of every numbered hex tile on the board
     * eg(tile #0 is located at x=-6, y=2*Math.sqrt(3), z=0)
     */
    public static final double[][] tileXY = {
            {-6,2*Math.sqrt(3)},
            {-6,0},
            {-6,-2*Math.sqrt(3)},
            {-3,3*Math.sqrt(3)},
            {-3,Math.sqrt(3)},
            {-3,-Math.sqrt(3)},
            {-3,-3*Math.sqrt(3)},
            {0,4*Math.sqrt(3)},
            {0,2*Math.sqrt(3)},
            {0,0},
            {0,-2*Math.sqrt(3)},
            {0,-4*Math.sqrt(3)},
            {3,3*Math.sqrt(3)},
            {3,Math.sqrt(3)},
            {3,-Math.sqrt(3)},
            {3,-3*Math.sqrt(3)},
            {6,2*Math.sqrt(3)},
            {6,0},
            {6,-2*Math.sqrt(3)}
    };


    /**
     * updateABC updates the x,y,z coordinates of intersection of the vector form the
     * camera to the origin with the view plane. Must be updated after the camera rotates
     */
    private void updateABC()
    {
        a = p*Math.sin(phi*deg)*Math.cos(theta*deg);
        b = p*Math.sin(phi*deg)*Math.sin(theta*deg);
        c = p*Math.cos(phi*deg);
    }

    /**
     * getx returns the x coordinate of the point {x,y,z} in 3D space as viewed from
     * the camera at {d, theta, phi} as it would appear on the plane ax+by+cz=p^2
     *
     * @param x - the x coordinate of a point in 3D space
     * @param y - the y coordinate of a point in 3D space
     * @param z - the z coordinate of a point in 3D space
     * @return the x coordinate on the screen of the point {x,y,x} in 3D space
     */
    public double getx(double x, double y, double z)
    {
        double t = (p*p-x*a-y*b-z*c)/(d*p-x*a-y*b-c*z); //where point intersects viewplane
        return ((b-y-t*(d*b/p-y))*a-(a-x-t*(d*a/p-x))*b)/Math.sqrt(a*a+b*b);
    }

    /**
     * gety returns the y coordinate of the point {x,y,z} in 3D space as viewed from
     * the camera at {d, theta, phi} as it would appear on the plane ax+by+cz=p^2
     *
     * @param x - the x coordinate of a point in 3D space
     * @param y - the y coordinate of a point in 3D space
     * @param z - the z coordinate of a point in 3D space
     * @return the y coordinate on the screen of the point {x,y,x} in 3D space
     */
    public double gety(double x, double y, double z)
    {
        double t = (p*p-x*a-y*b-z*c)/(d*p-x*a-y*b-c*z); //where point intersects viewplane
        double m = Math.sqrt((a*a+b*b)*(a*a+b*b)+(a*a+b*b)*c*c); //magnitude of plane axis
        return ((c-z-t*(d*c/p - z))*(a*a+b*b)-(b-y-t*(d*b/p - y))*(b*c)-(a-x-t*(d*a/p - x))*(a*c))/m;
    }

    public void drawBoard(Canvas canvas)
    {
        Paint fillColor = new Paint();
        fillColor.setColor(Color.YELLOW);
        fillColor.setStyle(Paint.Style.FILL);

        Paint strokeColor = new Paint();
        strokeColor.setColor(Color.BLACK);
        strokeColor.setStyle(Paint.Style.STROKE);
        strokeColor.setStrokeWidth(3);

        int centerX = canvas.getWidth()/2;
        int centerY = canvas.getHeight()/2;
        int scale = 400;

        for (int i=0; i<19; i++)
        {
            double x = tileXY[i][0];
            double y = tileXY[i][1];

            Path hex = new Path();
            hex.moveTo(centerX+(float)(scale*getx(x+2,y,0)),centerY+(float)(scale * gety(x +2,y,0)));
            hex.lineTo(centerX+(float)(scale*getx(x+1,y+Math.sqrt(3),0)),centerY+(float)(scale*gety(x+1,y+Math.sqrt(3),0)));
            hex.lineTo(centerX+(float)(scale*getx(x-1,y+Math.sqrt(3),0)),centerY+(float)(scale*gety(x-1,y+Math.sqrt(3),0)));
            hex.lineTo(centerX+(float)(scale*getx(x-2,y,0)),centerY+(float)(scale*gety(x-2,y,0)));
            hex.lineTo(centerX+(float)(scale*getx(x-1,y-Math.sqrt(3),0)),centerY+(float)(scale*gety(x-1,y-Math.sqrt(3),0)));
            hex.lineTo(centerX+(float)(scale*getx(x+1,y-Math.sqrt(3),0)),centerY+(float)(scale*gety(x+1,y-Math.sqrt(3),0)));
            hex.close();


            canvas.drawPath(hex, fillColor);
            canvas.drawPath(hex,strokeColor);
        }

    }
}
