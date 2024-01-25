package bloodIssueTable;

public class BissueBean {
     String Name;
     String Mobile;
     String Hospital;
     String Purpose;
     String BG;
     String Units;
     
     public BissueBean() {}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getHospital() {
		return Hospital;
	}

	public void setHospital(String hospital) {
		Hospital = hospital;
	}

	public String getPurpose() {
		return Purpose;
	}

	public void setPurpose(String purpose) {
		Purpose = purpose;
	}

	public String getBG() {
		return BG;
	}

	public void setBG(String bG) {
		BG = bG;
	}

	public String getUnits() {
		return Units;
	}

	public void setUnits(String units) {
		Units = units;
	}

	public BissueBean(String name, String mobile, String hospital, String purpose, String bG, String units) {
		super();
		Name = name;
		Mobile = mobile;
		Hospital = hospital;
		Purpose = purpose;
		BG = bG;
		Units = units;
	}
     
	
}
