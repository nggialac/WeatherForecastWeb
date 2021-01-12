package weather.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
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
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import WeatherMailer.bean.Mailer;
import weather.entity.BeautyImage;
import weather.entity.Country;
import weather.entity.Type;
import weather.entity.UserMess;
import weather.entity.WeatherDaily;

@Transactional
@Controller
@Scope("session")
@RequestMapping("/dashboard")
public class DashboardController {

	@Autowired
	SessionFactory factory;
	@Autowired
	ServletContext context;

	@Autowired
	Mailer mailer;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String report(ModelMap model, HttpSession ses) {
		// if (ses.getAttribute("user") == null) {
		// return "redirect:/admin.htm";
		// }
		return "admin/dashboard";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String da(ModelMap model, HttpSession ses) {
		ses.invalidate();
		return "redirect:/admin.htm";
	}

	@RequestMapping(value = "/data-weather/{page}", method = RequestMethod.GET)
	public String data(ModelMap model, @PathVariable("page") int page) {
		Session session = factory.getCurrentSession();
		String hql = "FROM WeatherDaily ORDER BY calendarDate DESC";
		Query query = session.createQuery(hql);
		if (page == 0)
			page = 1;
		query.setFirstResult((page - 1) * 10);
		query.setMaxResults(10);
		List<WeatherDaily> list = query.list();
		model.addAttribute("objects", list);
		model.addAttribute("page", page);
		return "admin/dashboard/dataAnalyst";
	}

	@ModelAttribute("countries")
	public List<Country> getCountries() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Country";
		Query query = session.createQuery(hql);
		List<Country> listCountries = query.list();
		return listCountries;
	}

	@ModelAttribute("types")
	public List<Type> getTypes() {
		Session session = factory.getCurrentSession();
		String hql = "FROM Type";
		Query query = session.createQuery(hql);
		List<Type> listTypes = query.list();
		return listTypes;
	}

	@ModelAttribute("userMess")
	public List<UserMess> getUserMess() {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserMess";
		Query query = session.createQuery(hql);
		List<UserMess> listUserMess = query.list();
		return listUserMess;
	}

	// @RequestMapping(value = "/data-customer", method = RequestMethod.GET)
	// public String dataCustomer(ModelMap model, HttpSession ses) {
	// Session session = factory.getCurrentSession();
	// String hql = "FROM UserMess";
	// Query query = session.createQuery(hql);
	// List<UserMess> list = query.list();
	// model.addAttribute("userMessage", list);
	// return "admin/dashboard/customerAnalyst";
	// }

	@RequestMapping(value = "/data-customer/{page}", method = RequestMethod.GET)
	public String dataCustomer(ModelMap model, HttpSession ses, @PathVariable("page") int page) {
		Session session = factory.getCurrentSession();
		String hql = "FROM UserMess";
		Query query = session.createQuery(hql);
		if (page == 0)
			page = 1;
		query.setFirstResult((page - 1) * 10);
		query.setMaxResults(10);
		List<UserMess> list = query.list();
		model.addAttribute("userMessage", list);
		model.addAttribute("page", page);
		return "admin/dashboard/customerAnalyst";
	}

	// INSERT WEATHER FORECAST
	@RequestMapping(value = "insert-weather", method = RequestMethod.GET)
	public String insert(ModelMap model, HttpSession ses) {
		model.addAttribute("weatherDaily", new WeatherDaily());
		return "admin/dashboard/insertWeather";
	}

	@RequestMapping(value = "insert-weather", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("weatherDaily") WeatherDaily weatherDaily,
			BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("loi", "PLEASE, INSERT CORRECT FORMAT!");
		}
		// VALIDATED
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDateTime now = LocalDateTime.now();
		if (weatherDaily.getCalendarDate() == null) {
			errors.rejectValue("calendarDate", "weatherDaily", "Insert date!");
		}
		if (weatherDaily.getMaxTemp() < -100 || weatherDaily.getMaxTemp() > 100) {
			errors.rejectValue("maxTemp", "weatherDaily", "Insert correct Max temperature format!");
		}
		if (weatherDaily.getMinTemp() < -100 || weatherDaily.getMinTemp() > 100) {
			errors.rejectValue("minTemp", "weatherDaily", "Insert correct Min temperature format!");
		}
		if (weatherDaily.getMinTemp() > weatherDaily.getMaxTemp()) {
			errors.rejectValue("minTemp", "weatherDaily", "Insert correct Min temperature format!");
		}
		if (weatherDaily.getHumidity() < 0 || weatherDaily.getHumidity() > 100) {
			errors.rejectValue("humidity", "weatherDaily", "Insert correct Humidity format!");
		}
		if (weatherDaily.getWind() < 0 || weatherDaily.getWind() > 100) {
			errors.rejectValue("wind", "weatherDaily", "Insert correct Wind format!");
		}

