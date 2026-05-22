package com.university;

import java.sql.*;
/*
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
*/

public class MainAppV2 {
    private static final String URL = "jdbc:mysql://localhost:3306/student-management";
    private static final String USER = "root";
    private static final String PASSWORD = "user1234";

    public static void main(String[] args) {
        String sqlQuery = "SELECT id, name, age FROM student";

        System.out.println("====== DANH SÁCH SINH VIÊN TỪ DATABASE ======");

        // Mở kết nối và tạo bộ thực thi câu lệnh
        //try-with-resources sẽ tự động đóng kết nối statement và resultSet sau khi xong việc
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery)) {

            // Dùng vòng lặp dữ liệu: Cứ còn dòng tiếp theo thì còn đọc
            while(resultSet.next()){
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                System.out.printf("Mã SV: %s | Họ tên: %s | Tuổi: %d%n", id, name, age);
            }
        } catch(SQLException e){
            System.out.println("❌ Lỗi lấy dữ liệu: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
