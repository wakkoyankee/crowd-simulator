package crs_sim.agent;

import com.google.common.base.Objects;
import crs_sim.agent.MovementCapacity;
import crs_sim.environment.Percept;
import crs_sim.utils.ParamSimu;
import crs_sim.utils.Types;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.Skill;
import java.util.ArrayList;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author hadrien
 */
@SarlSpecification("0.11")
@SarlElementType(22)
@SuppressWarnings("all")
public class MovementSkill extends Skill implements MovementCapacity {
  public void install() {
  }
  
  public void prepareUninstallation() {
  }
  
  public void uninstall() {
  }
  
  @Pure
  public double distance_vector(final Vector2d V) {
    double _x = V.getX();
    double _power = Math.pow(_x, 2);
    double _y = V.getY();
    double _power_1 = Math.pow(_y, 2);
    return Math.sqrt((_power + _power_1));
  }
  
  @Pure
  public double percent_distance_vector(final Vector2d V, final float T) {
    double _distance_vector = this.distance_vector(V);
    return (_distance_vector / T);
  }
  
  public void normalize_vector(final Vector2d V, final int T) {
    double _x = V.getX();
    double _abs = Math.abs(V.getX());
    double _abs_1 = Math.abs(V.getY());
    V.setX((_x / ((_abs + _abs_1) / T)));
    double _y = V.getY();
    double _abs_2 = Math.abs(V.getX());
    double _abs_3 = Math.abs(V.getY());
    V.setY((_y / ((_abs_2 + _abs_3) / T)));
  }
  
  public void normal_building(final Rectangle2d R, final Circle2d P, final Vector2d V) {
    if ((((P.getX() == R.getMaxX()) && (P.getY() < R.getMaxY())) && (P.getY() > R.getMinY()))) {
      double _x = V.getX();
      if ((_x < 0)) {
        V.setX(0);
        V.setY(ParamSimu.Speed);
      }
    } else {
      if ((((P.getX() == R.getMinX()) && (P.getY() < R.getMaxY())) && (P.getY() > R.getMinY()))) {
        double _x_1 = V.getX();
        if ((_x_1 > 0)) {
          V.setX(0);
          V.setY(ParamSimu.Speed);
        }
      }
    }
    if ((((P.getY() == R.getMaxY()) && (P.getX() < R.getMaxX())) && (P.getX() > R.getMinX()))) {
      double _y = V.getY();
      if ((_y < 0)) {
        V.setY(0);
        V.setX(ParamSimu.Speed);
      }
    } else {
      if ((((P.getY() == R.getMinY()) && (P.getX() < R.getMaxX())) && (P.getX() > R.getMinX()))) {
        double _y_1 = V.getY();
        if ((_y_1 > 0)) {
          V.setY(0);
          V.setX(ParamSimu.Speed);
        }
      }
    }
  }
  
