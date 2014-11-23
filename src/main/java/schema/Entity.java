package schema;

import java.util.Map;
import java.util.List;

/**
 * Created by darbour on 11/19/14.
 */
public class Entity extends RelationalItem {
    /* list of instance ids for this entity */
    private List<Integer> entityInstances;
    private int numberOfInstances;

    public Entity(String name, int numberOfInstances){
        super(name);
        this.numberOfInstances = numberOfInstances;
    }

    public int getNumberOfInstances(){
        return numberOfInstances;
    }

}