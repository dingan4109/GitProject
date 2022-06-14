package repository;

import model.LoaiMatBang;
import model.MatBang;
import model.TrangThai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiMatBangRepoImpl implements LoaiMatBangRepo{
    private static final String SELECT_ALL_LOAI_MAT_BANG = " SELECT * FROM loai_mat_bang ";
    @Override
    public List<LoaiMatBang> selectAllLoaiMatBang() {
        List<LoaiMatBang>  loaiMatBangList = new ArrayList<>();
        try(Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LOAI_MAT_BANG);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int maLoaiMatBang = rs.getInt(1);
                String tenLoaiMatBang = rs.getString(2);

                LoaiMatBang loaiMatBang = new LoaiMatBang(maLoaiMatBang,tenLoaiMatBang);
                loaiMatBangList.add(loaiMatBang);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return loaiMatBangList;
    }
}
