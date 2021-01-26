

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import Products.ProductDTO;

/**
 * Servlet implementation class CreateNewProduct
 */
@WebServlet("/CreateNewProduct")
public class CreateNewProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateNewProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 PrintWriter out = response.getWriter();

	        String productName = request.getParameter("product-name");
	        String productAvailability = request.getParameter("product-available");

	     
		        boolean avail = Boolean.parseBoolean(productAvailability);
		        ProductDTO pr = new ProductDTO();
		        pr.setName(productName);
		        pr.setAvailable(avail);
		      
		   
		        
		        Configuration con = new Configuration().configure().addAnnotatedClass(ProductDTO.class);
		        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties())
		        		.buildServiceRegistry();
		        
		        SessionFactory sf = con.buildSessionFactory(reg);
		        
		        Session session =sf.openSession();
		        Transaction tx = session.beginTransaction();
		        session.save(pr);
		        tx.commit();
		        out.println(pr);
	        
	        	        response.setContentType("text/html");
	        out.println("Product was created");
	}

}
