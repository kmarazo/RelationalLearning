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

    public Entity(String name, int numberOfInstances) throws Exception{
        super(name);
        if (numberOfInstances<=0){
            throw new Exception("Number of Instances must be positive");
        }
        else{
            this.numberOfInstances = numberOfInstances;
        }

    }

    public int getNumberOfInstances(){
        return numberOfInstances;
    }

}