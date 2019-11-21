package structure;


public class AscendingNode {

    private int value;
    private int x;
    private int y;

    private AscendingNode parent;

    /**
     * Constructor
     *
     * @param value
     * @param x
     * @param y
     * @param parent
     */
    public AscendingNode(int value,int x, int y, AscendingNode parent) {
        this.value = value;
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * Constructor as root
     *
     * @param value
     */
    public AscendingNode(int value,int x,int y) {
        this.value = value;
        this.x = x;
        this.y = y;
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
     * get value of current node
     *
     * @return value
     */
    public int getX() {
        return this.x;
    }
    /**
     * get value of current node
     *
     * @return value
     */
    public int getY() {
        return this.y;
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
     * Get the root of the current node
     *
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

