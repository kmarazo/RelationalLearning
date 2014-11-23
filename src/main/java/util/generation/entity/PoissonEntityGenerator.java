package util.generation.entity;

import org.apache.commons.math3.distribution.PoissonDistribution;
import schema.Entity;

/**
 * Generates Entities with the number of instances drawn from a poisson
 * Created by darbour on 11/22/14.
 */
public class PoissonEntityGenerator implements EntityGenerator{
    private PoissonDistribution distribution;

    public PoissonEntityGenerator(double mean){
        this.distribution = new PoissonDistribution(mean);
    }

    @Override
    public Entity generateEntity(String name) {
        /* generate a new entity with the number of instances drawn from a Poisson */
        return new Entity(name, distribution.sample());
    }
}
