
package dao;

import beans.Product;
import conexao.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Conexao conexao;
    private Connection conn;
    
    public ProductDAO(){
        this.conexao = new Conexao();
        this.conn = this.conexao.getConexao();
    }
    
    public void insert(Product product){
        String sql = "INSERT INTO products(productName, description, targetMarket, stack) VALUES "
                + "(?,?,?,?)";
        try {
          PreparedStatement stmt = this.conn.prepareStatement(sql);
          stmt.setString(1, product.getProductName());
          stmt.setString(2, product.getDescription());
          stmt.setString(3, product.getTargetMarket());
          stmt.setString(4, product.getStack());
          stmt.execute();
        
        } catch (Exception e) {
            System.out.println("Insert product error: " + e.getMessage());
        }
    }
    
    
    public void edit(Product product){
        String sql = "UPDATE products SET productName = ?, description = ?, targetMarket = ?, stack = ? WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setString(3, product.getTargetMarket());
            stmt.setString(4, product.getStack());
            stmt.setInt(5, product.getId());
            stmt.execute();

        } catch (Exception e) {
            System.out.println("Product update error: " + e.getMessage());
        }
    }

    public void delete(Integer id){
        String sql = "DELETE FROM products WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        } catch (Exception e) {
            System.out.println("Delet product error: " + e.getMessage());
        }
    }
      
    
    public Product getProduct(Integer id){
        String sql = "SELECT * FROM products WHERE id = ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Product product = new Product();
            
            rs.first();
            product.setId(id);
            product.setProductName(rs.getString("productName"));
            product.setDescription(rs.getString("description"));
            product.setTargetMarket(rs.getString("targetMarket"));
            product.setStack(rs.getString("stack"));
            return product;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List<Product> getProducts(String productName){
        String slq = "SELECT * FROM products WHERE productName LIKE ?";
        try {
            PreparedStatement stmt = this.conn.prepareStatement(slq);
            stmt.setString(1, "%" + productName + "%");
            ResultSet rs = stmt.executeQuery();
            List<Product> productsList = new ArrayList<>();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setProductName(rs.getString("productName"));
                product.setDescription(rs.getString("description"));
                product.setTargetMarket(rs.getString("targetMarket"));
                product.setStack(rs.getString("stack"));
                productsList.add(product);
            }
            return productsList;
        } catch (Exception e) {
            return null;
        }
    }
}
