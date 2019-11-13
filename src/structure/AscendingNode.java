package structure;


import java.util.Collection;
import java.util.List;

public class AscendingNode {
    private Integer value;
    private AscendingNode parent;
    private Collection<AscendingNode> children;

    /**
     * Constructor
     * @param value
     * @param parent
     * @param children
     */
    public AscendingNode(Integer value, AscendingNode parent, List<AscendingNode> children){
        this.value = value;
        this.children = children;
        this.parent = parent;
    }


    /**
     * GETTERS
     */

    /**
     * get value of current node
     * @return value
     */
    protected Integer getValue(){
        return this.value;
    }

    /**
     * get parent of current node
     * @return parent
     */
    protected AscendingNode getParent(){
        if(this.parent == null){
            throw new NullPointerException("This unionFind.node has no parent.");
        }
        return this.parent;
    }

    protected Collection<AscendingNode> getChildren(){
        if (this.children.isEmpty()){
            throw new NullPointerException("This unionFind.node has no children.");
        }
        return this.children;
    }

    protected AscendingNode getRoot(){
        if(!this.hasParent()){
            return this;
        }
        else{
            return getParent().getRoot();
        }

    }

    /**
     * SETTERS
     */

    /**
     *  set a new value (useless?)
     * @param value
     */
    protected void setValue(Integer value){
        this.value = value;
    }

    /**
     * set a new parent
     * @param parent
     */
    protected void setParent(AscendingNode parent){
        this.parent = parent;
    }

    /**
     * set a new set of children
     * @param children
     */
    protected void setChildren(List<AscendingNode> children){
        this.children = children;
    }

    /**
     * VERIFICATIONS
     */

    protected boolean isRoot(){
        return !hasParent();
    }

    protected boolean isLeaf(){
        return !hasChild();
    }
    private boolean hasChild(){
        return !this.children.isEmpty();
    }

     private boolean hasParent(){
        return this.parent != null;
     }
}
