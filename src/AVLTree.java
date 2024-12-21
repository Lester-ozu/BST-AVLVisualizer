import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    
    TreeNode <Integer> root;
    int nodeCount = 0;
    
    public AVLTree() {root = null;};
    public AVLTree(TreeNode<Integer> root) {this.root = root;}

    public void setRoot(TreeNode<Integer> root) {this.root = root;}
    public TreeNode<Integer> getRoot() {return root;}

    public boolean addNode(int elem) {

        if(contains(elem)) return false;

        else {

            root = addNode(root, elem);
            nodeCount++;
            return true;
        }
    }

    private TreeNode<Integer> addNode(TreeNode<Integer> node, int elem) {

        if(node == null) node = new TreeNode<>(elem);

        else {

            if(elem < node.getData()) {

                node.setLeftTree(addNode(node.getLeftTree(), elem));
            }

            else {

                node.setRightTree(addNode(node.getRightTree(), elem));
            }
        }

        update(node);

        return balance(node);
    }

    public boolean remove(int elem) {

        if(contains(elem)) {

            root = remove(root, elem);
            nodeCount--;
            return true;
        }

        return false;
    }

    private TreeNode<Integer> remove(TreeNode<Integer> node, int elem) {

        if (node == null) return null;

        if(elem < node.getData()) node.setLeftTree(remove(node.getLeftTree(), elem));

        else if (elem > node.getData()) node.setRightTree(remove(node.getRightTree(), elem));

        else {

            if(node.getLeftTree() == null) return node.getRightTree();

            else if(node.getRightTree() == null) return node.getLeftTree();

            else {

                if(node.getLeftTree().getHeight() > node.getRightTree().getHeight()) {

                    int successorValue = findMax(node.getLeftTree()).getData();
                    node.setData(successorValue);

                    node.setLeftTree(remove(node.getLeftTree(), successorValue));
                }

                else {

                    int successorValue = findMin(node.getRightTree()).getData();
                    node.setData(successorValue);

                    node.setRightTree(remove(node.getRightTree(), successorValue));
                }
            }
        }

        update(node);

        return balance(node);
    }

    public boolean contains(int elem) {

        return contains(root, elem);
    }

    private boolean contains(TreeNode<Integer> node, int elem) {

        if(node == null) return false;

        if(elem < node.getData()) return contains(node.getLeftTree(), elem);

        else if (elem > node.getData()) return contains(node.getRightTree(), elem);

        else return true;
    }

    private void update (TreeNode<Integer> node) {

        int leftNodeHeight = (node.getLeftTree() == null) ? -1 : node.getLeftTree().getHeight();
        int rightNodeHeight = (node.getRightTree() == null) ? -1 : node.getRightTree().getHeight();

        node.setHeight(1 + Math.max(leftNodeHeight, rightNodeHeight));

        node.setBalanceFactor(rightNodeHeight - leftNodeHeight);
    }

    private TreeNode<Integer> balance(TreeNode<Integer> node) {

        if (node.getBalanceFactor() == -2) {

            if(node.getLeftTree().getBalanceFactor() <= 0) {return leftLeftCase(node);}

            else {return leftRightCase(node);}
        }

        else if (node.getBalanceFactor() == +2) {

            if(node.getRightTree().getBalanceFactor() >= 0) {return rightRightCase(node);}

            else {return rightLeftCase(node);}
        }

        return node;
    }

    private TreeNode<Integer> leftLeftCase(TreeNode<Integer> node) {return rightRotation(node);}
    
    private TreeNode<Integer> rightRightCase(TreeNode<Integer> node) {return leftRotation(node);}

    private TreeNode<Integer> leftRightCase(TreeNode<Integer> node) {

        node.setLeftTree(leftRotation(node.getLeftTree()));
        return rightRotation(node);
    }

    private TreeNode<Integer> rightLeftCase(TreeNode<Integer> node) {

        node.setRightTree(rightRotation(node.getRightTree()));
        return leftRotation(node);
    }

    private TreeNode<Integer> leftRotation(TreeNode<Integer> node) {

        TreeNode<Integer> newParent = node.getRightTree();
        node.setRightTree(newParent.getLeftTree());
        newParent.setLeftTree(node);

        update(node);
        update(newParent);
        return newParent;
    }

    private TreeNode<Integer> rightRotation(TreeNode<Integer> node) {

        TreeNode<Integer> newParent = node.getLeftTree();
        node.setLeftTree(newParent.getRightTree());
        newParent.setRightTree(node);

        update(node);
        update(newParent);
        return newParent;
    }

    public TreeNode<Integer> findMax(TreeNode<Integer> node) {

        while (node.getRightTree() != null) node = node.getRightTree();
        return node;
    }

    public TreeNode<Integer> findMin(TreeNode<Integer> node) {

        while (node.getLeftTree() != null) node = node.getLeftTree();
        return node;
    }

    public int getHeight() {

        return getHeight(root);
    }

    private int getHeight(TreeNode<Integer> node) {

        if(node == null) return 0;

        return Math.max(getHeight(node.getLeftTree()) + 1, getHeight(node.getRightTree()) + 1);
    }

    public String inOrder() {

        StringBuilder path = new StringBuilder();

        inOrder(root, path);
        return path.toString();
    }

    private void inOrder(TreeNode<Integer> t, StringBuilder path) {
        
        if(t == null) return;

        inOrder(t.getLeftTree(), path);
        path.append(t.getData() + " ");
        inOrder(t.getRightTree(), path);
    }

    public String postOrder() {

        StringBuilder path = new StringBuilder();

        postOrder(root, path);
        return path.toString();
    }
    
    private void postOrder(TreeNode<Integer> t, StringBuilder path){
        if (t != null)
        {
            postOrder(t.getLeftTree(), path);
            postOrder(t.getRightTree(), path);
            path.append(t.getData() + " ");
        }
    }

    public String preOrder() {

        StringBuilder path = new StringBuilder();

        preOrder(root, path);
        return path.toString();
    }
     
    private void preOrder(TreeNode<Integer> t, StringBuilder path){
        if (t != null)
        {
            path.append(t.getData() + " ");
            preOrder(t.getLeftTree(), path);
            preOrder(t.getRightTree(), path);
        }
    }
}