package model;

/**
 * CityZipCode class
 * 
 * It has three attributes:
 * cityZipCodeId: an integer representing the unique ID of the city zip code.
 * zipCode: an integer representing the actual zip code.
 * city: a string representing the name of the city.
 * 
 * @author [Gruppe 3]
 * @version 1.0
 */
 
public class CityZipCode {
	private int cityZipCodeId;
	private int zipCode;
	private String city;

	/**
	 * Constructor.
	 * The constructor is being used to initialize the attributes when a new object is created.
	 * */
	public CityZipCode(int cityZipCodeId, int zipCode, String city) {
		super();
		this.cityZipCodeId = cityZipCodeId;
		this.zipCode = zipCode;
		this.city = city;
	}

	/**
	 * Getters and setters
	 * The setters allows the values of these attributes to be changed.
	 * The getters are used to access the current values of the different attributes.
	 * */
	public int getCityZipCodeId() {
		return cityZipCodeId;
	}

	public void setCityZipCodeId(int cityZipCodeId) {
		this.cityZipCodeId = cityZipCodeId;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Provides a string representation of the CityZipCode object. This string includes the 
	 * names and values of all the attributes.
	 * */
	@Override
	public String toString() {
		return "CityZipCode [cityZipCodeId=" + cityZipCodeId + ", zipCode=" + zipCode + ", city=" + city + "]";
	}

}
