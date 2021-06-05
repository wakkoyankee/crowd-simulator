package crs_sim.utils;

import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;

import crs_sim.body.Building;
import crs_sim.body.CRSBody;
import crs_sim.body.Destroyable;

public class ParamSimu {
	public static final double mapSizeX = 1000;
	public static final double mapSizeY = 1000;
	public static final int mapLeftOffset = 460;
	public static final double maxObjectsPerChild = 5;
	public static final int maxPanic = 10;
	public static final int minAggressive = 90;
	public static final int nbProtestors = 40;
	public static final int nbCRS = 3;
	
	public static final int BuildingForce = 10;
	
	public static final int PanicOnPanic = 5;
	public static final int NeutralOnPanic = 1;
	public static final int AgressiveOnPanic = 1;
	public static final int CRSOnPanic = 3;
	public static final int TargetPanic = 5;
	
	public static final int PanicOnNeutral = 5;
	public static final int NeutralOnNeutral = 1;
	public static final int AgressiveOnNeutral = 1;
	public static final int CRSOnNeutral = 3;
	public static final int TargetNeutral = 5;
	
	public static final int DestroyableOnAgressive = 1;
	public static final int PanicOnAgressive = 5;
	public static final int NeutralOnAgressive = 1;
	public static final int AgressiveOnAgressive = 1;
	public static final int CRSOnAgressive = 3;
	public static final int TargetAgressive = 5;
	
	public static final int RadiusPerceptProtestor = 100;
	
	public static final int PanicOnCrs = 5;
	public static final int NeutralOnCrs = 1;
	public static final int AgressiveOnCrs = 1;
	public static final int CRSOnCrs = 3;
	public static final int TargetCrs = 5;
	public static final int RadiusPerceptCrs = 100;
	
	public static final double Speed = 0.4; //raporter la distance de la speed au temps en seconde d'un tic 1 TICK = 0.5 sec
	
	

	//objectif neutre
	//objectif agg
	
