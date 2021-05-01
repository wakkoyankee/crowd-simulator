package crs_sim.utils;

import crs_sim.body.EnvObject;
import crs_sim.environment.QTNode;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Collection;
import org.arakhne.afc.math.geometry.d2.afp.Rectangle2afp;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class CRS_Sim_Utils extends Rectangle2d {
  private CRS_Sim_Utils() {
  }
  
  public static void buildTree(final QTNode tree, final Collection<EnvObject> collec) {
    for (final EnvObject element : collec) {
      tree.insert(element);
    }
  }
  
  @Pure
  public static boolean AinB(final Shape2d A, final Shape2d B) {
    if (((A instanceof Rectangle2d) && (B instanceof Rectangle2d))) {
      return A.intersects(B);
    } else {
      if (((A instanceof Point2d) && (B instanceof Rectangle2d))) {
        return B.contains(A);
      } else {
        if (((A instanceof Circle2d) && (B instanceof Rectangle2d))) {
          return B.intersects(A);
        }
      }
    }
    return false;
  }
  
  @Pure
  public static boolean AinB_RR(final Rectangle2d A, final Rectangle2d B) {
    boolean _intersectsRectangleRectangle = Rectangle2afp.intersectsRectangleRectangle(
      A.getMinX(), A.getMinY(), A.getMaxX(), A.getMaxY(), 
      B.getMinX(), B.getMinY(), B.getMaxX(), B.getMaxY());
    return (!_intersectsRectangleRectangle);
  }
  
  @Pure
  public static boolean AinB(final Point2d A, final Rectangle2d B) {
    return ((((A.getX() > B.getMinX()) && (A.getX() < B.getMaxX())) && (A.getY() > B.getMinY())) && (A.getY() < B.getMaxY()));
  }
  
  @Pure
  public static int childFit(final Shape2d<?> A, final Shape2d<?> B) {
    if (((A instanceof Rectangle2d) && (B instanceof Rectangle2d))) {
      return CRS_Sim_Utils.childFit(((Rectangle2d) A), ((Rectangle2d) B));
    } else {
      if (((A instanceof Point2d) && (B instanceof Rectangle2d))) {
        return CRS_Sim_Utils.childFit(((Point2d) A), ((Rectangle2d) B));
      } else {
        if (((A instanceof Circle2d) && (B instanceof Rectangle2d))) {
          double _centerX = ((Circle2d) A).getCenterX();
          double _centerY = ((Circle2d) A).getCenterY();
          Point2d _point2d = new Point2d(_centerX, _centerY);
          return CRS_Sim_Utils.childFit(_point2d, 
            ((Rectangle2d) B));
        }
      }
    }
    return 0;
  }
  
  @Pure
  public static int childFit(final Rectangle2d rect, final Rectangle2d area) {
    double _maxX = area.getMaxX();
    double _maxY = area.getMaxY();
    boolean _containsRectangleRectangle = Rectangle2afp.containsRectangleRectangle(
      area.getMinX(), area.getMinY(), (_maxX / 2), (_maxY / 2), 
      rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY());
    if (_containsRectangleRectangle) {
      return 1;
    } else {
      double _maxX_1 = area.getMaxX();
      double _maxY_1 = area.getMaxY();
      boolean _containsRectangleRectangle_1 = Rectangle2afp.containsRectangleRectangle(
        (_maxX_1 / 2), area.getMinY(), area.getMaxX(), (_maxY_1 / 2), 
        rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY());
      if (_containsRectangleRectangle_1) {
        return 2;
      } else {
        double _maxY_2 = area.getMaxY();
        double _maxX_2 = area.getMaxX();
        boolean _containsRectangleRectangle_2 = Rectangle2afp.containsRectangleRectangle(
          area.getMinX(), (_maxY_2 / 2), (_maxX_2 / 2), area.getMaxY(), 
          rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY());
        if (_containsRectangleRectangle_2) {
          return 3;
        } else {
          double _maxX_3 = area.getMaxX();
          double _maxY_3 = area.getMaxY();
          boolean _containsRectangleRectangle_3 = Rectangle2afp.containsRectangleRectangle(
            (_maxX_3 / 2), (_maxY_3 / 2), area.getMaxX(), area.getMaxY(), 
            rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY());
          if (_containsRectangleRectangle_3) {
            return 4;
          }
        }
      }
    }
    return 0;
  }
  
  @Pure
  public static int childFit(final Point2d point, final Rectangle2d area) {
    double _maxX = area.getMaxX();
    double _maxY = area.getMaxY();
    boolean _containsRectanglePoint = Rectangle2afp.containsRectanglePoint(
      area.getMinX(), area.getMinY(), (_maxX / 2), (_maxY / 2), 
      point.getX(), point.getY());
    if (_containsRectanglePoint) {
      return 1;
    } else {
      double _maxX_1 = area.getMaxX();
      double _maxY_1 = area.getMaxY();
      boolean _containsRectanglePoint_1 = Rectangle2afp.containsRectanglePoint(
        (_maxX_1 / 2), area.getMinY(), area.getMaxX(), (_maxY_1 / 2), 
        point.getX(), point.getY());
      if (_containsRectanglePoint_1) {
        return 2;
      } else {
        double _maxY_2 = area.getMaxY();
        double _maxX_2 = area.getMaxX();
        boolean _containsRectanglePoint_2 = Rectangle2afp.containsRectanglePoint(
          area.getMinX(), (_maxY_2 / 2), (_maxX_2 / 2), area.getMaxY(), 
          point.getX(), point.getY());
        if (_containsRectanglePoint_2) {
          return 3;
        } else {
          double _maxX_3 = area.getMaxX();
          double _maxY_3 = area.getMaxY();
          boolean _containsRectanglePoint_3 = Rectangle2afp.containsRectanglePoint(
            (_maxX_3 / 2), (_maxY_3 / 2), area.getMaxX(), area.getMaxY(), 
            point.getX(), point.getY());
          if (_containsRectanglePoint_3) {
            return 4;
          }
        }
      }
    }
    return 0;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public CRS_Sim_Utils clone() {
    try {
      return (CRS_Sim_Utils) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private static final long serialVersionUID = 3917681936L;
}