  public void colision_building(final Rectangle2d R, final Circle2d P, final Vector2d V) {
    double _x = P.getX();
    double _x_1 = V.getX();
    double _y = P.getY();
    double _y_1 = V.getY();
    Point2d C = new Point2d((_x + _x_1), (_y + _y_1));
    if (((((C.getX() <= R.getMaxX()) && (C.getX() >= R.getMinX())) && (C.getY() <= R.getMaxY())) && (C.getY() >= R.getMinY()))) {
      double _x_2 = P.getX();
      double _maxX = R.getMaxX();
      if ((_x_2 > _maxX)) {
        double _x_3 = V.getX();
        double _maxX_1 = R.getMaxX();
        double _x_4 = P.getX();
        double _abs = Math.abs((_maxX_1 - _x_4));
        double _x_5 = V.getX();
        V.setX((_x_3 * (_abs / _x_5)));
        double _y_2 = V.getY();
        double _maxX_2 = R.getMaxX();
        double _x_6 = P.getX();
        double _abs_1 = Math.abs((_maxX_2 - _x_6));
        double _x_7 = V.getX();
        V.setY((_y_2 * (_abs_1 / _x_7)));
      } else {
        double _x_8 = P.getX();
        double _minX = R.getMinX();
        if ((_x_8 < _minX)) {
          double _x_9 = V.getX();
          double _minX_1 = R.getMinX();
          double _x_10 = P.getX();
          double _abs_2 = Math.abs((_minX_1 - _x_10));
          double _x_11 = V.getX();
          V.setX((_x_9 * (_abs_2 / _x_11)));
          double _y_3 = V.getY();
          double _minX_2 = R.getMinX();
          double _x_12 = P.getX();
          double _abs_3 = Math.abs((_minX_2 - _x_12));
          double _x_13 = V.getX();
          V.setY((_y_3 * (_abs_3 / _x_13)));
        } else {
          double _y_4 = P.getY();
          double _maxY = R.getMaxY();
          if ((_y_4 > _maxY)) {
            double _x_14 = V.getX();
            double _maxY_1 = R.getMaxY();
            double _y_5 = P.getY();
            double _abs_4 = Math.abs((_maxY_1 - _y_5));
            double _y_6 = V.getY();
            V.setX((_x_14 * (_abs_4 / _y_6)));
            double _y_7 = V.getY();
            double _maxY_2 = R.getMaxY();
            double _y_8 = P.getY();
            double _abs_5 = Math.abs((_maxY_2 - _y_8));
            double _y_9 = V.getY();
            V.setY((_y_7 * (_abs_5 / _y_9)));
          } else {
            double _y_10 = P.getY();
            double _minY = R.getMinY();
            if ((_y_10 < _minY)) {
              double _x_15 = V.getX();
              double _minY_1 = R.getMinY();
              double _y_11 = P.getY();
              double _abs_6 = Math.abs((_minY_1 - _y_11));
              double _y_12 = V.getY();
              V.setX((_x_15 * (_abs_6 / _y_12)));
              double _y_13 = V.getY();
              double _minY_2 = R.getMinY();
              double _y_14 = P.getY();
              double _abs_7 = Math.abs((_minY_2 - _y_14));
              double _y_15 = V.getY();
              V.setY((_y_13 * (_abs_7 / _y_15)));
            }
          }
        }
      }
    }
  }
  
  public void protestor(final Vector2d v, final Percept target, final Circle2d position, final float perception, final float typeforce) {
    Shape2d _shape = target.getShape();
    Circle2d shape = ((Circle2d) _shape);
    double _x = position.getX();
    double _x_1 = shape.getX();
    double _y = position.getY();
    double _y_1 = shape.getY();
    Vector2d V = new Vector2d((_x - _x_1), (_y - _y_1));
    double _percent_distance_vector = this.percent_distance_vector(V, perception);
    double P = (1 - _percent_distance_vector);
    if (((position.getX() == shape.getX()) && (position.getY() == shape.getY()))) {
      V.setX(0);
      V.setY(0);
    } else {
      double _x_2 = V.getX();
      double _abs = Math.abs(V.getX());
      double _abs_1 = Math.abs(V.getY());
      V.setX((_x_2 / ((_abs + _abs_1) / typeforce)));
      double _y_2 = V.getY();
      double _abs_2 = Math.abs(V.getX());
      double _abs_3 = Math.abs(V.getY());
      V.setY((_y_2 / ((_abs_2 + _abs_3) / typeforce)));
      double _x_3 = V.getX();
      double _x_4 = v.getX();
      v.setX(((_x_3 * P) + _x_4));
      double _y_3 = V.getY();
      double _y_4 = v.getY();
      v.setY(((_y_3 * P) + _y_4));
    }
  }
  
