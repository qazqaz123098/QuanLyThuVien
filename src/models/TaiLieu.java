package models;

public abstract class TaiLieu {
    private int maTL;
    protected String ten;
    protected String trangThai;

    public TaiLieu(int maTL, String ten) {
        this.maTL = maTL;
        this.ten = ten;
        this.trangThai = "Sẵn sàng";
    }

    public int getMaTL() {
        return maTL;
    }

    public String getTen() {
        return ten;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public boolean muonTaiLieu() {
        if (this.trangThai.equals("Sẵn sàng")) {
            this.trangThai = "Đã cho mượn";
            return true;
        }
        return false;
    }

    public abstract String getLoai();
    public abstract String getChiTiet();
}
