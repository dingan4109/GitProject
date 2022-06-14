package model;

public class TrangThai {
    private int maTrangThai;
    private String tenTrangThai;

    public TrangThai() {
    }

    public TrangThai(int maTrangThai, String tenTrangThai) {
        this.maTrangThai = maTrangThai;
        this.tenTrangThai = tenTrangThai;
    }

    public int getMaTrangThai() {
        return maTrangThai;
    }

    public void setMaTrangThai(int maTrangThai) {
        this.maTrangThai = maTrangThai;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public void setTenTrangThai(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }


}
