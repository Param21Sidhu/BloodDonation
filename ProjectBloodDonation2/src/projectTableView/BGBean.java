package projectTableView;

public class BGBean {
     String Mobile;
     String Name;
     String Gender;
     String BloodGroup;
     String Disease;
     String Age;
     
     public BGBean() {}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getBloodGroup() {
		return BloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		BloodGroup = bloodGroup;
	}

	public String getDisease() {
		return Disease;
	}

	public void setDisease(String disease) {
		Disease = disease;
	}

	public String getAge() {
		return Age;
	}

	public void setAge(String age) {
		Age = age;
	}

	public BGBean(String mobile, String name, String gender, String bloodGroup, String disease, String age) {
		super();
		Mobile = mobile;
		Name = name;
		Gender = gender;
		BloodGroup = bloodGroup;
		Disease = disease;
		Age = age;
	}
     
     
}
