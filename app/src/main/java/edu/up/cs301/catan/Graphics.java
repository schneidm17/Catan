package edu.up.cs301.catan;

import android.graphics.Paint;

/**
 * @author Matthew Schneider
 * @version 9/15/2015
 *
 * @deprecated 10/22/2015
 * @see GameSurfaceView
 */
public class Graphics {
    public Graphics(int phi, int theta) {
        this.phi = phi;
        this.theta = theta;
        updateABC();

        wood = new Paint();
        wheat = new Paint();
        brick = new Paint();
        stone = new Paint();
        wool = new Paint();
        sand = new Paint();

        wood.setColor(0xFF466F37);
        wheat.setColor(0xFFE7B23E);
        brick.setColor(0xFFB16B32);
        stone.setColor(0xFF969696);
        wool.setColor(0xFF91C14B);
        sand.setColor(0xFFE2C581);

        wood.setStyle(Paint.Style.FILL);
        wheat.setStyle(Paint.Style.FILL);
        brick.setStyle(Paint.Style.FILL);
        stone.setStyle(Paint.Style.FILL);
        wool.setStyle(Paint.Style.FILL);
        sand.setStyle(Paint.Style.FILL);
}

    /*
     * The camera is stored in spherical coordinates as degrees {d, phi, theta}, which
     * make it easier for the user to rotate the board and prevents rounding errors, but
     * the function that maps the xyz coordinates of the board to the xy of the screen requires
     * cartesian coordinates {a, b, c} that are only calculated once after moving the camera
     */
    private int phi; //angle of the camera from the z axis (in spherical coordinates)
    private int theta; //angle of the camera from the x axis (in spherical coordinates)
    private final double d = 30; //distance of the camera from the origin (constant)

    private double a; //the x coordinate of the view plane (in cartesian coordinates)
    private double b; //the y coordinate of the view plane (in cartesian coordinates)
    private double c; //the z coordinate of the view plane (in cartesian coordinates)
    private final double p = 25; //distance of the view plane from the origin (constant)

    private double k1; //the coefficient for the i unit vector of getX()
    private double k2; //the coefficient for the j unit vector of getX()
    private double k3; //the coefficient for the i unit vector of getY()
    private double k4; //the coefficient for the j unit vector of getY()
    private double k5; //the coefficient for the k unit vector of getY()

    public static Paint wood; //color of the wood squares
    public static Paint wheat; //color of the wheat squares
    public static Paint brick; //color of the brick squares
    public static Paint stone; //color of the stone squares
    public static Paint wool; //color of the wool squares
    public static Paint sand; //color of the desert squares and the coast

    public static final double deg = 0.017453292519943295; //conversion factor for deg to rad
    public static final double r3 = Math.sqrt(3); //square root of 3

    /*
     * These are the physical locations on the board of every piece that we might draw
     * We have a numbering system in place so that we know that a settlement at site
     * ten is adjacent to tiles 1, 4 and 5 and roads 12, 13 amd 20;
     */

    public static final double tiles[][] = {{-6, 2 * r3}, {-6, 0}, {-6, -2 * r3},
            {-3, 3 * r3}, {-3, r3}, {-3, -r3}, {-3, -3 * r3}, {0, 4 * r3}, {0, 2 * r3},
            {0, 0}, {0, -2 * r3}, {0, -4 * r3}, {3, 3 * r3}, {3, r3}, {3, -r3}, {3, -3 * r3},
            {6, 2 * r3}, {6, 0}, {6, -2 * r3}};

