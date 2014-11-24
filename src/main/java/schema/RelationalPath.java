package schema;

import graph.OpenMapAdjacencyMatrix;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by darbour on 11/20/14.
 */
public class RelationalPath implements Iterable<RelationalPath.RelationalLink>{

    /**
     * Utility class that holds information about a link along a relational path.
     * Allows for directionality, etc.
     */
    public class RelationalLink {
        Relationship relationship;
        Entity from;
        Entity to;

        public RelationalLink(Entity from, Entity to, Relationship relationship){
            this.relationship = relationship;
            this.from = from;
            this.to = to;
        }

        public OpenMapAdjacencyMatrix getAdjacencyMatrix() throws Exception{
            return this.relationship.getAdjacencyMatrix(this.from, this.to);
        }

        @Override
        public boolean equals(Object object){
            RelationalLink other = (RelationalLink) object;
            return other.from == this.from && other.to == this.to && other.relationship == this.relationship;
        }
    }

    private List<RelationalLink> relationshipLinks;

    public RelationalPath(List<Entity> entities, List<Relationship> relationships) throws Exception{
        this.relationshipLinks = new ArrayList<RelationalLink>();
        Iterator<Entity> entityIterator =  entities.iterator();
        Entity currentEntity = entityIterator.next();
        Entity nextEntity;
        for(Relationship relationship : relationships){
            /* make sure we don't end prematurely */
            if(!entityIterator.hasNext()){
                /* TODO: explicitly not handling paths that start or end with a relationship */
                throw new Exception("There are not enough entities supplied for this path definition");
            }
            nextEntity = entityIterator.next();
            if(!relationship.hasEntity(currentEntity) || !relationship.hasEntity(nextEntity)){
                throw new Exception("Incorrectly specified path, relationships do not link adjacent entities");
            }
            this.relationshipLinks.add(new RelationalLink(currentEntity, nextEntity, relationship));
        }
        /* make sure there aren't too many entities supplied */
        if(entityIterator.hasNext()){
            throw new Exception("Too many entities were supplied for this path definition");
        }
    }

    /**
     * Checks the validity of a given relational path
     */
    // todo: return T/F or throw Exception?
    public boolean isValid(){
        // Condition 1: Alternate entity - relations
        // todo: This has been taken care by the constructor?

        // Condition 2: No ERE patterns
        Iterator<RelationalLink> it = this.relationshipLinks.iterator();
        RelationalLink link;
        while(it.hasNext()){
            link = it.next();
            if (link.from.equals(link.to)){
                return false;
            }
        }
        // Condition 3: If RER pattern, then E has cardinality many
        // go over successive relational links
        RelationalLink l1, l2;
        for (int i=0;i<this.relationshipLinks.size()-1;i++){
            l1 = this.relationshipLinks.get(i);
            l2 = this.relationshipLinks.get(i+1);
            if (l1.relationship.equals(l2.relationship) && l1.relationship.getCardinality(l1.to)==Cardinality.ONE){
                return false;
            }
        }
        return true;
    }

    /**
     * Get the length of the relational path
     * @return
     */
    public int length(){
        return this.relationshipLinks.size();
    }

    @Override
    public Iterator<RelationalLink> iterator() {
        return this.relationshipLinks.iterator();
    }

    @Override
    public String toString(){
        String pathString = "[";

        RelationalLink currentLink;
        Iterator<RelationalLink> relLinkIterator = this.iterator();

        currentLink = relLinkIterator.next();
        pathString += currentLink.from.toString() + "," + currentLink.relationship.toString() +
                "," + currentLink.to.toString();

        while(relLinkIterator.hasNext()){
            currentLink = relLinkIterator.next();
            pathString += "," + currentLink.relationship.toString() + "," + currentLink.to.toString();
        }

        return pathString + "]";
    }

    @Override
    public boolean equals(Object object){
        RelationalPath otherPath = (RelationalPath)object;
        /* all of the links in both of the paths have to be equal */
        Iterator<RelationalLink> relationalLinkIterator = this.iterator();
        Iterator<RelationalLink> otherRelationalLinkIterator = otherPath.iterator();
        while(relationalLinkIterator.hasNext() && otherRelationalLinkIterator.hasNext()){
            if(!relationalLinkIterator.next().equals(otherRelationalLinkIterator.next())){
                return false;
            }
        }
        /* and must be the same length */
        if(relationalLinkIterator.hasNext() || otherRelationalLinkIterator.hasNext()){
            return false;
        }
        return true;
    }
}
