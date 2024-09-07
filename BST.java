package BSTPractice;
import java.util.Stack;

public class BST {
	private Node root;
	
	class Node
	{
		int data;
		Node left;
		Node right;	
	}
	
	public BST()
	{
		root = null;
	}
	
	//Insert values to the tree
	public void insert(int a)
	{
		Node add = new Node();
		add.data = a;
		add.left = null;
		add.right = null;
		
		if(root == null) 
		{
			root = add;
			return; 
		}
		
		//Tree is not empty	
		Node curr = root;
		while(curr != null)
		{
			if(curr.data > a) //Putting the data to the left if it's smaller
			{
				if(curr.left == null) 
				{
					curr.left = add;
					return;
				}
				curr = curr.left;
			}
			else  //Putting the data to the right if it's larger
			{
				if(curr.right == null)
				{
					curr.right = add;
					return;
				}
				curr = curr.right;
			}
		}
	}
	
	public void insert2(int a)//Recursive way
	{
		Node add = new Node();
		add.data = a;
		add.left = null;
		add.right = null;
		
		if(root == null)
		{
			root = add;
			return;
		}
		insertRecursive(root, add);
	}
	
	
	private void insertRecursive(Node newRoot, Node add)
	{
		if(newRoot.data > add.data) 
		{
			if(newRoot.left == null)
			{
				newRoot.left = add;
				return;
			}
			insertRecursive(newRoot.left, add);
		}
		else
		{
			if(newRoot.right == null)
			{
				newRoot.right = add;
				return;
			}
			insertRecursive(newRoot.right, add);
		}
	}
	
	
	//In Order - tree traversal (left-root-right)
	public void InOrder()
	{
		recursiveInOrder(root);
	}
	
	private void recursiveInOrder(Node newRoot)
	{
		if(newRoot.left != null)//inside left side
		{
			if(newRoot.left.left == null && newRoot.left.right == null)//if the left child is only child of that root
			{
				System.out.print(newRoot.left.data + " ");
			}
			else//if its not the only child
			{
				recursiveInOrder(newRoot.left);
			}
		}
		System.out.print(newRoot.data + " ");//The second part is display root value 
		
		if(newRoot.right != null)//inside right side now
		{
			if(newRoot.right.left == null && newRoot.right.right == null)//if the left child is only child of that root
			{
				System.out.print(newRoot.right.data + " ");
			}
			else//if its not the only child
			{
				recursiveInOrder(newRoot.right);
			}
		}
		
	}
	
	private void recursiveInOrder2(Node newRoot)
	{
		if(newRoot == null)
			return;
		
		recursiveInOrder2(newRoot.left);
		System.out.print(newRoot.data + " ");
		recursiveInOrder2(newRoot.right);
	}
	
	public void inOrder2()//non-recursive way
	{
		Stack<Node> nodes = new Stack<>(); //We have to pop node type reference to stack
		Node curr = root;
		boolean checkLeft = true; //if it's true, check the left side
		
		while(curr != null)
		{
			if(checkLeft == true)
			{
				if(curr.left != null)
				{
					nodes.push(curr);
					curr = curr.left;
					checkLeft = true;
					continue; //If i move on to the left side of the left subtree 
				}
			}
			
			System.out.print(curr.data + " ");
			
			if(curr.right == null)
			{
				if(!nodes.empty()) //If the stack isn't empty then pop out the node 
				{
					curr = nodes.pop();
					checkLeft = false;
					continue;
				}
				else //stack is empty (
				{
					System.out.println("Done");
					break;
				}
			}
			else
			{
				curr = curr.right;
				checkLeft = true;
				continue;
			}
		}
	}
	
	//Pre-order tree traversal
	public void preOrderRecursive()
	{
		preOrder1(root);
	}
	
	private void preOrder1(Node newRoot) //Root-left-right
	{
		if(newRoot != null)
		{
			System.out.println(newRoot.data + " ");
			preOrder1(newRoot.left);
			preOrder1(newRoot.right);
		}
	}
	
	//Post-order tree traversal 
	public void postOrderRecursive()
	{
		postOrder1(root);
	}
	
	private void postOrder1(Node newRoot) //left-right-root
	{
		if(newRoot != null)
		{
			preOrder1(newRoot.left);
			preOrder1(newRoot.right);
			System.out.println(newRoot.data + " ");
		}
	}
	
	//PreOrder traversal
	public void preOrder()
	{
		Stack<Node> nodes = new Stack<>();
		Node curr = root;
		
		boolean leftCheck = true;
		
		while(curr !=null)
		{
			if(leftCheck == true)
			{
				System.out.println(curr.data + " ");
				if(curr.left != null)
				{
					
				}
			}
		}
	}
	
	//Search for data
	public boolean search(int a)
	{
		return searchRecursive(root, a);
	}
	
	public boolean searchRecursive(Node newRoot, int lookFor)
	{
		if (newRoot == null)
		{
			return false;
		}
		
		if (newRoot.data == lookFor)
		{
			return true;
		}
		else if(newRoot.data > lookFor)
		{
			return searchRecursive(newRoot.left, lookFor);
		}
		else 
		{
			return searchRecursive(newRoot.right, lookFor);
		}
	}
	
	public boolean search2(int a)
	{
		Node current = root;
		
		while(current != null)
		{
			if(current.data == a)
				return true;
			
			if(current.data > a)
				current = current.left;
			else
				current = current.right;
		}
		
		return false;
	}
	
	//Finding the maximum and minimum value
	public int findMax()
	{
		if (root == null)
		{
			return -1;
		}
		
		Node current = root;
		while(current.right != null)
		{
			current = current.right;
		}
		
		return current.data;
	}
	
	public int findMin()
	{
		if (root == null)
		{
			return -1;
		}
		
		Node current = root;
		while(current.left != null)
		{
			current = current.left;
		}
		
		return current.data;
	}
	
	//Remove
	public boolean remove(int a)
	{
		Node current = root;
		Node parent = null;
		boolean left = false;
		
		while(current != null)
		{
			if (current.data > a)
			{
				parent = current;
				left = true;
				current = current.left;
			}
			else if (current.data < a)
			{
				parent = current;
				left = false;
				current = current.right;

			}
			else
			{
				if (current.left == null && current.right == null)		
				{
					if(left == true)
						parent.left = null;
					else
						parent.right = null;
					return true;
				}
				else if (current.left != null && current.right == null)
				{
					if(left == true)
						parent.left = current.left;
					else
						parent.right = current.left;
					return true;
				}
				else if (current.right != null && current.left == null)
				{
					if(left == true)
						parent.left = current.right;
					else
						parent.right = current.right;
					return true;
				}
				else //has two children
				{
					Node toRemove = current;
					current = current.left;
					int rightMost;
					
					while (current != null)
					{
						parent = current;
						current = current.right;
					}
					
					rightMost = parent.data;
					remove(rightMost);
					
					toRemove.data = rightMost;
					return true;
				}
			}
		}
		
		return false;
	}
	
	public static void main(String[] args)
	{
		BST myTree = new BST();
		myTree.insert(50);
		myTree.insert(30);
		myTree.insert(25);
		myTree.insert(75);
		myTree.insert(82);
		myTree.insert(28);
		myTree.insert(63);
		myTree.insert(70);
		
		myTree.inOrder2();
		System.out.println("The largest number: " + myTree.findMax());
		System.out.println("The smallest number: " + myTree.findMin());
		
		if(myTree.remove(75))
			System.out.println("Removed");
		else
			System.out.println("Doesn't exist so can't remove");
		
		myTree.inOrder2();
	}
}