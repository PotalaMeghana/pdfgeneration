package eStoreProduct.controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import eStoreProduct.model.Product;
import eStoreProduct.model.custCredModel;
import eStoreProduct.utility.ProductStockPrice;
import eStoreProduct.DAO.WishlistDAO;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

@Controller
public class WishlistController {

	WishlistDAO wishlistdao;
	@Autowired
	public WishlistController(WishlistDAO wishlistimp)
	{
		wishlistdao=wishlistimp;
	}
	@GetMapping("/addToWishlist")
	@ResponseBody
	public String addToWishlist(@RequestParam(value = "productId", required = true) int productId, Model model, HttpSession session)
			throws NumberFormatException, SQLException {
		System.out.println("add to wishlist called1");
		//ProductDAO pdao = new ProductDAO();
		//System.out.println("add to wishlist called2");
		custCredModel cust = (custCredModel) session.getAttribute("customer");
		return wishlistdao.addToWishlist(productId, cust.getCustId()) + " Added to wishlist";

	}
	@GetMapping("/removeFromWishlist")
	@ResponseBody
	public String removeFromWishlist(@RequestParam(value = "productId", required = true) int productId, Model model, HttpSession session)
			throws NumberFormatException, SQLException {
		System.out.println("remove from wishlist called1");
		//ProductDAO pdao = new ProductDAO();
		System.out.println("remove from wishlist called2");
		custCredModel cust = (custCredModel) session.getAttribute("customer");
		return wishlistdao.removeFromWishlist(productId, cust.getCustId()) + " remove from wishlist";

	}
	@GetMapping("/wishlistItems")
	//@ResponseBody
	public String userWishlistItems(Model model,HttpSession session)
			throws NumberFormatException, SQLException {
		System.out.println("wishlist called1");
		 custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		 List<ProductStockPrice> products = wishlistdao.getWishlistProds(cust1.getCustId());
	        
	        // Set the products attribute in the model
	        model.addAttribute("products", products);
	        
	        // Forward to the cart.jsp view
	        return "wishlistCatalog";
	}
	
	
}
