import java.util.Objects;

public class TreeNode <T>{
    
    private T data;
    private TreeNode<T> leftTree, rightTree;
    private int height, balanceFactor;

    //Constructors
    public TreeNode() {

        this.data = null;
        this.leftTree = null;
        this.rightTree = null;
    }

    public TreeNode(T data, TreeNode<T> leftTree, TreeNode<T> rightTree) {

        this.data = data;
        this.leftTree = leftTree;
        this.rightTree = rightTree;
    }

    public TreeNode(T data) {

        this.data = data;
        this.leftTree = null;
        this.rightTree = null;
    }

    //Setters
    public void setData(T data) {this.data = data;}
    public void setLeftTree(TreeNode<T> leftTree) {this.leftTree = leftTree;}
    public void setRightTree(TreeNode<T> rightTree) {this.rightTree = rightTree;}
    public void setHeight(int height) {this.height = height;}
    public void setBalanceFactor(int balanceFactor) {this.balanceFactor = balanceFactor;}

    //Getters
    public T getData() {return this.data;}
    public TreeNode<T> getLeftTree() {return this.leftTree;}
    public TreeNode<T> getRightTree() {return this.rightTree;}
    public int getHeight() {return this.height;}
    public int getBalanceFactor() {return this.balanceFactor;}

    @Override
    public boolean equals(Object obj) {

        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;

        TreeNode<?> node = (TreeNode<?>) obj;
        return Objects.equals(this.data, node.getData()) &&
               Objects.equals(this.leftTree, node.getLeftTree()) &&
               Objects.equals(this.rightTree, node.getRightTree());
    }

    @Override
    public int hashCode() {

        return Objects.hash(data, leftTree, rightTree);
    }
}
