# 📚 Đồ án OOP: Quản lý Thư viện Mini

Dự án cuối kỳ môn Lập trình Hướng đối tượng, ứng dụng Java Core và Java Swing để quản lý tài liệu thư viện.

## 🎯 Điểm nhấn Kỹ thuật (OOP Principles)
Dự án áp dụng chặt chẽ 4 tính chất của Lập trình Hướng đối tượng:
- **Tính Trừu tượng (Abstraction):** Lớp `TaiLieu` định nghĩa bộ khung chuẩn cho mọi loại tài liệu.
- **Tính Kế thừa (Inheritance):** `Sach` và `TapChi` tái sử dụng mã nguồn từ `TaiLieu`.
- **Tính Đóng gói (Encapsulation):** Bảo vệ dữ liệu `maTL` và logic cho mượn thông qua phương thức `muonTaiLieu()`.
- **Tính Đa hình (Polymorphism):** Hiển thị chi tiết (Tác giả / Số phát hành) động trên JTable thông qua việc ghi đè phương thức `getChiTiet()`.

## 🛠 Công nghệ sử dụng
- **Ngôn ngữ:** Java 11+
- **Giao diện:** Java Swing (Thiết kế thuần không dùng công cụ kéo thả)
- **Kiến trúc:** MVC (Model - View - Controller)

## 🚀 Cách chạy dự án
1. Clone repository về máy.
2. Mở dự án bằng IDE (IntelliJ IDEA, Eclipse, hoặc NetBeans).
3. Chạy file `src/Main.java`.