package RBTrees;

/**
 * Created by HP on 6/9/2021.
 */
public class Node {
    public Node right;
    public Node left;
    public Node parent;
    int red;
    public String value;

    public Node(String value){
        this.right = null;
        this.left = null;
        this.parent = null;
        this.red = 1;
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }


}
