package crs_sim.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.json.simple.JSONObject;

import crs_sim.environment.Percept;
import crs_sim.environment.QTNode;

public class JsonGenerator_Utils {
	public static boolean jsonGenerator(QTNode rootTree) {
		List<Percept> results = GraphSearch_Utils.BFS(rootTree);
		JSONObject json = new JSONObject();
		JSONObject builder;
		for(Percept result : results) {
			builder = new JSONObject();
			if(result.getName() == Types.building || result.getName() == Types.destroyable) {
				builder.put("Type", result.getName());
				builder.put("MinX", ((Rectangle2d) result.getShape()).getMinX() );
				builder.put("MinY", ((Rectangle2d) result.getShape()).getMinY() );
				builder.put("MaxX", ((Rectangle2d) result.getShape()).getMaxX() );
				builder.put("MaxY", ((Rectangle2d) result.getShape()).getMaxY() );

				json.put(result.getUuid().toString(), builder);
			}else {
				builder.put("Type", result.getName());
				builder.put("X", ((Circle2d) result.getShape()).getCenterX() );
				builder.put("Y", ((Circle2d) result.getShape()).getCenterY() );
			
				json.put(result.getUuid().toString(),builder);
			}
		}
//		System.out.println(json.toString());
			
		//Write JSON file
        try (FileWriter file = new FileWriter("display.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(json.toJSONString()); 
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }
        
		return true;
	}
}

////JSON parser object to parse read file
//JSONParser jsonParser = new JSONParser();
// 
//try (FileReader reader = new FileReader("employees.json"))
//{
//    //Read JSON file
//    Object obj = jsonParser.parse(reader);
//
//    JSONArray employeeList = (JSONArray) obj;
//    System.out.println(employeeList);
//     
//    //Iterate over employee array
//    employeeList.forEach( emp -> parseEmployeeObject( (JSONObject) emp ) );
//
//} catch (FileNotFoundException e) {
//    e.printStackTrace();
//} catch (IOException e) {
//    e.printStackTrace();
//} catch (ParseException e) {
//    e.printStackTrace();
//}
//}
//
//private static void parseEmployeeObject(JSONObject employee) 
//{
////Get employee object within list
//JSONObject employeeObject = (JSONObject) employee.get("employee");
// 
////Get employee first name
//String firstName = (String) employeeObject.get("firstName");    
//System.out.println(firstName);
// 
////Get employee last name
//String lastName = (String) employeeObject.get("lastName");  
//System.out.println(lastName);
// 
////Get employee website name
//String website = (String) employeeObject.get("website");    
//System.out.println(website);
//}