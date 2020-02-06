/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class VeThang {
    private String HoTen,MaVe,MaXe,LoaiXe,NgayLam,NgayHet;
    private int GiaVe;
    public String getHoTen() {
        return HoTen;
    }
    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public String getMaVe() {
        return MaVe;
    }

    public void setMaVe(String MaVe) {
        this.MaVe = MaVe;
    }

    public String getMaXe() {
        return MaXe;
    }

    public void setMaXe(String MaXe) {
        this.MaXe = MaXe;
    }

    public String getLoaiXe() {
        return LoaiXe;
    }

    public void setLoaiXe(String LoaiXe) {
        this.LoaiXe = LoaiXe;
    }

    public int getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(int GiaVe) {
        this.GiaVe = GiaVe;
    }

    public String getNgayLam() {
        return NgayLam;
    }

    public void setNgayLam(String NgayLam) {
        this.NgayLam = NgayLam;
    }

    public String getNgayHet() {
        return NgayHet;
    }

    public void setNgayHet(String NgayHet) {
        this.NgayHet = NgayHet;
    }
    public void tinhThoiHan(VeThang vt,Date dateVao, String th){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        int sothang = Integer.parseInt(th);
        int sotien = 0;
        if(vt.getLoaiXe().equalsIgnoreCase("Xe đạp"))
        {
           sotien = sothang*70000;
        }
        else if(vt.getLoaiXe().equalsIgnoreCase("Xe máy"))
        {
           sotien = sothang*100000;
        }
        long thoigian = (long)sothang*30*1000*60*60*24;
        long timemilihet = dateVao.getTime()+ thoigian;
        Date dateHet = new Date();
        dateHet.setTime(timemilihet);
        String stringDateHet = dateFormat.format(dateHet);
        vt.setGiaVe(sotien);
        vt.setNgayHet(stringDateHet);
    }       
}
