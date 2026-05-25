package com.university.model;

//annotation @Data, @AllArgsConstructor, @NoArgsConstructor hiện bị lỗi

public class Student {
    private String id;
    private String name;
    private int age;

    // Hàm khởi tạo không tham số
    public Student() {}

    // Hàm khởi tạo đầy đủ tham số
    public Student(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Tự tay gõ các hàm Getter/Setter tiêu chuẩn
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}