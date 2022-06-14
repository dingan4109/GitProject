package service;

import model.MatBang;

import java.util.List;
import java.util.Map;

public interface MatBangService {
    List<MatBang> selectAllMatBang();
    MatBang  selectMatBangByMaMatBang(String maMatBang);
    List<MatBang> search(String giaTien,String soTang, String loaiMatBang);
    Map<String,String> createNewMatBang(MatBang matBang);
    void deleteMatBang (String maMatBang);
    Map<String,String> editMatBang(MatBang matBang);
}
