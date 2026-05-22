package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // 1. Đường dẫn URL kết nối đến MySQL của XAMPP cổng 3306, vào đúng database student-management
    private static final String URL = "jdbc:mysql://localhost:3306/student-management";

    // 2. Điền chính xác tài khoản và mật khẩu XAMPP mà em đã cấu hình ở Buổi 4 nhé
    private static final String USER = "root";
    private static final String PASSWORD = "user1234";

    public static void main(String[] args) {
       System.out.println("--- Đang thử nghiệm kết nối đến MySQL... ---");

        // 3. Sử dụng khối lệnh try-with-resources để tự động đóng kết nối khi chạy xong
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            if(connection != null){
                System.out.println("\uD83C\uDF89 CHÚC MỪNG EM! Java đã kết nối đến MySQL thành công rực rỡ!");
            }
        } catch(SQLException e){
            System.out.println("❌ Kết nối thất bại rồi! Hãy kiểm tra lại XAMPP hoặc Tài khoản/Mật khẩu nha.");
            e.printStackTrace(); //In ra chi tiết lỗi nếu có
        }
    }

}
