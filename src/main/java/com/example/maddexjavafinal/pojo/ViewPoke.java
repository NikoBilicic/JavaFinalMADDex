package com.example.maddexjavafinal.pojo;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ViewPoke {
    private int id;
    private String sprite;
    private String name;
    private String type;
    private String type2;
    private int gen;

    public ViewPoke(int id, String sprite, String name, String type, String type2, int gen) {
        this.id = id;
        this.sprite = sprite;
        this.name = name;
        this.type = type;
        this.type2 = type2;
        this.gen = gen;
    }

    public ViewPoke(int id, String sprite, String name, String type, int gen) {
        this.id = id;
        this.sprite = sprite;
        this.name = name;
        this.type = type;
        this.gen = gen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSprite() {
        return sprite;
    }

    public void setSprite(String sprite) {
        this.sprite = sprite;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }
}
