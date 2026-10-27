package models;

public class TapChi extends TaiLieu {
    private String soPhatHanh;

    public TapChi(int maTL, String ten, String soPhatHanh) {
        super(maTL, ten);
        this.soPhatHanh = soPhatHanh;
    }

    @Override
    public String getLoai() {
        return "Tạp chí";
    }

    @Override
    public String getChiTiet() {
        return "Số PH: " + soPhatHanh;
    }
}