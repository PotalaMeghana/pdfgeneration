package eStoreProduct.DAO;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import eStoreProduct.model.ProductRowMapper;
import eStoreProduct.model.cartModel;
import eStoreProduct.utility.ProductStockPrice;

@Component
public class cartDAOImp implements cartDAO {
	JdbcTemplate jdbcTemplate;
	private ProdStockDAO prodStockDAO;
	@Autowired
	public cartDAOImp(DataSource dataSource, ProdStockDAO prodStockDAO) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		 this.prodStockDAO = prodStockDAO;
	}
	private String insert_slam_cart = "INSERT INTO slam_cart (cust_id,prod_id,quantity) VALUES (?, ?,1)";
	private String delete_slam_cart = "DELETE FROM SLAM_CART WHERE cust_id=? AND prod_id=?";
	private String select_cart_products = "SELECT pd.*,sc.* FROM slam_Products pd, slam_cart sc WHERE sc.cust_id = ? AND sc.prod_id = pd.prod_id";
	private String update_qty = "update slam_cart set quantity=? where cust_id=? and prod_id=?";

	public int addToCart(int productId, int customerId) {
		int r = jdbcTemplate.update(insert_slam_cart, customerId, productId);
		if (r > 0) {
			System.out.println("inserted into cart");
			return productId;

		} else {
			return -1;
		}
	}

	public int removeFromCart(int productId, int customerId) {
		int r = jdbcTemplate.update(delete_slam_cart, customerId, productId);
		if (r > 0) {
			System.out.println("deleted from  cart");
			return productId;
		} else {
			return -1;
		}
	}

	public List<ProductStockPrice> getCartProds(int cust_id) {
		System.out.println(cust_id + " from model");
		try {
			List<ProductStockPrice> cproducts = jdbcTemplate.query(select_cart_products, new CartProductRowMapper(prodStockDAO), cust_id);
			System.out.println(cproducts.toString());
			return cproducts;
		} catch (Exception e) {
			e.printStackTrace();
			return Collections.emptyList(); // or throw an exception if required
		}
	}
	
	public int updateQty(cartModel cm) {
		int r = jdbcTemplate.update(update_qty, cm.getQuantity(), cm.getCid(), cm.getPid());
		if (r > 0) {
			System.out.println("updated in cart  "+r+"  hi");
			return r;
		} else {
			return -1;
		}
	}
    @Override
	public double getCartCostNonLogin(List<ProductStockPrice> list) {
		double cartcost=0.0;
		for(ProductStockPrice p:list)
		{
			cartcost+=p.getPrice()*p.getQuantity();
		}
		return cartcost;
		//return 0;
	}

}