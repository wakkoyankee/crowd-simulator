package crs_sim.body;

import crs_sim.body.EnvObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public abstract class MobileObject extends EnvObject {
  @Accessors({ AccessorType.PUBLIC_SETTER, AccessorType.PUBLIC_GETTER })
  private float speed;
  
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
    MobileObject other = (MobileObject) obj;
    if (Float.floatToIntBits(other.speed) != Float.floatToIntBits(this.speed))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Float.hashCode(this.speed);
    return result;
  }
  
  @SyntheticMember
  public MobileObject() {
    super();
  }
  
  @Pure
  public float getSpeed() {
    return this.speed;
  }
  
  public void setSpeed(final float speed) {
    this.speed = speed;
  }
}
