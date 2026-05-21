package com.university.model;

public class Student {
    private int id;
    private String name;
    private int age;

    public Student(){
        this.id = 0;
        this.name = "";
        this.age = 0;
    }

    public Student(int id, String name, int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }
}
