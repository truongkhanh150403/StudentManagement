package com.university.view;

import com.university.dao.StudentDAO;
import com.university.model.Student;
import java.util.List;
import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do{
            System.out.println("\n========== HỆ THỐNG QUẢN LÝ SINH VIÊN (MVC) ==========");
            System.out.println("1. Xem danh sách sinh viên hiện có");
            System.out.println("2. Thêm sinh viên mới");
            System.out.println("0. Thoát chương trình");
            System.out.print("👉 Mời em chọn tính năng (0-2): ");

            choice = scanner.nextInt();
            scanner.nextLine();  // Xóa bộ nhớ đệm sau khi nhập số

            switch (choice){
                case 1:
                    System.out.println("\n--- Đang tải danh sách sinh viên từ Database... ---");
                    // View gọi DAO bốc dữ liệu lên
                    List<Student> students = studentDAO.getAllStudents();

                    if(students.isEmpty()){
                        System.out.println("📭 Hiện tại Database trống, chưa có sinh viên nào.");
                    }else{
                        for(Student student : students){
                            System.out.printf("ID: %s | Name: %s | Age: %d\n", student.getId(), student.getName(), student.getAge());
                        }
                    }
                    break;

                case 2:
                    System.out.println("\n--- Nhập thông tin sinh viên mới ---");
                    System.out.println("Nhập mã sinh viên: ");
                    String id = scanner.nextLine();
                    System.out.println("Nhập họ và tên: ");
                    String name = scanner.nextLine();
                    System.out.println("Nhập tuổi: ");
                    int age = scanner.nextInt();

                    // Đóng gói dữ liệu người dùng nhập thành 1 đối tượng Model Student
                    Student newStudent = new Student(id, name, age);

                    // View gửi đối tượng này qua cho DAO tự bắn xuống MySQL
                    boolean isScuccess = studentDAO.insertStudent(newStudent);

                    if(isScuccess){
                        System.out.println("🎉 Tuyệt vời! Đã thêm sinh viên mới xuống Database thành công!");
                    }else{
                        System.out.println("❌ Thêm thất bại! Hãy kiểm tra lại trùng mã SV hoặc kết nối XAMPP.");
                    }
                    break;

                case 0:
                    System.out.println("👋 Tạm biệt em! Chương trình đã đóng an toàn.");
                    break;

                default:
                    System.out.println("⚠️ Lựa chọn không hợp lệ! Mời em nhập lại từ 0 đến 2.");

            }
        }while(choice != 0);

        scanner.close();
    }
}
