package crs_sim.body;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.eclipse.xtext.xbase.lib.Pure;

@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class ex {
  private Rectangle2d a;
  
  private Point2d b;
  
  private Rectangle2d c;
  
  @Pure
  public Object toto() {
    Object _xblockexpression = null;
    {
      boolean _contains = this.a.contains(this.b);
      if (_contains) {
      }
      Object _xifexpression = null;
      boolean _intersects = this.a.intersects(this.c);
      if (_intersects) {
        _xifexpression = null;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
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
  
  @SyntheticMember
  public ex() {
    super();
  }
}
