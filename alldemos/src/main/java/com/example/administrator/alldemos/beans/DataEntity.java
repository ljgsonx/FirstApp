package com.example.administrator.alldemos.beans;

/**
 * ---------------------------------------------------
 * Description:
 * Author: ljgsonx
 * Belong to: com.example.administrator.alldemos
 * Time: 2015/11/16 11:45
 * ---------------------------------------------------
 */
public class DataEntity {

    private int id;
    private String name;
    private int imgId;
    private String imgIntro;

    public DataEntity(int id, String name, int imgId, String imgIntro) {
        this.id = id;
        this.name = name;
        this.imgId = imgId;
        this.imgIntro = imgIntro;
    }

    public DataEntity() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public String getImgIntro() {
        return imgIntro;
    }

    public void setImgIntro(String imgIntro) {
        this.imgIntro = imgIntro;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
