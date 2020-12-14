package tw.team4.jspproject.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.team4.jspproject.dao.DaoCampaign;
import tw.team4.jspproject.javabean.Campaign;
import tw.team4.jspproject.javabean.QueryBean;

@WebServlet("/jsp/team4/campaign/CampaignQueryServlet")
public class CampaignQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		processAction(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processAction(request,response);
	}

	private void processAction(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		
		request.setCharacterEncoding("UTF-8");
		String queryStr = request.getParameter("queryStr");
		String queryType = request.getParameter("queryType");
		QueryBean queryBean = new QueryBean(queryStr,queryType);
		request.setAttribute("queryBean", queryBean);
		
		DaoCampaign daoCamp = new DaoCampaign();
		try{			
			daoCamp.createConnection();
			ArrayList<Campaign> camps = daoCamp.query(queryBean);
			request.setAttribute("campaigns", camps);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try {
				daoCamp.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("CampaignShow.jsp");
		
		try {
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
