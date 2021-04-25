package crs_sim.environment;

import crs_sim.body.EnvObject;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Collection;
import org.arakhne.afc.math.geometry.d2.d.Rectangle2d;
import org.arakhne.afc.math.tree.node.QuadTreeNode;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class DefaultQuadTreeNode extends QuadTreeNode<EnvObject, DefaultQuadTreeNode> {
  private Rectangle2d area;
  
  private QuadTreeNode tree;
  
  public DefaultQuadTreeNode() {
    super();
  }
  
  /**
   * Construct a node.
   * @param data are the initial user data
   */
  public DefaultQuadTreeNode(final Collection<EnvObject> collec) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method buildTree(DefaultQuadTreeNode, Collection<EnvObject>) is undefined for the type Class<DefaultTreeBuilder>");
  }
  
  public void insert(final EnvObject object) {
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
  private static final long serialVersionUID = -1749294451L;
}