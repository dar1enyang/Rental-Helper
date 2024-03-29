package com.example.rentalhelper;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.geo.Point;

@Entity("houses")
public class House {
	
	@Id
	private int postId;
	private float area;
	private String type;
	private int price;
	private String owner;
	private String address;
	private Point coordinate;
	
	private House() {
		
	}
	
	public House(int postId, float area, String type, int price, String owner, String address, Point coordinate) {
		this.postId = postId;
		this.area = area;
		this.type = type;
		this.price = price;
		this.owner = owner;
		this.address = address;
		this.coordinate = coordinate;
	}

	@Override
	public String toString() {
		return postId + " - " + area + "坪 - " + type + " - 每月" + price + "元 - 屋主: " + owner + " - 地址: " + address;
	}

	public float getArea() {
		return area;
	}

	public void setArea(float area) {
		this.area = area;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public Point getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(Point coordinate) {
		this.coordinate = coordinate;
	}

}
