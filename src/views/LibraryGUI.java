package views;

import controllers.LibraryManager;
import models.TaiLieu;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;

public class LibraryGUI extends JFrame {
    private LibraryManager manager;
    private DefaultTableModel model;
    private JTable table;

    // 1. CHUYỂN TOÀN BỘ PHÔNG CHỮ SANG ARIAL
    private final Font MAIN_FONT = new Font("Arial", Font.PLAIN, 14);
    private final Font BOLD_FONT = new Font("Arial", Font.BOLD, 14);

    public LibraryGUI() {
        manager = new LibraryManager();

        setTitle("Quản lý Thư Viện Mini");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        // FORM NHẬP LIỆU
        JPanel panelTop = new JPanel(new GridLayout(4, 2, 15, 15));

        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY), " Thêm Tài Liệu Mới ");
        titledBorder.setTitleFont(new Font("Arial", Font.BOLD, 15));
        titledBorder.setTitleColor(Color.BLACK); // Ép màu đen cho tiêu đề
        panelTop.setBorder(BorderFactory.createCompoundBorder(titledBorder, new EmptyBorder(10, 15, 10, 15)));

        JComboBox<String> cbLoai = new JComboBox<>(new String[]{"Sách", "Tạp chí"});
        cbLoai.setFont(MAIN_FONT);
        cbLoai.setForeground(Color.BLACK);

        JTextField txtTen = new JTextField();
        txtTen.setFont(MAIN_FONT);
        txtTen.setForeground(Color.BLACK);

        JTextField txtChiTiet = new JTextField();
        txtChiTiet.setFont(MAIN_FONT);
        txtChiTiet.setForeground(Color.BLACK);

        JLabel lblChiTiet = new JLabel("Tên Tác giả:");
        lblChiTiet.setFont(BOLD_FONT);
        lblChiTiet.setForeground(Color.BLACK);

        JButton btnAdd = new JButton("Thêm vào Thư Viện");
        styleButton(btnAdd); // Gọi hàm làm đẹp nút bấm

        cbLoai.addActionListener(e -> {
            if (cbLoai.getSelectedIndex() == 0) lblChiTiet.setText("Tên Tác giả:");
            else lblChiTiet.setText("Số phát hành (VD: T10/2026):");
        });

        panelTop.add(createLabel("Phân loại tài liệu:")); panelTop.add(cbLoai);
        panelTop.add(createLabel("Tên tài liệu:")); panelTop.add(txtTen);
        panelTop.add(lblChiTiet); panelTop.add(txtChiTiet);
        panelTop.add(new JLabel("")); panelTop.add(btnAdd);

        // BẢNG DỮ LIỆU
        String[] columns = {"Mã TL", "Tên Tài Liệu", "Phân Loại", "Thông Tin Chi Tiết", "Trạng Thái"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        table = new JTable(model);
        styleTable();

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        // NÚT CHỨC NĂNG
        JPanel panelBottom = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton btnBorrow = new JButton("Đăng ký Mượn tài liệu");
        btnBorrow.setPreferredSize(new Dimension(250, 45));
        styleButton(btnBorrow);

        panelBottom.add(btnBorrow);

        // XỬ LÝ SỰ KIỆN
        btnAdd.addActionListener(e -> {
            String ten = txtTen.getText().trim();
            String chiTiet = txtChiTiet.getText().trim();
            if (ten.isEmpty() || chiTiet.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            manager.themTaiLieu(cbLoai.getSelectedIndex(), ten, chiTiet);
            capNhatBang();
            txtTen.setText(""); txtChiTiet.setText("");
            txtTen.requestFocus();
        });

        btnBorrow.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn 1 tài liệu trên bảng!", "Nhắc nhở", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            if (manager.muonTaiLieu(row)) {
                capNhatBang();
                JOptionPane.showMessageDialog(this, "Mượn thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Tài liệu này đã có người mượn!", "Từ chối", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainPanel.add(panelTop, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(panelBottom, BorderLayout.SOUTH);
    }

    private void capNhatBang() {
        model.setRowCount(0);
        for (TaiLieu tl : manager.getDanhSach()) {
            model.addRow(new Object[]{
                    "#" + tl.getMaTL(),
                    tl.getTen(),
                    tl.getLoai(),
                    tl.getChiTiet(),
                    tl.getTrangThai()
            });
        }
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(BOLD_FONT);
        label.setForeground(Color.BLACK); // 2. ÉP CHỮ MÀU ĐEN CHO NHÃN
        return label;
    }

    private void styleButton(JButton btn) {
        btn.setFont(BOLD_FONT);
        btn.setForeground(Color.BLACK); // 3. ÉP CHỮ MÀU ĐEN CHO NÚT BẤM
        btn.setBackground(new Color(230, 230, 230)); // Đổi màu nền nút thành xám nhạt để nổi bật
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void styleTable() {
        table.setRowHeight(30);
        table.setFont(MAIN_FONT);
        table.setForeground(Color.BLACK); // Chữ trong bảng màu đen

        // Tùy chỉnh thanh Tiêu đề (Header)
        JTableHeader header = table.getTableHeader();
        header.setFont(BOLD_FONT);
        header.setBackground(new Color(220, 220, 220)); // Nền xám nhạt
        header.setForeground(Color.BLACK); // 4. ÉP CHỮ MÀU ĐEN CHO TIÊU ĐỀ BẢNG
        header.setPreferredSize(new Dimension(100, 35));

        // Tùy chỉnh cột Trạng thái
        table.getColumnModel().getColumn(4).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) {
                    if ("Sẵn sàng".equals(value)) {
                        c.setForeground(new Color(0, 128, 0)); // Xanh lá đậm cho dễ đọc
                    } else if ("Đã cho mượn".equals(value)) {
                        c.setForeground(Color.RED); // Đỏ
                    } else {
                        c.setForeground(Color.BLACK);
                    }
                }
                setFont(BOLD_FONT);
                return c;
            }
        });
    }
}