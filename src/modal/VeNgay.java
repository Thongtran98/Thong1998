/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Admin
 */

public class VeNgay {
    private String MaVe,MaXe,LoaiXe,NgayGui,GiaVe;
    int soTienNgayXD = 3000;
    int soTienNgayXM = 5000;
    int soTienDemXD  = 7000;
    int soTienDemXM  = 10000;
    int soTienThangXD = 70000;
    int soTienThangXM = 100000;
  
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
    
    public int tinhTienVN(VeNgay vn ,String ngayvao,String ngayra){
        int sotien;
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date ngayvaoDate;
        try {
            ngayvaoDate = dateFormat.parse(ngayvao); // chuyển tg vào thành kiểu Date
            Date ngayraDate = dateFormat.parse(ngayra);
            long timeDiff = Math.abs(ngayraDate.getTime() - ngayvaoDate.getTime()); // in miliseconds
            int soNgay = ((int)timeDiff / 1000)/(60*60*24); // trả về số ngày 
            String lx = vn.getLoaiXe();
            if(lx.equals("Xe máy")){
                if(soNgay==0)
                {
                    sotien = soTienNgayXM; 
                }else{
                    sotien = soNgay*(soTienNgayXM+soTienDemXM)+soTienNgayXM;
                }
            }else{
                if(soNgay==0)
                {
                    sotien = soTienNgayXD; 
                }else{
                    sotien = soNgay*(soTienNgayXD+soTienDemXD)+soTienNgayXD;
            }
        }
        return(sotien);
        } catch (ParseException ex) {
            Logger.getLogger(VeNgay.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
