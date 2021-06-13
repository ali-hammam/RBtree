package RBTrees;

public class RBtree extends Tree{

    public RBtree(String rootValue){
        super(rootValue);
        this.root.red = 0;
    }

    private Node createNewNodeFromParent(Node parent, String value) {
        Node newNode = new Node(value);
        newNode.parent = parent;
        return newNode;
    }

    public void insert(Node node , String value){
        if(value.compareTo(node.value) > 0){
            if (node.left == null) {
                node.left = createNewNodeFromParent(node, value);
                this.format(node.left);
                return;
            }
            insert(node.left , value);
        }else{
            if (node.right == null) {
                node.right = createNewNodeFromParent(node, value);
                this.format(node.right);
                return;
            }
            insert(node.right, value);
        }
    }

    private void format(Node node){
        if (node != null && !isblack(node.parent)) {
            format(node.parent);
        }

        if(isblack(node) || isblack(node.parent) || isIamRootNode(node)) {
            return;
        }

        Node parentNode = node.parent;
        Node uncleNode = uncle(parentNode);

        if(isRed(parentNode) && isRed(uncleNode)){
            this.redParentRedUncle(parentNode);
        } else {
            this.chooseRotation(node);
        }

        format(parentNode);
    }

    private void redParentRedUncle(Node parent){
        Node uncle = uncle(parent);
        Node grandParent = parent.parent;

        parent.red = 0;
        uncle.red = 0;
        grandParent.red = 1;
        if(grandParent.parent == null) {
            grandParent.red = 0;
        }
    }

    private void chooseRotation(Node node){
        Node parent = node.parent;
        Node grandParent = parent.parent;
        if (grandParent != null ){
            if(grandParent.left == parent) {
                if(parent.left == node){
                    this.leftLeftCase(node);
                    this.format(node.right);
                }else if (parent.right == node) {
                    this.leftRightCase(node);
                    this.format(node.right);
                }
            }else if(grandParent.right == parent) {
                if (parent.left == node) {
                    this.rightLeftCase(node);
                    this.format(node.left);
                } else if (parent.right == node) {
                    this.rightRightCase(node);
                }
            }
        }
    }

    public void leftLeftCase(Node node){
        Node parent = node.parent;
        Node grandParent = parent.parent;
        Node temp = grandParent.parent;

        grandParent.left = parent.right;
        if (grandParent.left != null) {
            grandParent.left.parent = grandParent;
        }

        parent.right = grandParent;
        parent.parent = grandParent.parent;

        if(parent.parent == null){
            this.root = parent;
            this.root.red = 0;
        }else if(temp.left == grandParent){
            temp.left = parent;
        }else if(temp.right == grandParent){
            temp.right = parent;
        }

        grandParent.parent = parent;

        parent.red = 0;
        grandParent.red = 1;
    }

    public void leftRightCase(Node node){
        Node leftChild = node.left;
        Node parent = node.parent;
        Node grandParent = parent.parent;

        grandParent.left = node;
        node.parent = grandParent;
        parent.right = leftChild;
        if (leftChild != null) {
            leftChild.parent = parent;
        }

        node.left = parent;
        parent.parent = node;
        this.leftLeftCase(parent);
    }

    public void rightRightCase(Node node){
        Node parent = node.parent;
        Node grandParent = parent.parent;
        Node temp = grandParent.parent;

        grandParent.right = parent.left;

        if (grandParent.right != null) {
            grandParent.right.parent = grandParent;
        }

        parent.left = grandParent;
        parent.parent = grandParent.parent;

        if(parent.parent == null){
            this.root = parent;
            this.root.red = 0;
        }else if(temp.left == grandParent){
            temp.left = parent;
        }else if(temp.right == grandParent){
            temp.right = parent;
        }

        grandParent.parent = parent;
        parent.red = 0;
        grandParent.red = 1;
    }

    public void rightLeftCase(Node node){
        Node leftChild = node.right;
        Node parent = node.parent;
        Node grandParent = parent.parent;

        grandParent.right = node;
        node.parent = grandParent;
        parent.left = leftChild;
        if(leftChild != null) {
            leftChild.parent = parent;
        }
        node.right = parent;
        parent.parent = node;


        this.rightRightCase(parent);
    }

    private boolean isblack(Node node){
        return node == null || node.red == 0;
    }

    private boolean isRed(Node node){
        return node != null && node.red == 1;
    }

    private boolean isIamRootNode(Node node) {
        return node.parent == null;
    }
}
