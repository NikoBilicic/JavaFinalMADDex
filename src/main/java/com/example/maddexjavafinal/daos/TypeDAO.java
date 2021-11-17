package com.example.maddexjavafinal.daos;

import com.example.maddexjavafinal.pojo.Type;

import java.util.ArrayList;

public interface TypeDAO {
    public ArrayList<Type> getAllType();
    public Type getType(int id);
}
