package weather.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Type")
public class Type {

	@Id
	private String typeCode;
	private String typeWeather;

	@OneToMany(mappedBy = "type", fetch = FetchType.EAGER)
	private Collection<WeatherDaily> weatherDaily;

	public Type(){}

	public Type(String typeCode, String typeWeather, Collection<WeatherDaily> weatherDaily) {
		super();
		this.typeCode = typeCode;
		this.typeWeather = typeWeather;
		this.weatherDaily = weatherDaily;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeWeather() {
		return typeWeather;
	}

	public void setTypeWeather(String typeWeather) {
		this.typeWeather = typeWeather;
	}

	public Collection<WeatherDaily> getWeatherDaily() {
		return weatherDaily;
	}

	public void setWeatherDaily(Collection<WeatherDaily> weatherDaily) {
		this.weatherDaily = weatherDaily;
	}
	
	
}
