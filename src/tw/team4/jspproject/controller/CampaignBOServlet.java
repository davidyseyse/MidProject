package tw.team4.jspproject.controller;

import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.team4.jspproject.dao.DaoCampaign;
import tw.team4.jspproject.javabean.Campaign;




@WebServlet("/jsp/team4/campaign/CampaignBOServlet")
public class CampaignBOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		//-----------獲取查詢字串並處理成能夠放進bean的格式-------------------
		String title = request.getParameter("title");
		String startDate = request.getParameter("startDate");
		String startTime = request.getParameter("startTime");
		String StartDateTime = startDate+" "+startTime+":00";
		
		Timestamp StartDateTimeStamp = Timestamp.valueOf(StartDateTime);
		
		String endDate = request.getParameter("endDate");
		String endTime = request.getParameter("endTime");
		String endDateTime = endDate+" "+endTime+":00";
		
		Timestamp endDateTimeStamp = Timestamp.valueOf(endDateTime);
		
		Boolean status = Boolean.valueOf(request.getParameter("status"));	
		String description = request.getParameter("description");
		//----------------------------------------------------------------		

		Campaign camp =  new Campaign(title,description,StartDateTimeStamp,endDateTimeStamp,status);
		request.setAttribute("camp", camp);
		
		DaoCampaign dao = new DaoCampaign();
		try {
			dao.createConnection();
			dao.insert(camp);
		} catch (Exception e) {
			//當新增失敗拋出例外會跳轉到失敗頁面
			String errorMsg = e.toString();
			request.setAttribute("errorType", "新增失敗");
			request.setAttribute("errorMsg", errorMsg);
			RequestDispatcher dis = request.getRequestDispatcher("ErrorPage.jsp");
			dis.forward(request, response);
		}finally {
			try {
				dao.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//新增成功跳轉至新增成功頁面
		RequestDispatcher dis = request.getRequestDispatcher("showResult.jsp");
		dis.forward(request, response);
	}

}
