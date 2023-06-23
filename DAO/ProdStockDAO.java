package eStoreProduct.DAO;

import java.util.List;

import eStoreProduct.model.ProdStock;

public interface ProdStockDAO {

	List<ProdStock> getAllProdStocks();

	ProdStock getProdStockById(int prodId);

	double getProdMrpById(int prodId);

	double getProdPriceById(int prodId);

}