package com.office.calender.organizer.comment;

import lombok.Data;

@Data
public class CommentVo {

	private int c_no; 
	private int or_no;
	private String m_id; 
	private String c_txt;
	private String c_reg_date;
	private String c_mod_date;
	public int getC_no() {
		return c_no;
	}
	public void setC_no(int c_no) {
		this.c_no = c_no;
	}
	public int getOr_no() {
		return or_no;
	}
	public void setOr_no(int or_no) {
		this.or_no = or_no;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getC_txt() {
		return c_txt;
	}
	public void setC_txt(String c_txt) {
		this.c_txt = c_txt;
	}
	public String getC_reg_date() {
		return c_reg_date;
	}
	public void setC_reg_date(String c_reg_date) {
		this.c_reg_date = c_reg_date;
	}
	public String getC_mod_date() {
		return c_mod_date;
	}
	public void setC_mod_date(String c_mod_date) {
		this.c_mod_date = c_mod_date;
	}
    
	
}
