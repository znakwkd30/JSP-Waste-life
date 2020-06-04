package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.api.FriendMgr;

/**
 * Servlet implementation class Follow
 */
@WebServlet("/follow")
public class Follow extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Follow() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("follow.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FriendMgr fm = new FriendMgr();
		
		String friendStr = request.getParameter("members");
		if(friendStr != null) {
			String[] friends = friendStr.split(";");
			if(friends != null) {
					try
					{
						System.out.println(friends[0]);
						System.out.println(friends[1]);
						int check = fm.checkFriendExists(friends[0], friends[1]);
						System.out.println(check);
						if(check == 0) {
							fm.addFriend(friends[0], friends[1]);
							request.getRequestDispatcher("home.jsp").forward(request, response);
						}
						else {
							request.getRequestDispatcher("follow.jsp").forward(request, response);
							return;
						}
						
					} catch (Exception e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		}
	}

}
