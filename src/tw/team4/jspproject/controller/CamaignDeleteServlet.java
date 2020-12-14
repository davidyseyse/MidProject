package tw.team4.jspproject.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.team4.jspproject.dao.DaoCampaign;


@WebServlet("/jsp/team4/campaign/CamaignDeleteServlet")
public class CamaignDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding("UTF-8");
		int campId = Integer.parseInt(request.getParameter("campId"));
		
		String queryStr = request.getParameter("queryStr");
		request.setAttribute("queryStr",queryStr);
		String queryType = request.getParameter("queryType");
		request.setAttribute("queryType",queryType);
		
		DaoCampaign dao = new DaoCampaign();
		
		try {
			dao.createConnection();
			int row = dao.delete(campId);
			System.out.println(campId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			try {
				dao.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//刪除後重新導向查詢Servlet
		RequestDispatcher dis = request.getRequestDispatcher("CampaignQueryServlet");		
		dis.forward(request, response);
		
	}

}
