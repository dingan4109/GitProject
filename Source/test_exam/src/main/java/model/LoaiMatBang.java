package model;

public class LoaiMatBang {
    private int maLoaiMatBang;
    private String tenLoaiMatBang;

    public LoaiMatBang() {
    }

    public LoaiMatBang(int maLoaiMatBang, String tenLoaiMatBang) {
        this.maLoaiMatBang = maLoaiMatBang;
        this.tenLoaiMatBang = tenLoaiMatBang;
    }

    public int getMaLoaiMatBang() {
        return maLoaiMatBang;
    }

    public void setMaLoaiMatBang(int maLoaiMatBang) {
        this.maLoaiMatBang = maLoaiMatBang;
    }

    public String getTenLoaiMatBang() {
        return tenLoaiMatBang;
    }

    public void setTenLoaiMatBang(String tenLoaiMatBang) {
        this.tenLoaiMatBang = tenLoaiMatBang;
    }
}