    public static final double sites[][] = {{-7, 3 * r3}, {-8, 2 * r3}, {-7, 1 * r3},
            {-8, 0}, {-7, -1 * r3}, {-8, -2 * r3}, {-7, -3 * r3}, {-4, 4 * r3}, {-5, 3 * r3},
            {-4, 2 * r3}, {-5, 1 * r3}, {-4, 0}, {-5, -1 * r3}, {-4, -2 * r3}, {-5, -3 * r3},
            {-4, -4 * r3}, {-1, 5 * r3}, {-2, 4 * r3}, {-1, 3 * r3}, {-2, 2 * r3}, {-1, 1 * r3},
            {-2, 0}, {-1, -1 * r3}, {-2, -2 * r3}, {-1, -3 * r3}, {-2, -4 * r3}, {-1, -5 * r3},
            {1, 5 * r3}, {2, 4 * r3}, {1, 3 * r3}, {2, 2 * r3}, {1, 1 * r3}, {2, 0}, {1, -1 * r3},
            {2, -2 * r3}, {1, -3 * r3}, {2, -4 * r3}, {1, -5 * r3}, {4, 4 * r3}, {5, 3 * r3},
            {4, 2 * r3}, {5, 1 * r3}, {4, 0}, {5, -1 * r3}, {4, -2 * r3}, {5, -3 * r3}, {4, -4 * r3},
            {7, 3 * r3}, {8, 2 * r3}, {7, 1 * r3}, {8, 0}, {7, -1 * r3}, {8, -2 * r3}, {7, -3 * r3}};

    public static final byte roads[][] = {{0, 1}, {2, 1}, {2, 3}, {4, 3}, {4, 5}, {6, 5},
            {8, 0}, {10, 2}, {12, 4}, {14, 6}, {7, 8}, {9, 8}, {9, 10}, {11, 10}, {11, 12},
            {13, 12}, {13, 14}, {15, 14}, {17, 7}, {19, 9}, {21, 11}, {23, 13}, {25, 15},
            {16, 17}, {18, 17}, {18, 19}, {20, 19}, {20, 21}, {22, 21}, {22, 23}, {24, 23},
            {24, 25}, {26, 25}, {27, 16}, {29, 18}, {31, 20}, {33, 22}, {35, 24}, {37, 26},
            {28, 27}, {28, 29}, {30, 29}, {30, 31}, {32, 31}, {32, 33}, {34, 33}, {34, 35},
            {36, 35}, {36, 37}, {38, 28}, {40, 30}, {42, 32}, {44, 34}, {46, 36}, {39, 38},
            {39, 40}, {41, 40}, {41, 42}, {43, 42}, {43, 44}, {45, 44}, {45, 46}, {47, 39},
            {49, 41}, {51, 43}, {53, 45}, {48, 47}, {48, 49}, {50, 49}, {50, 51}, {52, 51},
            {52, 53}};

    public static final double ports[][] = {{-9, 3*r3}, {-9, -r3}, {-6, -4*r3}, {-3, 5*r3},
            {0, -6*r3}, {3, 5*r3}, {6, -4*r3}, {9, -r3}, {9, 3*r3}};

