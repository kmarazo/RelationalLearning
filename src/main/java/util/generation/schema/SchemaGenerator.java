package util.generation.schema;

import java.util.Random;
import schema.Schema;
import schema.Entity;
import util.generation.entity.PoissonEntityGenerator;
import util.generation.relationship.RelationshipGenerator;
import util.generation.entity.EntityGenerator;
import util.generation.relationship.selfrelationship.ErdosRenyiGenerator;

/**
 * Created by darbour on 11/22/14.
 */
public class SchemaGenerator {

    public static Schema generateSchema(int numberOfEntities, int numberOfRelationships,
                                 EntityGenerator entityGenerator,
                                 RelationshipGenerator relationshipGenerator) throws Exception{
        System.out.println("Creating random number generator and Schema");
        Random random = new Random();
        Schema schema = new Schema();

        System.out.println("Generating entities");
        /* Generate Entities */
        for(int i = 0; i < numberOfEntities; i++){
            /* giving out generic entity names */
            schema.addEntity(entityGenerator.generateEntity("Entity_"+i));
        }
        System.out.println("Generating relationships");
        /* Generate Relationships */
        /* TODO: include varying cardinalities */
        for(int i = 0; i < numberOfRelationships; i++){
            /* randomly select entities */
            Entity fromEntity = schema.getEntity(random.nextInt(numberOfEntities));
            Entity toEntity = schema.getEntity(random.nextInt(numberOfEntities));
            /* generate and add to the schema */
            schema.addRelationship(relationshipGenerator.generateRelationship("Relationship_"+i, fromEntity, toEntity));
        }
        return schema;
    }

    public static void main(String[]args){
        Schema mySchema;
        EntityGenerator myEnts = new PoissonEntityGenerator(4);
        RelationshipGenerator myRels = new ErdosRenyiGenerator(0.3);
        try {
            mySchema = SchemaGenerator.generateSchema(100, 2, myEnts, myRels);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
