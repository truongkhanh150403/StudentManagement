package com.university.dao;

import com.university.model.Student;    // Import lớp Student từ package model
import java.sql.Connection;             // Import lớp Connection để quản lý kết nối cơ sở dữ liệu
import java.sql.PreparedStatement;      // Import lớp PreparedStatement để thực thi câu lệnh SQL với tham số
import java.sql.ResultSet;              // Import lớp ResultSet để xử lý kết quả truy vấn
import java.sql.Statement;              // Import lớp Statement để thực thi câu lệnh SQL
import java.util.ArrayList;             // Import lớp ArrayList để sử dụng danh sách động lưu trữ đối tượng Student
import java.util.List;                  // Import lớp List để sử dụng kiểu dữ liệu danh sách trong Java

public class StudentDAO {

    // HÀM 1: LẤY TOÀN BỘ DANH SÁCH SINH VIÊN (Chuyển đổi từ lệnh SELECT)
    public  List<Student> getAllStudents() {
        List<Student> list = new ArrayList<>();                 // Tạo một danh sách rỗng để lưu trữ đối tượng Student
        String sql = "SELECT id, name, age FROM student";       // Câu lệnh SQL để lấy dữ liệu từ bảng student

        // Sử dụng lại hàm kết nối từ DatabaseConnection có sẵn trong cùng package dao
        try(Connection connection = DatabaseConnection.getConnection(); // Tạm thời dùng hàm main cũ, lát thầy trò mình sẽ tối ưu sau nhé
            Statement statement = connection.createStatement();         // Tạo một đối tượng Statement để thực thi câu lệnh SQL
            ResultSet resultSet = statement.executeQuery(sql)) {        // Thực thi câu lệnh SQL và nhận kết quả trả về trong ResultSet

            // Dùng vòng lặp để đọc từng dòng dữ liệu trong ResultSet
            while(resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");

                // Đúc thành đối tượng Student và nạp vào danh sách List
                Student student = new Student(id, name, age);
                list.add(student);                                      // Thêm đối tượng Student vào danh sách
            }
        }catch(Exception e){
            System.out.println("❌ Lỗi khi lấy danh sách sinh viên: " + e.getMessage());
        }
        return list;   // Trả về danh sách sinh viên đã lấy được từ cơ sở dữ liệu
    }

    // HÀM 2: THÊM MỚI MỘT SINH VIÊN (Chuyển đổi từ lệnh INSERT)
    public boolean insertStudent(Student student){
        String sql = "INSERT INTO student (id, name, age) VALUES (?, ?, ?)";        // Câu lệnh SQL để thêm mới một sinh viên vào bảng student,
        // sử dụng dấu chấm hỏi (?) làm tham số
        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            // Lấy dữ liệu từ đối tượng student truyền vào để bắn vào dấu ?
            preparedStatement.setString(1, student.getId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setInt(3, student.getAge());

            int rowsInsertted = preparedStatement.executeUpdate();   // Thực thi câu lệnh SQL và nhận số dòng bị tác động (thêm mới)
            return rowsInsertted > 0; // Nếu số dòng ảnh hưởng > 0 tức là thêm thành công (true)
        }catch(Exception e){
            System.out.println("❌ Lỗi khi thêm sinh viên:" + e.getMessage());
            return false;   // Trả về false nếu có lỗi xảy ra khi thêm sinh viên
        }
    }

    // HÀM 3: CẬP NHẬT THÔNG TIN SINH VIÊN (UPDATE)
    public boolean updateStudent(Student student) {
        // Câu lệnh SQL để cập nhật thông tin sinh viên trong bảng student, sử dụng dấu chấm hỏi (?) làm tham số
        String sql = "UPDATE student SET name = ?, age = ? WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Bắn dữ liệu mới vào các dấu hỏi ?
            preparedStatement.setString(1, student.getName());
            preparedStatement.setInt(2, student.getAge());
            preparedStatement.setString(3, student.getId()); //WHERE id = ?

            int rowsUpdated = preparedStatement.executeUpdate();
            return  rowsUpdated > 0;    //// Trả về true nếu sửa thành công ít nhất 1 dòng

        }catch (Exception e) {
            System.out.println("❌ Lỗi khi cập nhật sinh viên: " + e.getMessage());
            return  false;
        }
    }

    // HÀM 4: XÓA SINH VIÊN THEO MÃ ID (DELETE)
    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM student WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, studentId); //WHERE id = ?

            int rowsDeleted = preparedStatement.executeUpdate();
            return rowsDeleted > 0;    // Trả về true nếu xóa thành công
        }catch(Exception e) {
            System.out.println("❌ Lỗi khi xóa sinh viên: " +e.getMessage());
            return false;
        }
    }
}
