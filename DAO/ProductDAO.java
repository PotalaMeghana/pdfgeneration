package eStoreProduct.DAO;

import java.util.List;

import eStoreProduct.model.Category;
import eStoreProduct.model.Product;
import eStoreProduct.utility.ProductStockPrice;

public interface ProductDAO {

	public boolean createProduct(Product p);

	public List<String> getAllProductCategories();

	public List<ProductStockPrice> getProductsByCategory(Integer category);

	public List<ProductStockPrice> getAllProducts();

	public List<Category> getAllCategories();

	public ProductStockPrice getProductById(Integer productId);
	
	public List<ProductStockPrice> sortProductsByPrice(List<ProductStockPrice> productList, String sortOrder);
	
	public List<ProductStockPrice> filterProductsByPriceRange(double minPrice, double maxPrice);
	
	 public boolean isPincodeValid(int pincode); 

}