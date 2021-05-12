package crs_sim.environment;

import com.google.common.base.Objects;
import crs_sim.agent.Protestor;
import crs_sim.body.Building;
import crs_sim.body.CRSBody;
import crs_sim.body.EnvObject;
import crs_sim.body.MobileObject;
import crs_sim.body.ProtestorBody;
import crs_sim.environment.Influence;
import crs_sim.environment.InfluenceEvent;
import crs_sim.environment.Percept;
import crs_sim.environment.PerceptionEvent;
import crs_sim.environment.QTNode;
import crs_sim.ui.Window;
import crs_sim.utils.CRS_Sim_Utils;
import crs_sim.utils.GraphSearch_Utils;
import crs_sim.utils.ParamSimu;
import crs_sim.utils.Types;
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
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Scope;
import io.sarl.lang.util.SerializableProxy;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Shape2d;
import org.arakhne.afc.math.geometry.d2.d.Vector2d;
import org.eclipse.xtext.xbase.lib.CollectionExtensions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author hadrien
 */
@SarlSpecification("0.11")
@SarlElementType(19)
@SuppressWarnings("all")
public class EnvironmentAgent extends Agent {
  private QTNode rootTree;
  
  private int agentSpawned = 0;
  
  private Map<UUID, ProtestorBody> protestorList;
  
  private Window win;
  
  private AtomicInteger protestorResp = new AtomicInteger(0);
  
