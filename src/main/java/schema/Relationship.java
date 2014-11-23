package schema;

import graph.OpenMapAdjacencyMatrix;

import java.util.HashMap;
import java.util.Map;
import model.attribute.Attribute;

import java.util.List;
import java.util.ArrayList;

enum Cardinality{
    ONE,
    MANY
}

/**
 * Created by darbour on 11/19/14.
 */
public abstract class Relationship extends RelationalItem {
    /* unique identifier */
    private String name;
    /* an adjacency matrix that contains the connections between the two entities */
    private OpenMapAdjacencyMatrix adjacencyMatrix;
    /* we need to store orientation of the matrix */
    private Entity row;
    private Entity column;
    /* mapping to attributes */
    private Map<String, Attribute> attributes;
    /* get the cardinality mapping */
    private Map<Entity, Cardinality> cardinalities;

    public boolean hasEntity(Entity entity){
        return cardinalities.containsKey(entity);
    }

    public Relationship(String name, Entity from, Entity to, Cardinality fromCard, Cardinality toCard){
        super(name);
        this.row = from;
        this.column = to;
        this.cardinalities = new HashMap<Entity, Cardinality>();
        this.cardinalities.put(from, fromCard);
        this.cardinalities.put(to, toCard);
        this.adjacencyMatrix = new OpenMapAdjacencyMatrix(from.getNumberOfInstances(), to.getNumberOfInstances());
    }

    public List<Entity> getParticipatingEntities(){
        List<Entity> out = new ArrayList();
        out.add(row);
        out.add(column);
        return out;
    }

    public OpenMapAdjacencyMatrix getAdjacencyMatrix(Entity from, Entity to) throws Exception {
        if(!cardinalities.containsKey(from) || !(cardinalities.containsKey(to))){
            throw new Exception("One or more entities are not contained in this relationship");
        }

        /* return the adjacency matrix oriented (from, to) */
        if(from.equals(row) && to.equals(column)) {
            return this.adjacencyMatrix;
        } else {
            return (OpenMapAdjacencyMatrix) this.adjacencyMatrix.transpose();
        }
    }

    protected void setAdjacencyMatrixEntry(int i, int j, int value){
        adjacencyMatrix.setEntry(i,j,value);
    }

}
