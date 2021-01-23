package Products;



public class ProductDTO {
	  Long id;
	    String name;
	    boolean available;
		public ProductDTO(long id, String name, boolean available) {
this.id=id;
this.name=name;
this.available = available;
		}
		   public ProductDTO(String name, boolean available) {
		        this.name = name;
		        this.available = available;
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
