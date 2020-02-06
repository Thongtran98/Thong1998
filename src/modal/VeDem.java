/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class VeDem {
    private String HoTen,MaVe,MaXe,LoaiXe,NgayGui,GiaVe;
    int soTienNgayXD = 3000;
    int soTienNgayXM = 5000;
    int soTienDemXD  = 7000;
    int soTienDemXM  = 10000;
    int soTienThangXD = 70000;
    int soTienThangXM = 100000;
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

    public String getNgayGui() {
        return NgayGui;
    }

    public void setNgayGui(String NgayGui) {
        this.NgayGui = NgayGui;
    }

    public String getGiaVe() {
        return GiaVe;
    }

    public void setGiaVe(String GiaVe) {
        this.GiaVe = GiaVe;
    }
    
    public int tinhTienVD(VeDem vd ,String ngayvao,String ngayra) {
        int sotien;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        //tinh gio ra de so sanh
        DateFormat Format = new SimpleDateFormat("hh");
        Date ngayvaoDate;
        Date gioraDate = new Date();
        String gioraString = Format.format(gioraDate);
        int gioraInt = Integer.parseInt(gioraString);
        System.out.println(gioraInt);
        try {
            ngayvaoDate = dateFormat.parse(ngayvao); // chuyển tg vào thành kiểu Date
            Date ngayraDate = dateFormat.parse(ngayra);
            long timeDiff = Math.abs(ngayraDate.getTime() - ngayvaoDate.getTime()); // in miliseconds
            int soNgay = ((int)timeDiff / 1000)/(60*60*24); // trả về số ngày 
            soNgay--;
            String lx = vd.getLoaiXe();
            if(lx.equals("Xe máy")){        
                if(soNgay==0)
                {
                    sotien = soTienDemXM; 
                }else{
                    sotien = soNgay*(soTienNgayXM+soTienDemXM)+soTienDemXM;
                }
                if (soNgay>7){
                sotien+=soTienNgayXM;
                }
            }else{
                if(soNgay==0)
                {
                    sotien = soTienDemXD;
                }else{
                    sotien = soNgay*(soTienNgayXD+soTienDemXD)+soTienDemXD;
                }
                if (soNgay>7){
                sotien+=soTienNgayXD;
                }
            }
            return(sotien);    
        } catch (ParseException ex) {
            Logger.getLogger(VeDem.class.getName()).log(Level.SEVERE, null, ex);
            return 0;   
        }           
    }
}
