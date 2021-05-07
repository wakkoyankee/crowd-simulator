package crs_sim.body;

import crs_sim.body.MobileObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class ProtestorBody extends MobileObject {
  private Circle2d position;
  
  private String beh;
  
  public ProtestorBody(final Circle2d position, final String beh) {
    this.position = position;
    this.beh = beh;
  }
  
  public ProtestorBody(final Circle2d position) {
    this.position = position;
  }
  
  public Shape2d<?> getArea() {
    return this.position;
  }
  
  @Pure
  public String getBeh() {
    return this.beh;
  }
  
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
    ProtestorBody other = (ProtestorBody) obj;
    if (!Objects.equals(this.beh, other.beh))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.beh);
    return result;
  }
}
