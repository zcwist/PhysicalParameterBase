package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import webutil.CatagoryTree;
import dao.RecordSampleTypeDao;
import dao.RecordTypeDao;

public class QueryServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public QueryServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		String parentNode = request.getParameter("parentNode");
		if (parentNode.length() == 0) parentNode = null;
		PrintWriter out = response.getWriter();
		out.println(CatagoryTree.getNodesByParentNode(parentNode));
		out.flush();
		out.close();
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		String askFor = request.getParameter("askFor");
		if (askFor.equals("recordTable")){
			String objectId = request.getParameter("objectId");
			System.out.println("oid:" + objectId);
			out.println(new RecordTypeDao().getItemsByOid(objectId).toString());
		}
		else if (askFor.equals("objectTree")){
		
			String parentNode = request.getParameter("parentNode");
			if (parentNode.length() == 0) parentNode = null;
			
			out.println(CatagoryTree.getNodesByParentNode(parentNode));
		}
		else if (askFor.equals("sampleTable")){
			String objectId = request.getParameter("objectId");
			String parameterId = request.getParameter("parameterId");
			out.println(new RecordSampleTypeDao().getRecordSampleByOidPid(objectId, parameterId));
			
		}
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
