package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import db.api.*;
import db.bean.*;


/**
 * Servlet implementation class Main
 */
@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ProjectBean project = (ProjectBean)session.getAttribute("project");
		
		
		if(project == null) {
			request.getRequestDispatcher("home").forward(request, response);
			return;
		}
		
		int projectid = project.getId();
		
		ProjectMgr projectmgr = new ProjectMgr();
		ArrayList<MemberBean> member = projectmgr.getProjectMembers(projectid);
		session.setAttribute("projectmember", member);
		
		TimeLineMgr timeline = new TimeLineMgr();
		ArrayList<TimeLineBean> TL = timeline.getComment(projectid);
		session.setAttribute("timeline", TL);
		
		//File
		FileMgr file = new FileMgr();
		ArrayList<FileBean> files = file.getAllProjectFiles(projectid);
		session.setAttribute("files", files);
		
		request.getRequestDispatcher("main.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
