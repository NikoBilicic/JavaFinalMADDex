package com.example.maddexjavafinal.pojo;

import java.util.ArrayList;

public class Poke {
    private int id;
    private String sprite;
    private String name;
    private ArrayList type;
    private int gen;

    public Poke(int id, String sprite, String name, ArrayList type, int gen) {
        this.id = id;
        this.sprite = sprite;
        this.name = name;
        this.type = type;
        this.gen = gen;
    }

    public Poke(int id, String sprite, String name, int gen) {
        this.id = id;
        this.sprite = sprite;
        this.name = name;
        this.gen = gen;
    }

    public Poke(String sprite, String name, ArrayList type, int gen) {
        this.sprite = sprite;
        this.name = name;
        this.type = type;
        this.gen = gen;
    }



    @Override
    public String toString() {
        return "Poke{" +
                "id=" + id +
                ", sprite='" + sprite + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", gen=" + gen +
                '}';
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

    public ArrayList getType() {
        return type;
    }

    public void setType(ArrayList type) {
        this.type = type;
    }

    public int getGen() {
        return gen;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }
}