	public static final  Circle2d[] initCollecProtestor= {
//			//rt JT
			new Circle2d(950, 320, 1),
			new Circle2d(960, 320, 1),
			new Circle2d(970, 320, 1),
			new Circle2d(980, 320, 1),
			new Circle2d(990, 320, 1),
			// rt AP
			new Circle2d(50, 320, 1),
			new Circle2d(60, 320, 1),
			new Circle2d(70, 320, 1),
			new Circle2d(80, 320, 1),
			new Circle2d(90, 320, 1),
//			// rt b
			new Circle2d(310, 50, 1),
			new Circle2d(315, 50, 1),
			new Circle2d(320, 50, 1),
			new Circle2d(325, 50, 1),
			new Circle2d(330, 50, 1),
//			// rt PEH
//			new Circle2d(610, 50, 1),
//			new Circle2d(615, 50, 1),
//			new Circle2d(620, 50, 1),
//			new Circle2d(625, 50, 1),
//			new Circle2d(630, 50, 1),
//			new Circle2d(635, 50, 1),
//			new Circle2d(640, 50, 1),
//			new Circle2d(645, 50, 1),
//			new Circle2d(650, 50, 1),
//			new Circle2d(655, 50, 1),
//			new Circle2d(660, 50, 1),
//			new Circle2d(665, 50, 1),
//			new Circle2d(670, 50, 1),
//			new Circle2d(675, 50, 1),
//			new Circle2d(680, 50, 1),
//			new Circle2d(685, 50, 1),
//			new Circle2d(690, 50, 1),
//			new Circle2d(695, 50, 1),
//			new Circle2d(610, 75, 1),
//			new Circle2d(615, 75, 1),
//			new Circle2d(620, 75, 1),
//			new Circle2d(625, 75, 1),
//			new Circle2d(630, 75, 1),
//			new Circle2d(635, 75, 1),
//			new Circle2d(640, 75, 1),
//			new Circle2d(645, 75, 1),
//			new Circle2d(650, 75, 1),
//			new Circle2d(655, 75, 1),
//			new Circle2d(660, 75, 1),
//			new Circle2d(665, 75, 1),
//			new Circle2d(670, 75, 1),
//			new Circle2d(675, 75, 1),
//			new Circle2d(680, 75, 1),
//			new Circle2d(685, 75, 1),
//			new Circle2d(690, 75, 1),
//			new Circle2d(695, 75, 1),
//			new Circle2d(610, 100, 1),
//			new Circle2d(615, 100, 1),
//			new Circle2d(620, 100, 1),
//			new Circle2d(625, 100, 1),
//			new Circle2d(630, 100, 1),
//			new Circle2d(635, 100, 1),
//			new Circle2d(640, 100, 1),
//			new Circle2d(645, 100, 1),
//			new Circle2d(650, 100, 1),
//			new Circle2d(655, 100, 1),
//			new Circle2d(660, 100, 1),
//			new Circle2d(665, 100, 1),
//			new Circle2d(670, 100, 1),
//			new Circle2d(675, 100, 1),
//			new Circle2d(680, 100, 1),
//			new Circle2d(685, 100, 1),
//			new Circle2d(690, 100, 1),
//			new Circle2d(695, 100, 1),
//			new Circle2d(610, 150, 1),
//			new Circle2d(615, 150, 1),
//			new Circle2d(620, 150, 1),
//			new Circle2d(625, 150, 1),
//			new Circle2d(630, 150, 1),
//			new Circle2d(635, 150, 1),
//			new Circle2d(640, 150, 1),
//			new Circle2d(645, 150, 1),
//			new Circle2d(650, 150, 1),
//			new Circle2d(655, 150, 1),
//			new Circle2d(660, 150, 1),
//			new Circle2d(665, 150, 1),
//			new Circle2d(670, 150, 1),
//			new Circle2d(675, 150, 1),
//			new Circle2d(680, 150, 1),
//			new Circle2d(685, 150, 1),
//			new Circle2d(690, 150, 1),
//			new Circle2d(695, 150, 1),
//			new Circle2d(610, 200, 1),
//			new Circle2d(615, 200, 1),
//			new Circle2d(620, 200, 1),
//			new Circle2d(625, 200, 1),
//			new Circle2d(630, 200, 1),
//			new Circle2d(635, 200, 1),
//			new Circle2d(640, 200, 1),
//			new Circle2d(645, 200, 1),
//			new Circle2d(650, 200, 1),
//			new Circle2d(655, 200, 1),
//			new Circle2d(660, 200, 1),
//			new Circle2d(665, 200, 1),
//			new Circle2d(670, 200, 1),
//			new Circle2d(675, 200, 1),
//			new Circle2d(680, 200, 1),
//			new Circle2d(685, 200, 1),
//			new Circle2d(690, 200, 1),
//			new Circle2d(695, 200, 1),
//			new Circle2d(610, 250, 1),
//			new Circle2d(615, 250, 1),
//			new Circle2d(620, 250, 1),
//			new Circle2d(625, 250, 1),
//			new Circle2d(630, 250, 1),
//			new Circle2d(635, 250, 1),
//			new Circle2d(640, 250, 1),
//			new Circle2d(645, 250, 1),
//			new Circle2d(650, 250, 1),
//			new Circle2d(655, 250, 1),
//			new Circle2d(660, 250, 1),
//			new Circle2d(665, 250, 1),
//			new Circle2d(670, 250, 1),
//			new Circle2d(675, 250, 1),
//			new Circle2d(680, 250, 1),
//			new Circle2d(685, 250, 1),
//			new Circle2d(690, 250, 1),
//			new Circle2d(695, 250, 1),
//			new Circle2d(610, 300, 1),
//			new Circle2d(615, 300, 1),
//			new Circle2d(620, 300, 1),
//			new Circle2d(625, 300, 1),
//			new Circle2d(630, 300, 1),
//			new Circle2d(635, 300, 1),
//			new Circle2d(640, 300, 1),
//			new Circle2d(645, 300, 1),
//			new Circle2d(650, 300, 1),
//			new Circle2d(655, 300, 1),
//			new Circle2d(660, 300, 1),
//			new Circle2d(665, 300, 1),
//			new Circle2d(670, 300, 1),
//			new Circle2d(675, 300, 1),
//			new Circle2d(680, 300, 1),
//			new Circle2d(685, 300, 1),
//			new Circle2d(690, 300, 1),
//			new Circle2d(695, 300, 1),
			// place
			new Circle2d(375, 375, 1),
			new Circle2d(425, 375, 1),
			new Circle2d(475, 375, 1),
			new Circle2d(525, 375, 1),
			new Circle2d(575, 375, 1),
			new Circle2d(625, 375, 1),
			new Circle2d(375, 425, 1),
			new Circle2d(425, 425, 1),
			new Circle2d(475, 425, 1),
			new Circle2d(525, 425, 1),
			new Circle2d(575, 425, 1),
			new Circle2d(625, 425, 1),
			new Circle2d(375, 475, 1),
			new Circle2d(425, 475, 1),
			new Circle2d(475, 475, 1),
			new Circle2d(525, 475, 1),
			new Circle2d(575, 475, 1),
			new Circle2d(625, 475, 1),
			new Circle2d(375, 525, 1),
			new Circle2d(425, 525, 1),
			new Circle2d(475, 525, 1),
			new Circle2d(525, 525, 1),
			new Circle2d(575, 525, 1),
			new Circle2d(625, 525, 1),
			new Circle2d(375, 575, 1),
			new Circle2d(425, 575, 1),
			new Circle2d(475, 575, 1),
			new Circle2d(525, 575, 1),
			new Circle2d(575, 575, 1),
			new Circle2d(625, 575, 1)
	};
//	public static final ProtestorBody[]  initCollecProtestor = {
//			new ProtestorBody(new Point2d(375, 375)),
//			new ProtestorBody(new Point2d(425, 375)),
//			new ProtestorBody(new Point2d(475, 375))
//	};
	public static final CRSBody[] initCollecCRS = {
			//new CRSBody(new Circle2d(375, 600, 1),Types.crs),
			new CRSBody(new Circle2d(500, 500, 1),Types.crs),
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
			new Building(new Rectangle2d(750,375,250,200),Types.building), // Bat 6
			new Building(new Rectangle2d(0,700,300,300),Types.building), // Bat 7
			new Building(new Rectangle2d(350,700,75,300),Types.building), // Bat 8
			new Building(new Rectangle2d(475,700,75,300),Types.building), // Bat 9
			new Building(new Rectangle2d(700,650,300,350),Types.building), // Bat 10
	};
	
	public static final Rectangle2d neutralObj = new Rectangle2d(550,970,150,30); //au pif
	
	public static final Destroyable[] aggObjs = {
			new Destroyable(new Rectangle2d(300,600,25,75),Types.destroyable), 
			new Destroyable(new Rectangle2d(450,700,75,25),Types.destroyable)  

	};
}
