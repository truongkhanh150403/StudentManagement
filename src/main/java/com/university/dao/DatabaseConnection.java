package com.university.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    // 1. Đường dẫn URL kết nối đến MySQL của XAMPP cổng 3306, vào đúng database student-management
    private static final String URL = "jdbc:mysql://localhost:3306/student-management";
    private static final String USER = "root";

    // 2. Điền chính xác tài khoản và mật khẩu XAMPP mà em đã cấu hình ở Buổi 4 nhé
    private  static final String PASSWORD = "user1234";

    // Sửa hàm thành hàm trả về Connection để các class khác mượn dùng chung
    public static Connection getConnection() throws Exception{
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    // Hàm main này giữ lại để em bấm test độc lập nếu muốn
    public static void main(String[] args) {
        try(Connection conn = getConnection()){
            if(conn != null) System.out.println("\uD83C\uDF89 Kết nối thành công!");
        }catch(Exception e){
            System.out.println("❌ Kết nối thất bại: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