  public void build(final Vector2d v, final Percept target, final Circle2d position, final float perception, final float typeforce) {
    Shape2d _shape = target.getShape();
    Rectangle2d shape = ((Rectangle2d) _shape);
    double _x = position.getX();
    double _minX = shape.getMinX();
    if ((_x < _minX)) {
      double _y = position.getY();
      double _minY = shape.getMinY();
      if ((_y < _minY)) {
        double _x_1 = position.getX();
        double _minX_1 = shape.getMinX();
        double _y_1 = position.getY();
        double _minY_1 = shape.getMinY();
        Vector2d V = new Vector2d((_x_1 - _minX_1), (_y_1 - _minY_1));
        double _percent_distance_vector = this.percent_distance_vector(V, perception);
        double P = (1 - _percent_distance_vector);
        double _x_2 = V.getX();
        double _abs = Math.abs(V.getX());
        double _abs_1 = Math.abs(V.getY());
        V.setX((_x_2 / ((_abs + _abs_1) / typeforce)));
        double _y_2 = V.getY();
        double _abs_2 = Math.abs(V.getX());
        double _abs_3 = Math.abs(V.getY());
        V.setY((_y_2 / ((_abs_2 + _abs_3) / typeforce)));
        double _x_3 = V.getX();
        double _x_4 = v.getX();
        v.setX(((_x_3 * P) + _x_4));
        double _y_3 = V.getY();
        double _y_4 = v.getY();
        v.setY(((_y_3 * P) + _y_4));
      } else {
        double _y_5 = position.getY();
        double _maxY = shape.getMaxY();
        if ((_y_5 > _maxY)) {
          double _x_5 = position.getX();
          double _minX_2 = shape.getMinX();
          double _y_6 = position.getY();
          double _maxY_1 = shape.getMaxY();
          Vector2d V_1 = new Vector2d((_x_5 - _minX_2), (_y_6 - _maxY_1));
          double _percent_distance_vector_1 = this.percent_distance_vector(V_1, perception);
          double P_1 = (1 - _percent_distance_vector_1);
          double _x_6 = V_1.getX();
          double _abs_4 = Math.abs(V_1.getX());
          double _abs_5 = Math.abs(V_1.getY());
          V_1.setX((_x_6 / ((_abs_4 + _abs_5) / typeforce)));
          double _y_7 = V_1.getY();
          double _abs_6 = Math.abs(V_1.getX());
          double _abs_7 = Math.abs(V_1.getY());
          V_1.setY((_y_7 / ((_abs_6 + _abs_7) / typeforce)));
          double _x_7 = V_1.getX();
          double _x_8 = v.getX();
          v.setX(((_x_7 * P_1) + _x_8));
          double _y_8 = V_1.getY();
          double _y_9 = v.getY();
          v.setY(((_y_8 * P_1) + _y_9));
        } else {
          double _x_9 = position.getX();
          double _minX_3 = shape.getMinX();
          Vector2d V_2 = new Vector2d((_x_9 - _minX_3), 0);
          double _percent_distance_vector_2 = this.percent_distance_vector(V_2, perception);
          double P_2 = (1 - _percent_distance_vector_2);
          double _x_10 = V_2.getX();
          double _abs_8 = Math.abs(V_2.getX());
          double _abs_9 = Math.abs(V_2.getY());
          V_2.setX((_x_10 / ((_abs_8 + _abs_9) / typeforce)));
          double _x_11 = V_2.getX();
          double _x_12 = v.getX();
          v.setX(((_x_11 * P_2) + _x_12));
        }
      }
    } else {
      double _x_13 = position.getX();
      double _maxX = shape.getMaxX();
      if ((_x_13 > _maxX)) {
        double _y_10 = position.getY();
        double _minY_2 = shape.getMinY();
        if ((_y_10 < _minY_2)) {
          double _x_14 = position.getX();
          double _maxX_1 = shape.getMaxX();
          double _y_11 = position.getY();
          double _minY_3 = shape.getMinY();
          Vector2d V_3 = new Vector2d((_x_14 - _maxX_1), (_y_11 - _minY_3));
          double _percent_distance_vector_3 = this.percent_distance_vector(V_3, perception);
          double P_3 = (1 - _percent_distance_vector_3);
          double _x_15 = V_3.getX();
          double _abs_10 = Math.abs(V_3.getX());
          double _abs_11 = Math.abs(V_3.getY());
          V_3.setX((_x_15 / ((_abs_10 + _abs_11) / typeforce)));
          double _y_12 = V_3.getY();
          double _abs_12 = Math.abs(V_3.getX());
          double _abs_13 = Math.abs(V_3.getY());
          V_3.setY((_y_12 / ((_abs_12 + _abs_13) / typeforce)));
          double _x_16 = V_3.getX();
          double _x_17 = v.getX();
          v.setX(((_x_16 * P_3) + _x_17));
          double _y_13 = V_3.getY();
          double _y_14 = v.getY();
          v.setY(((_y_13 * P_3) + _y_14));
        } else {
          double _y_15 = position.getY();
          double _maxY_2 = shape.getMaxY();
          if ((_y_15 > _maxY_2)) {
            double _x_18 = position.getX();
            double _maxX_2 = shape.getMaxX();
            double _y_16 = position.getY();
            double _maxY_3 = shape.getMaxY();
            Vector2d V_4 = new Vector2d((_x_18 - _maxX_2), (_y_16 - _maxY_3));
            double _percent_distance_vector_4 = this.percent_distance_vector(V_4, perception);
            double P_4 = (1 - _percent_distance_vector_4);
            double _x_19 = V_4.getX();
            double _abs_14 = Math.abs(V_4.getX());
            double _abs_15 = Math.abs(V_4.getY());
            V_4.setX((_x_19 / ((_abs_14 + _abs_15) / typeforce)));
            double _y_17 = V_4.getY();
            double _abs_16 = Math.abs(V_4.getX());
            double _abs_17 = Math.abs(V_4.getY());
            V_4.setY((_y_17 / ((_abs_16 + _abs_17) / typeforce)));
            double _x_20 = V_4.getX();
            double _x_21 = v.getX();
            v.setX(((_x_20 * P_4) + _x_21));
            double _y_18 = V_4.getY();
            double _y_19 = v.getY();
            v.setY(((_y_18 * P_4) + _y_19));
          } else {
            double _x_22 = position.getX();
            double _maxX_3 = shape.getMaxX();
            Vector2d V_5 = new Vector2d((_x_22 - _maxX_3), 0);
            double _percent_distance_vector_5 = this.percent_distance_vector(V_5, perception);
            double P_5 = (1 - _percent_distance_vector_5);
            double _x_23 = V_5.getX();
            double _abs_18 = Math.abs(V_5.getX());
            double _abs_19 = Math.abs(V_5.getY());
            V_5.setX((_x_23 / ((_abs_18 + _abs_19) / typeforce)));
            double _x_24 = V_5.getX();
            double _x_25 = v.getX();
            v.setX(((_x_24 * P_5) + _x_25));
          }
        }
      } else {
        double _y_20 = position.getY();
        double _minY_4 = shape.getMinY();
        if ((_y_20 < _minY_4)) {
          double _y_21 = position.getY();
          double _minY_5 = shape.getMinY();
          Vector2d V_6 = new Vector2d(0, (_y_21 - _minY_5));
          double _percent_distance_vector_6 = this.percent_distance_vector(V_6, perception);
          double P_6 = (1 - _percent_distance_vector_6);
          double _y_22 = V_6.getY();
          double _abs_20 = Math.abs(V_6.getX());
          double _abs_21 = Math.abs(V_6.getY());
          V_6.setY((_y_22 / ((_abs_20 + _abs_21) / typeforce)));
          double _y_23 = V_6.getY();
          double _y_24 = v.getY();
          v.setY(((_y_23 * P_6) + _y_24));
        } else {
          double _y_25 = position.getY();
          double _maxY_4 = shape.getMaxY();
          Vector2d V_7 = new Vector2d(0, (_y_25 - _maxY_4));
          double _percent_distance_vector_7 = this.percent_distance_vector(V_7, perception);
          double P_7 = (1 - _percent_distance_vector_7);
          double _y_26 = V_7.getY();
          double _abs_22 = Math.abs(V_7.getX());
          double _abs_23 = Math.abs(V_7.getY());
          V_7.setY((_y_26 / ((_abs_22 + _abs_23) / typeforce)));
          double _y_27 = V_7.getY();
          double _y_28 = v.getY();
          v.setY(((_y_27 * P_7) + _y_28));
        }
      }
    }
  }
  
