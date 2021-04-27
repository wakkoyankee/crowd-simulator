package crs_sim.utils;

import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;

import crs_sim.body.Building;
import crs_sim.body.CRSBody;
import crs_sim.body.ProtestorBody;

public class ParamSimu {
	public static final double mapSizeX = 1000;
	public static final double mapSizeY = 1000;
	public static final double maxObjectsPerChild = 4;
	/*public static final CRSBody[] initCollecCRS = {
			new CRSBody(new Point2d(10, 10)),
			new CRSBody(new Point2d(10, 20)),
			new CRSBody(new Point2d(10, 30))
	};
	public static final ProtestorBody[]  initCollecProtestor = {
			new ProtestorBody(new Point2d(50, 10)),
			new ProtestorBody(new Point2d(50, 20)),
			new ProtestorBody(new Point2d(50, 30))
	};
	public static final Building[]  initCollecBuilding = {
			new Building(new Rectangle2d(100,100,40,20)),
			new Building(new Rectangle2d(100,200,40,20)),
			new Building(new Rectangle2d(100,300,40,20))
	};*/
}
