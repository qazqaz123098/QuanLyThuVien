package models;

public class Sach extends TaiLieu {
    private String tacGia;

    public Sach(int maTL, String ten, String tacGia) {
        super(maTL, ten);
        this.tacGia = tacGia;
    }

    @Override
    public String getLoai() {
        return "Sách";
    }

    @Override
    public String getChiTiet() {
        return "Tác giả: " + tacGia;
    }
}