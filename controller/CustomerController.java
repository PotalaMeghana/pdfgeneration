package eStoreProduct.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import eStoreProduct.model.Product;
import eStoreProduct.model.custCredModel;
import eStoreProduct.model.productqty;
import eStoreProduct.utility.ProductStockPrice;
import eStoreProduct.BLL.BLL;
import eStoreProduct.BLL.BLLClass2;
import eStoreProduct.model.orderModel;
import eStoreProduct.DAO.OrderDAO;
import eStoreProduct.model.InvoiceMailModel;
import eStoreProduct.DAO.ProductDAO;
import eStoreProduct.DAO.cartDAO;
import eStoreProduct.DAO.customerDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {
	customerDAO cdao;
	BLL BLL;
    BLLClass2 bl2;
	//BLLClass obj;
    ProductDAO pdaoimp;
	cartDAO cartimp;
	OrderDAO orderdao;
	@Autowired
	public CustomerController(cartDAO cartdao,customerDAO customerdao,BLLClass2 bl2,BLL bl1 ,ProductDAO productdao,OrderDAO odao) {
		cdao = customerdao;
		cartimp=cartdao;
		this.bl2=bl2;
		this.BLL=bl1;
		pdaoimp=productdao;
		orderdao=odao;
		//cartdao1 = cartdao;
	}
	@RequestMapping(value = "/profilePage")
	public String sendProfilePage(Model model, HttpSession session) {
		custCredModel cust = (custCredModel) session.getAttribute("customer");
		model.addAttribute("cust", cust);
		return "profile";
	}

	// on clicking update Profile in profile page
	@RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
	public String userupdate(@ModelAttribute("Customer") custCredModel cust, Model model, HttpSession session) {
		cdao.updatecustomer(cust);
		custCredModel custt = cdao.getCustomerById(cust.getCustId());
		if (custt != null) {
			model.addAttribute("cust", custt);
		}
		return "profile";
	}
	
	@RequestMapping(value = "/invoice", method = RequestMethod.POST)
	public String showPaymentOption(Model model, HttpSession session,HttpServletRequest request,HttpServletResponse response) {
		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		double var = (double) session.getAttribute("cartcost");
		List<ProductStockPrice> products = cartimp.getCartProds(cust1.getCustId());
		model.addAttribute("products", products);
		model.addAttribute("customer", cust1);
		//============
		orderModel ordermodel=new orderModel();
		ordermodel.setCustid(cust1.getCustId());
		ordermodel.setOrd_billno(request.getParameter("razorpay_order_id"));
		ordermodel.setOrdertotal(var);
		ordermodel.setOrder_gst(50);
		ordermodel.setOrderpayid(request.getParameter("razorpay_payment_id"));
		ordermodel.setSaddress(cust1.getCustSAddress());
		orderdao.insertIntoOrders(ordermodel, products);
		orderModel vieworder=orderdao.getOrderDetails(1,15);
		System.out.println("oder before sending to jsp    "+vieworder);
		System.out.println("session cust name    ======================"+cust1.getCustName());
		session.setAttribute("vieworder", vieworder);
		try {
			InvoiceMailModel.sendEmail(request, response, vieworder,cust1.getCustEmail());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "invoice";
	}
	@RequestMapping(value = "/showInvoice", method = RequestMethod.POST)
	public String showInvoice(Model model, HttpSession session) {
		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		
		return "invoice";
	}
	
	@PostMapping("/updateshipment")
	@ResponseBody
	public String handleFormSubmission(custCredModel cust,Model model, HttpSession session) {
		int pincode = Integer.parseInt(cust.getCustSpincode());
		boolean isValid = pdaoimp.isPincodeValid(pincode);
		if (isValid) {
			session.setAttribute("custspincode",pincode);
			return "Saved";
		} else {
			return "UnSaved";
		}
	}
	
	@RequestMapping(value = "/makePayment", method = RequestMethod.GET)
	@ResponseBody
	public String pay(Model model,HttpSession session) {
		String orderId = null;
		custCredModel cust1 = (custCredModel) session.getAttribute("customer");
		if (cust1 != null) {
		double cartcost=BLL.getCartCost(cust1.getCustId());
		orderId = bl2.createRazorpayOrder(cartcost);
		}
		return orderId;
	}
}
