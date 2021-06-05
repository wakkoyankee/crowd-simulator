package crs_sim.agent;

import crs_sim.agent.MovementSkill;
import crs_sim.environment.Percept;
import crs_sim.utils.Types;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.DefaultSkill;
import java.util.ArrayList;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
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
  public abstract Vector2d influenceKinematic(final Circle2d position, final ArrayList<Percept> ListOfTarget, final Rectangle2d obj, final Types type);
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends MovementCapacity> extends Capacity.ContextAwareCapacityWrapper<C> implements MovementCapacity {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public Vector2d influenceKinematic(final Circle2d position, final ArrayList<Percept> ListOfTarget, final Rectangle2d obj, final Types type) {
      try {
        ensureCallerInLocalThread();
        return this.capacity.influenceKinematic(position, ListOfTarget, obj, type);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
