package eStoreProduct.DAO;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import eStoreProduct.utility.ProductStockPrice;

@Component
public class WishlistDAOImp implements WishlistDAO {

	JdbcTemplate jdbcTemplate;
	private ProdStockDAO prodStockDAO;
	@Autowired
	public WishlistDAOImp(DataSource dataSource, ProdStockDAO prodStockDAO) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		 this.prodStockDAO = prodStockDAO;
	}
	private String insert_slam_wishlist = "INSERT INTO slam_wishlist (cust_id,prod_id) VALUES (?, ?)";
	private String delete_slam_wishlist = "DELETE FROM slam_wishlist WHERE cust_id=? AND prod_id=?";
	private String select_slam_wishlist = "SELECT p.prod_id, p.prod_title, p.prod_brand, p.image_url, p.prod_desc, ps.prod_price FROM \r\n"
			+ "       slam_Products p, slam_productstock ps,slam_wishlist sw \r\n"
			+ "       where p.prod_id = ps.prod_id and sw.cust_id = ? AND sw.prod_id = p.prod_id";

	public int addToWishlist(int productId, int customerId) {
		int r = jdbcTemplate.update(insert_slam_wishlist, customerId, productId);
		if (r > 0) {
			System.out.println("inserted into cart");
			return productId;

		} else {
			return -1;
		}
	}

	public int removeFromWishlist(int productId, int customerId) {
		int r = jdbcTemplate.update(delete_slam_wishlist, customerId, productId);
		if (r > 0) {
			System.out.println("deleted from  cart");
			return productId;
		} else {
			return -1;
		}
	}

	public List<ProductStockPrice> getWishlistProds(int cust_id) {
		try {
			List<ProductStockPrice> cproducts = jdbcTemplate.query(select_slam_wishlist, new WishlistRowMapper(prodStockDAO), cust_id);
			System.out.println(cproducts.toString());
			return cproducts;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList(); // or throw an exception if required
		}}
}