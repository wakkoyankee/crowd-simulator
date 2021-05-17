package crs_sim.ui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
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

	public Window() {
		this._win = new JFrame();
		this._win.setTitle("CRS simulator");
//		this._win.setSize((int)ParamSimu.mapSizeX, (int)ParamSimu.mapSizeY);
		this._win.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this._win.setLocationRelativeTo(null);
		this._win.setVisible(true);
		
		this._pan = new Panel((int)ParamSimu.mapSizeX, (int)ParamSimu.mapSizeY);
		this._win.add(_pan);
	}
	
	public void update(List<Percept> bodies) {
		this._pan.setBodies(bodies);
		this._pan.updateUI();
	}
	
	class Panel extends JPanel {
		
		public List<Percept> bodies;
		URL crsURL;
		URL gjURL;

        Panel(int width, int height) { // l'ordre est peut etre pas bon
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(width,height));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(ParamSimu.mapLeftOffset,0,(int) ParamSimu.mapSizeX,(int) ParamSimu.mapSizeY);
           
            try {
				crsURL = new File("texturePack/CRS.gif").toURI().toURL();
            	gjURL = new File("texturePack/GJ.gif").toURI().toURL();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
            Icon icon = new ImageIcon(crsURL);
    	    JLabel label = new JLabel(icon);
    	    label.setBounds(0, 0, 50, 50);
    	    _pan.add(label);
    	    
            for(Percept body : bodies) {
//            	if(body.getShape() instanceof Rectangle2d) {
//            		g.setColor(Color.BLACK); 
//            		Rectangle2d r = (Rectangle2d)body.getShape();
//            		g.fillRect((int) r.getMinX() + ParamSimu.mapLeftOffset,(int) r.getMinY(),(int) (r.getMaxX()-r.getMinX()),(int) (r.getMaxY()-r.getMinY()));
//            	}else if(body.getShape() instanceof Circle2d) {
//            		g.setColor(Color.BLUE); 
//            		Circle2d c = (Circle2d) body.getShape();
//            		g.fillOval((int)c.getX() + ParamSimu.mapLeftOffset,(int) c.getY(),10,10);
//            	}
//            	
//            	
            	
            	if(body.getName() == Types.building) {
            		g.setColor(Color.BLACK); 
            		Rectangle2d r = (Rectangle2d)body.getShape();
            		g.fillRect((int) r.getMinX() + ParamSimu.mapLeftOffset,(int) r.getMinY(),(int) (r.getMaxX()-r.getMinX()),(int) (r.getMaxY()-r.getMinY()));
            	}else if(body.getName() == Types.crs) {
            		g.setColor(Color.BLUE); 
            		Circle2d c = (Circle2d) body.getShape();
            		g.fillOval((int)c.getX() + ParamSimu.mapLeftOffset,(int) c.getY(),10,10);
            	}else if(body.getName() == Types.protestor_agg) {
            		g.setColor(Color.RED); 
            		Circle2d c = (Circle2d) body.getShape();
            		g.fillOval((int)c.getX() + ParamSimu.mapLeftOffset,(int) c.getY(),10,10);
            	}else if(body.getName() == Types.protestor_panic) {
            		g.setColor(Color.YELLOW); 
            		Circle2d c = (Circle2d) body.getShape();
            		g.fillOval((int)c.getX() + ParamSimu.mapLeftOffset,(int) c.getY(),10,10);
            	}else if(body.getName() == Types.protestor_neutral){//protestor neutral
            		g.setColor(Color.PINK); 
            		Circle2d c = (Circle2d) body.getShape();
            		g.fillOval((int)c.getX() + ParamSimu.mapLeftOffset,(int) c.getY(),10,10);
            	}
            	
            }
            
        }
        
        public void setBodies(List<Percept> bodies) {
        	this.bodies = bodies;
        }
    }
	
}


