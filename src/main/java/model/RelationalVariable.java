package model;

import model.attribute.Attribute;
import schema.RelationalPath;

import java.util.List;

/**
 * Created by darbour on 11/19/14.
 */
public class RelationalVariable {
    private RelationalPath path;
    private Attribute attribute;
    private List<RelationalVariable> parents;
    private List<RelationalVariable> children;

    public boolean isCanonical(){
        if (path.length() == 1) return true;
        else return false;
    }

    public List<RelationalVariable> getParents(){
        return parents;
    }

    public List<RelationalVariable> getChildren(){
        return children;
    }

    public RelationalPath getPath(){
        return path;
    }

    public Attribute getAttribute(){
        return attribute;
    }

    @Override
    public String toString(){
        return path.toString() + "." + attribute.toString();
    }

    @Override
    public boolean equals(Object other){
        RelationalVariable otherVariable = (RelationalVariable) other;
        return path.equals(otherVariable.getPath()) && attribute.equals(otherVariable.getAttribute());
    }
}

