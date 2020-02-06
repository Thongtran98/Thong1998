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
public class NOIvedem extends NOI {
    public static ResultSet rsvd = null;
    public boolean addVeDem(modal.VeDem bd) {
        String ktmv = bd.getMaVe();  
        if (KiemTraTrungMV(ktmv)){
        String sql = "INSERT INTO VeDem(MaVe,MaXe,LoaiXe,NgayGui) "
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

public boolean deleteVeDem(String mv) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM VeDem WHERE MaVe = ?");
            ps.setString(1, mv);
            ps.executeUpdate();
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi :"+e.getMessage());
        }       
        return false;
    }
    
    public boolean updateVeDem(modal.VeDem vd) {
        
        String sql =
                "update VeDem" +
                "set MaVe = ?, " +
                "MaXe = ?, " +
                "LoaiXe = ?, " +
                "NgayGui = ?" +
                "where MaVe = ?"; 
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, vd.getMaVe());
            ps.setString(2, vd.getMaXe());
            ps.setString(3, vd.getLoaiXe());
            ps.setString(4, vd.getNgayGui());
            JOptionPane.showMessageDialog(null, "cập nhập thành công");
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "cập nhập không thành công");
        return false;
    }
    public boolean TimVeDem(modal.VeDem vd, String maVeNhap) {
        boolean kt = false;
        String sql ="select * from VeDem";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                String mave = rs.getString("MaVe");
                if (mave.equalsIgnoreCase(maVeNhap))
                {
                  vd.setMaVe(rs.getString("MaVe"));
                  vd.setMaXe(rs.getString("MaXe"));
                  vd.setLoaiXe(rs.getString("LoaiXe"));
                  vd.setNgayGui(rs.getString("NgayGui"));
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
    public ArrayList<modal.VeDem> getListVDSearched(String sql) {
        ArrayList<modal.VeDem> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                modal.VeDem bd = new modal.VeDem();
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
        String sql ="select MaVe from VeDem";      
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
        String sql ="select MaVe from VeDem";      
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
    public ResultSet selectVeDem(){
     
        String query ="select MaVe,MaXe,LoaiXe,NgayGui from VeDem";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            rsvd = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(""+ex.toString());
        }
        return rsvd;
    }
}


