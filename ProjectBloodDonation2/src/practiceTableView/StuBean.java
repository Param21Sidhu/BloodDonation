package practiceTableView;

public class StuBean{
           String Name;
           int rollno;
           float per;
           String branch;
           public StuBean() {}
           
           
		public StuBean(String name, int rollno, float per, String branch) {
			super();
			Name = name;
			this.rollno = rollno;
			this.per = per;
			this.branch = branch;
		}

		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public int getRollno() {
			return rollno;
		}
		public void setRollno(int rollno) {
			this.rollno = rollno;
		}
		public float getPer() {
			return per;
		}
		public void setPer(float per) {
			this.per = per;
		}
		public String getBranch() {
			return branch;
		}
		public void setBranch(String branch) {
			this.branch = branch;
		}
           
}
