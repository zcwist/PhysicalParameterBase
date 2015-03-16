package tester;

import webutil.CatagoryTree;

public class WebUtilTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		categoryTree();
	}

	public static void categoryTree()
	{
		System.out.println(CatagoryTree.getNodesByParentNode(null));
		System.out.println(CatagoryTree.getNodesByParentNode("介质类型:美元"));
		System.out.println(CatagoryTree.getNodesByParentNode("新旧程度:七"));
	}
}
