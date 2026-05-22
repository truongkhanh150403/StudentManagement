package com.university;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainAppV3 {
    private static final String URL = "jdbc:mysql://localhost:3306/student-management";
    private static final String USER = "root";
    private static final String PASSWORD = "user1234";

    public static void main(String[] args) {
        // 1. Khung lệnh SQL sử dụng dấu chấm hỏi làm đại diện
        String sqlInsert = "INSERT INTO student (id, name, age) VALUES (?, ?, ?)";

        System.out.println("--- Đang chuẩn bị thêm sinh viên mới vào Database... ---");

        // 2. Mở kết nối và chuẩn bị bộ khung PreparedStatement
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement prepareStatement = connection.prepareStatement(sqlInsert)) {

            // 3. Tiến hành "bắn" dữ liệu thật vào thay thế cho các dấu chấm hỏi (?)
            prepareStatement.setString(1, "S04");           // Dấu hỏi số 1 (id): kiểu String
            prepareStatement.setString(2, "Trương Khánh"); // Dấu hỏi số 2 (name): kiểu String
            prepareStatement.setInt(3, 23);               // Dấu hỏi số 3 (age): kiểu Int

            // 4. Thực thi lệnh ghi dữ liệu xuống Database
            // Lệnh executeUpdate() trả về số dòng bị tác động (thay đổi) trong Database
            int rowsInserted = prepareStatement.executeUpdate();
            if(rowsInserted > 0){
                System.out.println("\uD83C\uDF89 XUẤT SẮC! Đã thêm thành công sinh viên 'Trương Khánh' vào MySQL!");
            }
        } catch(SQLException e){
            System.out.println("❌ Lỗi khi thêm dữ liệu:" + e.getMessage());
            e.printStackTrace();
        }
    }
}
