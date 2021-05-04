package crs_sim.ui;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import crs_sim.environment.Percept;
import crs_sim.utils.ParamSimu;

public class Window {
	public JFrame _win;
	public Panel _pan;

	public Window() {
		this._win = new JFrame();
		this._win.setTitle("CRS simulator");
		this._win.setSize((int)ParamSimu.mapSizeX, (int)ParamSimu.mapSizeY);
		this._win.setLocationRelativeTo(null);
		this._win.setVisible(true);
		
		this._pan = new Panel((int)ParamSimu.mapSizeX, (int)ParamSimu.mapSizeY);
		this._win.add(_pan);
	}
	
	public void update(ArrayList<Percept> bodies) {
		this._pan.setBodies(bodies);
		this._pan.updateUI();
	}
	
	class Panel extends JPanel {
		
		public ArrayList<Percept> bodies;

        Panel(int width, int height) { // l'ordre est peut etre pas bon
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(width,height));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            //for all bodies in arraylist
            // if rectangle
            //g.setColor(Color.RED);  
            //g.fillRect(230,80,10,10); 
            //else circle
            //g.fillOval(c.x, c.y, c.diameter, c.diameter)
            
            
            g.drawRect(100, 200, 400, 400);
        }
        
        public void setBodies(ArrayList<Percept> bodies) {
        	this.bodies = bodies;
        }
    }
	
}


