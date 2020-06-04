package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.api.FriendMgr;
import db.api.ProjectMgr;
import db.bean.FriendBean;
import db.bean.MemberBean;
import db.bean.ProjectBean;

/**
 * Servlet implementation class FriendServlet
 */
@WebServlet("/friend")
public class FriendServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FriendServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean)session.getAttribute("user");
		
		if(member != null) {
			FriendMgr fm = new FriendMgr();
			ArrayList<FriendBean> list = fm.getFriendList(member.getId());
			
			session.setAttribute("memberlist", list);
		}
		
		request.getRequestDispatcher("friend.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String removeId = request.getParameter("id");
		FriendMgr fm = new FriendMgr();
		try
		{
			fm.removeFriend(removeId);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		response.sendRedirect("friend");
	}

}
