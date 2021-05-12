package crs_sim.agent;

import crs_sim.agent.MovementSkill;
import crs_sim.environment.Percept;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.DefaultSkill;
import java.util.ArrayList;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;

/**
 * @author hadrien
 */
@DefaultSkill(MovementSkill.class)
@FunctionalInterface
@SarlSpecification("0.11")
@SarlElementType(20)
@SuppressWarnings("all")
public interface MovementCapacity extends Capacity {
  public abstract Vector2d influenceKinematic(final Point2d position, final double linearSpeed, final double maxLinearSpeed, final ArrayList<Percept> ListOfTarget);
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends MovementCapacity> extends Capacity.ContextAwareCapacityWrapper<C> implements MovementCapacity {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public Vector2d influenceKinematic(final Point2d position, final double linearSpeed, final double maxLinearSpeed, final ArrayList<Percept> ListOfTarget) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.influenceKinematic(position, linearSpeed, maxLinearSpeed, ListOfTarget);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
