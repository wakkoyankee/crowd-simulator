package crs_sim.body;

import crs_sim.body.MobileObject;
import crs_sim.utils.Types;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class ProtestorBody extends MobileObject {
  private Circle2d position;
  
  @Accessors
  private Types protestorType;
  
  public ProtestorBody(final Circle2d position, final Types protestorType) {
    this.position = position;
    this.protestorType = protestorType;
  }
  
  public ProtestorBody(final Circle2d position) {
    this.position = position;
  }
  
  public Shape2d<?> getArea() {
    return this.position;
  }
  
  public Point2d getPoition() {
    double _centerX = this.position.getCenterX();
    double _centerY = this.position.getCenterY();
    return new Point2d(_centerX, _centerY);
  }
  
  public void setArea(final Shape2d<?> area) {
    this.setArea(area);
  }
  
  public void setArea(final int x, final int y) {
    this.position.setX(x);
    this.position.setY(y);
  }
  
  public void setArea(final Point2d point) {
    this.position.setX(point.getX());
    this.position.setY(point.getY());
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
  
  @Pure
  public Types getProtestorType() {
    return this.protestorType;
  }
  
  public void setProtestorType(final Types protestorType) {
    this.protestorType = protestorType;
  }
}
