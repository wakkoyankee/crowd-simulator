package crs_sim.environment;

import com.google.common.base.Objects;
import crs_sim.body.CRSBody;
import crs_sim.body.Destroyable;
import crs_sim.body.EnvObject;
import crs_sim.body.MobileObject;
import crs_sim.body.ProtestorBody;
import crs_sim.environment.Influence;
import crs_sim.environment.InfluenceEvent;
import crs_sim.environment.QTNode;
import crs_sim.ui.Window;
import crs_sim.utils.Action;
import crs_sim.utils.GraphSearch_Utils;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.ContextJoined;
import io.sarl.core.ContextLeft;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.core.MemberJoined;
import io.sarl.core.MemberLeft;
import io.sarl.core.ParticipantJoined;
import io.sarl.core.ParticipantLeft;
import io.sarl.core.Schedules;
import io.sarl.core.SpaceCreated;
import io.sarl.core.SpaceDestroyed;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Queue;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author hadrien
 */
@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class EnvironmentAgent extends Agent {
  private QTNode rootTree;
  
  private AtomicInteger agentSpawned = new AtomicInteger(0);
  
  private TreeMap<UUID, ProtestorBody> protestorList;
  
  private TreeMap<UUID, CRSBody> crsList;
  
  private Window win;
  
  private AtomicInteger protestorResp = new AtomicInteger(0);
  
  private AtomicInteger crsResp = new AtomicInteger(0);
  
  private Queue<Influence> influenceList = new ConcurrentLinkedQueue<Influence>();
  
  private TreeMap<UUID, Destroyable> aggObjs;
  
  private ArrayList<EnvObject> bodiesDespawned;
  
  private int time = 0;
  
  private int nbPro = 0;
  
  private int nbCrs = 0;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    throw new Error("Unresolved compilation problems:"
      + "\nType mismatch: cannot convert from Class<Protestor> to Class<? extends Agent>");
  }
  
  private void $behaviorUnit$AgentSpawned$1(final AgentSpawned occurrence) {
    int cpt = this.agentSpawned.incrementAndGet();
    if ((this.agentSpawned != null ? (this.agentSpawned.intValue() == (this.nbPro + this.nbCrs)) : false)) {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("All agents have spawned");
      this.StartSim();
    }
  }
  
  protected void StartSim() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method getType() is undefined for the type ProtestorBody"
      + "\n!= cannot be resolved");
  }
  
  protected void redraw() {
    this.win.update(GraphSearch_Utils.BFS(this.rootTree));
  }
  
  private void $behaviorUnit$InfluenceEvent$2(final InfluenceEvent occurrence) {
    int cpt = 0;
    this.influenceList.add(occurrence.influence);
    boolean _isProtestor = occurrence.influence.isProtestor();
    if (_isProtestor) {
      int _incrementAndGet = this.protestorResp.incrementAndGet();
      int _get = this.crsResp.get();
      cpt = (_incrementAndGet + _get);
    } else {
      int _incrementAndGet_1 = this.crsResp.incrementAndGet();
      int _get_1 = this.protestorResp.get();
      cpt = (_incrementAndGet_1 + _get_1);
    }
    if ((cpt == (this.nbPro + this.nbCrs))) {
      this.protestorResp.set(0);
      for (final Influence influence : this.influenceList) {
        {
          Action action = influence.getAction();
          boolean _equals = Objects.equal(action, Action.move);
          if (_equals) {
            MobileObject body = null;
            boolean _isProtestor_1 = influence.isProtestor();
            if (_isProtestor_1) {
              ProtestorBody _get_2 = this.protestorList.get(influence.getUuid());
              body = _get_2;
            } else {
              CRSBody _get_3 = this.crsList.get(influence.getUuid());
              body = _get_3;
            }
            Shape2d<?> _area = body.getArea();
            Vector2d _move = influence.getMove();
            Circle2d move = ((Circle2d) _area).operator_plus(_move);
            double _x = move.getX();
            double _y = move.getY();
            Point2d _point2d = new Point2d(_x, _y);
            this.rootTree.moveBody(body, _point2d);
          } else {
            boolean _equals_1 = Objects.equal(action, Action.arrest);
            if (_equals_1) {
            } else {
              boolean _equals_2 = Objects.equal(action, Action.destroy);
              if (_equals_2) {
              } else {
                boolean _equals_3 = Objects.equal(action, Action.despawn);
                if (_equals_3) {
                } else {
                  boolean _equals_4 = Objects.equal(action, Action.smoke);
                  if (_equals_4) {
                  } else {
                    boolean _equals_5 = Objects.equal(action, Action.changeBeh);
                    if (_equals_5) {
                    }
                  }
                }
              }
            }
          }
        }
      }
      this.redraw();
      this.influenceList.clear();
      this.StartSim();
    }
  }
  
  protected void resolveInfluences(final ArrayList<Influence> influences) {
    for (final Influence influence : influences) {
      {
        Action action = influence.getAction();
        boolean _equals = Objects.equal(action, Action.move);
        if (_equals) {
        } else {
          boolean _equals_1 = Objects.equal(action, Action.arrest);
          if (_equals_1) {
          } else {
            boolean _equals_2 = Objects.equal(action, Action.destroy);
            if (_equals_2) {
            }
          }
        }
      }
    }
  }
  
  private void $behaviorUnit$Destroy$3(final Destroy occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent environment was stopped.");
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
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
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
  
  @Extension
  @ImportedCapacityFeature(Schedules.class)
  @SyntheticMember
  private transient AtomicSkillReference $CAPACITY_USE$IO_SARL_CORE_SCHEDULES;
  
  @SyntheticMember
  @Pure
  private Schedules $CAPACITY_USE$IO_SARL_CORE_SCHEDULES$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES == null || this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES = $getSkill(Schedules.class);
    }
    return $castSkill(Schedules.class, this.$CAPACITY_USE$IO_SARL_CORE_SCHEDULES);
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
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentSpawned$1(occurrence));
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
  private void $guardEvaluator$InfluenceEvent(final InfluenceEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$InfluenceEvent$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Destroy(final Destroy occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Destroy$3(occurrence));
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    EnvironmentAgent other = (EnvironmentAgent) obj;
    if (other.time != this.time)
      return false;
    if (other.nbPro != this.nbPro)
      return false;
    if (other.nbCrs != this.nbCrs)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.time);
    result = prime * result + Integer.hashCode(this.nbPro);
    result = prime * result + Integer.hashCode(this.nbCrs);
    return result;
  }
  
  @SyntheticMember
  public EnvironmentAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public EnvironmentAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public EnvironmentAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
