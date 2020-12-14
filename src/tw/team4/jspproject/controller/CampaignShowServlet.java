package tw.team4.jspproject.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.team4.jspproject.javabean.Campaign;
import tw.team4.jspproject.dao.DaoCampaign;

/**
 * Servlet implementation class CampaignShowServlet
 */
@WebServlet("/jsp/team4/campaign/CampaignShowServlet")
public class CampaignShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processAction(request,response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DaoCampaign dao = new DaoCampaign();
		try {
			dao.createConnection();
			ArrayList<Campaign> camps = dao.queryAll();
			request.setAttribute("campaigns", camps);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				dao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		RequestDispatcher dis = request.getRequestDispatcher("CampaignShow.jsp");
		dis.forward(request, response);
	}

}
