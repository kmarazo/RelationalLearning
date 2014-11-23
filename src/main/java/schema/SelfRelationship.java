package schema;

import javax.smartcardio.Card;

/**
 * Created by darbour on 11/21/14.
 */
public class SelfRelationship extends Relationship {
    public SelfRelationship(String name, Entity entity){
        super(name, entity, entity, Cardinality.MANY, Cardinality.MANY);
    }

    /**
     * Given the indices of two entity instances, create a relationship between them.
     *
     * @param from the index of one entity
     * @param to the index of the other entity
     */
    public void addRelation(int from, int to){
        this.setAdjacencyMatrixEntry(from, to, 1);
    }
}
