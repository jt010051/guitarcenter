package Products;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    final static String SELECT_ALL_SQL = "SELECT id, name, available FROM info;";
    final static String INSERT_SQL = "INSERT INTO info (name, available) VALUES (?, ?);";
    final static String UPDATE_SQL = "UPDATE info SET name = ?, available = ? WHERE id = ?;";
    final static String DELETE_SQL = "DELETE FROM info WHERE id = ?;";

    Connection conn = null;

    public ProductDAOImpl() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/guitarcenter?createDatabaseIfNotExist=true", "root", "admin");
        } catch (ClassNotFoundException e) {
            System.out.println("Driver not found");
        } catch (SQLException e) {
            System.out.println("SQL exception");
            e.printStackTrace();
        }
    }

    public List<ProductDTO> getAll() {
        List<ProductDTO> drinks = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(SELECT_ALL_SQL);
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                boolean available = rs.getBoolean("available");
                drinks.add(new ProductDTO(id, name, available));
            }
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return drinks;
    }

    
    public ProductDTO create(ProductDTO product) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(
                INSERT_SQL,
                Statement.RETURN_GENERATED_KEYS
        )) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, (product.isAvailable() ? 1 : 0));
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Unable to create record");
            }
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getLong(1));
                    return product;
                } else {
                    throw new SQLException("Creating drink failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            System.out.println("unable to run query");
            e.printStackTrace();
        }
        return null;
    }

    
    public void remove(Long id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, id.intValue());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    
    public void remove(ProductDTO product) {
        try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_SQL)) {
            preparedStatement.setInt(1, product.getId().intValue());
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
    }

    
    public ProductDTO update(ProductDTO product) {
        if (product.getId() > Integer.MAX_VALUE) {
            throw new RuntimeException("ID too large");
        }
        try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.isAvailable() ? 1 : 0);
            preparedStatement.setInt(3, product.getId().intValue());
            int changes = preparedStatement.executeUpdate();
            if (changes > 0) {
                return product;
            }
        } catch (SQLException e) {
            System.out.println("Unable to run query");
            System.out.println("SQL State: " + e.getSQLState());
            System.out.println("Error Code: " + e.getErrorCode());
            e.printStackTrace();
        }
        return null;
    }

    public int count() {
        return 0;
    }

	public boolean isAvailable(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
