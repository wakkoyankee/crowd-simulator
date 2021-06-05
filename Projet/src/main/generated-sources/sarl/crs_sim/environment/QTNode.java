package crs_sim.environment;

import com.google.common.base.Objects;
import crs_sim.body.EnvObject;
import crs_sim.body.MobileObject;
import crs_sim.body.ProtestorBody;
import crs_sim.environment.Percept;
import crs_sim.utils.CRS_Sim_Utils;
import crs_sim.utils.ParamSimu;
import crs_sim.utils.Types;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.arakhne.afc.math.tree.node.QuadTreeNode;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class QTNode extends QuadTreeNode<EnvObject, QTNode> {
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final Rectangle2d area;
  
  public QTNode(final Rectangle2d area) {
    super();
    this.area = area;
  }
  
  /**
   * Construct a node.
   * @param data are the initial user data
   */
  public QTNode(final Collection<EnvObject> collec, final Rectangle2d area) {
    super(collec);
    this.area = area;
  }
  
  public void insert(final EnvObject object) {
    if ((CRS_Sim_Utils.AinB(object.getArea(), this.area) && (this.getUserDataCount() < ParamSimu.maxObjectsPerChild))) {
      this.addUserData(object);
    } else {
      if ((CRS_Sim_Utils.AinB(object.getArea(), this.area) && (this.getUserDataCount() >= ParamSimu.maxObjectsPerChild))) {
        int _notNullChildCount = this.getNotNullChildCount();
        if ((_notNullChildCount <= 0)) {
          this.initChilds();
        }
        int _childFitInsert = CRS_Sim_Utils.childFitInsert(object.getArea(), this.area);
        switch (_childFitInsert) {
          case 0:
            this.addUserData(object);
            break;
          case 1:
            this.getFirstChild().insert(object);
            break;
          case 2:
            this.getSecondChild().insert(object);
            break;
          case 3:
            this.getThirdChild().insert(object);
            break;
          case 4:
            this.getFourthChild().insert(object);
            break;
          case 5:
            this.addUserData(object);
            break;
        }
      } else {
        this.getParentNode().insert(object);
      }
    }
  }
  
  public boolean initChilds() {
    Rectangle2d rect = new Rectangle2d();
    double _minX = this.area.getMinX();
    int x1 = ((int) _minX);
    double _minX_1 = this.area.getMinX();
    double _maxX = this.area.getMaxX();
    double _minX_2 = this.area.getMinX();
    int x2 = ((int) (_minX_1 + ((_maxX - _minX_2) / 2)));
    double _maxX_1 = this.area.getMaxX();
    int x3 = ((int) _maxX_1);
    double _minY = this.area.getMinY();
    int y1 = ((int) _minY);
    double _minY_1 = this.area.getMinY();
    double _maxY = this.area.getMaxY();
    double _minY_2 = this.area.getMinY();
    int y2 = ((int) (_minY_1 + ((_maxY - _minY_2) / 2)));
    double _maxY_1 = this.area.getMaxY();
    int y3 = ((int) _maxY_1);
    if (((((!Objects.equal(this.getFirstChild(), null)) && (!Objects.equal(this.getSecondChild(), null))) && 
      (!Objects.equal(this.getThirdChild(), null))) && (!Objects.equal(this.getFourthChild(), null)))) {
      return false;
    } else {
      Rectangle2d _rectangle2d = new Rectangle2d(x1, y1, (x2 - x1), (y2 - y1));
      QTNode _qTNode = new QTNode(_rectangle2d);
      this.setFirstChild(_qTNode);
      Rectangle2d _rectangle2d_1 = new Rectangle2d(x2, y1, (x3 - x2), (y2 - y1));
      QTNode _qTNode_1 = new QTNode(_rectangle2d_1);
      this.setSecondChild(_qTNode_1);
      Rectangle2d _rectangle2d_2 = new Rectangle2d(x1, y2, (x2 - x1), (y3 - y2));
      QTNode _qTNode_2 = new QTNode(_rectangle2d_2);
      this.setThirdChild(_qTNode_2);
      Rectangle2d _rectangle2d_3 = new Rectangle2d(x2, y2, (x3 - x2), (y3 - y2));
      QTNode _qTNode_3 = new QTNode(_rectangle2d_3);
      this.setFourthChild(_qTNode_3);
      return true;
    }
  }
  
  @Pure
  public List<Percept> getBodies() {
    List<EnvObject> objects = this.getAllUserData();
    List<Percept> result = new ArrayList<Percept>();
    for (final EnvObject object : objects) {
      Shape2d<?> _area = object.getArea();
      UUID _uuid = object.getUuid();
      Types _type = object.getType();
      Percept _percept = new Percept(_area, _uuid, _type);
      result.add(_percept);
    }
    return IterableExtensions.<Percept>toList(result);
  }
  
  @Pure
  public List<Percept> getPercept(final Circle2d perception) {
    List<EnvObject> objects = this.getAllUserData();
    List<Percept> result = new ArrayList<Percept>();
    for (final EnvObject object : objects) {
      boolean _intersects = perception.intersects(object.getArea());
      if (_intersects) {
        Shape2d<?> _area = object.getArea();
        UUID _uuid = object.getUuid();
        Types _type = object.getType();
        Percept _percept = new Percept(_area, _uuid, _type);
        result.add(_percept);
      }
    }
    return IterableExtensions.<Percept>toList(result);
  }
  
  public void moveBody(final MobileObject body, final Point2d point) {
    boolean _contains = this.getAllUserData().contains(body);
    if (_contains) {
      if (((((point.getX() > ParamSimu.mapSizeX) || (point.getX() < 0)) || (point.getY() > ParamSimu.mapSizeY)) || 
        (point.getY() < 0))) {
        Types _type = body.getType();
        double _x = point.getX();
        double _y = point.getY();
        InputOutput.<String>println((((((("C\'est en dehors de la map ça " + _type) + " ") + " ! C\'est en x : ") + Double.valueOf(_x)) + " y : ") + Double.valueOf(_y)));
        InputOutput.<String>println("Je te tp où tu étais");
        return;
      }
      boolean _AinB = CRS_Sim_Utils.AinB(point, this.area);
      if (_AinB) {
        body.setArea(point);
      } else {
        this.removeUserData(body);
        body.setArea(point);
        this.insert(body);
      }
    } else {
      try {
        int _childFit = CRS_Sim_Utils.childFit(body.getPosition(), this.area);
        switch (_childFit) {
          case 0:
            if (((((point.getX() > ParamSimu.mapSizeX) || (point.getX() < 0)) || (point.getY() > ParamSimu.mapSizeY)) || (point.getY() < 0))) {
              Types _type_1 = body.getType();
              UUID _uuid = body.getUuid();
              double _x_1 = point.getX();
              double _y_1 = point.getY();
              InputOutput.<String>println(
                ((((((("C\'est en dehors de la map ça " + _type_1) + " ") + _uuid) + " ! C\'est en x : ") + Double.valueOf(_x_1)) + " y : ") + Double.valueOf(_y_1)));
              InputOutput.<String>println("Je te tp où tu étais");
              return;
            } else {
              Types _type_2 = body.getType();
              Point2d _position = body.getPosition();
              InputOutput.<String>println(((((("buddy your body is lost.. RIP\r\n\t\t\t\t\t\t\t\t__body : " + _type_2) + " Position : ") + _position) + "Body : ") + body));
              int _childFit_1 = CRS_Sim_Utils.childFit(body.getPosition(), this.area);
              InputOutput.<String>println(("Result childFit : " + Integer.valueOf(_childFit_1)));
            }
            break;
          case 1:
            this.getFirstChild().moveBody(body, point);
            break;
          case 2:
            this.getSecondChild().moveBody(body, point);
            break;
          case 3:
            this.getThirdChild().moveBody(body, point);
            break;
          case 4:
            this.getFourthChild().moveBody(body, point);
            break;
        }
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          int _childFit_2 = CRS_Sim_Utils.childFit(body.getPosition(), this.area);
          String _plus = (("Erreur de déplacement d\'enfant \nLe résultat de childFit est : " + Integer.valueOf(_childFit_2)) + 
            "body : ");
          Types _type_3 = body.getType();
          String _plus_1 = ((_plus + _type_3) + " Position : ");
          Point2d _position_1 = body.getPosition();
          InputOutput.<String>print(((((_plus_1 + _position_1) + "\nTargetted spot : ") + point) + "\n\n"));
          InputOutput.<Exception>print(e);
          InputOutput.<String>print(" ");
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
  }
  
  public void changeBehavior(final EnvObject body, final Types state) {
    if ((body instanceof ProtestorBody)) {
      InputOutput.<String>print("you can\'t change the behavior of something else than a protestor");
      return;
    }
    boolean _contains = this.getAllUserData().contains(body);
    if (_contains) {
      body.setType(state);
    } else {
      int _childFit = CRS_Sim_Utils.childFit(body.getPosition(), this.area);
      switch (_childFit) {
        case 0:
          InputOutput.<String>print("buddy your body is lost.. RIP");
          break;
        case 1:
          this.getFirstChild().changeBehavior(body, state);
          break;
        case 2:
          this.getSecondChild().changeBehavior(body, state);
          break;
        case 3:
          this.getThirdChild().changeBehavior(body, state);
          break;
        case 4:
          this.getFourthChild().changeBehavior(body, state);
          break;
      }
    }
  }
  
  public void deleteBody(final EnvObject body, final ArrayList<EnvObject> save) {
    boolean _contains = this.getAllUserData().contains(body);
    if (_contains) {
      this.removeUserData(body);
      InputOutput.<String>print(("Body deleted : " + body));
      return;
    } else {
      try {
        int _childFit = CRS_Sim_Utils.childFit(body.getPosition(), this.area);
        switch (_childFit) {
          case 0:
            InputOutput.<String>print("buddy your body is lost.. RIP");
            break;
          case 1:
            this.getFirstChild().deleteBody(body, save);
            break;
          case 2:
            this.getSecondChild().deleteBody(body, save);
            break;
          case 3:
            this.getThirdChild().deleteBody(body, save);
            break;
          case 4:
            this.getFourthChild().deleteBody(body, save);
            break;
        }
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          int _childFit_1 = CRS_Sim_Utils.childFit(body.getPosition(), this.area);
          String _plus = (("Erreur de suppression\nLe résultat de childFit est : " + Integer.valueOf(_childFit_1)) + "body : ");
          Types _type = body.getType();
          String _plus_1 = ((_plus + _type) + " Position : ");
          Point2d _position = body.getPosition();
          InputOutput.<String>print(((_plus_1 + _position) + "\n"));
          InputOutput.<Exception>print(e);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
    }
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
  private static final long serialVersionUID = 1271754376L;
  
  @Pure
  public Rectangle2d getArea() {
    return this.area;
  }
}
