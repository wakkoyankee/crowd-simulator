package crs_sim.body;

import crs_sim.body.EnvObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class Building extends EnvObject {
  private final Rectangle2d area;
  
  public Building(final Rectangle2d area) {
    super();
    this.area = area;
  }
  
  public Shape2d<?> getArea() {
    return this.area;
  }
  
  public Point2d getPoition() {
    InputOutput.<String>print("You can\'t ask the position of a Building");
    return null;
  }
  
  public void setArea(final Shape2d<?> area) {
    this.setArea(area);
  }
  
  public void setArea(final int x, final int y) {
    InputOutput.<String>print("It\'s a building.. youcan\'t move buildings bro!");
  }
  
  public void setArea(final Point2d point) {
    InputOutput.<String>print("It\'s a building.. youcan\'t move buildings bro!");
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
}
