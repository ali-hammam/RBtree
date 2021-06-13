package RBTrees;

public class Tree {
    protected Node root;

    public Tree(String val){
        root = new Node(val);
    }

    public void insert(Node node , String value){
        if(value.compareTo(node.value) > 0){
            if(node.left == null){
                node.left = new Node(value);
                node.left.parent = node;
                return;
            }
            insert(node.left , value);
        }else{
            if(node.right == null){
                node.right = new Node(value);
                node.right.parent = node;
                return;
            }
            insert(node.right , value);
        }
    }

    public int height(Node node){
        if (node == null)
            return 0;
        else
        {
            int lHeight = height(node.left);
            int rHeight = height(node.right);

            if (lHeight > rHeight)
                return (lHeight + 1);
            else
                return (rHeight + 1);
        }
    }

    public int size(Node node){
        if(node==null)
            return 0;

        return 1 + size(node.left) + size(node.right);
    }

    public Node find(String val){
        Node node = this.root;
        int counter = 0;
        while (true) {
            /*if(val.equals(node.value))
                break;

            if(isChild(node)) {
                node = new Node("null");
                break;
            }*/
            if(val.equals(node.value)){
                node = new Node("null");
                break;
            }
            if (val.compareTo(node.value) > 0) {
                node = node.left;
            }else{
                node = node.right;
            }
        }
        return node;
    }

    public boolean ifNodeExists( Node node, String key)
    {
        if (node == null)
            return false;

        if (node.value.equals(key))
            return true;

        boolean res1 = ifNodeExists(node.left, key);

        if(res1) return true;

        boolean res22 = ifNodeExists(node.right, key);

        return res22;
    }

    private boolean isChild(Node node){
        return node.left == null && node.right == null;
    }

    public Node getRoot(){return this.root;}

    public Node uncle(Node node){
        if(node.parent == null){
            return null;
        }

        if(node.parent.left == node && node.parent.right != null){
            return node.parent.right;
        }else if(node.parent.right == node && node.parent.left != null) {
            return node.parent.left;
        }
        return null;
    }

    public void print(Node node){
        if(node.left != null) {
            print(node.left);
        }
        System.out.println(node.value);
        if(node.right != null){
            print(node.right);
        }
    }

    public void visualize(Node node){
        if(node.left != null && node.right != null){
            System.out.println("Parent " + node + " color(" + node.red +") {left: " + node.left + " color(" + node.left.red + ") , right: " + node.right + " color(" + node.right.red + ")}");
            visualize(node.left);
            visualize(node.right);
        }else if(node.left != null){
            System.out.println("Parent " + node + " color(" + node.red +"){left: " + node.left + " color(" + node.left.red +  ") , right: null}");
            visualize(node.left);
        }else if(node.right != null){
            System.out.println("Parent " + node + " color(" + node.red + "){left: null" + " , right: " + node.right + " color(" + node.right.red + ")}");
            visualize(node.right);
        }
    }
}
