package com.university.view;

import com.university.dao.StudentDAO;
import com.university.model.Student;
import java.util.List;
import java.util.Scanner;

public class MainConsole {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();
        Scanner scanner = new Scanner(System.in);
        int choice = -1; // Biến lưu lựa chọn của người dùng, khởi tạo bằng -1 để đảm bảo vòng lặp chạy ít nhất một lần

        do{
            System.out.println("\n========== HỆ THỐNG QUẢN LÝ SINH VIÊN (MVC) ==========");
            System.out.println("1. Xem danh sách sinh viên hiện có");
            System.out.println("2. Thêm sinh viên mới");
            System.out.println("3. Sửa thông tin sinh viên");
            System.out.println("4. Xóa sinh viên");
            System.out.println("0. Thoát chương trình");
            System.out.print("👉 Mời em chọn tính năng (0-4): ");

            try{
                choice = scanner.nextInt();
                scanner.nextLine();  // Xóa bộ nhớ đệm sau khi nhập số
            }catch (Exception e){
                System.out.println("⚠️ Lỗi: Vui lòng nhập một số nguyên hợp lệ!");
                scanner.nextLine();     //Xóa ký tự chữ bị lỗi trong bộ đệm
                continue;  // Ép vòng lặp chạy lại từ đầu menu
            }


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
                case 3:
                    System.out.println("n--- Tính năng: Sửa thông tin sinh viên ---");
                    System.out.println("Nhập mã sinh viên cần sửa: ");
                    String updateId = scanner.nextLine();

                    System.out.println("Nhập tên mới: ");
                    String newName = scanner.nextLine();

                    int newAge = 0;
                    while(true){
                        try{
                            System.out.println("Nhập tuổi mới: ");
                            newAge = scanner.nextInt();
                            if(newAge > 0) break;
                            System.out.println("⚠️ Tuổi phải là số nguyên dương!");
                        }catch(Exception e) {
                            System.out.println("⚠️ Tuổi không hợp lệ! Vui lòng nhập lại bằng SỐ.");
                            scanner.nextLine(); // Xóa bộ nhớ đệm sau khi nhập lỗi
                        }
                    }

                    Student editStudent = new Student(updateId, newName, newAge);
                    if(studentDAO.updateStudent(editStudent)){
                        System.out.println("\uD83C\uDF89 Đã cập nhật thông tin thành công!");
                    }else {
                        System.out.println("❌ Sửa thất bại! Không tìm thấy mã sinh viên: \" + updateId");
                    }
                    break;

                case 4:
                    System.out.println("\n--- Tính năng: Xóa sinh viên ---");
                    System.out.print("Nhập mã sinh viên cần xóa khỏi hệ thống: ");
                    String deleteId = scanner.nextLine();

                    System.out.println("⚠\uFE0F Em có chắc chắn muốn xóa không? (Y/N):");
                    String confirm = scanner.nextLine();

                    if(confirm.equalsIgnoreCase("Y")){
                        if(studentDAO.deleteStudent(deleteId)) {
                            System.out.println("\uD83D\uDDD1\uFE0F Đã xóa vĩnh viễn sinh viên \" + deleteId + \" khỏi Database!");
                        }else {
                            System.out.println("❌ Xóa thất bại! Không tìm thấy mã sinh viên: " + deleteId);
                        }
                    }else {
                        System.out.println("↩\uFE0F Đã hủy thao tác xóa.");
                    }

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
