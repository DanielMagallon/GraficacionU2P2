package lib.staticlass;

import java.awt.geom.GeneralPath;

public class ShapePoints
{
    public static GeneralPath gp1;
    static {
        gp1=new GeneralPath();
        gp1.moveTo(150, 180);
        gp1.lineTo(200, 180);
        gp1.lineTo(200, 250);
        gp1.curveTo(200, 250, 270, 300, 200, 350);
        gp1.lineTo(150, 350);
        gp1.curveTo(150, 350, 80, 300, 150, 250);
        gp1.moveTo(150, 180);
        gp1.closePath();
    }

    //aerosnith walk this

    public static boolean isInRange(int[] points,int[] clickPoints)
    {
        return (clickPoints[0]>=points[0] && clickPoints[0]<=points[1]) &&
                (clickPoints[1]>=points[2] && clickPoints[1]<=points[3]);
    }
}
