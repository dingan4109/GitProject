package service;

import model.MatBang;
import repository.MatBangRepo;
import repository.MatBangRepoImpl;
import util.Regex;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatBangServiceImpl implements MatBangService{
    MatBangRepo matBangRepo = new MatBangRepoImpl();
    @Override
    public List<MatBang> selectAllMatBang() {
        return matBangRepo.selectAllMatBang();
    }

    @Override
    public MatBang selectMatBangByMaMatBang(String maMatBang) {
        return matBangRepo.selectMatBangByMaMatBang(maMatBang);
    }

    @Override
    public List<MatBang> search(String giaTien, String soTang, String loaiMatBang) {
        return matBangRepo.search(giaTien,soTang,loaiMatBang);
    }

    @Override
    public Map<String, String> createNewMatBang(MatBang matBang) {
        Map<String,String> errors = new HashMap<>();

        if(!Regex.validateCustomerCode(matBang.getMaMatBang())) {
            errors.put("maMatBang","Mã mặt bằng không đúng định dạng");
        }
        if(matBang.getDienTich()<=20) {
            errors.put("dienTich","Diện tích phải lớn hơn 20m2");
        }
        if(matBangRepo.selectMatBangByMaMatBang(matBang.getMaMatBang())!=null) {
            errors.put("maMatBang","Mã mặt bằng đã tồn tại");
        }
        if(Regex.dateCompare(matBang.getNgayBatDau(),matBang.getNgayKetThuc()) <180) {
            errors.put("ngay","Thời gian thuê chưa đúng");
        }

        if(errors.isEmpty()) {
            matBangRepo.createNewMatBang(matBang);
        }

        return errors;
    }

    @Override
    public void deleteMatBang(String maMatBang) {
        matBangRepo.deleteMatBang(maMatBang);
    }

    @Override
    public Map<String, String> editMatBang(MatBang matBang) {
        return null;
    }
}
