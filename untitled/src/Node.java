public class Node {
     private Integer value;
     private Node leftNode;
     private Node rightNode;
     private Node parentNode;

    public Node(Integer value, Node leftNode, Node rightNode) {
        this.value = value;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public Node(Integer value) {
        this.value = value;
    }

    public Node(Node leftNode) {
        this.leftNode = leftNode;
    }


    public Integer getValue() {
        if (value == null) {
            return 0;
        }
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }
}
