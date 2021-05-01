package crs_sim.environment;

import com.google.common.base.Objects;
import crs_sim.body.EnvObject;
import crs_sim.utils.CRS_Sim_Utils;
import crs_sim.utils.ParamSimu;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Collection;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.tree.node.QuadTreeNode;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class QTNode extends QuadTreeNode<EnvObject, QTNode> {
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
      Rectangle2d _rectangle2d_1 = new Rectangle2d((_maxX_1 / 2), _minY_1, _maxX_2, (_maxY_1 / 2));
      QTNode _qTNode_1 = new QTNode(_rectangle2d_1);
      this.setSecondChild(_qTNode_1);
      double _minX_1 = this.area.getMinX();
      double _maxY_2 = this.area.getMaxY();
      double _maxX_3 = this.area.getMaxX();
      double _maxY_3 = this.area.getMaxY();
      Rectangle2d _rectangle2d_2 = new Rectangle2d(_minX_1, (_maxY_2 / 2), (_maxX_3 / 2), _maxY_3);
      QTNode _qTNode_2 = new QTNode(_rectangle2d_2);
      this.setThirdChild(_qTNode_2);
      double _maxX_4 = this.area.getMaxX();
      double _maxY_4 = this.area.getMaxY();
      double _maxX_5 = this.area.getMaxX();
      double _maxY_5 = this.area.getMaxY();
      Rectangle2d _rectangle2d_3 = new Rectangle2d((_maxX_4 / 2), (_maxY_4 / 2), _maxX_5, _maxY_5);
      QTNode _qTNode_3 = new QTNode(_rectangle2d_3);
      this.setFourthChild(_qTNode_3);
      return true;
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
  private static final long serialVersionUID = 988147283L;
}
