

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GC
 */
@WebServlet("/GC")
public class GC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        out.println("<form action='' method='POST'>");
	        out.println("<label>Enter id: <input type='text' name='id'></input></label>");
	        out.println("<input type='submit'>Find Customer</input>");
	        out.println("</form>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
        if (id != null) {

            /** This is what makes it a "valid" login */
            request.getSession().setAttribute(
                    "id", id
            );

            response.sendRedirect("Products");
        }
        this.doGet(request, response);
	}

}
