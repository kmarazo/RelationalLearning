package model.attribute;

import model.DataType;
import model.Distribution;
import schema.RelationalItem;

/**
 * Created by darbour on 11/19/14.
 */
public abstract class Attribute {
    /* unique identifier */
    private String name;
    private RelationalItem containingRelationalItem;
    Distribution distribution;
    DataType datatype;

    @Override
    public int hashCode(){
        String stringToHash = this.name + this.containingRelationalItem.toString();
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        Attribute other = (Attribute) object;
        return this.name.equals(other.name) &&
                this.containingRelationalItem.equals(other.containingRelationalItem);
    }

    @Override
    public String toString(){
        return this.name;
    }
}
