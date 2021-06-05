package crs_sim.body;

import crs_sim.utils.Types;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
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
public abstract class EnvObject {
  @Accessors
  private final UUID uuid = UUID.randomUUID();
  
  public abstract Shape2d<?> getArea();
  
  public abstract Types getType();
  
  public abstract Point2d getPosition();
  
  public abstract void setArea(final Shape2d<?> area);
  
  public abstract void setArea(final int x, final int y);
  
  public abstract void setArea(final Point2d point);
  
  public abstract void setType(final Types type);
  
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
  
  @Pure
  public UUID getUuid() {
    return this.uuid;
  }
}
