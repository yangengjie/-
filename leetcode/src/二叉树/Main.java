package 二叉树;

public class Main {
/**
 * 节点的左子树只包含小于当前节点的数。
节点的右子树只包含大于当前节点的数。
所有左子树和右子树自身必须也是二叉搜索树。
[5,1,4,null,null,3,6]
 * @param args
 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root1=new TreeNode(5);
		TreeNode root2=new TreeNode(1);
		TreeNode root3=new TreeNode(3);
		TreeNode root4=new TreeNode(4);
		TreeNode root5=new TreeNode(6);
		
		root1.left=root2;
		
		root2.right=root4;
		root4.left=root3;
		
		root1.right=root5;
		 _98_验证二叉搜索树  aa=new  _98_验证二叉搜索树 ();
		 System.out.println(aa.isValidBST(root1));
	}

}
