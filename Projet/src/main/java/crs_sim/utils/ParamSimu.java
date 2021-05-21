package crs_sim.utils;

import java.util.ArrayList;

import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;

import crs_sim.body.Building;
import crs_sim.body.CRSBody;
import crs_sim.body.Destroyable;
import crs_sim.body.ProtestorBody;

public class ParamSimu {
	public static final double mapSizeX = 1000;
	public static final double mapSizeY = 1000;
	public static final int mapLeftOffset = 460;
	public static final double maxObjectsPerChild = 4;
	public static final int maxPanic = 30;
	public static final int minAggressive = 70;
	public static final int nbProtestors = 6;
	public static final int PanicForce = 1;
	public static final int NeutralForce = 1;
	public static final int AgressiveForce = 1;
	public static final int BuildingForce = 1;
	public static final int CRSForce = 1;
	public static final int TargetForce = 10;
	public static final int RadiusPerceptProtestor = 100;
<<<<<<< HEAD
	public static final int Speed = 10; //raporter la distance de la speed au temps en seconde d'un tic 
	public static final int PanicOnPanic = 5;
=======
	public static final int RadiusPerceptCrs = 100;
>>>>>>> 4b3bb0c0aa70f7002bbebfd1daa98dac05f079dc

	//objectif neutre
	//objectif agg
	
	public static final  Circle2d[] initCollecProtestor= {
			new Circle2d(375, 375, 1),
			new Circle2d(425, 375, 1),
			new Circle2d(475, 375, 1),
			new Circle2d(500, 375, 1),
			new Circle2d(525, 375, 1),
			new Circle2d(575, 375, 1)
	};
//	public static final ProtestorBody[]  initCollecProtestor = {
//			new ProtestorBody(new Point2d(375, 375)),
//			new ProtestorBody(new Point2d(425, 375)),
//			new ProtestorBody(new Point2d(475, 375))
//	};
	public static final CRSBody[] initCollecCRS = {
			new CRSBody(new Circle2d(375, 600, 1),Types.crs),
			new CRSBody(new Circle2d(425, 600, 1),Types.crs),
			new CRSBody(new Circle2d(475, 600, 1),Types.crs)
	};
//	public static final CRSBody[] initCollecCRS = {
//			new CRSBody(new Point2d(600, 375)),
//			new CRSBody(new Point2d(600, 375)),
//			new CRSBody(new Point2d(600, 375))
//	};
	public static final Building[]  initCollecBuilding = {
			new Building(new Rectangle2d(0,0,1000,100),Types.building),//gros haut
			new Building(new Rectangle2d(0,900,1000,100),Types.building),//gros bas
			new Building(new Rectangle2d(100,200,50,50),Types.building),// petit 1
			new Building(new Rectangle2d(200,200,50,50),Types.building),// petit 2
			new Building(new Rectangle2d(300,200,50,50),Types.building)//petit 3
	};

	public static final Building[]  initplaceDesJacobins = {
			new Building(new Rectangle2d(0,0,300,300),Types.building), // Bat 1
			new Building(new Rectangle2d(400,0,200,300),Types.building), // Bat 2
			new Building(new Rectangle2d(700,0,300,300),Types.building), // Bat 3
			new Building(new Rectangle2d(0,350,300,200),Types.building), // Bat 4
			new Building(new Rectangle2d(0,600,300,50),Types.building), // Bat 5
			new Building(new Rectangle2d(700,375,300,200),Types.building), // Bat 6
			new Building(new Rectangle2d(0,700,300,300),Types.building), // Bat 7
			new Building(new Rectangle2d(350,700,75,300),Types.building), // Bat 8
			new Building(new Rectangle2d(475,700,75,300),Types.building), // Bat 9
			new Building(new Rectangle2d(700,650,300,350),Types.building), // Bat 10
	};
	
	public static final Rectangle2d neutralObj = new Rectangle2d(900,900,30,30); //au pif
	
	public static final Destroyable[] aggObjs = {
			new Destroyable(new Rectangle2d(700,375,300,200),Types.destroyable), // Bat 6
			new Destroyable(new Rectangle2d(0,700,300,300),Types.destroyable), // Bat 7
			new Destroyable(new Rectangle2d(350,700,75,300),Types.destroyable) // Bat 8

	};
}
