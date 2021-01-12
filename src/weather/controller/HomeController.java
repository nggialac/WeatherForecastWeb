package weather.controller;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import WeatherMailer.bean.Mailer;
import weather.entity.Country;
import weather.entity.Type;
import weather.entity.UserMess;
import weather.entity.WeatherDaily;

@Transactional
@Controller
@RequestMapping("/")
public class HomeController {
	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext context;
	
	@Autowired
	Mailer mailer;

	public final static String NATION = "VN";
	public final static String mailFrom = "lacnguyenspringmvc2@gmail.com";
	public final static String mailSubject = "Welcome to come with Lac's WeatherForecast Website";
	public final static String mailBody = "Thank you for your feedback regarding my performance on the recent content. "
			+ "What you shared with me will help me improve my website with others on the next content. "
			+ "I appreciate your continued guidance!";

	@RequestMapping("home")
	public String login(ModelMap model) {
		Session session = factory.getCurrentSession();
		String hql = "SELECT country.countryName, calendarDate, minTemp, maxTemp, humidity, wind, type.typeWeather FROM WeatherDaily w WHERE country.nameCode = :code AND w.calendarDate >= GETDATE() ORDER BY w.calendarDate";
		Query query = session.createQuery(hql);
		query.setParameter("code", NATION);
		
		List<Object[]> listXL = query.list();
		model.put("object0", listXL.get(0)[0]);
		model.put("object1", listXL.get(0)[1]);
		model.put("object2", listXL.get(0)[2]);
		model.put("object3", listXL.get(0)[3]);
		model.put("object4", listXL.get(0)[4]);
		model.put("object5", listXL.get(0)[5]);
		model.put("object6", listXL.get(0)[6]);
		
		query.setFirstResult(1);
		query.setMaxResults(4);
		List<Object[]> list = query.list();
		model.put("objects", list);
		
		//LOAD IMAGE 2 rows

		String hqlImg = "SELECT country.countryName, path, dateUpload, imageId FROM BeautyImage ORDER BY imageId DESC";
		Query queryImg = session.createQuery(hqlImg);
		queryImg.setFirstResult(0);
		queryImg.setMaxResults(4);
		List<Object[]> listImg = queryImg.list();
		model.put("bi", listImg);

		queryImg.setFirstResult(4);
		queryImg.setMaxResults(4);
		List<Object[]> listImg2 = queryImg.list();
		model.put("bi2", listImg2);
		
		//FLAG
		String hqlFlag = "SELECT nameCode, countryName FROM Country";
		Query queryFlag = session.createQuery(hqlFlag);
//		queryImg.setFirstResult(0);
//		queryImg.setMaxResults(4);
		List<Object[]> listFlag = queryFlag.list();
		model.put("flag", listFlag);
		System.out.println(listFlag.get(0)[0]);

		return "TrangChu/home";
	}

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String detail() {
		return "Details/Detail";
	}

	@RequestMapping(value = "contact", method = RequestMethod.GET)
	public String contact(ModelMap model) {
		model.addAttribute("userMess", new UserMess());
		return "Contact/contact";
	}

	@RequestMapping(value = "contact", method = RequestMethod.POST)
	public String insert(ModelMap model, @Validated @ModelAttribute("userMess") UserMess userMess,
			BindingResult errors) {
		Session sessionTemp = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM UserMess WHERE userMail= :userMailTemp ";
		Query query = sessionTemp.createQuery(hql);

		query.setParameter("userMailTemp", userMess.getUserMail());
		long count = (long) query.uniqueResult();
		
		//MAILE
		mailer.send(mailFrom, userMess.getUserMail(), mailSubject, mailBody);

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if (count == 0) {
				session.save(userMess);
				t.commit();
				model.addAttribute("message", "THANKS FOR YOUR FEEDBACK!");
			} else {
				model.addAttribute("message", "EMAIL ALREADY EXIST!");
			}

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			System.out.println(e);
			model.addAttribute("message", "FEEDBACK FAILED!");
		} finally {
			session.close();
		}
		return "Contact/contact";
	}


	
}
