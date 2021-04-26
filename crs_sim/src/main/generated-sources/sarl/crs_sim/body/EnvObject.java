package crs_sim.body;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class EnvObject {
  private Point2d position;
  
  private UUID uuid;
  
  @Pure
  public Point2d getPosition() {
    return this.position;
  }
  
  public void setPosition(final Point2d position) {
    this.position = position;
  }
  
  public abstract void getArea();
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    EnvObject other = (EnvObject) obj;
    if (!Objects.equals(this.uuid, other.uuid))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.uuid);
    return result;
  }
  
  @SyntheticMember
  public EnvObject() {
    super();
  }
}
