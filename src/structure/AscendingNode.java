package structure;


public class AscendingNode {
    private int value;

    private AscendingNode parent;
    private int x;
    private int y;

    /**
     * Constructor
     *
     * @param value
     * @param parent

     */
    public AscendingNode(Integer value, AscendingNode parent) {
        this.value = value;
        this.parent = parent;
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor as root
     *
     * @param value
     */
    public AscendingNode(Integer value) {
        this.value = value;
        setParent(this);
    }

    /**
     * GETTERS
     */

    /**
     * get value of current node
     *
     * @return value
     */
    public int getValue() {
        return this.value;
    }

    /**
     * get parent of current node
     *
     * @return parent
     */
    public AscendingNode getParent() {
        return this.parent;
    }

    /**
<<<<<<< HEAD
     *
     * @return int[2] containing (x,y)
     */
    public int[] getPoint(){
        return new int[] {x,y};
    }

    /**
     *  Get the root of the current node
=======
     * Get the root of the current node
     *
>>>>>>> updated UnionFind
     * @return root
     */
    public AscendingNode getRoot() {
        if (this.getParent() == this) {
            return this;
        } else {
            return this.getParent().getRoot();
        }

    }

    /**
     * SETTERS
     */

    /**
     * set a new value
     *
     * @param value
     */
    protected void setValue(int value) {
        this.value = value;
    }

    /**
     * set a new parent
     *
     * @param parent
     */
    protected void setParent(AscendingNode parent) {
        this.parent = parent;
    }


    /**
     * VERIFICATIONS
     */

    /**
     * Check whether the current node is the root
     *
     * @return
     */
    protected boolean isRoot() {
        return this.parent == this;
    }
}

