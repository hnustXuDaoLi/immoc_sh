package entity;

import java.util.Date;

public class Books {
	private String sid;//编号
	private String sname;//书名
	private Date loandate;//日期
	private String total;//数目
	
	
	public Books() {

	}
	
	public Books(String sid, String sname, Date loandate, String total) {
		//super();
		this.sid = sid;
		this.sname = sname;
		this.loandate = loandate;
		this.total = total;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public Date getLoandate() {
		return loandate;
	}

	public void setLoandate(Date loandate) {
		this.loandate = loandate;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Books [sid=" + sid + ", sname=" + sname + ", loandate="
				+ loandate + ", total=" + total + "]";
	}
	
	
}
