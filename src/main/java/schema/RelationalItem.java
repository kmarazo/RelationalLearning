package schema;

import java.util.Map;
import java.util.HashMap;
import model.attribute.Attribute;

/**
 * Created by darbour on 11/19/14.
 */
public abstract class RelationalItem {
    /* map from attribute name to an instance of attribute class */
    Map<String, Attribute> attributes;
    /* unique identifier for the entity type */
    private String name;

    public RelationalItem(String name){
        this.name = name;
        this.attributes = new HashMap<String, Attribute>();
    }

    public String getName() { return this.name; }

    @Override
    public String toString(){
        return this.name;
    }

    @Override
    public int hashCode(){
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object object){
        return this.name.equals(((RelationalItem)object).name);
    }
}
