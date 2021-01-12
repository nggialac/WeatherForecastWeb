package weather.controller;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import weather.entity.Country;
import weather.entity.WeatherDaily;

@Transactional
@Controller
@RequestMapping("/")
public class DetailController {

	@Autowired
	SessionFactory factory;

	public List<Country> getCountries(String nameCode) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Country c WHERE c.nameCode =:nc";
		Query query = session.createQuery(hql);
		query.setParameter("nc", nameCode);
		List<Country> listCountries = query.list();
		return listCountries;
	}

	public List<WeatherDaily> getWeatherDaily(String countryName) {
		Session session = factory.getCurrentSession();
		String hql = "FROM Country c WHERE c.countryName LIKE :nc";
		Query query = session.createQuery(hql);
		query.setParameter("nc", "%" + countryName + "%");
		List<WeatherDaily> listWeather = query.list();
		return listWeather;
	}

	@RequestMapping(value = "nations", method = RequestMethod.GET)
	public String nation(ModelMap model) {
		return "Details/nations";
	}

	@RequestMapping(value = "nation/country")
	public String search(ModelMap model, @RequestParam("cn") String countryName) {
		Session session = factory.getCurrentSession();

		
		//Session session = factory.getCurrentSession();
		String hql1 = "SELECT w.country.countryName, w.calendarDate, w.minTemp, w.maxTemp, c.introduce, w.type.typeWeather FROM WeatherDaily w, Country c WHERE c.countryName= :cn AND w.country.nameCode=c.nameCode AND w.calendarDate >= GETDATE() ORDER BY w.calendarDate";
		Query query1 = session.createQuery(hql1);
		query1.setParameter("cn", countryName);
		query1.setFirstResult(1);
		query1.setMaxResults(5);
		List<Object> list1 = query1.list();
		model.put("test", list1);
		
		Query query2 = session.createQuery(hql1);
		query2.setParameter("cn", countryName);
		query2.setFirstResult(0);
		query2.setMaxResults(1);
		List<Object> list2 = query2.list();
		model.put("test0", list2);
		
		String hqlImg = "SELECT b.country.countryName, b.path, b.dateUpload, b.imageId FROM BeautyImage b, Country c WHERE c.countryName = :cn AND c.nameCode=b.country.nameCode ORDER BY imageId DESC";
		Query queryImg = session.createQuery(hqlImg);
		queryImg.setParameter("cn", countryName);
		queryImg.setFirstResult(0);
		queryImg.setMaxResults(4);
		List<Object[]> listImg = queryImg.list();
		model.put("bi", listImg);
		
		return "Details/nationDetails";
	}



}
