package repository;

import model.LoaiMatBang;

import java.util.List;

public interface LoaiMatBangRepo {
    List<LoaiMatBang> selectAllLoaiMatBang();
}
