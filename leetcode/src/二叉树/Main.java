package ������;

public class Main {
/**
 * �ڵ��������ֻ����С�ڵ�ǰ�ڵ������
�ڵ��������ֻ�������ڵ�ǰ�ڵ������
�������������������������Ҳ�Ƕ�����������
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
		 _98_��֤����������  aa=new  _98_��֤���������� ();
		 System.out.println(aa.isValidBST(root1));
	}

}
