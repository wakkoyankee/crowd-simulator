package crs_sim.utils;

import java.util.ArrayList;

import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;

import crs_sim.body.Building;
import crs_sim.body.CRSBody;
import crs_sim.body.ProtestorBody;

public class ParamSimu {
	public static final double mapSizeX = 1000;
	public static final double mapSizeY = 1000;
	public static final double maxObjectsPerChild = 4;
	public static final int maxPanic = 30;
	public static final int minAggressive = 70;
	public static final int nbProtestors = 3;

	
	public static final  Circle2d[] initCollecProtestor= {
			new Circle2d(375, 375, 1),
			new Circle2d(425, 375, 1),
			new Circle2d(475, 375, 1)
	};
//	public static final ProtestorBody[]  initCollecProtestor = {
//			new ProtestorBody(new Point2d(375, 375)),
//			new ProtestorBody(new Point2d(425, 375)),
//			new ProtestorBody(new Point2d(475, 375))
//	};
	public static final CRSBody[] initCollecCRS = {
			new CRSBody(new Circle2d(375, 600, 1)),
			new CRSBody(new Circle2d(425, 600, 1)),
			new CRSBody(new Circle2d(475, 600, 1))
	};
//	public static final CRSBody[] initCollecCRS = {
//			new CRSBody(new Point2d(600, 375)),
//			new CRSBody(new Point2d(600, 375)),
//			new CRSBody(new Point2d(600, 375))
//	};
	public static final Building[]  initCollecBuilding = {
			new Building(new Rectangle2d(0,0,1000,100)),//gros haut
			new Building(new Rectangle2d(0,900,1000,100)),//gros bas
			new Building(new Rectangle2d(100,200,50,50)),// petit 1
			new Building(new Rectangle2d(200,200,50,50)),// petit 2
			new Building(new Rectangle2d(300,200,50,50))//petit 3
	};

	public static final Building[]  initplaceDesJacobins = {
			new Building(new Rectangle2d(0,0,300,300)), // Bat 1
			new Building(new Rectangle2d(400,0,200,300)), // Bat 2
			new Building(new Rectangle2d(700,0,300,300)), // Bat 3
			new Building(new Rectangle2d(0,350,300,200)), // Bat 4
			new Building(new Rectangle2d(0,600,300,50)), // Bat 5
			new Building(new Rectangle2d(700,375,300,200)), // Bat 6
			new Building(new Rectangle2d(0,700,300,300)), // Bat 7
			new Building(new Rectangle2d(350,700,75,300)), // Bat 8
			new Building(new Rectangle2d(475,700,75,300)), // Bat 9
			new Building(new Rectangle2d(700,650,300,350)), // Bat 10
	};
	
	public static ArrayList<Rectangle2d> Destroyables; //Besoin d'une liste dynamique ici
	public static Point2d NeutralObjective = new Point2d(300,300);
	
	
	
	
	
}
