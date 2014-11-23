package util.generation.relationship;

import schema.Relationship;
import schema.Entity;

/**
 * Created by darbour on 11/22/14.
 */
public interface RelationshipGenerator {
    public Relationship generateRelationship(String name, Entity from, Entity to) throws Exception;
}
