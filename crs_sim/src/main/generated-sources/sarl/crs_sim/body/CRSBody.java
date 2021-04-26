package crs_sim.body;

import crs_sim.body.MobileObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import org.arakhne.afc.math.geometry.d2.d.Point2d;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class CRSBody extends MobileObject {
  public CRSBody(final Point2d position) {
    this.setPosition(position);
  }
  
  public void getArea() {
    throw new Error("Unresolved compilation problems:"
      + "\nVoid functions cannot return a value.");
  }
}
