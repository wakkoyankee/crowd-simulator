package crs_sim.utils;

import java.util.List;

import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.json.JSONObject;

import crs_sim.environment.Percept;
import crs_sim.environment.QTNode;

public class jsonGenerator_Utils {
	public static boolean jsonGenerator(QTNode rootTree) {
		List<Percept> results = GraphSearch_Utils.BFS(rootTree);
		JSONObject json = new JSONObject();
		for(Percept result : results) {
			if(result.getName() == Types.building || result.getName() == Types.destroyable)
				json.put(result.getName().toString(), 
						((Rectangle2d) result.getShape()).getMinX() +
						((Rectangle2d) result.getShape()).getMinY() +
						((Rectangle2d) result.getShape()).getMaxX() +
						((Rectangle2d) result.getShape()).getMaxY() );
			else
				json.put(result.getName().toString(), 
					((Circle2d) result.getShape()).getCenterX() +
					((Circle2d) result.getShape()).getCenterY() );
		}
		System.out.println(json.toString());
		return true;
	}

}