  public Vector2d SocialModelForce(final Circle2d position, final ArrayList<Percept> ListOfTarget, final Rectangle2d obj, final float panic, final float neutral, final float agressive, final float crs, final float building, final float destroyable, final double speed, final float perception, final float arrival) {
    double _minX = obj.getMinX();
    double _x = position.getX();
    double _minY = obj.getMinY();
    double _y = position.getY();
    Vector2d v = new Vector2d((_minX - _x), (_minY - _y));
    double _x_1 = v.getX();
    double _abs = Math.abs(v.getX());
    double _abs_1 = Math.abs(v.getY());
    v.setX((_x_1 / ((_abs + _abs_1) / arrival)));
    double _y_1 = v.getY();
    double _abs_2 = Math.abs(v.getX());
    double _abs_3 = Math.abs(v.getY());
    v.setY((_y_1 / ((_abs_2 + _abs_3) / arrival)));
    for (final Percept target : ListOfTarget) {
      {
        Types _name = target.getName();
        boolean _equals = Objects.equal(_name, Types.protestor_panic);
        if (_equals) {
          this.protestor(v, target, position, perception, panic);
        }
        Types _name_1 = target.getName();
        boolean _equals_1 = Objects.equal(_name_1, Types.protestor_neutral);
        if (_equals_1) {
          this.protestor(v, target, position, perception, neutral);
        }
        Types _name_2 = target.getName();
        boolean _equals_2 = Objects.equal(_name_2, Types.protestor_agg);
        if (_equals_2) {
          this.protestor(v, target, position, perception, agressive);
        }
        Types _name_3 = target.getName();
        boolean _equals_3 = Objects.equal(_name_3, Types.crs);
        if (_equals_3) {
          this.protestor(v, target, position, perception, crs);
        }
        Types _name_4 = target.getName();
        boolean _equals_4 = Objects.equal(_name_4, Types.building);
        if (_equals_4) {
          this.build(v, target, position, perception, building);
        }
        Types _name_5 = target.getName();
        boolean _equals_5 = Objects.equal(_name_5, Types.destroyable);
        if (_equals_5) {
          this.build(v, target, position, perception, destroyable);
        }
      }
    }
    double _x_2 = v.getX();
    double _abs_4 = Math.abs(v.getX());
    double _abs_5 = Math.abs(v.getY());
    v.setX((_x_2 / ((_abs_4 + _abs_5) / speed)));
    double _y_2 = v.getY();
    double _abs_6 = Math.abs(v.getX());
    double _abs_7 = Math.abs(v.getY());
    v.setY((_y_2 / ((_abs_6 + _abs_7) / speed)));
    for (final Percept target_1 : ListOfTarget) {
      if ((Objects.equal(target_1.getName(), Types.building) || Objects.equal(target_1.getName(), Types.destroyable))) {
        Shape2d _shape = target_1.getShape();
        Rectangle2d shape = ((Rectangle2d) _shape);
        this.normal_building(shape, position, v);
        this.colision_building(shape, position, v);
      }
    }
    return v;
  }
  
