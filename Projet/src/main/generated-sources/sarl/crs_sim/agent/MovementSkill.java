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
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Installing the skill");
  }
  
  public void prepareUninstallation() {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Preparing the uninstallation of the skill");
  }
  
  public void uninstall() {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Uninstalling the skill");
  }
  
  @Override
  public Vector2d influenceKinematic(final Point2d position, final double linearSpeed, final double maxLinearSpeed, final ArrayList<Percept> ListOfTarget) {
    Point2d obj = new Point2d(200, 200);
    double _x = obj.getX();
    double _x_1 = position.getX();
    double _y = obj.getY();
    double _y_1 = position.getY();
    Vector2d v = new Vector2d((_x - _x_1), (_y - _y_1));
    for (final Percept target : ListOfTarget) {
      {
        Types _name = target.getName();
        boolean _equals = Objects.equal(_name, Types.protestor_panic);
        if (_equals) {
          Shape2d _shape = target.getShape();
          Circle2d shape = ((Circle2d) _shape);
          double _x_2 = position.getX();
          double _x_3 = shape.getX();
          double _x_4 = v.getX();
          v.setX(((ParamSimu.PanicForce * (_x_2 - _x_3)) + _x_4));
          double _y_2 = position.getY();
          double _y_3 = shape.getY();
          double _y_4 = v.getY();
          v.setY(((ParamSimu.PanicForce * (_y_2 - _y_3)) + _y_4));
        }
        Types _name_1 = target.getName();
        boolean _equals_1 = Objects.equal(_name_1, Types.protestor_neutral);
        if (_equals_1) {
          Shape2d _shape_1 = target.getShape();
          Circle2d shape_1 = ((Circle2d) _shape_1);
          double _x_5 = position.getX();
          double _x_6 = shape_1.getX();
          double _x_7 = v.getX();
          v.setX(((ParamSimu.NeutralForce * (_x_5 - _x_6)) + _x_7));
          double _y_5 = position.getY();
          double _y_6 = shape_1.getY();
          double _y_7 = v.getY();
          v.setY(((ParamSimu.NeutralForce * (_y_5 - _y_6)) + _y_7));
        }
        Types _name_2 = target.getName();
        boolean _equals_2 = Objects.equal(_name_2, Types.protestor_agg);
        if (_equals_2) {
          Shape2d _shape_2 = target.getShape();
          Circle2d shape_2 = ((Circle2d) _shape_2);
          double _x_8 = position.getX();
          double _x_9 = shape_2.getX();
          double _x_10 = v.getX();
          v.setX(((ParamSimu.AgressiveForce * (_x_8 - _x_9)) + _x_10));
          double _y_8 = position.getY();
          double _y_9 = shape_2.getY();
          double _y_10 = v.getY();
          v.setY(((ParamSimu.AgressiveForce * (_y_8 - _y_9)) + _y_10));
        }
        Types _name_3 = target.getName();
        boolean _equals_3 = Objects.equal(_name_3, Types.building);
        if (_equals_3) {
          Shape2d _shape_3 = target.getShape();
          Rectangle2d shape_3 = ((Rectangle2d) _shape_3);
        }
      }
    }
    return v;
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
