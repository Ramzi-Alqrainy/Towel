package test.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import com.towel.bean.Formatter;
import com.towel.el.annotation.Resolvable;

public class Person {
//	@Resolvable(colName = "Name")
	private String name;
	@Resolvable(colName = "Age")
	private int age;
	@Resolvable(colName = "Live")
	private boolean live;
	@Resolvable(colName = "Birth", formatter = DateFormatter.class)
	private GregorianCalendar birth;
	@Resolvable
	private double money;
	private Person parent; 


	public Person(String name, int age, boolean live, String birth) {
		this.name = name;
		this.age = age;
		this.live = live;
		this.birth = new GregorianCalendar();
		try {
			this.birth.setTime(new SimpleDateFormat("ddMMyyyy").parse(birth));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}


	public Person(String name, int age, boolean live) {
		this.name = name;
		this.age = age;
		this.live = live;
	}
	
	public Person(double d) {
		this.age = (int) d;
	}

	public Person(String str, double d) {
		this.name = str;
		this.age = (int) d;
	}

	private class DateFormatter implements Formatter {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

		@Override
		public String format(Object arg0) {
			return format.format(((GregorianCalendar) arg0).getTime());
		}

		@Override
		public String getName() {
			return "date";
		}

		@Override
		public Object parse(Object arg0) {
			GregorianCalendar cal = new GregorianCalendar();
			try {
				cal.setTime(format.parse(arg0.toString()));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return cal;
		}

	}

	public boolean isLive() {
		return live;
	}

	public String toString() {
		return "Name: " + name + " age: " + age;
	}
	

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}


	public void setParent(Person parent) {
		this.parent = parent;
	}


	public Person getParent() {
		return parent;
	}
}
