package com.office.calender.board;

import lombok.Data;

@Data
public class BoardVo {

	private int b_no;
    private String m_id; 
	private String b_title;
	private String b_body;
	private int b_hit;
	private int b_group;
	private int b_step;
	private int b_indent;
	private String b_reg_date;
	private String b_mod_date;
	
	
	
	public int getB_no() {
		return b_no;
	}

	public void setB_no(int b_no) {
		this.b_no = b_no;
	}

	public String getM_id() {
		return m_id;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

	public String getB_title() {
		return b_title;
	}

	public void setB_title(String b_title) {
		this.b_title = b_title;
	}

	public String getB_body() {
		return b_body;
	}

	public void setB_body(String b_body) {
		this.b_body = b_body;
	}

	public int getB_hit() {
		return b_hit;
	}

	public void setB_hit(int b_hit) {
		this.b_hit = b_hit;
	}

	public int getB_group() {
		return b_group;
	}

	public void setB_group(int b_group) {
		this.b_group = b_group;
	}

	public int getB_step() {
		return b_step;
	}

	public void setB_step(int b_step) {
		this.b_step = b_step;
	}

	public int getB_indent() {
		return b_indent;
	}

	public void setB_indent(int b_indent) {
		this.b_indent = b_indent;
	}

	public String getB_reg_date() {
		return b_reg_date;
	}

	public void setB_reg_date(String b_reg_date) {
		this.b_reg_date = b_reg_date;
	}

	public String getB_mod_date() {
		return b_mod_date;
	}

	public void setB_mod_date(String b_mod_date) {
		this.b_mod_date = b_mod_date;
	}

	public BoardVo () {
		
	}
	
	public BoardVo (int b_no, 
					String m_id, 
					String b_title, 
					String b_body, 
					int b_hit, 
					int b_group, 
					int b_step, 
					int b_indent, 
					String b_reg_date, 
					String b_mod_date) {
		
		this.b_no = b_no;
		this.m_id = m_id;
		this.b_title = b_title;
		this.b_body = b_body;
		this.b_hit = b_hit;
		this.b_group = b_group;
		this.b_step = b_step;
		this.b_indent = b_indent;
		this.b_reg_date = b_reg_date;
		this.b_mod_date = b_mod_date;
		
	}
	
}
