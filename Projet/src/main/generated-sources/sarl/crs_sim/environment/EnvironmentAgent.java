package crs_sim.environment;

import crs_sim.agent.Protestor;
import crs_sim.body.Building;
import crs_sim.body.CRSBody;
import crs_sim.body.EnvObject;
import crs_sim.body.ProtestorBody;
import crs_sim.environment.QTNode;
import crs_sim.ui.Window;
import crs_sim.utils.CRS_Sim_Utils;
import crs_sim.utils.ParamSimu;
import io.sarl.core.AgentKilled;
import io.sarl.core.AgentSpawned;
import io.sarl.core.ContextJoined;
import io.sarl.core.ContextLeft;
import io.sarl.core.Destroy;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
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
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.AtomicSkillReference;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
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
  
  private ArrayList<EnvObject> collec = new ArrayList<EnvObject>();
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent environment was started.");
    CollectionExtensions.<ProtestorBody>addAll(this.collec, ParamSimu.initCollecProtestor);
    CollectionExtensions.<CRSBody>addAll(this.collec, ParamSimu.initCollecCRS);
    CollectionExtensions.<Building>addAll(this.collec, ParamSimu.initplaceDesJacobins);
    Rectangle2d _rectangle2d = new Rectangle2d(0, 0, ParamSimu.mapSizeX, ParamSimu.mapSizeY);
    QTNode _qTNode = new QTNode(_rectangle2d);
    this.rootTree = _qTNode;
    CRS_Sim_Utils.buildTree(this.rootTree, this.collec);
    InputOutput.<String>println(("Père : " + this.rootTree));
    QTNode _firstChild = this.rootTree.getFirstChild();
    InputOutput.<String>println(("NW : " + _firstChild));
    QTNode _secondChild = this.rootTree.getSecondChild();
    InputOutput.<String>println(("NE : " + _secondChild));
    QTNode _thirdChild = this.rootTree.getThirdChild();
    InputOutput.<String>println(("SW : " + _thirdChild));
    QTNode _fourthChild = this.rootTree.getFourthChild();
    InputOutput.<String>println(("SE : " + _fourthChild));
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.spawn(Protestor.class, Integer.valueOf(1), Integer.valueOf(2));
    Window win = new Window();
  }
  
  private void $behaviorUnit$Destroy$1(final Destroy occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER();
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The agent environment was stopped.");
  }
  
  private void $behaviorUnit$AgentSpawned$2(final AgentSpawned occurrence) {
  }
  
  private void $behaviorUnit$AgentKilled$3(final AgentKilled occurrence) {
  }
  
  private void $behaviorUnit$ContextJoined$4(final ContextJoined occurrence) {
  }
  
  private void $behaviorUnit$ContextLeft$5(final ContextLeft occurrence) {
  }
  
  private void $behaviorUnit$MemberJoined$6(final MemberJoined occurrence) {
  }
  
  private void $behaviorUnit$MemberLeft$7(final MemberLeft occurrence) {
  }
  
  private void $behaviorUnit$MemberLeft$8(final MemberLeft occurrence) {
  }
  
  private void $behaviorUnit$SpaceCreated$9(final SpaceCreated occurrence) {
  }
  
  private void $behaviorUnit$SpaceDestroyed$10(final SpaceDestroyed occurrence) {
  }
  
  private void $behaviorUnit$ParticipantJoined$11(final ParticipantJoined occurrence) {
  }
  
  private void $behaviorUnit$ParticipantLeft$12(final ParticipantLeft occurrence) {
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
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ContextLeft$5(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SpaceDestroyed(final SpaceDestroyed occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SpaceDestroyed$10(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ContextJoined(final ContextJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ContextJoined$4(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MemberLeft(final MemberLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberLeft$7(occurrence));
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberLeft$8(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SpaceCreated(final SpaceCreated occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SpaceCreated$9(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$AgentSpawned(final AgentSpawned occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentSpawned$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ParticipantJoined(final ParticipantJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ParticipantJoined$11(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ParticipantLeft(final ParticipantLeft occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ParticipantLeft$12(occurrence));
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
  private void $guardEvaluator$AgentKilled(final AgentKilled occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$AgentKilled$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$MemberJoined(final MemberJoined occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$MemberJoined$6(occurrence));
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