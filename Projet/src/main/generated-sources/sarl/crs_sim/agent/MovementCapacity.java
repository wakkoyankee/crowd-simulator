package crs_sim.agent;

import crs_sim.agent.MovementSkill;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
import io.sarl.lang.core.DefaultSkill;

/**
 * @author hadrien
 */
@DefaultSkill(MovementSkill.class)
@FunctionalInterface
@SarlSpecification("0.11")
@SarlElementType(20)
@SuppressWarnings("all")
public interface MovementCapacity extends Capacity {
  public abstract void influenceKinematic();
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends MovementCapacity> extends Capacity.ContextAwareCapacityWrapper<C> implements MovementCapacity {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void influenceKinematic() {
      try {
        ensureCallerInLocalThread();
        this.capacity.influenceKinematic();
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
