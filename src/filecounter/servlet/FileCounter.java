package filecounter.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import filecounter.dao.FileDao;

/**
 * Servlet implementation class FileCounter
 */
@WebServlet("/FileCounter")
public class FileCounter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	int count;
	private FileDao dao;
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileCounter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set a cookie for the user so that refreshing won't increase the count
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(5);
		response.setContentType("text/plain");
		PrintWriter printWriter = response.getWriter();
		if (session.isNew()) count++;
		printWriter.println("The web has been accessed for " + count +" times.");
	}
	
	public void init() throws ServletException{
		dao = new FileDao();
		try{
			count = dao.getCount();
		}
		catch (Exception ex){
			getServletContext().log("Exception during init", ex);
			throw new ServletException("Exception in init" + ex.getMessage());
		}
	}
	
	public void destroy() {
		super.destroy();
		try{
			dao.save(count);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
