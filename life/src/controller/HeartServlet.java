package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.api.HeartMgr;
import db.api.ProjectMgr;
import db.bean.MemberBean;

/**
 * Servlet implementation class HeartServlet
 */
@WebServlet("/heart")
public class HeartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		MemberBean member = (MemberBean)session.getAttribute("user");
		System.out.println(member.getId());
		System.out.println(Integer.parseInt(request.getParameter("timelineId")));
		String id = member.getId();
		int timelineId = Integer.parseInt(request.getParameter("timelineId"));
		
		HeartMgr hm = new HeartMgr();
		if(hm.firstHeart(id, timelineId) == 1) {
			ProjectMgr pm = new ProjectMgr();
			pm.plusHeart(id, timelineId);
		}
		
		response.sendRedirect("home");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
