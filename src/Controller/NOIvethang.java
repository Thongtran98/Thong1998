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
public class NOIvethang extends NOI {
    public static ResultSet rsvt = null;
    public boolean addVeThang(modal.VeThang bd) {
        String ktmv = bd.getMaVe();   
        if (KiemTraTrungMV(ktmv)){
            String sql = "INSERT INTO VeThang(MaVe,HoTen,MaXe,LoaiXe,GiaVe,NgayLam,NgayHet) "
            + "VALUES(?,?,?,?,?,?,?)";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, bd.getMaVe());
                ps.setString(2, bd.getHoTen());
                ps.setString(3, bd.getMaXe());
                ps.setString(4, bd.getLoaiXe());
                ps.setInt   (5, bd.getGiaVe());
                ps.setString(6, bd.getNgayLam());
                ps.setString(7, bd.getNgayHet());
                JOptionPane.showMessageDialog(null, "Thêm mới thành công");
                return ps.executeUpdate() > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;    
        }
        else{
           JOptionPane.showMessageDialog(null, "Mã vé trùng");
        }
        return false;       
}

public boolean deleteVeThang(modal.VeThang bd) {
        try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM BangDiem WHERE ID = " + bd.getMaVe());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateVeThang(modal.VeThang bd) {
        
        String sql =
                "update VeNgay" +
                "set MaVe = ?, " +
                "HoTen = ?, " +
                "MaXe = ?, " +
                "LoaiXe = ?, " +
                "GiaVe = ?, " +
                "NgayLam = ?" +
                "NgayHet = ?" +
                "where MaVe = ?"; 
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(2, bd.getMaVe());
            ps.setString(1, bd.getHoTen());
            ps.setString(3, bd.getMaXe());
            ps.setString(4, bd.getLoaiXe());
            ps.setInt   (5, bd.getGiaVe());
            ps.setString(6, bd.getNgayLam());
            ps.setString(7, bd.getNgayHet());
            
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public ArrayList<modal.VeThang> getListVTSearched(String sql) {
        ArrayList<modal.VeThang> list = new ArrayList<>();
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                modal.VeThang bd = new modal.VeThang();
                bd.setMaVe(rs.getString("MaVe"));
                bd.setHoTen(rs.getString("HoTen"));
                bd.setMaXe(rs.getString("MaXe"));
                bd.setLoaiXe(rs.getString("LoaiXe"));
                bd.setGiaVe(rs.getInt("GiaVe"));
                bd.setNgayLam(rs.getString("NgayLam"));
                bd.setNgayHet(rs.getString("NgayHet"));
                list.add(bd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    private boolean KiemTraTrungMV(String mv){
        boolean kt = true;   
        String sql ="select MaVe from VeThang";      
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
    public ResultSet selectVeThang(){
     
        String query ="select MaVe,HoTen,MaXe,LoaiXe,GiaVe,NgayLam,NgayHet from VeThang";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            rsvt = ps.executeQuery();
        } catch (SQLException ex) {
            System.out.println(""+ex.toString());
        }
        return rsvt;
    }
}
