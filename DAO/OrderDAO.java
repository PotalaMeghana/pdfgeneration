
  package eStoreProduct.DAO;
  
  import java.util.List;

import eStoreProduct.model.orderModel;
import eStoreProduct.utility.ProductStockPrice;
 
  public interface OrderDAO {
  
  //public orderModel getOrderDetails(int oid);
  
  public orderModel getOrderDetails(int orderid, int custid);

void insertIntoOrders(orderModel or, List<ProductStockPrice> al);
  
  }
  
 

