
-- 1. Tạo bảng student gồm 3 cột (Cú pháp chuẩn MySQL)
CREATE TABLE student(
    id VARCHAR(10) PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    age INT
);

-- 2. Chèn 3 dòng dữ liệu sinh viên mẫu
INSERT INTO student (id, name, age) VALUES ('S01', 'Nguyen Van A', 20);
INSERT INTO student (id, name, age) VALUES ('S02', 'Tran Thi B', 17);
INSERT INTO student (id, name, age) VALUES ('S03', 'John Doe', 22);

-- 3. [Em hãy tự gõ] Viết câu lệnh SELECT lấy ra toàn bộ sinh viên có tuổi >= 18
SELECT * FROM student WHERE age>=18;

-- 4. [Em hãy tự gõ] Viết câu lệnh UPDATE sửa tên của sinh viên 'S02' thành 'Tran Thi Binh'
UPDATE student SET name = 'Tran Thi Binh' WHERE id = 'S02';

-- 5. [Em hãy tự gõ] Viết câu lệnh DELETE xóa sinh viên 'S03' ra khỏi hệ thống
DELETE FROM student WhERE id = 'S03';