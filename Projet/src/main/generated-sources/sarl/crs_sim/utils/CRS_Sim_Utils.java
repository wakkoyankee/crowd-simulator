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
    return ((((((int) A.getX()) > ((int) B.getMinX())) && (((int) A.getX()) < ((int) B.getMaxX()))) && (((int) A.getY()) > ((int) B.getMinY()))) && (((int) A.getY()) < ((int) B.getMaxY())));
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
    double _minX = area.getMinX();
    int x1 = ((int) _minX);
    double _minX_1 = area.getMinX();
    double _maxX = area.getMaxX();
    double _minX_2 = area.getMinX();
    int x2 = ((int) (_minX_1 + ((_maxX - _minX_2) / 2)));
    double _maxX_1 = area.getMaxX();
    int x3 = ((int) _maxX_1);
    double _minY = area.getMinY();
    int y1 = ((int) _minY);
    double _minY_1 = area.getMinY();
    double _maxY = area.getMaxY();
    double _minY_2 = area.getMinY();
    int y2 = ((int) (_minY_1 + ((_maxY - _minY_2) / 2)));
    double _maxY_1 = area.getMaxY();
    int y3 = ((int) _maxY_1);
    boolean _containsRectangleRectangle = Rectangle2afp.containsRectangleRectangle(x1, y1, x2, y2, 
      rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY());
    if (_containsRectangleRectangle) {
      return 1;
    } else {
      boolean _containsRectangleRectangle_1 = Rectangle2afp.containsRectangleRectangle(x2, y1, x3, y2, 
        rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY());
      if (_containsRectangleRectangle_1) {
        return 2;
      } else {
        boolean _containsRectangleRectangle_2 = Rectangle2afp.containsRectangleRectangle(x1, y2, x2, y3, 
          rect.getMinX(), rect.getMinY(), rect.getMaxX(), rect.getMaxY());
        if (_containsRectangleRectangle_2) {
          return 3;
        } else {
          boolean _containsRectangleRectangle_3 = Rectangle2afp.containsRectangleRectangle(x2, y2, x3, y3, 
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
    double x1 = area.getMinX();
    double _minX = area.getMinX();
    double _maxX = area.getMaxX();
    double _minX_1 = area.getMinX();
    double x2 = (_minX + ((_maxX - _minX_1) / 2));
    double x3 = area.getMaxX();
    double y1 = area.getMinY();
    double _minY = area.getMinY();
    double _maxY = area.getMaxY();
    double _minY_1 = area.getMinY();
    double y2 = (_minY + ((_maxY - _minY_1) / 2));
    double y3 = area.getMaxY();
    double _x = point.getX();
    double _y = point.getY();
    boolean _containsRectanglePoint = Rectangle2afp.containsRectanglePoint(x1, y1, x2, y2, 
      ((int) _x), ((int) _y));
    if (_containsRectanglePoint) {
      return 1;
    } else {
      double _x_1 = point.getX();
      double _y_1 = point.getY();
      boolean _containsRectanglePoint_1 = Rectangle2afp.containsRectanglePoint(x2, y1, x3, y2, 
        ((int) _x_1), ((int) _y_1));
      if (_containsRectanglePoint_1) {
        return 2;
      } else {
        double _x_2 = point.getX();
        double _y_2 = point.getY();
        boolean _containsRectanglePoint_2 = Rectangle2afp.containsRectanglePoint(x1, y2, x2, y3, 
          ((int) _x_2), ((int) _y_2));
        if (_containsRectanglePoint_2) {
          return 3;
        } else {
          double _x_3 = point.getX();
          double _y_3 = point.getY();
          boolean _containsRectanglePoint_3 = Rectangle2afp.containsRectanglePoint(x2, y2, x3, y3, 
            ((int) _x_3), ((int) _y_3));
          if (_containsRectanglePoint_3) {
            return 4;
          }
        }
      }
    }
    return 0;
  }
  
  @Pure
  public static int childFitInsert(final Shape2d<?> A, final Shape2d<?> B) {
    if (((A instanceof Point2d) && (B instanceof Rectangle2d))) {
      return CRS_Sim_Utils.childFitInsert(((Point2d) A), ((Rectangle2d) B));
    }
    return 0;
  }
  
  @Pure
  public static int childFitInsert(final Point2d point, final Rectangle2d area) {
    double x1 = area.getMinX();
    double _minX = area.getMinX();
    double _maxX = area.getMaxX();
    double _minX_1 = area.getMinX();
    double x2 = (_minX + ((_maxX - _minX_1) / 2));
    double x3 = area.getMaxX();
    double y1 = area.getMinY();
    double _minY = area.getMinY();
    double _maxY = area.getMaxY();
    double _minY_1 = area.getMinY();
    double y2 = (_minY + ((_maxY - _minY_1) / 2));
    double y3 = area.getMaxY();
    if (((point.getX() == x2) && (point.getY() == y2))) {
      return 5;
    } else {
      double _x = point.getX();
      double _y = point.getY();
      boolean _containsRectanglePoint = Rectangle2afp.containsRectanglePoint(x1, y1, x2, y2, 
        ((int) _x), ((int) _y));
      if (_containsRectanglePoint) {
        return 1;
      } else {
        double _x_1 = point.getX();
        double _y_1 = point.getY();
        boolean _containsRectanglePoint_1 = Rectangle2afp.containsRectanglePoint(x2, y1, x3, y2, 
          ((int) _x_1), ((int) _y_1));
        if (_containsRectanglePoint_1) {
          return 2;
        } else {
          double _x_2 = point.getX();
          double _y_2 = point.getY();
          boolean _containsRectanglePoint_2 = Rectangle2afp.containsRectanglePoint(x1, y2, x2, y3, 
            ((int) _x_2), ((int) _y_2));
          if (_containsRectanglePoint_2) {
            return 3;
          } else {
            double _x_3 = point.getX();
            double _y_3 = point.getY();
            boolean _containsRectanglePoint_3 = Rectangle2afp.containsRectanglePoint(x2, y2, x3, y3, 
              ((int) _x_3), ((int) _y_3));
            if (_containsRectanglePoint_3) {
              return 4;
            }
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
  private static final long serialVersionUID = 6303982497L;
}
