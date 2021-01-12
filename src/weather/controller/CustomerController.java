package weather.controller;

import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import weather.entity.UserMess;

@Transactional
@Controller
@Scope("session")
@RequestMapping("/dashboard")
public class CustomerController {

	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;
	
	@RequestMapping(value = "delete-customer/{id}", method = RequestMethod.GET)
	public String deleteCus(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		UserMess um = (UserMess) session.get(UserMess.class, id);
		model.addAttribute("userMess", um);
		return "admin/dashboard/deleteCustomer";
	}

	@RequestMapping(value = "delete-customer/{id}", method = RequestMethod.POST)
	public String deleteCus(ModelMap model, @ModelAttribute("userMess") UserMess userMess) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(userMess);
			t.commit();
			model.addAttribute("mes", "SUCCESS!");
			
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("mes", "DELETE FAILED!");
		} finally {
			session.close();
		}
		return "admin/dashboard/deleteCustomer";
	}
}
