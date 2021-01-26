package Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "product")
@Entity
public class ProductDTO {
	 @Column(name = "id")
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	  Long id;
    @Column
	    String name;
    @Column
	    boolean available;
    
		public ProductDTO(long id, String name, boolean available) {
this.id=id;
this.name=name;
this.available = available;
		}
		   public ProductDTO() {
		  
		    }

		public ProductDTO(String name2, Boolean isAvailable) {
			// TODO Auto-generated constructor stub
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public boolean isAvailable() {
			return available;
		}
		public void setAvailable(boolean available) {
			this.available = available;
		}
		   public static int compareByName(ProductDTO a, ProductDTO b) {
		        return a.name.compareTo(b.name);
		    }
		@Override
		public String toString() {
			return "ProductDTO [id=" + id + 
					", name=" + name + 
					", available=" + available + "]";
		}
	   
	    
}
