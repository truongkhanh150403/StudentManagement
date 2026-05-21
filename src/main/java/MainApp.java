import com.university.model.Student;

public class MainApp {
    public static void main(String[] args) {
        Student std1 = new Student(1, "John", 17);
        System.out.printf("Student 1: id = %d, name = %s, age = %d\n", std1.getId(), std1.getName(), std1.getAge());

    }
}
