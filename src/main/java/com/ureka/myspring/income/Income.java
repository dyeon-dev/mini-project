package com.ureka.myspring.income;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@ToString
@Entity
@Getter
@Setter
public class Income {
	@Id
	private int no;
	private String title;
	private String price;
	
	private Date indate;
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Date getIndate() {
		return indate;
	}
	public void setIndate(Date indate) {
		this.indate = indate;
	}
	public String getFormattedIndate() {
		if (indate != null) {
			return dateFormat.format(indate);
		}
		return null;
	}

	public void setFormattedIndate(String dateStr) throws ParseException {
		this.indate = dateFormat.parse(dateStr);
	}
}