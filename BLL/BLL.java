package eStoreProduct.BLL;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eStoreProduct.DAO.CartProductRowMapper;
import eStoreProduct.DAO.ProductDAO;
import eStoreProduct.DAO.cartDAO;
import eStoreProduct.model.productqty;
import eStoreProduct.utility.ProductStockPrice;
@Component
public class BLL {
 ProductDAO pdaoimp;
 cartDAO cartimp;
 HttpSession session;
 @Autowired
public BLL(ProductDAO productdao,cartDAO ca) {
	 pdaoimp=productdao;
	 cartimp=ca;
}
 /*static private ArrayList<productqty> prd = new ArrayList<>();
	public static void addProduct(int pid, Double prrc) {
		try {
			if (prd.isEmpty()) {
				//System.out.println("IN BLLLLLLLLLL");
				//System.out.println(pid);
				productqty obj = new productqty();
				obj.setPid(pid);
				obj.setPrice(prrc);
				obj.setQty(1);
				prd.add(obj);
			} else {
				for (productqty p : prd) {
					if (p.getPid() != pid) {
						//System.out.println("IN BLLLLLLLLLL in else");
						//System.out.println(pid);
						productqty obj = new productqty();
						obj.setPid(pid);
						obj.setPrice(prrc);
						obj.setQty(1);
						prd.add(obj);
						break;
					} else {
						int qt = p.getQty();
						p.setQty(qt + 1);
						p.setPrice(prrc*(p.getQty()));
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void remove(int pid) {
		for (productqty p : prd) {
			if (p.getPid() == pid) {
				prd.remove(p);
				break; // Add break statement here to exit the loop after removing the item
			}
		}
	}

	public List<ProductStockPrice> getcart() {
		List<ProductStockPrice> prds = new ArrayList<>(); // Initialize prds with an empty ArrayList
		if (prd.isEmpty()) {
			return prds;
		} else {
			for (productqty p : prd) {
				ProductStockPrice product = pdaoimp.getProductById(p.getPid());
				prds.add(product);
			}
			return prds;
		}
	}

	public void updateqty(int pid, int qty) {
	    //System.out.println("Quantity updated:");
	    //System.out.println(prd.isEmpty());
	    for (productqty p : prd) {

	        if (p.getPid() == pid) {
	            p.setQty(qty);
	            //System.out.println("Updated quantity:if " + p.getQty());
				ProductStockPrice product = pdaoimp.getProductById(p.getPid());

	            double price = product.getPrice() * qty;
	            p.setPrice(price);
	            //System.out.println("Price updated: " + price);
				System.out.println(p.getPid()+"  pid   "+p.getQty()+"  qty  "+p.getPrice()+"    price");

	            break;
	        }
	    }
	}
    public void addproducts(List<ProductStockPrice> pr)
    {
    	for(ProductStockPrice p:pr)
    	{
    		BLL.addProduct(p.getProd_id(),p.getPrice());
    	}
    }

	public double getcartcost() {
		double cartcost = 0.0;
		for (productqty p : prd) {
			cartcost += p.getPrice();
			//System.out.println("in bll cartcost calc  "+p.getPid()+"   "+p.getQty()+"    "+p.getPrice());
			//System.out.println("Total "+cartcost);
		}
		return cartcost;
	}

	public double getcost(List<ProductStockPrice> pr) {
		double cost=0;
		for (ProductStockPrice p : pr) {
			System.out.println("pid "+p.getProd_id()+"  price "+p.getPrice());
			cost += p.getPrice();
		}
		return cost;
	}
	public List<productqty> getproductqtys()
	{
		return prd;
	}*/
 
 public double getCartCost(int id) {
		double cartcost=0.0;
		List<ProductStockPrice> cproducts = cartimp.getCartProds(id);
		for(ProductStockPrice p:cproducts)
		{
			cartcost+=p.getPrice()*p.getQuantity();
		}
		//String custspincode=(String) session.getAttribute("custspincode");
		//System.out.println("in getting cart cost custspincode  "+custspincode);
		return cartcost;
	}
 public double getShipmentCharges()
 {
	 String custspincode=(String) session.getAttribute("custspincode");
	 
	return 0;
	 
 }
}