  @Override
  public Vector2d influenceKinematic(final Circle2d position, final ArrayList<Percept> ListOfTarget, final Rectangle2d obj, final Types type) {
    Vector2d _xifexpression = null;
    boolean _equals = Objects.equal(type, Types.protestor_panic);
    if (_equals) {
      _xifexpression = this.SocialModelForce(position, ListOfTarget, obj, ParamSimu.PanicOnPanic, ParamSimu.NeutralOnPanic, ParamSimu.AgressiveOnPanic, ParamSimu.CRSOnPanic, ParamSimu.BuildingForce, ParamSimu.BuildingForce, ParamSimu.Speed, ParamSimu.RadiusPerceptProtestor, ParamSimu.TargetPanic);
    } else {
      Vector2d _xifexpression_1 = null;
      boolean _equals_1 = Objects.equal(type, Types.protestor_neutral);
      if (_equals_1) {
        _xifexpression_1 = this.SocialModelForce(position, ListOfTarget, obj, ParamSimu.PanicOnNeutral, ParamSimu.NeutralOnNeutral, ParamSimu.AgressiveOnNeutral, ParamSimu.CRSOnNeutral, ParamSimu.BuildingForce, ParamSimu.BuildingForce, ParamSimu.Speed, ParamSimu.RadiusPerceptProtestor, ParamSimu.TargetNeutral);
      } else {
        Vector2d _xifexpression_2 = null;
        boolean _equals_2 = Objects.equal(type, Types.protestor_agg);
        if (_equals_2) {
          _xifexpression_2 = this.SocialModelForce(position, ListOfTarget, obj, ParamSimu.PanicOnAgressive, ParamSimu.NeutralOnAgressive, ParamSimu.AgressiveOnAgressive, ParamSimu.CRSOnAgressive, ParamSimu.BuildingForce, ParamSimu.DestroyableOnAgressive, ParamSimu.Speed, ParamSimu.RadiusPerceptProtestor, ParamSimu.TargetAgressive);
        } else {
          Vector2d _xifexpression_3 = null;
          boolean _equals_3 = Objects.equal(type, Types.crs);
          if (_equals_3) {
            _xifexpression_3 = this.SocialModelForce(position, ListOfTarget, obj, ParamSimu.PanicOnCrs, ParamSimu.NeutralOnCrs, ParamSimu.AgressiveOnCrs, ParamSimu.CRSOnCrs, ParamSimu.BuildingForce, ParamSimu.BuildingForce, ParamSimu.Speed, ParamSimu.RadiusPerceptCrs, ParamSimu.TargetCrs);
          }
          _xifexpression_2 = _xifexpression_3;
        }
        _xifexpression_1 = _xifexpression_2;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @SyntheticMember
  public MovementSkill() {
    super();
  }
  
  @SyntheticMember
  public MovementSkill(final Agent agent) {
    super(agent);
  }
}
