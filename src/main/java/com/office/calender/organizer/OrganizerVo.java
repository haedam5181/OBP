package com.office.calender.organizer;

import lombok.Data;

@Data
public class OrganizerVo {

	private int or_no; 
	private int or_ori_no;
	private String or_ori_owner_id;
	private String m_id;
	private int or_year;
	private int or_month;
	private int or_date;
	private String or_img_name;
	private String or_title;
	private String or_body;
	private String or_reg_date;
	private String or_mod_date;
	
	public int getOr_no() {
		return or_no;
	}
	public void setOr_no(int or_no) {
		this.or_no = or_no;
	}
	public int getOr_ori_no() {
		return or_ori_no;
	}
	public void setOr_ori_no(int or_ori_no) {
		this.or_ori_no = or_ori_no;
	}
	public String getOr_ori_owner_id() {
		return or_ori_owner_id;
	}
	public void setOr_ori_owner_id(String or_ori_owner_id) {
		this.or_ori_owner_id = or_ori_owner_id;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public int getOr_year() {
		return or_year;
	}
	public void setOr_year(int or_year) {
		this.or_year = or_year;
	}
	public int getOr_month() {
		return or_month;
	}
	public void setOr_month(int or_month) {
		this.or_month = or_month;
	}
	public int getOr_date() {
		return or_date;
	}
	public void setOr_date(int or_date) {
		this.or_date = or_date;
	}
	public String getOr_img_name() {
		return or_img_name;
	}
	public void setOr_img_name(String or_img_name) {
		this.or_img_name = or_img_name;
	}
	public String getOr_title() {
		return or_title;
	}
	public void setOr_title(String or_title) {
		this.or_title = or_title;
	}
	public String getOr_body() {
		return or_body;
	}
	public void setOr_body(String or_body) {
		this.or_body = or_body;
	}
	public String getOr_reg_date() {
		return or_reg_date;
	}
	public void setOr_reg_date(String or_reg_date) {
		this.or_reg_date = or_reg_date;
	}
	public String getOr_mod_date() {
		return or_mod_date;
	}
	public void setOr_mod_date(String or_mod_date) {
		this.or_mod_date = or_mod_date;
	}
	
}
