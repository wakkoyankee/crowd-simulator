package crs_sim.utils;

import crs_sim.body.EnvObject;
import crs_sim.environment.DefaultQuadTreeNode;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import java.util.Collection;

/**
 * @author Thomas
 */
@SarlSpecification("0.11")
@SarlElementType(10)
@SuppressWarnings("all")
public class DefaultTreeBuilder {
  private DefaultTreeBuilder() {
  }
  
  public DefaultQuadTreeNode buildTree(final DefaultQuadTreeNode tree, final Collection<EnvObject> collec) {
    for (final EnvObject element : collec) {
      tree.insert(element);
    }
    return tree;
  }
}