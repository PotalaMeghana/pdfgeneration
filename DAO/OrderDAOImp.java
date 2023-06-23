package eStoreProduct.DAO;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eStoreProduct.model.orderModel;
import eStoreProduct.utility.ProductStockPrice;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
@Component
public class OrderDAOImp implements OrderDAO {

	JdbcTemplate jdbcTemplate;
	
	@Autowired
	public OrderDAOImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
		// this.prodStockDAO = prodStockDAO;
	}
	
	private final String get_order_details="select ordr_id,ordr_cust_id,ordr_billno,ordr_odate,ordr_total,ordr_gst,ordr_payreference,\r\n"
			+ "       ordr_saddress,cust_name,cust_mobile,cust_email,cust_location from slam_orders,slam_customers where ordr_cust_id=cust_id and cust_id=? and ordr_id=?";
	private String ins = "INSERT INTO slam_orders (ordr_cust_id, ordr_billno, ordr_odate, ordr_total, ordr_gst, ordr_payreference, ordr_paymode, "
			+ "ordr_saddress, order_shipment_status,ordr_shipment_date) "
			+ "VALUES (?, ?, CURRENT_TIMESTAMP,?, ?,?, ?, ?, ?, CURRENT_DATE + INTERVAL '5 days') RETURNING ordr_id";
	private String ins_ord_prd = "INSERT INTO slam_orderproducts (ordr_id, prod_id, orpr_qty,  orpr_price, orpr_shipment_status)\r\n"
			+ "VALUES (?, ?, ?, ?, ?)";

	
	@Override
	public orderModel getOrderDetails(int orderid,int custid) {
		orderModel order=jdbcTemplate.queryForObject(get_order_details, new Object[] { 1,15 }, new OrderMapper());
		System.out.println("order obj inn jdbc     "+order);
		return order;
	}
	
	@Override
	public void insertIntoOrders(orderModel or, List<ProductStockPrice> al) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(con -> {
			PreparedStatement ps = con.prepareStatement(ins, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, or.getCustid());
			ps.setString(2, or.getOrd_billno());
			ps.setDouble(3, or.getOrdertotal());
			ps.setDouble(4, or.getOrder_gst());
			ps.setString(5, or.getOrderpayid());
			ps.setString(6, "Online");
			//ps.setString(7, "");
			ps.setString(7, or.getSaddress());
			ps.setString(8, "Order_Placed");

			return ps;
		}, keyHolder);

		Number generatedOrderId = keyHolder.getKey();
		int ordrId = generatedOrderId != null ? generatedOrderId.intValue() : 0;

		for (ProductStockPrice product : al) {
			jdbcTemplate.update(ins_ord_prd, ordrId, product.getProd_id(), product.getQuantity(),
					product.getPrice(), "Order_Placed");
		}
	}
	

}
