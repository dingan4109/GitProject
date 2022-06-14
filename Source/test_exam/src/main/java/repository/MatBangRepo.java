package repository;

import model.MatBang;

import java.sql.SQLException;
import java.util.List;

public interface MatBangRepo {
    List<MatBang> selectAllMatBang();
    MatBang  selectMatBangByMaMatBang(String maMatBang);
    List<MatBang> search(String giaTien,String soTang, String loaiMatBang);
    void createNewMatBang(MatBang matBang);
    void deleteMatBang (String maMatBang);
    void editMatBang(MatBang matBang);

}
