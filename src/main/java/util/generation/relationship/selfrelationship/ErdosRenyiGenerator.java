package util.generation.relationship.selfrelationship;

import org.apache.commons.math3.distribution.UniformRealDistribution;
import schema.Entity;
import schema.SelfRelationship;

/**
 * Created by darbour on 11/22/14.
 */
public class ErdosRenyiGenerator extends AbstractSelfRelationshipGenerator {
    private double linkProbability;

    public ErdosRenyiGenerator(double linkProbability){
        this.linkProbability = linkProbability;
    }

    public void setLinkProbability(double newProbability){
        this.linkProbability = newProbability;
    }

    public SelfRelationship generateRelationship(String relationshipName, Entity entity) {
        SelfRelationship relation = new SelfRelationship(relationshipName, entity);
        /* used for sampling edge existence */
        UniformRealDistribution runif = new UniformRealDistribution();
        /*
        For all possible edges (i,j) create a relationship with proability linkProbability
         */
        for(int i = 0; i < entity.getNumberOfInstances(); i++){
            /* only consider the upper diagonal because it's undirected */
            for(int j = i; j < entity.getNumberOfInstances(); j++){
                /* an entity cannot be related to itself */
                if(i == j){
                    continue;
                }
                /* if a random draw is <= p create an edge */
                if(runif.sample() <= linkProbability){
                    /* undirected, add in both directions */
                    relation.addRelation(i, j);
                    relation.addRelation(j, i);
                }
            }
        }
        return relation;
    }
}