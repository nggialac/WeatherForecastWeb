package weather.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="WeatherDaily")
public class WeatherDaily implements Serializable {

	@ManyToOne
	@JoinColumn(name="nameCodeId")
	private Country country;
	

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date calendarDate;
	
	@ManyToOne
	@JoinColumn(name="typeCode")
	private Type type;
	
	@Id
	@GeneratedValue
	private int id;
	

	private int minTemp;
	

	private int maxTemp;
	

	private float humidity;
	

	private int wind;
	
	public WeatherDaily(){}
	
	public WeatherDaily(Country country, Date calendarDate, int minTemp, int maxTemp, float humidity, int wind, Type type,int id) {
		super();
		this.country = country;
		this.calendarDate = calendarDate;
		this.minTemp = minTemp;
		this.maxTemp = maxTemp;
		this.humidity = humidity;
		this.wind = wind;
		this.type = type;
	}

	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public Date getCalendarDate() {
		return calendarDate;
	}
	public void setCalendarDate(Date calendarDate) {
		this.calendarDate = calendarDate;
	}
	public int getMinTemp() {
		return minTemp;
	}
	public void setMinTemp(int minTemp) {
		this.minTemp = minTemp;
	}
	public int getMaxTemp() {
		return maxTemp;
	}
	public void setMaxTemp(int maxTemp) {
		this.maxTemp = maxTemp;
	}
	public float getHumidity() {
		return humidity;
	}
	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}
	public int getWind() {
		return wind;
	}
	public void setWind(int wind) {
		this.wind = wind;
	}
	
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
