package Products;



public interface ProductDAO extends GenericDAO<ProductDTO> {

    boolean isAvailable(Long id);

}
