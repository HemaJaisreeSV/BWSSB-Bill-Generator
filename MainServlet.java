package com.bwssb.water.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bwssb.water.bean.WaterBillBean;
import com.bwssb.water.service.Administrator;

public class MainServlet extends HttpServlet 
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{

		response.setContentType("text/html");

		String operation = request.getParameter("operation");

		if (operation.equals("generate")) 
		{
			String s1 = generateWaterBill(request);
			if (s1.equals("FAIL")) 
			{
				request.getRequestDispatcher("error.html").forward(request, response);
			} 
			else 
			{
				request.getRequestDispatcher("success.html").forward(request, response);
			}
		} 
		else if (operation.equals("view")) 
		{
			WaterBillBean b = viewWaterBill(request);

			if (b == null) 
			{
				request.getRequestDispatcher("viewBill.jsp").forward(request, response);
			} 
			else 
			{
				request.setAttribute("waterBill", b);
				request.getRequestDispatcher("displayBill.jsp").forward(request, response);
			}
		}
		/*
		 * else { RequestDispatcher req = request.getRequestDispatcher("generateBill.jsp"); }
		 */
	}

	public String generateWaterBill(HttpServletRequest request) 
	{
		int consumerNumber = Integer.parseInt(request.getParameter("consumerNumber"));
		String billMonth = request.getParameter("billMonth");
		String year = request.getParameter("year");
		int startReading = Integer.parseInt(request.getParameter("startReading"));
		int currentReading = Integer.parseInt(request.getParameter("currentReading"));
		String connectionType = request.getParameter("connectionType");

		WaterBillBean obj = new WaterBillBean();
		obj.setconsumerNumber(consumerNumber);
		obj.setbillMonth(billMonth);
		obj.setYear(year);
		obj.setstartReading(startReading);
		obj.setcurrentReading(currentReading);
		obj.setconnectionType(connectionType);

		Administrator obj1 = new Administrator();
		String s = obj1.addWaterBill(obj);
		return s;

	}

	public WaterBillBean viewWaterBill(HttpServletRequest request) 
	{
		int consumerNumber = Integer.parseInt(request.getParameter("consumerNumber"));
		String billMonth = request.getParameter("billMonth");
		String year = request.getParameter("year");

		Administrator obj2 = new Administrator();
		WaterBillBean b1 = obj2.viewWaterBill(consumerNumber, billMonth, year);
		return b1;
	}
}
