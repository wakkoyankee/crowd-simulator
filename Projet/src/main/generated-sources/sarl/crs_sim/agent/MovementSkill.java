package crs_sim.agent;

import crs_sim.agent.MovementCapacity;
import crs_sim.environment.Percept;
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
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
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
  
  @Override
  public Vector2d influenceKinematic(final Circle2d position, final ArrayList<Percept> ListOfTarget, final Rectangle2d obj) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field Speed is undefined for the type Class<ParamSimu>"
      + "\nThe method or field Speed is undefined for the type Class<ParamSimu>");
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
