package model;

import java.util.List;
import java.util.ArrayList;

import model.attribute.Attribute;
import schema.Schema;

/**
 * Created by darbour on 11/19/14.
 */
public class Model {
    private Schema schema;
    private List<RelationalVariable> relationalVariableList;

    /**
     * Gets all canonical relational variables contained in the model
     * @return a list containing all canonical relational variables
     */
    public List<RelationalVariable> getAllCanonicalRelationalVariables(){
        List<RelationalVariable> canonicalRelationalVariables = new ArrayList<RelationalVariable>();
        for(RelationalVariable relVar : relationalVariableList){
            if(relVar.isCanonical()){
                canonicalRelationalVariables.add(relVar);
            }
        }
        return canonicalRelationalVariables;
    }

    /**
     * Returns the canonical relational variable that corresponds to this attribute
     * @param attribute, the attribute to find a relational variable fo
     * @return an instance of RelationalVariable that is the canonical variable
     */
    public RelationalVariable getCanonicalVariable(Attribute attribute){
        RelationalVariable canonicalVariable = null;

        return canonicalVariable;
    }

    public String toString(){
        String modelString = "";
        /* iterate over all canonical variables */
        for(RelationalVariable relVar : getAllCanonicalRelationalVariables()){
            /* for all the parents of this variable */
            for(RelationalVariable parent : relVar.getParents()){
                modelString += parent.toString() + "->" + relVar.toString() + ";\n";
            }
        }
        return modelString;
    }

    public String toDotString(){
        /* TODO: allow models to be named */
        return "digraph model {\n" + this.toString() + "}";
    }
}