    public static final double coastline[][] = {{-8, 3.2 * r3}, {-8, 3 * r3}, {-7.4, 3 * r3},
            {-8.2, 2.2 * r3}, {-8.5, 2.5 * r3}, {-8.8, 2.4 * r3}, {-7.4, 1 * r3}, {-8.8, -0.4 * r3},
            {-8.5, -0.5 * r3}, {-8.2, -0.2 * r3}, {-7.5, -0.9 * r3}, {-8.1, -0.9 * r3},
            {-8.1, -1.1 * r3}, {-7.5, -1.1 * r3}, {-8.4, -2 * r3}, {-7.2, -3.2 * r3},
            {-5.4, -3.2 * r3}, {-5.7, -3.5 * r3}, {-5.4, -3.6 * r3}, {-5.1, -3.3 * r3},
            {-4.4, -4 * r3}, {-5, -4 * r3}, {-5, -4.2 * r3}, {-2.2, -4.2 * r3}, {-1.2, -5.2 * r3},
            {-0.8, -5.6 * r3}, {-0.5, -5.5 * r3}, {-0.8, -5.2 * r3}, {0.8, -5.2 * r3},
            {0.5, -5.5 * r3}, {0.8, -5.6 * r3}, {1.2, -5.2 * r3}, {2.2, -4.2 * r3}, {5, -4.2 * r3},
            {5, -4 * r3}, {4.4, -4 * r3}, {5.1, -3.3 * r3}, {5.4, -3.6 * r3}, {5.7, -3.5 * r3},
            {5.4, -3.2 * r3}, {7.2, -3.2 * r3}, {8.4, -2 * r3}, {7.5, -1.1 * r3}, {8.1, -1.1 * r3},
            {8.1, -0.9 * r3}, {7.5, -0.9 * r3}, {8.2, -0.2 * r3}, {8.5, -0.5 * r3}, {8.8, -0.4 * r3},
            {7.4, 1 * r3}, {8.8, 2.4 * r3}, {8.5, 2.5 * r3}, {8.2, 2.2 * r3}, {7.4, 3 * r3}, {8, 3 * r3},
            {8, 3.2 * r3}, {5.2, 3.2 * r3}, {3.8, 4.6 * r3}, {3.5, 4.5 * r3}, {3.8, 4.2 * r3},
            {2.4, 4.2 * r3}, {2.7, 4.5 * r3}, {2.4, 4.6 * r3}, {2.1, 4.3 * r3}, {1.2, 5.2 * r3},
            {-1.2, 5.2 * r3}, {-2.1, 4.3 * r3}, {-2.4, 4.6 * r3}, {-2.7, 4.5 * r3}, {-2.4, 4.2 * r3},
            {-3.8, 4.2 * r3}, {-3.5, 4.5 * r3}, {-3.8, 4.6 * r3}, {-5.2, 3.2 * r3}};

    /**
     * updateABC updates the x,y,z coordinates of intersection of the vector form the
     * camera to the origin with the view plane. Must be updated after the camera rotates
     */
    private void updateABC() {
        a = p * Math.sin(phi * deg) * Math.cos(theta * deg); //x-val of viewplane axis
        b = p * Math.sin(phi * deg) * Math.sin(theta * deg); //y-val of viewplane axis
        c = p * Math.cos(phi * deg); //z-val of viewplane axis (in R3)
        k1 = Math.cos(deg * theta);
        k2 = -1 * Math.sin(deg * theta);
        k3 = -1 * Math.cos(deg * theta) * Math.cos(deg * phi);
        k4 = -1 * Math.sin(deg * theta) * Math.cos(deg * phi);
        k5 = Math.sin(deg * phi);
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
    public double getx(double x, double y, double z) {
        double t = (p * p - x * a - y * b - z * c) / (d * p - x * a - y * b - c * z);
        return (b - y - t * (d * b / p - y)) * k1 + (a - x - t * (d * a / p - x)) * k2;
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
    public double gety(double x, double y, double z) {
        double t = (p * p - x * a - y * b - z * c) / (d * p - x * a - y * b - c * z);
        return (a - x - t * (d * a / p - x)) * k3 + (b - y - t * (d * b / p - y)) * k4 + (c - z - t * (d * c / p - z)) * k5;
    }

    /**
     * rotate the game board 5 degrees to the right
     */
    public void rotateRight() {
        theta += 5;
        if (theta <= -180)
            theta += 360;
        else if (theta > 180)
            theta -= 360;
        updateABC();
    }

    /**
     * rotate the game board 5 degrees to the left
     */
    public void rotateLeft() {
        theta -= 5;
        if (theta <= -180)
            theta += 360;
        else if (theta > 180)
            theta -= 360;
        updateABC();
    }

    /**
     * rotate the camera 5 degrees up
     */
    public void rotateUp() {
        phi -= 5;
        if (phi < 5)
            phi = 5;
        else if (phi > 80)
            phi = 80;
        updateABC();
    }

    /**
     * rotate the camera 5 degrees down
     */
    public void rotateDown() {
        phi += 5;
        if (phi < 5)
            phi = 5;
        else if (phi > 80)
            phi = 80;
        updateABC();
    }
}
