package eStoreProduct.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eStoreProduct.DAO.ProductDAO;
import eStoreProduct.model.Category;
import eStoreProduct.model.Product;
import eStoreProduct.utility.ProductStockPrice;

@Controller
// @ComponentScan(basePackages = "Products")
public class ProductController {

	private final ProductDAO pdaoimp;

	@Autowired
	public ProductController(ProductDAO productdao) {
		pdaoimp = productdao;
	}

	@GetMapping("/CategoriesServlet")
	@ResponseBody
	public String displayCategories(Model model) {
		List<Category> categories = pdaoimp.getAllCategories();
		StringBuilder htmlContent = new StringBuilder();
		htmlContent.append("<option disabled selected>Select Product category</option>");
		for (Category category : categories) {
			htmlContent.append("<option value='").append(category.getPrct_id()).append("'>")
					.append(category.getPrct_title()).append("</option>");
		}

		return htmlContent.toString();
	}

	@PostMapping("/categoryProducts")
	public String showCategoryProducts(@RequestParam(value = "category_id", required = false) int categoryId,
			Model model) {
		//System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiii");
		//System.out.println("based on category method" + categoryId);

		List<ProductStockPrice> products;
		if (categoryId != 0) {
			products = pdaoimp.getProductsByCategory(categoryId);System.out.println("hiiiiiiiiiii");
		} else {
			products = pdaoimp.getAllProducts();System.out.println("hiiiiiiiiiiiiiiiiiii");
		}
		model.addAttribute("products", products);
		return "productCatalog";
	}

	@GetMapping("/productsDisplay")
	public String showAllProducts(Model model) {
		// System.out.println("all prod display method mapping");
		List<ProductStockPrice> products = pdaoimp.getAllProducts();

		model.addAttribute("products", products);

		return "productCatalog";
	}

	@RequestMapping(value = "/prodDescription", method = RequestMethod.GET)
	public String getSignUpPage(@RequestParam(value = "productId", required = false) int productId, Model model) {
		//System.out.println("product description Page");
		ProductStockPrice product = pdaoimp.getProductById(productId);
		//System.out.println("product recieved when image is clicked " + product);
		model.addAttribute("oneproduct", product);

		// call the view
		return "prodDescription";
	}

	@GetMapping("/products/{productId}")
	public String showProductDetails(@PathVariable int productId, Model model) {

		ProductStockPrice product = pdaoimp.getProductById(productId);
		model.addAttribute("product", product);
		return "productDetails";
	}
	@RequestMapping(value="/sortProducts", method = RequestMethod.POST)
	public String sortProducts(@RequestParam("sortOrder") String sortOrder, Model model) {
		// Get the product list from the data source
		List<ProductStockPrice> productList = pdaoimp.getAllProducts();
		//System.out.println(productList.toString()+"yeahhhhhhh");
		// Sort the products based on the selected sorting option
		if (sortOrder.equals("lowToHigh") || sortOrder.equals("highToLow")) {
			productList = pdaoimp.sortProductsByPrice(productList, sortOrder);
		}
		 model.addAttribute("products", productList);
		
		// Return the view
		return "productCatalog";
	}
	
	@RequestMapping(value="/filterProducts", method = RequestMethod.POST)
	public String getFilteredProducts(@RequestParam("priceRange") String priceRange, Model model) {
		double minPrice;
		double maxPrice;
		
		// Parse the selected price range
		if (priceRange.equals("0-500")) {
			minPrice = 0.0;
			maxPrice = 500.0;
		} else if (priceRange.equals("500-1000")) {
			minPrice = 500.0;
			maxPrice = 1000.0;
		} else if(priceRange.equals("1000-5000")){
			minPrice = 1000.0;
			maxPrice = 5000.0;
		}
		else {
			// Default range or invalid option selected
			List<ProductStockPrice> products = pdaoimp.getAllProducts();
			System.out.println(products.toString()+"byeeeeeee");
			model.addAttribute("products", products);
			return "productCatalog";
		}
		System.out.println("min price  "+minPrice+"    maxprice  "+maxPrice);
		// Call the filterProductsByPriceRange() method from the DAO
		List<ProductStockPrice> products =  pdaoimp.filterProductsByPriceRange(minPrice, maxPrice);
		model.addAttribute("products", products);
		return "productCatalog";
	}
	@PostMapping("/checkPincode")
	@ResponseBody
	public String checkPincode(@RequestParam("pincode") int pincode) {
		boolean isValid = pdaoimp.isPincodeValid(pincode);
		return String.valueOf(isValid);
	}
	@RequestMapping(value = "/orderPlaced", method = RequestMethod.GET)
	public String orderPlaced(Model model) {
		
		return "";
	}

}
