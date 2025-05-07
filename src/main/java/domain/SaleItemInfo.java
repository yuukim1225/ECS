package domain;

import java.sql.Timestamp;

public class SaleItemInfo {
	private int itemSaleId;
	private String itemSaleName;
	private int itemSalePrice;
	private String userId;
	private String buyUserId;
	private String itemSaleCategory;
	private String itemSaleText;
	private Timestamp itemSaleDate;
	private String itemSaleImg;
	private int itemSaleState;
	
	public int getItemSaleId() {
		return itemSaleId;
	}
	public void setItemSaleId(int itemSaleId) {
		this.itemSaleId = itemSaleId;
	}
	public String getItemSaleName() {
		return itemSaleName;
	}
	public void setItemSaleName(String itemSaleName) {
		this.itemSaleName = itemSaleName;
	}
	public int getItemSalePrice() {
		return itemSalePrice;
	}
	public void setItemSalePrice(int itemSalePrice) {
		this.itemSalePrice = itemSalePrice;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBuyUserId() {
		return buyUserId;
	}
	public void setBuyUserId(String buyUserId) {
		this.buyUserId = buyUserId;
	}
	public String getItemSaleCategory() {
		return itemSaleCategory;
	}
	public void setItemSaleCategory(String itemSaleCategory) {
		this.itemSaleCategory = itemSaleCategory;
	}
	public String getItemSaleText() {
		return itemSaleText;
	}
	public void setItemSaleText(String itemSaleText) {
		this.itemSaleText = itemSaleText;
	}
	public Timestamp getItemSaleDate() {
		return itemSaleDate;
	}
	public void setItemSaleDate(Timestamp itemSaleDate) {
		this.itemSaleDate = itemSaleDate;
	}
	public String getItemSaleImg() {
		return itemSaleImg;
	}
	public void setItemSaleImg(String itemSaleImg) {
		this.itemSaleImg = itemSaleImg;
	}
	public int getItemSaleState() {
		return itemSaleState;
	}
	public void setItemSaleState(int itemSaleState) {
		this.itemSaleState = itemSaleState;
	}
}
