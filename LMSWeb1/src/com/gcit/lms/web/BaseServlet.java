package com.gcit.lms.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BaseServlet
 */
@WebServlet({"/BaseServlet"})
public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected static final String PAGE_NOT_FOUND = "404.html";
	protected static final String INTERNAL_SERVER_ERROR = "500.jsp";


       
    public BaseServlet() {
        super();
    }
    protected String getUrl() 
    {
    return req.getRequestURI().substring(req.getContextPath().length(), req.getRequestURI().length());
    }
    
    protected static void dispatch(HttpServletRequest req, HttpServletResponse resp, String target)
    {
    	try {
			req.getRequestDispatcher(target).forward(req, resp);
		} 
    	catch (ServletException | IOException e) 
    	{
			e.printStackTrace();
		} 
    }
	protected HttpServletRequest req;
	protected HttpServletResponse resp;
	//protected Connection conn = ConnectionUtil.openConnection();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		this.req = req;
		this.resp = resp;
		dispatch(req,resp,execute());
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
			doGet(req,resp);
		}
public abstract String execute();		
}