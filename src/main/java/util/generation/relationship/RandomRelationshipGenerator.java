package util.generation.relationship;

import org.apache.commons.math3.distribution.UniformRealDistribution;
import schema.Cardinality;
import schema.Entity;
import schema.Relationship;

import javax.smartcardio.Card;
import java.util.*;

/**
 * Created by darbour on 11/23/14.
 */
public class RandomRelationshipGenerator implements RelationshipGenerator{
    private double linkingProbability;
    private Random random;

    public RandomRelationshipGenerator(double linkingProbability){
        this.linkingProbability = linkingProbability;
        this.random = new Random();
    }

    @Override
    public Relationship generateRelationship(String name, Entity from, Entity to) throws Exception {
        /* flip a coin to decide whether it's a one-to-one, one-to-many or many-to-many */
        switch(random.nextInt(3)){
            case 0: return generateOneToOneRelationship(name, from, to);
            case 1: return generateOneToManyRelationship(name, from, to);
            case 2: return generateManyToManyRelationship(name, from, to);
            default: throw new Exception("Invalid relationship type drawn");
        }
    }

    /***
     * Erdos-renyi adjacency matrix generation
     * @param name
     * @param from
     * @param to
     * @return
     */
    public Relationship generateManyToManyRelationship(String name, Entity from, Entity to){
        Relationship relationship = new Relationship(name, from, to, Cardinality.MANY, Cardinality.MANY);
        UniformRealDistribution runif = new UniformRealDistribution();

        for(int i = 0; i < from.getNumberOfInstances(); i++){
            for(int j = 0; j < from.getNumberOfInstances(); j++){
                if(runif.sample() > this.linkingProbability){
                    /* add in both directions */
                    relationship.addRelation(i, j);
                    relationship.addRelation(j, i);
                }
            }
        }
        return relationship;
    }

    /**
     * Create a one-to-many relationship by associating each one to a many uniformly at random.
     * @param name
     * @param from
     * @param to
     * @return
     */
    public Relationship generateOneToManyRelationship(String name, Entity from, Entity to){
        Relationship relationship = new Relationship(name, from, to, Cardinality.MANY, Cardinality.ONE);

        for(int i = 0; i < from.getNumberOfInstances(); i++){
            int toIndex = random.nextInt(to.getNumberOfInstances());
            relationship.addRelation(i, toIndex);
        }
        return relationship;
    }

    /**
     * Create a one-to-one relationship by pairing completely at random.
     * @param name
     * @param from
     * @param to
     * @return
     */
    public Relationship generateOneToOneRelationship(String name, Entity from, Entity to){
        Relationship relationship = new Relationship(name, from, to, Cardinality.ONE, Cardinality.ONE);
        List<Integer> usedEntities = new ArrayList<Integer>();
        for(int i = 0; i < to.getNumberOfInstances(); i++){
            usedEntities.add(i);
        }
        Collections.shuffle(usedEntities);
        Iterator<Integer> toIterator = usedEntities.iterator();
        for(int i = 0; i < from.getNumberOfInstances(); i++){
            if(toIterator.hasNext()){
                relationship.addRelation(i, toIterator.next());
            }
        }

        return relationship;
    }
}
