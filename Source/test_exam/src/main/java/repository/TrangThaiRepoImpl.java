package repository;

import model.TrangThai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrangThaiRepoImpl implements TrangThaiRepo{
    private static final String SELECT_ALL_TRANG_THAI = " SELECT * FROM trang_thai ";
    @Override
    public List<TrangThai> selectAllTrangThai() {
        List<TrangThai>  trangThaiList = new ArrayList<>();
        try(Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRANG_THAI);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int maTrangThai = rs.getInt(1);
                String tenTrangThai = rs.getString(2);

                TrangThai trangThai = new TrangThai(maTrangThai,tenTrangThai);
                trangThaiList.add(trangThai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return trangThaiList;
    }
}
