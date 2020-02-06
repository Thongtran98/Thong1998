/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.NOI.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NOIDoanhThu extends NOI {
    public static ResultSet rsdt = null;  
    public boolean addDoanhThu(modal.DoanhThu dt){
        String ktngay = dt.getNgay();
        if (KiemTraTrungNgay(ktngay)){
            String sql = "INSERT INTO DoanhThu(Ngay,TongTien) "
                + "VALUES(?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, dt.getNgay());
                ps.setInt(2, dt.getTongTien());
                JOptionPane.showMessageDialog(null, "Thêm mới thành công");
                return ps.executeUpdate() > 0;           
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Thêm không thành công");
                return false;
            }
        }
        else{
            int ttien = layTongTien(ktngay);
            int tien = dt.getTongTien();
            ttien = ttien+ tien;
            updatethanhvien(ktngay,ttien);
    }
        return false;
}     
 public void updatethanhvien(String ngay, int tien){
            String sql ="Update DoanhThu set TongTien = ? where Ngay = ? ";
        try {
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, tien);
            pst.setString(2, ngay);
            // phải có hàm này mới đc
            pst.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "cập nhật k thành công"+ex.toString());
        }
    }    
private boolean KiemTraTrungNgay(String ngay){
        boolean kt = true;   
        String sql ="select Ngay from DoanhThu";      
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String ng = rs.getString("Ngay");
                if (ng.equalsIgnoreCase(ngay))
                {
                  kt =false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kt;
    }
 private int layTongTien( String ngay){
        int tien = 0;   
        String sql ="select * from DoanhThu";      
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String ng = rs.getString("Ngay");
                if (ng.equalsIgnoreCase(ngay))
                {
                  tien = rs.getInt("TongTien");
                  return tien;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tien;
    }
 public ResultSet selectDoanhThu(){
     
        String query ="select * from DoanhThu";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            rsdt = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(""+ex.toString());
        }
        return rsdt;
    }
}

    
