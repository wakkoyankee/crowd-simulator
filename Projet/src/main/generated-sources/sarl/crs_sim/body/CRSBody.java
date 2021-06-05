package crs_sim.body;

import crs_sim.body.MobileObject;
import crs_sim.utils.Types;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class CRSBody extends MobileObject {
  private final Circle2d position;
  
  private Types type;
  
  public CRSBody(final Circle2d position, final Types type) {
    this.position = position;
    this.type = type;
  }
  
  public Shape2d<?> getArea() {
    return this.position;
  }
  
  public Point2d getPosition() {
    double _centerX = this.position.getCenterX();
    double _centerY = this.position.getCenterY();
    return new Point2d(_centerX, _centerY);
  }
  
  public Types getType() {
    return this.type;
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
  public void setType(final Types type) {
    throw new UnsupportedOperationException("TODO: auto-generated method stub");
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
