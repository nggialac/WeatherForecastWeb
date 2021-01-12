package weather.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.sun.istack.internal.NotNull;

@Entity
@Table(name="BeautyImage")
public class BeautyImage {

	@Id
	@GeneratedValue
	private int imageId;
	
	@ManyToOne
	@JoinColumn(name="nameCode")
	private Country country;
	
	private String path;
	
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private Date dateUpload;
	
	public BeautyImage(){}

	public BeautyImage(int imageId, Country country, String path, Date dateUpload) {
		super();
		this.imageId = imageId;
		this.country = country;
		this.path = path;
		this.dateUpload = dateUpload;
	}

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getDateUpload() {
		return dateUpload;
	}

	public void setDateUpload(Date dateUpload) {
		this.dateUpload = dateUpload;
	}
	
	
}
