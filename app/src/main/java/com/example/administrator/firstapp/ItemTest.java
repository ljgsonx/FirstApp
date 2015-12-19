package com.example.administrator.firstapp;

/**
 *---------------------------------------------------
 * Description: 实体类 ItemTest ListView的item
 * Author: ljgsonx
 * Belong to: com.example.administrator.firstapp
 * Time: 2015/11/11 16:17
 *---------------------------------------------------
 */
public class ItemTest {
	private int mImageViewID;
	private String mstrName;
	
	public ItemTest(){
		super();
	}
	
	public ItemTest(int ImageViewID, String strName) {
		this.mImageViewID = ImageViewID;
		this.mstrName = strName;
	}
	
	public int getImageViewID() {
		return mImageViewID;
	}
	
	public String getstrName() {
		return mstrName;
	}
}
