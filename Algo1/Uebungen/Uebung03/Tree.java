/**
 * Repraesentation eines Binaeren Suchbaums
 * Hinweis: Im Gegensatz zum Pseudocode der Vorlesungsunterlagen fehlt 
 * bei einige Methoden das erste Argument (root, etc.), 
 * da in dieser Klasse die Wurzel als Klassenvariable deklariert wurde. 
 */
package Tree;

public class Tree {

	public Node root;
	Node x, y, z;
	
	/**
	 * Erstellt einen leeren Binaeren Suchbaum
	 */
	public Tree() {
		root = null;
	} // 

	/**
	 * Erstellt einen neuen Binaren Suchbaum mit einem Wurzeknoten
	 * @param key Wert des Wurzelknontens
	 */
	public Tree(int key) {
		root = new Node(key);
	}

	public void inorderTreeWalk(Node x) {
		System.out.println("Ausgabe der Knoten des Baumes in aufsteigender Reihenfolge:");
		if (x != null){
			inorderTreeWalk(x.left);
			print(x.key);
			inorderTreeWalk(x.right);
		}
	}
	
	public void treeInsert(Node z) {
	    // x.parent = x.right = x.left = null;
            if (root == null){
		root = z;
            }
            x = root;
            while(x != null){
		y = x;		//m�glicher Elternknoten
		if(z.key < y.key){
                    x = x.left;
		}
		else{
                    x = x.right;
		}
            }
            z.parent = y;
            if (z.key < y.key){
		y.left = z;
            }
            else {
		y.right = z;
            }
	}
	
	public void treeDelete(Node z) {
            Node delNode;
            if (z.left == null || z.right == null){
		delNode = z;
            }
            else{
		delNode = treeSuccessor(z);
            }
            if (delNode.left != null){
		x = delNode.left;
            }
            else{
		x = delNode.right;		//x ist Kind von delNode
            }
            if (x != null){
		x.parent = delNode.parent;
            }
            if (delNode.parent == null){
		root = x;				//falls delNode keinen Elternknoten hat, hat auch x keinen
            }
            else{
		if(delNode == delNode.parent){
                	delNode.parent.left = x;
		}
		else{
                    delNode.parent.right = x;
		}
            }
            if (z != delNode.key){
		z.key = delNode.key;
		z.
		}
	} // treeDelete
	
	public Node treeSearch(Node x, int k) {
		if (x == null || k == x.key){
			return x;
		}
		if (k < x.key){
			return treeSearch(x.left, k);
		}
		else {
			return treeSearch(x.right, k);
		}
	}
	
	public Node treeMinimum() {
		if (x == null){
			return null; 
		}
		while (x.left != null){
			x = x.left;
		}
		return x;
	}
	
	public Node treeSuccessor(Node x) {
            Node k;
            if (x == null)
                return null;
            if (x.right == null){
                y = x.parent;
                while(y != null && x == y.right){
                    x = y;
                    y = y.parent;
                }
                return y;
            }    
	}
	
	/**
	 * main-Methode
	 * @param args
	 */
	public static void main(String[] args) {
		Tree tree = new Tree();
		tree.treeInsert(new Node(7));
		tree.treeInsert(new Node(8));
		tree.treeInsert(new Node(1));
		tree.treeInsert(new Node(3));
		tree.treeInsert(new Node(4));
		tree.treeInsert(new Node(7));
		tree.treeInsert(new Node(7));
		tree.treeInsert(new Node(93));
		tree.treeInsert(new Node(35));
		tree.treeInsert(new Node(-5));
		tree.inorderTreeWalk();

	} // main

}