package com.model;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/*
 
 DB에
 create table photo(
 name
 age
 image >> 업로드한 파일명 >> 1.jpg
 
 
 */


public class Photo {
	private String name;
	private int age;
	private String image; // 이미지 파일명 
	
	// Point
	private CommonsMultipartFile file; // 업로드한 파일 정보를 담는 변수
	// 단 조건 : <input type="file" name="file" ....

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	// POINT
	public CommonsMultipartFile getFile() {
		return file;
	}

	public void setFile(CommonsMultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "Photo [name=" + name + ", age=" + age + ", image=" + image + ", file=" + file + "]";
	}
	
	
	
}
