package crs_sim.agent;

import com.google.common.base.Objects;
import crs_sim.agent.Memory;
import crs_sim.agent.MovementSkill;
import crs_sim.agent.Protestor;
import crs_sim.environment.Influence;
import crs_sim.environment.InfluenceEvent;
import crs_sim.environment.Percept;
import crs_sim.environment.PerceptionEvent;
import crs_sim.utils.Action;
import crs_sim.utils.ParamSimu;
import crs_sim.utils.Types;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.ContextJoined;
import io.sarl.core.ContextLeft;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Logging;
import io.sarl.core.MemberJoined;
import io.sarl.core.MemberLeft;
import io.sarl.core.ParticipantJoined;
import io.sarl.core.ParticipantLeft;
import io.sarl.core.SpaceCreated;
import io.sarl.core.SpaceDestroyed;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Scope;
import io.sarl.lang.util.SerializableProxy;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author hadrien
 */
@SarlSpecification("0.11")
@SarlElementType(21)
@SuppressWarnings("all")
public class Neutral extends Behavior {
  private Memory memory;
  
  private MovementSkill moveS;
  
  public Neutral(final Protestor owner, final Memory memory) {
    super(owner);
    this.memory = memory;
  }
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    MovementSkill _movementSkill = new MovementSkill();
    this.moveS = _movementSkill;
    this.<MovementSkill>setSkill(this.moveS);
  }
  
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Neutral behavior was stopped.");
  }
  
  private void $behaviorUnit$PerceptionEvent$2(final PerceptionEvent occurrence) {
    Rectangle2d obj = ((Rectangle2d) occurrence.obj);
    Circle2d body = ((Circle2d) occurrence.body);
    ArrayList perceptions = ((ArrayList) occurrence.perceptions);
    boolean _intersects = body.intersects(obj);
    if (_intersects) {
      UUID _iD = this.getOwner().getID();
      Influence inf = new Influence(_iD, Action.despawn, Types.protestor_neutral);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      InfluenceEvent _influenceEvent = new InfluenceEvent(inf);
      class $SerializableClosureProxy implements Scope<Address> {
        
        private final UUID $_uUID;
        
        public $SerializableClosureProxy(final UUID $_uUID) {
          this.$_uUID = $_uUID;
        }
        
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, $_uUID);
        }
      }
      final Scope<Address> _function = new Scope<Address>() {
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          UUID _uUID_1 = occurrence.getSource().getUUID();
          return Objects.equal(_uUID, _uUID_1);
        }
        private Object writeReplace() throws ObjectStreamException {
          return new SerializableProxy($SerializableClosureProxy.class, occurrence.getSource().getUUID());
        }
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_influenceEvent, _function);
      return;
    }
    Types beh = this.CalculateBeh(perceptions);
    boolean _equals = Objects.equal(beh, null);
    if (_equals) {
      Vector2d move = this.moveS.influenceKinematic(body, perceptions, obj, Types.protestor_neutral);
      UUID _iD_1 = this.getOwner().getID();
      Influence inf_1 = new Influence(_iD_1, Action.move, Types.protestor_panic, move);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      InfluenceEvent _influenceEvent_1 = new InfluenceEvent(inf_1);
      class $SerializableClosureProxy_1 implements Scope<Address> {
        
        private final UUID $_uUID;
        
        public $SerializableClosureProxy_1(final UUID $_uUID) {
          this.$_uUID = $_uUID;
        }
        
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, $_uUID);
        }
      }
      final Scope<Address> _function_1 = new Scope<Address>() {
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          UUID _uUID_1 = occurrence.getSource().getUUID();
          return Objects.equal(_uUID, _uUID_1);
        }
        private Object writeReplace() throws ObjectStreamException {
          return new SerializableProxy($SerializableClosureProxy_1.class, occurrence.getSource().getUUID());
        }
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(_influenceEvent_1, _function_1);
    } else {
      UUID _iD_2 = this.getOwner().getID();
      Influence inf_2 = new Influence(_iD_2, Action.changeBeh, beh);
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      InfluenceEvent _influenceEvent_2 = new InfluenceEvent(inf_2);
      class $SerializableClosureProxy_2 implements Scope<Address> {
        
        private final UUID $_uUID;
        
        public $SerializableClosureProxy_2(final UUID $_uUID) {
          this.$_uUID = $_uUID;
        }
        
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          return Objects.equal(_uUID, $_uUID);
        }
      }
      final Scope<Address> _function_2 = new Scope<Address>() {
        @Override
        public boolean matches(final Address it) {
          UUID _uUID = it.getUUID();
          UUID _uUID_1 = occurrence.getSource().getUUID();
          return Objects.equal(_uUID, _uUID_1);
        }
        private Object writeReplace() throws ObjectStreamException {
          return new SerializableProxy($SerializableClosureProxy_2.class, occurrence.getSource().getUUID());
        }
      };
      _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.emit(_influenceEvent_2, _function_2);
      this.Behaviourchange(beh);
    }
  }
  
  public Types CalculateBeh(final ArrayList<Percept> perceptions) {
    int numP = 0;
    int numA = 0;
    int numN = 0;
    int numC = 0;
    for (final Percept percept : perceptions) {
      Types _name = percept.getName();
      if (_name != null) {
        switch (_name) {
          case protestor_panic:
            numP++;
            break;
          case protestor_agg:
            numA++;
            break;
          case protestor_neutral:
            numN++;
            break;
          case crs:
            numC++;
            break;
          default:
            break;
        }
      }
    }
    double b = this.memory.getBehavior();
    if ((b < ParamSimu.maxPanic)) {
      return Types.protestor_panic;
    } else {
      if ((b > ParamSimu.minAggressive)) {
        return Types.protestor_agg;
      } else {
        return null;
      }
    }
  }
  
  public void Behaviourchange(final Types newbeh) {
    Agent _owner = this.getOwner();
    Protestor o = ((Protestor) _owner);
    o.changeBehavior(newbeh);
  }
  
  private void $behaviorUnit$AgentSpawned$3(final AgentSpawned occurrence) {
  }
  
  private void $behaviorUnit$AgentKilled$4(final AgentKilled occurrence) {
  }
  
  private void $behaviorUnit$ContextJoined$5(final ContextJoined occurrence) {
  }
  
  private void $behaviorUnit$ContextLeft$6(final ContextLeft occurrence) {
  }
  
  private void $behaviorUnit$MemberJoined$7(final MemberJoined occurrence) {
  }
  
  private void $behaviorUnit$MemberLeft$8(final MemberLeft occurrence) {
  }
  
  private void $behaviorUnit$MemberLeft$9(final MemberLeft occurrence) {
  }
  
  private void $behaviorUnit$SpaceCreated$10(final SpaceCreated occurrence) {
  }
  
  private void $behaviorUnit$SpaceDestroyed$11(final SpaceDestroyed occurrence) {
  }
  
  private void $behaviorUnit$ParticipantJoined$12(final ParticipantJoined occurrence) {
  }
  
  private void $behaviorUnit$ParticipantLeft$13(final ParticipantLeft occurrence) {
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
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SpaceDestroyed(final SpaceDestroyed occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SpaceDestroyed$11(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MemberLeft(final MemberLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberLeft$8(occurrence));
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberLeft$9(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SpaceCreated(final SpaceCreated occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SpaceCreated$10(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$AgentSpawned(final AgentSpawned occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentSpawned$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ParticipantJoined(final ParticipantJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ParticipantJoined$12(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$AgentKilled(final AgentKilled occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentKilled$4(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MemberJoined(final MemberJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberJoined$7(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ContextLeft(final ContextLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ContextLeft$6(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ContextJoined(final ContextJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ContextJoined$5(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ParticipantLeft(final ParticipantLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ParticipantLeft$13(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$PerceptionEvent(final PerceptionEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$PerceptionEvent$2(occurrence));
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
}
