package crs_sim.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;

import crs_sim.environment.Percept;
import crs_sim.utils.ParamSimu;
import crs_sim.utils.Types;

public class Window {
	public JFrame _win;
	public Panel _pan;
	// one label instance per body
	JLabel[] labelCRS = new JLabel[ParamSimu.nbProtestors + ParamSimu.nbCRS];
	JLabel[] labelGJPanic = new JLabel[ParamSimu.nbProtestors + ParamSimu.nbCRS];
	JLabel[] labelGJNeutral = new JLabel[ParamSimu.nbProtestors + ParamSimu.nbCRS];
	JLabel[] labelGJAgg = new JLabel[ParamSimu.nbProtestors + ParamSimu.nbCRS];

	public Window() {

		URL crsURL = null;
		URL gjPanicURL= null;
		URL gjNeutralURL= null;
		URL gjAggURL= null;
		Icon iconGJPanic= null;
		Icon iconGJNeutral= null;
		Icon iconGJAgg= null;
		Icon iconCRS= null;
		
		this._win = new JFrame();
		this._win.setTitle("CRS simulator");
		this._win.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this._win.setLocationRelativeTo(null);
		this._win.setVisible(true);
		this._pan = new Panel((int)ParamSimu.mapSizeX, (int)ParamSimu.mapSizeY);
		this._win.add(_pan);
		
		
		try {
			crsURL = new File("C:\\Users\\carte\\Desktop\\crowd-simulator\\Projet\\src\\main\\java\\crs_sim\\texturePack\\test.gif").toURI().toURL();
			gjPanicURL = new File("C:\\Users\\carte\\Desktop\\crowd-simulator\\Projet\\src\\main\\java\\crs_sim\\texturePack\\test.gif").toURI().toURL();
			gjNeutralURL = new File("C:\\Users\\carte\\Desktop\\crowd-simulator\\Projet\\src\\main\\java\\crs_sim\\texturePack\\test.gif").toURI().toURL();
			gjAggURL = new File("C:\\Users\\carte\\Desktop\\crowd-simulator\\Projet\\src\\main\\java\\crs_sim\\texturePack\\test.gif").toURI().toURL();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		iconCRS = new ImageIcon(crsURL);
		iconGJPanic = new ImageIcon(gjPanicURL);
		iconGJNeutral = new ImageIcon(gjNeutralURL);
		iconGJAgg = new ImageIcon(gjAggURL);
	    for(int i = 0; i<(ParamSimu.nbCRS+ParamSimu.nbProtestors); i++) {
	    	labelCRS[i] = new JLabel(iconCRS);
	    	labelGJPanic[i] = new JLabel(iconGJPanic);
	    	labelGJNeutral[i] = new JLabel(iconGJNeutral);
	    	labelGJAgg[i] = new JLabel(iconGJAgg);
	    	labelCRS[i].setVisible(false);
	    	labelGJPanic[i].setVisible(false);
	    	labelGJNeutral[i].setVisible(false);
	    	labelGJAgg[i].setVisible(false);
    	    _pan.add(labelCRS[i]);
    	    _pan.add(labelGJPanic[i]);
    	    _pan.add(labelGJNeutral[i]);
    	    _pan.add(labelGJAgg[i]);
	    }
		
	}
	
	public void update(List<Percept> bodies) {
		this._pan.setBodies(bodies);
		this._pan.updateUI();
	}
	
	class Panel extends JPanel {
		
		public List<Percept> bodies = null;

        Panel(int width, int height) { // l'ordre est peut etre pas bon
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(width,height));
        }

        @Override
        public void paintComponent(Graphics g) {
        	int i = 0;
            super.paintComponent(g);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(ParamSimu.mapLeftOffset,0,(int) ParamSimu.mapSizeX,(int) ParamSimu.mapSizeY);
            
    		if(bodies != null) {
	            for(Percept body : bodies) {
	            	
	            	if(body.getName() == Types.building) {
	            		g.setColor(Color.BLACK); 
	            		Rectangle2d r = (Rectangle2d)body.getShape();
	            		g.fillRect((int) r.getMinX() + ParamSimu.mapLeftOffset,(int) r.getMinY(),(int) (r.getMaxX()-r.getMinX()),(int) (r.getMaxY()-r.getMinY()));
	            	}else if(body.getName() == Types.crs) {
	            		Circle2d c = (Circle2d) body.getShape();
	            		g.setColor(Color.BLUE); 
	            		g.fillOval((int)c.getX() + ParamSimu.mapLeftOffset - 5,(int) c.getY() - 5,10,10);
	//            		labelCRS[i].setVisible(true);
	//            		labelCRS[i].setLocation((int)(c.getX() + ParamSimu.mapLeftOffset), (int) c.getY());
	            	}else if(body.getName() == Types.protestor_agg) {
	            		Circle2d c = (Circle2d) body.getShape();
	            		g.setColor(Color.RED); 
	            		g.fillOval((int)(c.getX() + ParamSimu.mapLeftOffset - 5),(int) c.getY() - 5,10,10);
	//            		labelGJPanic[i].setVisible(false);
	//            		labelGJNeutral[i].setVisible(false);
	//            		labelGJAgg[i].setVisible(true);
	//            		labelGJAgg[i].setLocation((int)(c.getX() + ParamSimu.mapLeftOffset), (int) c.getY());
	            	}else if(body.getName() == Types.protestor_panic) {
	            		Circle2d c = (Circle2d) body.getShape();
	            		g.setColor(Color.YELLOW); 
	            		g.fillOval((int)(c.getX() + ParamSimu.mapLeftOffset - 5),(int) c.getY() - 5,10,10);
	//            		labelGJAgg[i].setVisible(false);
	//            		labelGJNeutral[i].setVisible(false);
	//            		labelGJPanic[i].setVisible(true);
	//            		labelGJPanic[i].setLocation((int) c.getX() + ParamSimu.mapLeftOffset, (int) c.getY());
	            	}else if(body.getName() == Types.protestor_neutral){//protestor neutral
	            		Circle2d c = (Circle2d) body.getShape();
	            		g.setColor(Color.GREEN); 
	            		g.fillOval((int)(c.getX() + ParamSimu.mapLeftOffset - 5),(int) c.getY() - 5,10,10);
	//            		labelGJAgg[i].setVisible(false);
	//            		labelGJPanic[i].setVisible(false);
	//            		labelGJNeutral[i].setVisible(true);
	//            		labelGJNeutral[i].setLocation((int)(c.getX() + ParamSimu.mapLeftOffset), (int) c.getY());
	            	}else if(body.getName() == Types.destroyable) {
	            		g.setColor(Color.RED); 
	            		Rectangle2d r = (Rectangle2d) body.getShape();
	            		g.fillRect((int) r.getMinX() + ParamSimu.mapLeftOffset,(int) r.getMinY(),(int) (r.getMaxX()-r.getMinX()),(int) (r.getMaxY()-r.getMinY()));
	            	}
	            	i++;
	            }
    		}
            /*g.setColor(Color.GREEN);
            g.fillRect((int) (ParamSimu.mapLeftOffset + ParamSimu.neutralObj.getMinX()),
            		(int) (ParamSimu.neutralObj.getMinY()),
            		(int) (ParamSimu.neutralObj.getMaxX() - ParamSimu.neutralObj.getMinX())
            		,(int) (ParamSimu.neutralObj.getMaxY() - ParamSimu.neutralObj.getMinY()) );*/
                   
        }
        
        public void setBodies(List<Percept> bodies) {
        	this.bodies = bodies;
        }
    }
	
}


