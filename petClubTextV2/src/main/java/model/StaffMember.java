package model;

import java.io.Serializable;

public class StaffMember  implements Serializable{
	Integer idStaff;
	String staffName;
	public StaffMember() {
		super();
	}
	public StaffMember(String staffName) {
		super();
		this.staffName = staffName;
	}
	public Integer getIdStaff() {
		return idStaff;
	}
	public void setIdStaff(Integer idStaff) {
		this.idStaff = idStaff;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	@Override
	public String toString() {
		return "ID:"+this.getIdStaff()
			+"\t 員工姓名:"+this.getStaffName()+"\n";

	}

}
