package donorCollectionTbl;

public class DonorBean {
     String Mobile;
     String BloodGroup;
     String DateofDon;
     
     public DonorBean() {}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
	}
	public DonorBean(String mobile, String bloodGroup, String dateofDon) {
		super();
		Mobile = mobile;
		BloodGroup = bloodGroup;
		DateofDon = dateofDon;
	}
	public String getBloodGroup() {
		return BloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		BloodGroup = bloodGroup;
	}
	public String getDateofDon() {
		return DateofDon;
	}
	public void setDateofDon(String dateofDon) {
		DateofDon = dateofDon;
	}
     
     
}
