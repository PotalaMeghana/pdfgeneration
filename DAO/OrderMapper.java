package eStoreProduct.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.springframework.jdbc.core.RowMapper;

import eStoreProduct.model.orderModel;

public class OrderMapper implements RowMapper<orderModel> {

	@Override
	public orderModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		orderModel order=new orderModel();
		order.setOrdr_id(rs.getInt(1));
		order.setCustid(rs.getInt(2));
		order.setOrd_billno(rs.getString(3));
		java.sql.Timestamp timestamp = rs.getTimestamp(4);

        // Convert the SQL timestamp to a Java Date object
        Date javaDate = new Date(timestamp.getTime());
		order.setOrderdate(javaDate);
		order.setOrdertotal(rs.getDouble(5));
		order.setOrder_gst(rs.getDouble(6));
		order.setOrderpayid(rs.getString(7));
		order.setSaddress(rs.getString(8));
		order.setCustname(rs.getString(9));
		order.setMobile(rs.getString(10));
		order.setLocation(rs.getString(11));
		return order;
	}

}
