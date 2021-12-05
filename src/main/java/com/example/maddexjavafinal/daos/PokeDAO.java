package com.example.maddexjavafinal.daos;

import com.example.maddexjavafinal.pojo.Poke;

import java.util.ArrayList;

public interface PokeDAO {
    public void createPoke(Poke poke);
    public ArrayList<Poke> getAllPoke();
    public Poke getPoke(int id);
    public void deletePoke(Poke poke);
}