		Session sessionTemp = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM WeatherDaily WHERE calendarDate= :dateTemp AND country.nameCode= :nameCodeTemp ";
		Query query = sessionTemp.createQuery(hql);
		query.setParameter("dateTemp", weatherDaily.getCalendarDate());
		query.setParameter("nameCodeTemp", weatherDaily.getCountry().getNameCode());
		long count = (long) query.uniqueResult();

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if (count == 0) {
				session.save(weatherDaily);
				t.commit();
				model.addAttribute("message", "SUCCESS!");
			} else {
				model.addAttribute("message", "DATA ALREADY EXIST!");
			}

		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			System.out.println(e);
			model.addAttribute("message", "INSERT FAILED!");
		} finally {
			session.close();
		}
		return "admin/dashboard/insertWeather";
	}

	// DELETE
	@RequestMapping(value = "delete-weather/{id}", method = RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("id") int id) {

		Session session = factory.getCurrentSession();
		WeatherDaily wd = (WeatherDaily) session.get(WeatherDaily.class, id);
		model.addAttribute("weatherDaily", wd);
		return "admin/dashboard/deleteWeather";
	}

	@RequestMapping(value = "delete-weather/{id}", method = RequestMethod.POST)
	public String delete(ModelMap model, @ModelAttribute("weatherDaily") WeatherDaily weatherDaily) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(weatherDaily);
			t.commit();
			model.addAttribute("message", "SUCCESS!");

		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "DELETE FAILED!");
		} finally {
			session.close();
		}
		return "admin/dashboard/deleteWeather";
	}

	// UPDATE
	@RequestMapping(value = "update-weather/{id}", method = RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		WeatherDaily wd = (WeatherDaily) session.get(WeatherDaily.class, id);
		model.addAttribute("weatherDaily", wd);
		return "admin/dashboard/updateWeather";
	}

	@RequestMapping(value = "update-weather/{id}", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("weatherDaily") WeatherDaily weatherDaily) {
		Session sessionTemp = factory.getCurrentSession();
		String hql = "SELECT COUNT(*) FROM WeatherDaily WHERE calendarDate= :dateTemp AND country.nameCode= :nameCodeTemp ";
		Query query = sessionTemp.createQuery(hql);
		query.setParameter("dateTemp", weatherDaily.getCalendarDate());
		query.setParameter("nameCodeTemp", weatherDaily.getCountry().getNameCode());
		long count = (long) query.uniqueResult();

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			if (count == 0) {
				session.update(weatherDaily);
				t.commit();
				model.addAttribute("message", "SUCCESS!");
			} else {
				model.addAttribute("message", "DATA ALREADY EXIST!");
			}

		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "UPDATE FAILED!");
		} finally {
			session.close();
		}
		return "admin/dashboard/updateWeather";
	}

	// UPLOAD FILE
	@RequestMapping("upload-image")
	public String uploadImage(ModelMap model, HttpSession ses) {
		// if (ses.getAttribute("user") == null) {
		// return "redirect:/admin.htm";
		// }
		model.addAttribute("beautyImage", new BeautyImage());
		return "admin/dashboard/uploadImage";
	}

	@RequestMapping(value = "upload-success", method = RequestMethod.POST)
	public String uploadImage(ModelMap model, @RequestParam("photo") MultipartFile photo,
			@ModelAttribute("beautyImage") BeautyImage beautyImage) {
		if (photo.isEmpty()) {
			model.addAttribute("message", "SELECT FILE!");
		} else {
			try {

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MMddyyyy_HHmmss");
				LocalDateTime now = LocalDateTime.now();

				String photoPath = context.getRealPath("/files/" + photo.getOriginalFilename());

				// Save image
				BufferedImage src = ImageIO.read(new ByteArrayInputStream(photo.getBytes()));
				File destination = new File("E:\\LTW\\WeatherForecast_Project\\WebContent\\files\\" + dtf.format(now)
						+ photo.getOriginalFilename());
				ImageIO.write(src, "png", destination);
				photo.transferTo(new File(photoPath));

				// System.out.println(destination);
				// System.out.println(photoPath);

				model.addAttribute("countryName", beautyImage.getCountry().getNameCode());
				model.addAttribute("photo_name", photo.getOriginalFilename());
				model.addAttribute("photo_type", photo.getContentType());
				model.addAttribute("photo_size", photo.getSize());

				Session session = factory.openSession();
				Transaction t = session.beginTransaction();

				try {

					beautyImage.setDateUpload(new Date());
					beautyImage.setPath(dtf.format(now) + photo.getOriginalFilename());
					session.save(beautyImage);
					t.commit();
					model.addAttribute("message", "SUCCESS!");
				} catch (Exception e) {
					t.rollback();
					model.addAttribute("message", "UPLOAD FAILED!");
				}

				return "admin/dashboard/uploadSuccess";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("message", "ERROR SAVE FILE!");
			}
		}
		return "admin/dashboard/uploadImage";
	}

	// MAIL
	@RequestMapping("news-mail")
	public String index() {
		return "admin/dashboard/newsMail";
	}

	@RequestMapping("sent-mail")
	public String send(ModelMap model, @RequestParam("from") String from, @RequestParam("to") String to,
			@RequestParam("subject") String subject, @RequestParam("body") String body) {
		try {
			mailer.send(from, to, subject, body);
			model.addAttribute("message", "Gui mail thanh cong!");
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
			model.addAttribute("message", "Gui mail that bai!");
		}
		return "admin/dashboard/newsMail";
	}

}
