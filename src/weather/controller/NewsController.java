package weather.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import weather.entity.Country;
import weather.entity.News;
import weather.entity.WeatherDaily;

@Transactional
@Controller
@RequestMapping("/")
public class NewsController {

	@Autowired
	SessionFactory factory;

	@Autowired
	ServletContext context;

	// @RequestMapping("news")
	// public String news(ModelMap model) {
	// List<Object[]> listNews = getListNews();
	// model.put("objNews", listNews);
	// return "News/news";
	// }

	@RequestMapping("news/page-{page}")
	public String news(ModelMap model, @PathVariable("page") int page) {
		List<Object[]> listNews = getListNews(page);
		model.put("objNews", listNews);
		List<Object[]> listMenu = getMenuNews();
		model.put("objMenu", listMenu);
		return "News/news";
	}

	@RequestMapping("news/{id}")
	public String detailNews(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		String hql = "FROM News WHERE newsId= :code";
		Query query = session.createQuery(hql);
		query.setParameter("code", id);
		List<News> list = query.list();
		model.addAttribute("objNews", list);

		List<Object[]> listNews = getMenuNews();
		model.addAttribute("listNews", listNews);
		return "News/newsDetail";
	}

	public List<Object[]> getListNews() {
		Session session = factory.getCurrentSession();
		String hql = "Select newsId, title, intro, body, newsDate From News ORDER BY newsId DESC";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getMenuNews() {
		Session session = factory.getCurrentSession();
		String hql = "Select newsId, title, intro, body, newsDate From News ORDER BY NEWID()";
		Query query = session.createQuery(hql);
		query.setFirstResult(0);
		query.setMaxResults(5);
		List<Object[]> list = query.list();
		return list;
	}

	public List<Object[]> getListNews(int page) {
		Session session = factory.getCurrentSession();
		String hql = "Select newsId, title, intro, body, newsDate From News ORDER BY newsId DESC";
		Query query = session.createQuery(hql);
		if (page == 0)
			page = 1;
		query.setFirstResult((page - 1) * 5);
		query.setMaxResults(5);
		List<Object[]> list = query.list();
		return list;
	}

	// DASHBOARD
	@RequestMapping(value = "dashboard/data-news/{page}", method = RequestMethod.GET)
	public String data(ModelMap model, @PathVariable("page") int page) {
		Session session = factory.getCurrentSession();
		String hql = "FROM News ORDER BY newsDate DESC";
		Query query = session.createQuery(hql);
		if (page == 0)
			page = 1;
		query.setFirstResult((page - 1) * 10);
		query.setMaxResults(10);
		List<News> list = query.list();
		model.addAttribute("ns", list);
		model.addAttribute("page", page);
		return "admin/dashboard/dataNews";
	}

	@RequestMapping("dashboard/createNews")
	public String createNews(ModelMap model) {
		model.addAttribute("news", new News());
		return "admin/dashboard/createNews";
	}

	@RequestMapping(value = "dashboard/createNews", method = RequestMethod.POST)
	public String insert(ModelMap model, @ModelAttribute("news") News news, BindingResult errors) {

		if (errors.hasErrors()) {
			model.addAttribute("loi", "PLEASE, INSERT CORRECT FORMAT!");
		}
		// VALIDATED
		if (news.getNewsDate() == null) {
			errors.rejectValue("newsDate", "news", "Insert date!");
		}
		if (news.getTitle().equals("")) {
			errors.rejectValue("title", "news", "Insert title!");
		}
		if (news.getTitle().length()>100) {
			errors.rejectValue("title", "news", "Too long!");
		}
		if (news.getIntro().equals("")) {
			errors.rejectValue("intro", "news", "Insert intro!");
		}
		if (news.getBody().equals("")) {
			errors.rejectValue("body", "news", "Insert body!");
		}

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			news.setNewsDate(new Date());
			session.save(news);
			t.commit();
			model.addAttribute("message", "SUCCESS!");
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
			model.addAttribute("message", "INSERT FAILED!");
		}

		return "admin/dashboard/createNews";
	}

	@RequestMapping(value = "dashboard/delete-news/{id}", method = RequestMethod.GET)
	public String delete(ModelMap model, @PathVariable("id") int id) {

		Session session = factory.getCurrentSession();
		News ns = (News) session.get(News.class, id);
		model.addAttribute("news", ns);
		return "admin/dashboard/deleteNews";
	}

	@RequestMapping(value = "dashboard/delete-news/{id}", method = RequestMethod.POST)
	public String delete(ModelMap model, @ModelAttribute("news") News news) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(news);
			t.commit();
			model.addAttribute("message", "SUCCESS!");

		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "DELETE FAILED!");
		} finally {
			session.close();
		}
		return "admin/dashboard/deleteNews";
	}

	@RequestMapping(value = "dashboard/update-news/{id}", method = RequestMethod.GET)
	public String update(ModelMap model, @PathVariable("id") int id) {
		Session session = factory.getCurrentSession();
		News ns = (News) session.get(News.class, id);
		model.addAttribute("news", ns);
		return "admin/dashboard/updateNews";
	}

	@RequestMapping(value = "dashboard/update-news/{id}", method = RequestMethod.POST)
	public String update(ModelMap model, @ModelAttribute("news") News news) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(news);
			t.commit();
			model.addAttribute("message", "SUCCESS!");
		} catch (Exception e) {
			t.rollback();
			model.addAttribute("message", "UPDATE FAILED!");
		} finally {
			session.close();
		}
		return "admin/dashboard/updateNews";
	}
}
