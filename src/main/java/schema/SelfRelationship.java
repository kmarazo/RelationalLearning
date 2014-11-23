package schema;

import javax.smartcardio.Card;

/**
 * Created by darbour on 11/21/14.
 */
public class SelfRelationship extends Relationship {
    public SelfRelationship(String name, Entity entity){
        super(name, entity, entity, Cardinality.MANY, Cardinality.MANY);
    }


}
