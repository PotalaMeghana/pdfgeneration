package eStoreProduct.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import eStoreProduct.DAO.customerDAO;
import eStoreProduct.model.custCredModel;

@Controller
public class homeController {
	static boolean flag = false;
	customerDAO cdao;
	

	@Autowired
	public homeController(customerDAO customerdao) {
		cdao = customerdao;
		
	}
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getHome(Model model) {
		//System.out.println("Home Page");
		model.addAttribute("fl", flag);

		// call the view
		return "home";
	}
	

	@RequestMapping(value = "/loggedIn", method = RequestMethod.GET)
	public String getHomeLogged(Model model) {
		//System.out.println("Home Page");

		flag = true;
		model.addAttribute("fl", flag);
		// call the view
		return "home";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String getSignUpPage(Model model) {
		//System.out.println("Sign Up Page");

		// call the view
		return "signUp";
	}

	@RequestMapping(value = "/signIn", method = RequestMethod.GET)
	public String getSignInPage(Model model) {
		//System.out.println("Sign In Page");

		// call the view
		return "signIn";
	}

	

	@RequestMapping(value = "/signOk", method = RequestMethod.POST)
	public ResponseEntity<String> getHomeFinal(@RequestParam("em") String email, @RequestParam("ps") String psd, Model model, HttpSession session) {
	    custCredModel cust = cdao.getCustomer(email, psd);
	    System.out.println("email recieved "+email+"  password  "+psd);
	    if (cust != null) {
	        try {
	            cdao.updatelastlogin(cust.getCustId());
	            session.setAttribute("customer", cust);
	            model.addAttribute("fl", true);
	            custCredModel cust1 = (custCredModel) session.getAttribute("customer");
	            model.addAttribute("cust1", cust1);
	            return ResponseEntity.ok("valid");
	        } catch (Exception e) {
	            System.out.println(e);
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	        }
	    } else {
	        return ResponseEntity.ok("invalid");
	    }
	}
	
	@RequestMapping(value = "/signInCreateAccount", method = RequestMethod.POST)
	public String createAccount(@Validated custCredModel ccm, Model model) {
		//System.out.println("sign Up page creating account");
		boolean b = cdao.createCustomer(ccm);

		// set it to the model
		if (b) {
			model.addAttribute("customer", ccm);
		}
		// call the view
		return "createdMsg";
	}

	@RequestMapping(value = "/logout")
	public String userlogout(Model model, HttpSession session) {
		custCredModel cust = (custCredModel) session.getAttribute("customer");
		model.addAttribute("cust", cust);
		flag = false;
		model.addAttribute("fl", flag);

		return "home";
	}

	
}
