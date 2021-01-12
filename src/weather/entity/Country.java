package weather.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Countries")
public class Country {

	@Id
	private String nameCode;
	private String countryName;
	private String introduce;

	@OneToMany(mappedBy = "country", fetch = FetchType.EAGER)
	private Collection<WeatherDaily> weatherDaily;

	public Country() {
	}

	public Country(String nameCode, String countryName, Collection<WeatherDaily> weatherDaily, String introduce) {
		super();
		this.nameCode = nameCode;
		this.countryName = countryName;
		this.weatherDaily = weatherDaily;
		this.introduce = introduce;
	}

	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Collection<WeatherDaily> getWeatherDaily() {
		return weatherDaily;
	}

	public void setWeatherDaily(Collection<WeatherDaily> weatherDaily) {
		this.weatherDaily = weatherDaily;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
}
