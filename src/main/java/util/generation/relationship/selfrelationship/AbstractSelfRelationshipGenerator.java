package util.generation.relationship.selfrelationship;

import schema.Relationship;
import schema.SelfRelationship;
import schema.Entity;
import util.generation.relationship.RelationshipGenerator;

/**
 * Created by darbour on 11/21/14.
 */
public abstract class AbstractSelfRelationshipGenerator implements RelationshipGenerator {

    public abstract SelfRelationship generateRelationship(String name, Entity e);

    /**
     * Wrapper method
     * @param relationshipName
     * @param from
     * @param to
     * @return
     * @throws Exception
     */
    public Relationship generateRelationship(String relationshipName, Entity from, Entity to) throws Exception{
        /* self relationships only */
        if(!from.equals(to)){
            throw new Exception("Non-self relationship requested");
        }
        return generateRelationship(relationshipName, from);
    }
}
