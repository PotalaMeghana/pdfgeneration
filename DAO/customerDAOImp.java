package eStoreProduct.DAO;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import eStoreProduct.model.custCredModel;
import eStoreProduct.model.customerMapper;
import eStoreProduct.model.passwordHashing;

@Component
public class customerDAOImp implements customerDAO {

	JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT_CUSTOMER = "insert into slam_customers(cust_name,  cust_mobile , cust_regdate ,cust_location , cust_email ,cust_address, cust_pincode , cust_saddress,cust_spincode,cust_status ,cust_lastlogindate, cust_password ) values(?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String SQL_CHECK_CUSTOMER = "select * from slam_customers where cust_email=? and cust_password=? ";

	@Autowired
	public customerDAOImp(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@PersistenceContext
	private EntityManager em;

	public void persist(custCredModel ccm) {
		em.persist(ccm);
	}

	public boolean createCustomer(custCredModel ccm) {
		System.out.println( "spincode:"+ccm.getCustSpincode());
		return jdbcTemplate.update(SQL_INSERT_CUSTOMER, ccm.getCustName(), ccm.getCustMobile(), ccm.getCustRegDate(),
				ccm.getCustLocation(), ccm.getCustEmail(), ccm.getCustAddress(),ccm.getCustPincode(), ccm.getCustSAddress(),
				 ccm.getCustSpincode(),ccm.getCustStatus(), ccm.getCustLastLoginDate(), ccm.getCustPassword()) > 0;
	}

	// public custCredModel checkCustomer(String email, String pswd) {
	// // TODO Auto-generated method stub
	//
	// return jdbcTemplate.queryForObject(SQL_CHECK_CUSTOMER, new Object[] { email, pswd }, new customerMapper());
	// }

	public void updatelastlogin(int cid) {
		String updateQuery = "UPDATE slam_customers SET cust_lastlogindate = CURRENT_TIMESTAMP WHERE cust_id = ?";
		jdbcTemplate.update(updateQuery, cid);
	}

	public void updateccm(custCredModel ccm) {
		String updateQuery = "UPDATE slam_customers SET cust_name = ?, cust_mobile = ?, cust_location = ?, cust_address = ?,cust_pincode = ?, cust_saddress = ?,cust_spincode=?  WHERE cust_id = ?";

		jdbcTemplate.update(updateQuery, ccm.getCustName(), ccm.getCustMobile(), ccm.getCustLocation(),
				ccm.getCustAddress(),ccm.getCustPincode(), ccm.getCustSAddress(),ccm.getCustSpincode(),  ccm.getCustId());
	}

	public custCredModel getCustomerById(int id) {
		String custSelect = "SELECT * FROM slam_customers WHERE cust_id=?";

		try {
			custCredModel cu = jdbcTemplate.queryForObject(custSelect, new Object[] { id }, new customerMapper());
			return cu;
		} catch (Exception e) {
			// Handle the exception appropriately (e.g., logging, throwing custom exception, etc.)
			e.printStackTrace();
			return null; // or throw an exception if required
		}
	}

	
	
	public custCredModel getCustomer(String email, String password) {
		password = passwordHashing.hashString(password);
		//System.out.println(email + "  " + password);
		// password = passwordHashing.hashString(password);

		//System.out.println(email + "" + password);
		String custSelectQuery = "SELECT * FROM slam_customers WHERE cust_email = ? AND cust_password = ?";

		try {
			custCredModel cu = jdbcTemplate.queryForObject(custSelectQuery, new Object[] { email, password },
					new customerMapper());
			//System.out.println("query before object "+cu);
			return cu;
		} catch (Exception e) {
			// Handle the exception appropriately (e.g., logging, throwing custom exception, etc.)
			e.printStackTrace();
			return null; // or throw an exception if required
		}
	}

	public void updatecustomer(custCredModel customer) {
		String updateQuery = "UPDATE slam_customers SET cust_name = ?, cust_mobile = ?, cust_location = ?, cust_address = ?,cust_pincode = ?, cust_saddress = ?,cust_spincode = ?  WHERE cust_id = ?";

		jdbcTemplate.update(updateQuery, customer.getCustName(), customer.getCustMobile(), customer.getCustLocation(),
				customer.getCustAddress(),customer.getCustPincode(), customer.getCustSAddress(),customer.getCustSpincode(),  customer.getCustId());
	}

	public void updatePassword(String password, String email) {
		password = passwordHashing.hashString(password);
		// password = passwordHashing.hashString(password);
		String sql = "UPDATE slam_customers SET cust_password=? WHERE cust_email=?";

		jdbcTemplate.update(sql, password, email);
	}

	@Override
	public custCredModel getCustomerByEmail(String email) {
		String custSelect = "SELECT * FROM slam_customers WHERE cust_email=?";

		try {
			custCredModel cu = jdbcTemplate.queryForObject(custSelect, new Object[] { email }, new customerMapper());
			return cu;
		} catch (Exception e) {
			// Handle the exception appropriately (e.g., logging, throwing custom exception, etc.)
			//e.printStackTrace();
			return null; // or throw an exception if required
		}
	}

}