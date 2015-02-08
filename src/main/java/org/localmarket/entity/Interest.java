package org.localmarket.entity;

import java.util.Map;

public class Interest {

	

	public Interest(Map<String, String> data) {
		super();
		this.email = data.get("email");
		this.location = data.get("location");
		this.buy = data.get("buy");
		this.sell = data.get("sell");
		this.items = data.get("items");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String isBuy() {
		return buy;
	}

	public void setBuy(String buy) {
		this.buy = buy;
	}

	public String isSell() {
		return sell;
	}

	public void setSell(String sell) {
		this.sell = sell;
	}

	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	String email;
	String location;
	String buy;
	String sell;
	String items;

}
