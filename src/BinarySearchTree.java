public class BinarySearchTree {
    
    TreeNode <Integer> root;
    int nodeCount = 0;
    
    public BinarySearchTree() {root = null;};
    public BinarySearchTree(TreeNode<Integer> root) {this.root = root;}

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

        return node;
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

                TreeNode<Integer> temp = findMin(node.getRightTree());

                node.setData(temp.getData());

                node.setRightTree(remove(node.getRightTree(), temp.getData()));
            }
        }

        return node;
    }

    public boolean contains(int elem) {

        return contains(root, elem);
    }

    public TreeNode<Integer> findMax(TreeNode<Integer> node) {

        while (node.getRightTree() != null) node = node.getRightTree();
        return node;
    }

    public TreeNode<Integer> findMin(TreeNode<Integer> node) {

        while (node.getLeftTree() != null) node = node.getLeftTree();
        return node;
    }

    private boolean contains(TreeNode<Integer> node, int elem) {

        if(node == null) return false;

        if(elem < node.getData()) return contains(node.getLeftTree(), elem);

        else if (elem > node.getData()) return contains(node.getRightTree(), elem);

        else return true;
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
