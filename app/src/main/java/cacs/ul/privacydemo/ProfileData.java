
package cacs.ul.privacydemo;


public class ProfileData {
	private long id;



	private String startplace;
	private String startkm;
	private String startdate;

	private String endplace;
	private String endKM;
	private String enddate;

	private String longi;

	public String getStartkm() {
		return startkm;
	}

	public void setStartkm(String startkm) {
		this.startkm = startkm;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEndKM() {
		return endKM;
	}

	public void setEndKM(String endKM) {
		this.endKM = endKM;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public void setLongi(String longi) {
		this.longi = longi;
	}
	public String getLongi() {
		return longi;
	}

	public String getStartplace() {
		return startplace;
	}

	public void setStartplace(String startplace) {
		this.startplace = startplace;
	}

	public String getEndplace() {
		return endplace;
	}

	public void setEndplace(String endplace) {
		this.endplace = endplace;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
