package repository;

import model.MatBang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatBangRepoImpl implements MatBangRepo{
    private static final String SELECT_ALL_MAT_BANG = " SELECT * FROM mat_bang ORDER BY dien_tich ASC";
    private static final String SELECT_BY_MA_MAT_BANG = " SELECT * FROM mat_bang WHERE ma_mat_bang = ? ";
    private static final String CREATE_MAT_BANG = " INSERT INTO mat_bang VALUES (?,?,?,?,?,?,?,?) ";
    private static final String DELETE_MAT_BANG = " DELETE FROM mat_bang WHERE ma_mat_bang = ? ";
    private static final String SEARCH = "SELECT * FROM mat_bang WHERE gia_tien LIKE ? AND so_tang LIKE ? AND " +
            "ma_loai_mat_bang LIKE ? ";
    @Override
    public List<MatBang> selectAllMatBang() {
        List<MatBang> matBangList = new ArrayList<>();
        try(Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MAT_BANG);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String maMatBang = rs.getString(1);
                int maTrangThai = rs.getInt(2);
                double dienTich = rs.getDouble(3);
                int soTang = rs.getInt(4);
                int maLoaiMatBang = rs.getInt(5);
                double giaTien = rs.getDouble(6);
                String ngayBatDau = rs.getString(7);
                String ngayKetThuc = rs.getString(8);

                MatBang matBang = new MatBang(maMatBang,maTrangThai,dienTich,soTang,maLoaiMatBang,giaTien,ngayBatDau,
                        ngayKetThuc);
                matBangList.add(matBang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matBangList;
    }

    @Override
    public MatBang selectMatBangByMaMatBang(String maMatBang) {
        MatBang matBang = null;
        try(Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_MA_MAT_BANG);
            preparedStatement.setString(1,maMatBang);

            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String ma = rs.getString(1);
                int maTrangThai = rs.getInt(2);
                double dienTich = rs.getDouble(3);
                int soTang = rs.getInt(4);
                int maLoaiMatBang = rs.getInt(5);
                double giaTien = rs.getDouble(6);
                String ngayBatDau = rs.getString(7);
                String ngayKetThuc = rs.getString(8);

                matBang = new MatBang(ma,maTrangThai,dienTich,soTang,maLoaiMatBang,giaTien,ngayBatDau,
                        ngayKetThuc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matBang;
    }

    @Override
    public List<MatBang> search(String giaTien, String soTang, String loaiMatBang) {
        List<MatBang> matBangList = new ArrayList<>();
        try(Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SEARCH);
            preparedStatement.setString(1,"%"+giaTien+"%");
            preparedStatement.setString(2,"%"+soTang+"%");
            preparedStatement.setString(3,"%"+loaiMatBang+"%");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maMatBang = rs.getString(1);
                int maTrangThai = rs.getInt(2);
                double dienTich = rs.getDouble(3);
                int tang = rs.getInt(4);
                int maLoaiMatBang = rs.getInt(5);
                double gia = rs.getDouble(6);
                String ngayBatDau = rs.getString(7);
                String ngayKetThuc = rs.getString(8);

                MatBang matBang = new MatBang(maMatBang,maTrangThai,dienTich,tang,maLoaiMatBang,gia,ngayBatDau,
                        ngayKetThuc);
                matBangList.add(matBang);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matBangList;
    }

    @Override
    public void createNewMatBang(MatBang matBang) {
        try(Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_MAT_BANG);
            preparedStatement.setString(1,matBang.getMaMatBang());
            preparedStatement.setInt(2,matBang.getMaTrangThai());
            preparedStatement.setDouble(3,matBang.getDienTich());
            preparedStatement.setInt(4,matBang.getSoTang());
            preparedStatement.setInt(5,matBang.getMaLoaiMatBang());
            preparedStatement.setDouble(6,matBang.getGiaTien());
            preparedStatement.setString(7,matBang.getNgayBatDau());
            preparedStatement.setString(8,matBang.getNgayKetThuc());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteMatBang(String maMatBang) {
        try(Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MAT_BANG);
            preparedStatement.setString(1,maMatBang);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editMatBang(MatBang matBang) {

    }
}
