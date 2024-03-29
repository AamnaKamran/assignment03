class BST {
    //node class that defines BST node
    class Node {
        String key;
        Node left, right;

        public Node(String data){
            key = data;
            left = right = null;
        }
    }
    // BST root node 
    Node root;

    // Constructor for BST =>initial empty tree
    BST(){
        root = null;
    }

    String minValue(Node root)  {
        //initially minval = root
        String minval = root.key;
        //find minval
        while (root.left != null)  {
            minval = root.left.key;
            root = root.left;
        }
        return minval;
    }

    // insert a node in BST 
    void insert(String key)  {
        root = insert_Recursive(root, key);
    }

    //recursive insert function
    Node insert_Recursive(Node root, String key) {
        //tree is empty
        if (root == null) {
            root = new Node(key);
            return root;
        }
        //traverse the tree
        if (key.compareTo(root.key) < 0)     //insert in the left subtree
            root.left = insert_Recursive(root.left, key);
        else if (key.compareTo(root.key) >= 0)    //insert in the right subtree
            root.right = insert_Recursive(root.right, key);
        // return pointer
        return root;
    }

    // method for inorder traversal of BST
    void inorder() {
        inorder_Recursive(root);
    }

    // recursively traverse the BST  
    void inorder_Recursive(Node root) {
        if (root != null) {
            inorder_Recursive(root.left);
            System.out.print(root.key + " ");
            inorder_Recursive(root.right);
        }
    }

    boolean search(String key)  {
        root = search_Recursive(root, key);
        if (root!= null)
            return true;
        else
            return false;
    }

    //recursive insert function
    Node search_Recursive(Node root, String key)  {
        // Base Cases: root is null or key is present at root 
        if (root==null || root.key.equals(key))
            return root;
        // val is greater than root's key 
        if (root.key.compareTo(key) > 0)
            return search_Recursive(root.left, key);
        // val is less than root's key 
        return search_Recursive(root.right, key);
    }
}