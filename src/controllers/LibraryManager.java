package controllers;

import models.Sach;
import models.TaiLieu;
import models.TapChi;
import java.util.ArrayList;

public class LibraryManager {
    private ArrayList<TaiLieu> danhSachTL = new ArrayList<>();
    private int maCounter = 100;

    public void themTaiLieu(int loai, String ten, String chiTiet) {
        TaiLieu tl;
        if (loai == 0) {
            tl = new Sach(maCounter++, ten, chiTiet);
        } else {
            tl = new TapChi(maCounter++, ten, chiTiet);
        }
        danhSachTL.add(tl);
    }

    public boolean muonTaiLieu(int index) {
        if (index >= 0 && index < danhSachTL.size()) {
            return danhSachTL.get(index).muonTaiLieu();
        }
        return false;
    }

    public ArrayList<TaiLieu> getDanhSach() {
        return danhSachTL;
    }
}