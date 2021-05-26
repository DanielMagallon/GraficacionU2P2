package lib.staticlass;

import java.awt.geom.GeneralPath;


public class ShapePoints
{
    public static GeneralPath gp1;
    static {
        gp1=new GeneralPath();
        gp1.moveTo(150,186);
        gp1.lineTo(183,179);
        gp1.lineTo(209,174);
        gp1.lineTo(220,173);
        gp1.lineTo(241,172);
        gp1.lineTo(252,174);
        gp1.lineTo(267,178);
        gp1.lineTo(278,184);
        gp1.lineTo(289,191);
        gp1.lineTo(300,199);
        gp1.lineTo(309,209);
        gp1.lineTo(325,223);
        gp1.lineTo(333,236);
        gp1.lineTo(341,228);
        gp1.lineTo(352,222);
        gp1.lineTo(363,219);
        gp1.lineTo(374,218);
        gp1.lineTo(395,220);
        gp1.lineTo(413,220);
        gp1.lineTo(411,227);
        gp1.lineTo(401,230);
        gp1.lineTo(401,239);
        gp1.lineTo(400,249);
        gp1.lineTo(397,255);
        gp1.lineTo(392,260);
        gp1.lineTo(365,260);
        gp1.lineTo(389,266);
        gp1.lineTo(403,265);
        gp1.lineTo(423,263);
        gp1.lineTo(442,264);
        gp1.lineTo(457,273);
        gp1.lineTo(472,283);
        gp1.lineTo(483,292);
        gp1.lineTo(493,305);
        gp1.lineTo(510,323);
        gp1.lineTo(523,345);
        gp1.lineTo(533,369);
        gp1.lineTo(543,410);
        gp1.lineTo(530,413);
        gp1.lineTo(526,408);
        gp1.lineTo(518,408);
        gp1.lineTo(510,406);
        gp1.lineTo(492,395);
        gp1.lineTo(482,384);
        gp1.lineTo(475,379);
        gp1.lineTo(467,374);
        gp1.lineTo(462,366);
        gp1.lineTo(452,365);
        gp1.lineTo(443,355);
        gp1.lineTo(433,353);
        gp1.lineTo(423,346);
        gp1.lineTo(416,344);
        gp1.lineTo(409,343);
        gp1.lineTo(404,338);
        gp1.lineTo(394,338);
        gp1.lineTo(384,333);
        gp1.lineTo(379,329);
        gp1.lineTo(369,330);
        gp1.lineTo(357,323);
        gp1.lineTo(333,323);
        gp1.lineTo(338,328);
        gp1.lineTo(328,331);
        gp1.lineTo(311,338);
        gp1.lineTo(294,343);
        gp1.lineTo(273,347);
        gp1.lineTo(251,371);
        gp1.lineTo(236,394);
        gp1.lineTo(222,420);
        gp1.lineTo(223,406);
        gp1.lineTo(220,390);
        gp1.lineTo(214,382);
        gp1.lineTo(203,379);
        gp1.lineTo(193,376);
        gp1.lineTo(169,372);
        gp1.lineTo(179,367);
        gp1.lineTo(189,358);
        gp1.lineTo(202,351);
        gp1.lineTo(213,344);
        gp1.lineTo(225,336);
        gp1.lineTo(243,329);
        gp1.lineTo(249,317);
        gp1.lineTo(235,305);
        gp1.lineTo(262,294);
        gp1.lineTo(268,288);
        gp1.lineTo(262,282);
        gp1.lineTo(259,273);
        gp1.lineTo(252,270);
        gp1.lineTo(253,262);
        gp1.lineTo(248,257);
        gp1.lineTo(249,249);
        gp1.lineTo(242,246);
        gp1.lineTo(242,238);
        gp1.lineTo(233,237);
        gp1.lineTo(230,230);
        gp1.lineTo(219,230);
        gp1.lineTo(218,224);
        gp1.lineTo(201,223);
        gp1.lineTo(196,216);
        gp1.lineTo(184,216);
        gp1.lineTo(175,210);
        gp1.lineTo(167,205);
        gp1.lineTo(159,198);
        gp1.lineTo(151,194);
        gp1.lineTo(150,186);
        
    }

    //aerosnith walk this

    public static boolean isInRange(int[] points,int[] clickPoints)
    {
        return (clickPoints[0]>=points[0] && clickPoints[0]<=points[1]) &&
                (clickPoints[1]>=points[2] && clickPoints[1]<=points[3]);
    }
}