package structure;


public class AscendingNode {

    private int id;
    private int x;
    private int y;

    private AscendingNode parent;

    /**
     * Constructor
     *
     * @param id
     * @param x
     * @param y
     * @param parent
     */
    public AscendingNode(int id,int x, int y, AscendingNode parent) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    /**
     * Constructor as root
     *
     * @param id
     */
    public AscendingNode(int id,int x,int y) {
        this.id = id;
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
    public int getId() {
        return this.id;
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


}

