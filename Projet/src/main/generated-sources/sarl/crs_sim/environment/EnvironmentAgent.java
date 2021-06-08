package crs_sim.environment;

import com.google.common.base.Objects;
import crs_sim.agent.CRS;
import crs_sim.agent.Protestor;
import crs_sim.body.Building;
import crs_sim.body.CRSBody;
import crs_sim.body.Destroyable;
import crs_sim.body.EnvObject;
import crs_sim.body.MobileObject;
import crs_sim.body.ProtestorBody;
import crs_sim.environment.DeathEvent;
import crs_sim.environment.Influence;
import crs_sim.environment.InfluenceEvent;
import crs_sim.environment.Percept;
import crs_sim.environment.PerceptionEvent;
import crs_sim.environment.QTNode;
import crs_sim.ui.Window;
import crs_sim.utils.Action;
import crs_sim.utils.CRS_Sim_Utils;
import crs_sim.utils.GraphSearch_Utils;
import crs_sim.utils.ParamSimu;
import crs_sim.utils.Types;
import crs_sim.utils.jsonGenerator_Utils;
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
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.inject.Inject;
import org.arakhne.afc.math.geometry.d2.d.Circle2d;
import org.arakhne.afc.math.geometry.d2.d.Point2d;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.geometry.d2.d.Segment2d;
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
  
  private AtomicInteger agentSpawned = new AtomicInteger(0);
  
  private TreeMap<UUID, ProtestorBody> protestorList;
  
  private TreeMap<UUID, CRSBody> crsList;
  
  private Window win;
  
  private AtomicInteger agentResp = new AtomicInteger(0);
  
  private ConcurrentSkipListMap<UUID, Influence> influenceList = new ConcurrentSkipListMap<UUID, Influence>();
  
  private TreeMap<UUID, Destroyable> aggObjs;
  
  private ArrayList<EnvObject> bodiesDespawned;
  
  private int time = 0;
  
  private int nbPro = 0;
  
  private int nbCrs = 0;
  
  private ArrayList<EnvObject> save;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent environment was started.");
    TreeMap<UUID, ProtestorBody> _treeMap = new TreeMap<UUID, ProtestorBody>();
    this.protestorList = _treeMap;
    Random rand = new Random();
    double[] b = new double[ParamSimu.nbProtestors];
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
    TreeMap<UUID, Destroyable> _treeMap_1 = new TreeMap<UUID, Destroyable>();
    this.aggObjs = _treeMap_1;
    for (final Destroyable destroyable : ParamSimu.aggObjs) {
      this.aggObjs.put(destroyable.getUuid(), destroyable);
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
    Set<Map.Entry<UUID, Destroyable>> _entrySet_1 = this.aggObjs.entrySet();
    for (final Map.Entry<UUID, Destroyable> entry_1 : _entrySet_1) {
      this.rootTree.insert(entry_1.getValue());
    }
    TreeMap<UUID, CRSBody> _treeMap_2 = new TreeMap<UUID, CRSBody>();
    this.crsList = _treeMap_2;
    for (int i = 0; (i < ParamSimu.initCollecCRS.length); i++) {
      this.crsList.put(ParamSimu.initCollecCRS[i].getUuid(), ParamSimu.initCollecCRS[i]);
    }
    this.nbPro = this.protestorList.size();
    this.nbCrs = this.crsList.size();
    Window _window = new Window();
    this.win = _window;
    this.redraw();
    int cpt = 0;
    Set<Map.Entry<UUID, ProtestorBody>> _entrySet_2 = this.protestorList.entrySet();
    for (final Map.Entry<UUID, ProtestorBody> entry_2 : _entrySet_2) {
      {
        Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
        DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
        _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(Protestor.class, entry_2.getKey(), _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext(), Double.valueOf(b[cpt]), Integer.valueOf(t[cpt]));
        cpt++;
      }
    }
    Set<Map.Entry<UUID, CRSBody>> _entrySet_3 = this.crsList.entrySet();
    for (final Map.Entry<UUID, CRSBody> entry_3 : _entrySet_3) {
      Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
      DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
      _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawnInContextWithID(CRS.class, entry_3.getKey(), _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.getDefaultContext());
    }
    jsonGenerator_Utils.jsonGenerator(this.rootTree);
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
    Set<Map.Entry<UUID, ProtestorBody>> _entrySet = this.protestorList.entrySet();
    for (final Map.Entry<UUID, ProtestorBody> entry : _entrySet) {
      {
        UUID id = entry.getKey();
        Shape2d<?> _area = entry.getValue().getArea();
        Circle2d body = ((Circle2d) _area);
        double _x = body.getX();
        double _y = body.getY();
        Circle2d perceptionZone = new Circle2d(_x, _y, ParamSimu.RadiusPerceptProtestor);
        List<Percept> percept = GraphSearch_Utils.PerceptBFS(this.rootTree, perceptionZone);
        Shape2d<?> obj = null;
        UUID uuidObj = null;
        Types _type = entry.getValue().getType();
        boolean _notEquals = (!Objects.equal(_type, Types.protestor_agg));
        if (_notEquals) {
          obj = ParamSimu.neutralObj;
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
          PerceptionEvent _perceptionEvent = new PerceptionEvent(body, percept, obj, uuidObj, 0);
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
        } else {
          boolean seeAggObj = false;
          for (final Percept p : percept) {
            Types _name = p.getName();
            boolean _equals = Objects.equal(_name, Types.destroyable);
            if (_equals) {
              obj = p.getShape();
              DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
              PerceptionEvent _perceptionEvent_1 = new PerceptionEvent(body, percept, obj, uuidObj, 0, false);
              class $SerializableClosureProxy_1 implements Scope<Address> {
                
                private final UUID id;
                
                public $SerializableClosureProxy_1(final UUID id) {
                  this.id = id;
                }
                
                @Override
                public boolean matches(final Address it) {
                  UUID _uUID = it.getUUID();
                  return Objects.equal(_uUID, id);
                }
              }
              final Scope<Address> _function_1 = new Scope<Address>() {
                @Override
                public boolean matches(final Address it) {
                  UUID _uUID = it.getUUID();
                  return Objects.equal(_uUID, id);
                }
                private Object writeReplace() throws ObjectStreamException {
                  return new SerializableProxy($SerializableClosureProxy_1.class, id);
                }
              };
              _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(_perceptionEvent_1, _function_1);
              seeAggObj = true;
              break;
            }
          }
          if ((!seeAggObj)) {
            obj = ParamSimu.neutralObj;
            DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
            PerceptionEvent _perceptionEvent_2 = new PerceptionEvent(body, percept, obj, uuidObj, 0, true);
            class $SerializableClosureProxy_2 implements Scope<Address> {
              
              private final UUID id;
              
              public $SerializableClosureProxy_2(final UUID id) {
                this.id = id;
              }
              
              @Override
              public boolean matches(final Address it) {
                UUID _uUID = it.getUUID();
                return Objects.equal(_uUID, id);
              }
            }
            final Scope<Address> _function_2 = new Scope<Address>() {
              @Override
              public boolean matches(final Address it) {
                UUID _uUID = it.getUUID();
                return Objects.equal(_uUID, id);
              }
              private Object writeReplace() throws ObjectStreamException {
                return new SerializableProxy($SerializableClosureProxy_2.class, id);
              }
            };
            _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_2.emit(_perceptionEvent_2, _function_2);
          }
        }
      }
    }
    Set<Map.Entry<UUID, CRSBody>> _entrySet_1 = this.crsList.entrySet();
    for (final Map.Entry<UUID, CRSBody> entry_1 : _entrySet_1) {
      {
        UUID id = entry_1.getKey();
        Shape2d<?> _area = entry_1.getValue().getArea();
        Circle2d body = ((Circle2d) _area);
        double _x = body.getX();
        double _y = body.getY();
        Circle2d perceptionZone = new Circle2d(_x, _y, ParamSimu.RadiusPerceptCrs);
        List<Percept> percept = GraphSearch_Utils.PerceptBFS(this.rootTree, perceptionZone);
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
    this.win.update(GraphSearch_Utils.BFS(this.rootTree));
  }
  
  private void $behaviorUnit$InfluenceEvent$2(final InfluenceEvent occurrence) {
    this.influenceList.put(occurrence.influence.getUuid(), occurrence.influence);
    int _incrementAndGet = this.agentResp.incrementAndGet();
    if ((_incrementAndGet == (this.nbPro + this.nbCrs))) {
      this.agentResp.set(0);
      Set<Map.Entry<UUID, Influence>> _entrySet = this.influenceList.entrySet();
      for (final Map.Entry<UUID, Influence> entry : _entrySet) {
        {
          Influence influence = entry.getValue();
          Action action = influence.getAction();
          boolean _equals = Objects.equal(action, Action.move);
          if (_equals) {
            MobileObject body = null;
            boolean _isProtestor = influence.isProtestor();
            if (_isProtestor) {
              ProtestorBody _get = this.protestorList.get(influence.getUuid());
              body = _get;
            } else {
              CRSBody _get_1 = this.crsList.get(influence.getUuid());
              body = _get_1;
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
              DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
              DeathEvent _deathEvent = new DeathEvent();
              class $SerializableClosureProxy implements Scope<Address> {
                
                private final UUID $_target;
                
                public $SerializableClosureProxy(final UUID $_target) {
                  this.$_target = $_target;
                }
                
                @Override
                public boolean matches(final Address it) {
                  UUID _uUID = it.getUUID();
                  return Objects.equal(_uUID, $_target);
                }
              }
              final Scope<Address> _function = new Scope<Address>() {
                @Override
                public boolean matches(final Address it) {
                  UUID _uUID = it.getUUID();
                  UUID _target = influence.getTarget();
                  return Objects.equal(_uUID, _target);
                }
                private Object writeReplace() throws ObjectStreamException {
                  return new SerializableProxy($SerializableClosureProxy.class, influence.getTarget());
                }
              };
              _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_deathEvent, _function);
              this.rootTree.deleteBody(this.protestorList.get(influence.getTarget()), this.save);
              this.protestorList.remove(influence.getTarget());
            } else {
              boolean _equals_2 = Objects.equal(action, Action.despawn);
              if (_equals_2) {
                DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER();
                DeathEvent _deathEvent_1 = new DeathEvent();
                class $SerializableClosureProxy_1 implements Scope<Address> {
                  
                  private final UUID $_uuid;
                  
                  public $SerializableClosureProxy_1(final UUID $_uuid) {
                    this.$_uuid = $_uuid;
                  }
                  
                  @Override
                  public boolean matches(final Address it) {
                    UUID _uUID = it.getUUID();
                    return Objects.equal(_uUID, $_uuid);
                  }
                }
                final Scope<Address> _function_1 = new Scope<Address>() {
                  @Override
                  public boolean matches(final Address it) {
                    UUID _uUID = it.getUUID();
                    UUID _uuid = influence.getUuid();
                    return Objects.equal(_uUID, _uuid);
                  }
                  private Object writeReplace() throws ObjectStreamException {
                    return new SerializableProxy($SerializableClosureProxy_1.class, influence.getUuid());
                  }
                };
                _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(_deathEvent_1, _function_1);
                this.rootTree.deleteBody(this.protestorList.get(influence.getUuid()), this.save);
                this.protestorList.remove(influence.getUuid());
                this.nbPro--;
              } else {
                boolean _equals_3 = Objects.equal(action, Action.smoke);
                if (_equals_3) {
                } else {
                  boolean _equals_4 = Objects.equal(action, Action.changeBeh);
                  if (_equals_4) {
                    ProtestorBody _get_2 = this.protestorList.get(influence.getUuid());
                    ProtestorBody body_1 = _get_2;
                    body_1.setType(influence.getAgentType());
                    this.protestorList.replace(influence.getUuid(), body_1);
                  }
                }
              }
            }
          }
        }
      }
      this.redraw();
      this.influenceList.clear();
      this.time++;
      if ((this.time < 15000)) {
        InputOutput.<Integer>println(Integer.valueOf(this.time));
        this.StartSim();
      }
    }
  }
  
  protected void resolveInfluences(final ConcurrentSkipListMap<UUID, Influence> influences) {
    ArrayList<UUID> ids = new ArrayList<UUID>();
    ArrayList<Segment2d> segs = new ArrayList<Segment2d>();
    Set<Map.Entry<UUID, Influence>> _entrySet = influences.entrySet();
    for (final Map.Entry<UUID, Influence> entry : _entrySet) {
      {
        Influence influence = entry.getValue();
        Action action = influence.getAction();
        boolean _equals = Objects.equal(action, Action.move);
        if (_equals) {
          MobileObject body = null;
          boolean _isProtestor = influence.isProtestor();
          if (_isProtestor) {
            ProtestorBody _get = this.protestorList.get(influence.getUuid());
            body = _get;
          } else {
            CRSBody _get_1 = this.crsList.get(influence.getUuid());
            body = _get_1;
          }
          Shape2d<?> _area = body.getArea();
          Circle2d pos = ((Circle2d) _area);
          Vector2d _move = influence.getMove();
          Circle2d move = pos.operator_plus(_move);
          ids.add(influence.getUuid());
          double _x = pos.getX();
          double _y = pos.getY();
          Point2d _point2d = new Point2d(_x, _y);
          double _x_1 = move.getX();
          double _y_1 = move.getY();
          Point2d _point2d_1 = new Point2d(_x_1, _y_1);
          Segment2d _segment2d = new Segment2d(_point2d, _point2d_1);
          segs.add(_segment2d);
        }
      }
    }
    Random rand = new Random();
    for (int i = 0; (i < (segs.size() - 1)); i++) {
      for (int j = (i + 1); (j < segs.size()); j++) {
        {
          Segment2d seg2 = segs.get(j);
          boolean _intersects = segs.get(i).intersects(seg2);
          if (_intersects) {
            UUID id = null;
            int _nextInt = rand.nextInt(2);
            if ((_nextInt == 0)) {
              id = ids.get(i);
              segs.set(i, null);
              Influence inf = influences.get(id);
              Vector2d _vector2d = new Vector2d(0, 0);
              inf.setMove(_vector2d);
              influences.replace(id, inf);
              break;
            } else {
              id = ids.get(j);
              segs.set(j, null);
              Influence inf_1 = influences.get(id);
              Vector2d _vector2d_1 = new Vector2d(0, 0);
              inf_1.setMove(_vector2d_1);
              influences.replace(id, inf_1);
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
