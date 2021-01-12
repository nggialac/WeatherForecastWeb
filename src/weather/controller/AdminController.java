package weather.controller;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import weather.entity.Account;

@Transactional
@Controller
@RequestMapping("/")
public class AdminController {

	@Autowired
	SessionFactory factory;

	// @RequestMapping("index")
	// public String index(ModelMap model) {
	// Session session = factory.getCurrentSession();
	// String hql = "FROM Staff";
	// Query query = session.createQuery(hql);
	// List<Staff> list = query.list();
	// model.addAttribute("staffs", list);
	// return "staff/index";
	// }

	@RequestMapping("admin")
	public String login() {
		return "admin/login";
	}

	@RequestMapping(value = "admin", method = RequestMethod.POST)
	public String login(ModelMap model, Account account, HttpSession ses) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT acc.accountId, acc.email, acc.pass " + "FROM Account acc";
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i)[1]);
			System.out.println(list.get(i)[2]);
			if (list.get(i)[1].equals(account.getEmail()) && list.get(i)[2].equals(account.getPass())) {
				ses.setAttribute("user", account.getEmail());
				return "redirect:dashboard.htm";
			}
		}
		model.put("error", "Invalid Account!");
		return "admin/login";
	}




}