  private List<Influence> influenceList = new ArrayList<Influence>();
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent environment was started.");
    TreeMap<UUID, ProtestorBody> _treeMap = new TreeMap<UUID, ProtestorBody>();
    this.protestorList = _treeMap;
    Random rand = new Random();
    int[] b = new int[ParamSimu.nbProtestors];
    int[] t = new int[ParamSimu.nbProtestors];
    for (int i = 0; (i < ParamSimu.nbProtestors); i++) {
      {
        int r1 = rand.nextInt(101);
        int r2 = rand.nextInt(11);
        b[i] = r1;
        t[i] = r2;
        ProtestorBody pb = null;
        if ((r1 < ParamSimu.maxPanic)) {
          Circle2d _get = ParamSimu.initCollecProtestor[i];
          ProtestorBody _protestorBody = new ProtestorBody(_get, Types.protestor_panic);
          pb = _protestorBody;
        } else {
          if ((r1 > ParamSimu.minAggressive)) {
            Circle2d _get_1 = ParamSimu.initCollecProtestor[i];
            ProtestorBody _protestorBody_1 = new ProtestorBody(_get_1, Types.protestor_agg);
            pb = _protestorBody_1;
          } else {
            Circle2d _get_2 = ParamSimu.initCollecProtestor[i];
            ProtestorBody _protestorBody_2 = new ProtestorBody(_get_2, Types.protestor_neutral);
            pb = _protestorBody_2;
          }
        }
        this.protestorList.put(pb.getUuid(), pb);
      }
    }
    ArrayList<EnvObject> collec = new ArrayList<EnvObject>();
    CollectionExtensions.<CRSBody>addAll(collec, ParamSimu.initCollecCRS);
    CollectionExtensions.<Building>addAll(collec, ParamSimu.initplaceDesJacobins);
    Rectangle2d _rectangle2d = new Rectangle2d(0, 0, ParamSimu.mapSizeX, ParamSimu.mapSizeY);
    QTNode _qTNode = new QTNode(_rectangle2d);
    this.rootTree = _qTNode;
    CRS_Sim_Utils.buildTree(this.rootTree, collec);
    Set<Map.Entry<UUID, ProtestorBody>> _entrySet = this.protestorList.entrySet();
    for (final Map.Entry<UUID, ProtestorBody> entry : _entrySet) {
      this.rootTree.insert(entry.getValue());
    }
    InputOutput.<String>println(("Père : " + this.rootTree));
    QTNode _firstChild = this.rootTree.getFirstChild();
    InputOutput.<String>println(("NW : " + _firstChild));
    QTNode _secondChild = this.rootTree.getSecondChild();
    InputOutput.<String>println(("NE : " + _secondChild));
    QTNode _thirdChild = this.rootTree.getThirdChild();
    InputOutput.<String>println(("SW : " + _thirdChild));
    QTNode _fourthChild = this.rootTree.getFourthChild();
    InputOutput.<String>println(("SE : " + _fourthChild));
    EnvObject _get = collec.get(5);
    String _plus = (_get + " : \n");
    InputOutput.<String>print(_plus);
    InputOutput.<Shape2d<?>>print(collec.get(5).getArea());
    EnvObject _get_1 = collec.get(5);
    Point2d _point2d = new Point2d(800, 800);
    this.rootTree.moveBodyPasOpti(((MobileObject) _get_1), _point2d);
    EnvObject _get_2 = collec.get(5);
    String _plus_1 = (_get_2 + " en 800 800: \n");
    InputOutput.<String>print(_plus_1);
    InputOutput.<Shape2d<?>>print(collec.get(5).getArea());
    InputOutput.<String>println(("Père : " + this.rootTree));
    QTNode _firstChild_1 = this.rootTree.getFirstChild();
    InputOutput.<String>println(("NW : " + _firstChild_1));
    QTNode _secondChild_1 = this.rootTree.getSecondChild();
    InputOutput.<String>println(("NE : " + _secondChild_1));
    QTNode _thirdChild_1 = this.rootTree.getThirdChild();
    InputOutput.<String>println(("SW : " + _thirdChild_1));
    QTNode _fourthChild_1 = this.rootTree.getFourthChild();
    InputOutput.<String>println(("SE : " + _fourthChild_1));
    int cpt = 0;
    Set<Map.Entry<UUID, ProtestorBody>> _entrySet_1 = this.protestorList.entrySet();
    for (final Map.Entry<UUID, ProtestorBody> entry_1 : _entrySet_1) {
      {
        Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(Protestor.class, entry_1.getKey(), _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext(), Integer.valueOf(b[cpt]), Integer.valueOf(t[cpt]));
        cpt++;
      }
    }
  }
  
  private void $behaviorUnit$AgentSpawned$1(final AgentSpawned occurrence) {
    this.agentSpawned++;
    if ((this.agentSpawned == ParamSimu.nbProtestors)) {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("All agents have spawned");
      this.StartSim();
    }
  }
  
  protected void StartSim() {
    Set<Map.Entry<UUID, ProtestorBody>> _entrySet = this.protestorList.entrySet();
    for (final Map.Entry<UUID, ProtestorBody> entry : _entrySet) {
      {
        UUID id = entry.getKey();
        Shape2d<?> body = entry.getValue().getArea();
        List<Percept> percept = GraphSearch_Utils.PerceptBFS(this.rootTree, ((Circle2d) body));
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        PerceptionEvent _perceptionEvent = new PerceptionEvent(body, percept, 0);
        class $SerializableClosureProxy implements Scope<Address> {
          
          private final UUID id;
          
          public $SerializableClosureProxy(final UUID id) {
            this.id = id;
          }
          
          @Override
          public boolean matches(final Address it) {
            UUID _uUID = it.getUUID();
            return Objects.equal(_uUID, id);
          }
        }
        final Scope<Address> _function = new Scope<Address>() {
          @Override
          public boolean matches(final Address it) {
            UUID _uUID = it.getUUID();
            return Objects.equal(_uUID, id);
          }
          private Object writeReplace() throws ObjectStreamException {
            return new SerializableProxy($SerializableClosureProxy.class, id);
          }
        };
        _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_perceptionEvent, _function);
      }
    }
  }
  
  protected void redraw() {
    List<Percept> _BFS = GraphSearch_Utils.BFS(this.rootTree);
    this.win.update(((ArrayList) _BFS));
  }
  
  private void $behaviorUnit$InfluenceEvent$2(final InfluenceEvent occurrence) {
    int cpt = this.protestorResp.getAndIncrement();
    this.influenceList.add(occurrence.influence);
    int _size = this.protestorList.size();
    if ((cpt == _size)) {
      this.protestorResp.set(0);
      for (final Influence influence : this.influenceList) {
        {
          ProtestorBody body = this.protestorList.get(influence.getUuid());
          Shape2d<?> _area = body.getArea();
          Vector2d _move = influence.getMove();
          Circle2d move = ((Circle2d) _area).operator_plus(_move);
          double _x = move.getX();
          double _y = move.getY();
          Point2d _point2d = new Point2d(_x, _y);
          this.rootTree.moveBodyPasOpti(body, _point2d);
        }
      }
      this.influenceList.clear();
    }
    for (int i = 0; (i < this.protestorList.size()); i++) {
      UUID _uuid = this.protestorList.get(Integer.valueOf(i)).getUuid();
      UUID _uUID = occurrence.getSource().getUUID();
      boolean _equals = Objects.equal(_uuid, _uUID);
      if (_equals) {
        break;
      }
    }
  }
  
  protected void resolveInfluences() {
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
    if (other.agentSpawned != this.agentSpawned)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Integer.hashCode(this.agentSpawned);
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
