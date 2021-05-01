package crs_sim.ui;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import crs_sim.utils.ParamSimu;

public class Window {
	public JFrame _win;
	public JPanel _pan;

	public Window() {
		this._win = new JFrame();
		this._win.setTitle("CRS simulator");
		this._win.setSize((int)ParamSimu.mapSizeX, (int)ParamSimu.mapSizeY);
		this._win.setLocationRelativeTo(null);
		this._win.setVisible(true);
		
		this._pan = new Panel((int)ParamSimu.mapSizeX, (int)ParamSimu.mapSizeY);
		this._win.add(_pan);		
	}
	
	class Panel extends JPanel {

        Panel(int width, int height) { // l'ordre est peut etre pas bon
            // set a preferred size for the custom panel.
            setPreferredSize(new Dimension(width,height));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(100, 200, 400, 400);
        }
    }
	
}


