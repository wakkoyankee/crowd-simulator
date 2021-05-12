package crs_sim.environment;

import com.google.common.base.Objects;
import crs_sim.body.EnvObject;
import crs_sim.body.MobileObject;
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
        int _childFit = CRS_Sim_Utils.childFit(object.getArea(), this.area);
        switch (_childFit) {
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
        }
      } else {
        this.getParentNode().insert(object);
      }
    }
  }
  
  public boolean initChilds() {
    Rectangle2d rect = new Rectangle2d();
    if (((((!Objects.equal(this.getFirstChild(), null)) && (!Objects.equal(this.getSecondChild(), null))) && 
      (!Objects.equal(this.getThirdChild(), null))) && (!Objects.equal(this.getFourthChild(), null)))) {
      return false;
    } else {
      double _minX = this.area.getMinX();
      double _minY = this.area.getMinY();
      double _maxX = this.area.getMaxX();
      double _maxY = this.area.getMaxY();
      Rectangle2d _rectangle2d = new Rectangle2d(_minX, _minY, (_maxX / 2), (_maxY / 2));
      QTNode _qTNode = new QTNode(_rectangle2d);
      this.setFirstChild(_qTNode);
      double _maxX_1 = this.area.getMaxX();
      double _minY_1 = this.area.getMinY();
      double _maxX_2 = this.area.getMaxX();
      double _maxY_1 = this.area.getMaxY();
      Rectangle2d _rectangle2d_1 = new Rectangle2d((_maxX_1 / 2), _minY_1, (_maxX_2 / 2), (_maxY_1 / 2));
      QTNode _qTNode_1 = new QTNode(_rectangle2d_1);
      this.setSecondChild(_qTNode_1);
      double _minX_1 = this.area.getMinX();
      double _maxY_2 = this.area.getMaxY();
      double _maxX_3 = this.area.getMaxX();
      double _maxY_3 = this.area.getMaxY();
      Rectangle2d _rectangle2d_2 = new Rectangle2d(_minX_1, (_maxY_2 / 2), (_maxX_3 / 2), (_maxY_3 / 2));
      QTNode _qTNode_2 = new QTNode(_rectangle2d_2);
      this.setThirdChild(_qTNode_2);
      double _maxX_4 = this.area.getMaxX();
      double _maxY_4 = this.area.getMaxY();
      double _maxX_5 = this.area.getMaxX();
      double _maxY_5 = this.area.getMaxY();
      Rectangle2d _rectangle2d_3 = new Rectangle2d((_maxX_4 / 2), (_maxY_4 / 2), (_maxX_5 / 2), (_maxY_5 / 2));
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
      Percept _percept = new Percept(_area, _uuid, 
        null);
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
        Percept _percept = new Percept(_area, _uuid, 
          Types.protestor_neutral);
        result.add(_percept);
      }
    }
    return IterableExtensions.<Percept>toList(result);
  }
  
  public void moveBodyPasOpti(final MobileObject body, final Point2d point) {
    boolean _contains = this.getAllUserData().contains(body);
    if (_contains) {
      boolean _AinB = CRS_Sim_Utils.AinB(point, this.area);
      if (_AinB) {
        body.setArea(point);
      } else {
        this.removeUserData(body);
        body.setArea(point);
        this.insert(body);
      }
    } else {
      int _childFit = CRS_Sim_Utils.childFit(body.getPoition(), this.area);
      switch (_childFit) {
        case 0:
          InputOutput.<String>print("buddy your body is lost.. RIP");
          break;
        case 1:
          this.getFirstChild().moveBodyPasOpti(body, point);
          break;
        case 2:
          this.getSecondChild().moveBodyPasOpti(body, point);
          break;
        case 3:
          this.getThirdChild().moveBodyPasOpti(body, point);
          break;
        case 4:
          this.getFourthChild().moveBodyPasOpti(body, point);
          break;
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
  private static final long serialVersionUID = 1763480262L;
  
  @Pure
  public Rectangle2d getArea() {
    return this.area;
  }
}
