package schema;

import graph.OpenMapAdjacencyMatrix;
import org.apache.commons.math3.linear.RealMatrix;


import java.util.*;

/**
 * Created by darbour on 11/19/14.
 */
public class Schema {
    List<Entity> entities;
    List<Relationship> relationships;

    /**
     * Create an empty schema
     */
    public Schema(){
        entities = new ArrayList<Entity>();
        relationships = new ArrayList<Relationship>();
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public void addRelationship(Relationship rel) { relationships.add(rel); }

    public Entity getEntity(int entityIndex) { return entities.get(entityIndex); }

    /**
     * Find all the items that are reachable along the path
     *
     * @param path
     * @return
     */
    public RealMatrix getItemsForPath(RelationalPath path, boolean burnBridges) throws Exception{
        /* iterate over each link in the path */
        Iterator<RelationalPath.RelationalLink>  pathIter = path.iterator();
        /* start with the first relationship's adjacency matrix */
        OpenMapAdjacencyMatrix reachableItems = pathIter.next().getAdjacencyMatrix();
        Map<Entity, OpenMapAdjacencyMatrix> alreadyVisited;
        alreadyVisited = new HashMap<Entity, OpenMapAdjacencyMatrix>();

        while(pathIter.hasNext()){
            RelationalPath.RelationalLink currentLink = pathIter.next();
            OpenMapAdjacencyMatrix currentAdjacencyMatrix = currentLink.getAdjacencyMatrix();
            reachableItems = reachableItems.multiply(currentAdjacencyMatrix);

            if(burnBridges){
                if(alreadyVisited.containsKey(currentLink.to)){
                    reachableItems = reachableItems.getReachable(alreadyVisited.get(currentLink.to));
                    alreadyVisited.replace(currentLink.to, alreadyVisited.get(currentLink.to).union(reachableItems));
                } else{
                    alreadyVisited.put(currentLink.to, reachableItems);
                }
            }
        }

        return reachableItems;
    }

}
