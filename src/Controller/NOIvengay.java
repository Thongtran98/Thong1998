/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class NOIvengay extends NOI {
    public static ResultSet rsvn = null;
    public boolean addVeNgay(modal.VeNgay bd) {
        String ktmv = bd.getMaVe();  
        if (KiemTraTrungMV(ktmv)){
            String sql = "INSERT INTO VeNgay(MaVe,MaXe,LoaiXe,NgayGui) "
                + "VALUES(?,?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, bd.getMaVe());
                ps.setString(2, bd.getMaXe());
                ps.setString(3, bd.getLoaiXe());
                ps.setString(4, bd.getNgayGui());
                JOptionPane.showMessageDialog(null, "Thêm mới thành công");
                return ps.executeUpdate() > 0;           
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Thêm không thành công");
            return false;
        }
        else{
           JOptionPane.showMessageDialog(null, "Mã vé trùng");
       }
        return false;
}

public boolean deleteVeNgay(String mv) {
        String sql = "DELETE FROM VeNgay WHERE MaVe = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, mv);
            ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi :"+e.getMessage());
        }
        return false;
    }
    
    public boolean updateVeNgay(modal.VeNgay bd) {
        
        String sql =
                "update VeNgay" +
                "set MaVe = ?, " +
                "MaXe = ?, " +
                "LoaiXe = ?, " +
                "NgayGui = ?" +
                "where MaVe = ?"; 
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, bd.getMaVe());
            ps.setString(2, bd.getMaXe());
            ps.setString(3, bd.getLoaiXe());
            ps.setString(4, bd.getNgayGui());
            JOptionPane.showMessageDialog(null, "cập nhập thành công");
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "cập nhập không thành công");
        return false;
    }
    
    public boolean TimVeNgay( modal.VeNgay vn, String maVeNhap){
        
        boolean kt = false;
        String sql ="select * from VeNgay";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String mave = rs.getString("MaVe");
                if (mave.equalsIgnoreCase(maVeNhap))
                {
                  vn.setMaVe(rs.getString("MaVe"));
                  vn.setMaXe(rs.getString("MaXe"));
                  vn.setLoaiXe(rs.getString("LoaiXe"));
                  vn.setNgayGui(rs.getString("NgayGui"));
                  kt = true;
                }
            }
            if(kt == false){
            JOptionPane.showMessageDialog(null, " Mã vé không tồn tại");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kt;
    }
    
    public ArrayList<modal.VeNgay> getListVNSearched(String sql) {
        ArrayList<modal.VeNgay> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                modal.VeNgay bd = new modal.VeNgay();
                bd.setMaVe(rs.getString("MaVe"));
                bd.setMaXe(rs.getString("MaXe"));
                bd.setLoaiXe(rs.getString("LoaiXe"));
                bd.setNgayGui(rs.getString("NgayGui"));
                list.add(bd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private boolean KiemTraTrungMV(String mv){
        boolean kt = true;   
        String sql ="select MaVe from VeNgay";      
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String mave = rs.getString("MaVe");
                if (mave.equalsIgnoreCase(mv))
                {
                  kt =false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kt;
    }
    public boolean KiemTraMV(String mv){
        boolean kt = false;   
        String sql ="select MaVe from VeNgay";      
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String mave = rs.getString("MaVe");
                if (mave.equalsIgnoreCase(mv))
                {
                  kt =true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kt;
    }
    public ResultSet selectVeNgay(){
     
        String query ="select MaVe,MaXe,LoaiXe,NgayGui from VeNgay";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            rsvn = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(""+ex.toString());
        }
        return rsvn;
    }
}
