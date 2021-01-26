package Products;


import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.sessionutil.SessionUtil;

 
public class ProductDAO {
    
    public void addProduct(ProductDTO bean){
        Configuration con = new Configuration().configure().addAnnotatedClass(ProductDTO.class);
        ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties())
        		.buildServiceRegistry();
        SessionFactory sf = con.buildSessionFactory(reg);

        Session session = sf.openSession();        
        Transaction tx = session.beginTransaction();
        addProduct(session,bean);        
        tx.commit();
        session.close();
        
    }
    
    private void addProduct(Session session, ProductDTO bean){
        ProductDTO product = new ProductDTO();
        
        product.setName(bean.getName());
        product.setAvailable(bean.isAvailable());
        
        session.save(product);
    }
    
    public List<ProductDTO> getProducts(){
        Session session = SessionUtil.getSession();    
        Query query = session.createQuery("from product");
        List<ProductDTO> product =  query.list();
        session.close();
        return product;
    }
 
    public int deleteProduct(int id) {
        Session session = SessionUtil.getSession();
        Transaction tx = session.beginTransaction();
        String hql = "delete from product where id = :id";
        Query query = session.createQuery(hql);
        query.setInteger("id",id);
        int rowCount = query.executeUpdate();
        System.out.println("Rows affected: " + rowCount);
        tx.commit();
        session.close();
        return rowCount;
    }
    
    public int updateProduct(int id, ProductDTO prod){
         if(id <=0)  
               return 0;  
         Session session = SessionUtil.getSession();
            Transaction tx = session.beginTransaction();
            String hql = "update product set name = :name, available=:available where id = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id",id);
            query.setString("name",prod.getName());
            query.setBoolean("available",prod.isAvailable());
            int rowCount = query.executeUpdate();
            System.out.println("Rows affected: " + rowCount);
            tx.commit();
            session.close();
            return rowCount;
    }
}
