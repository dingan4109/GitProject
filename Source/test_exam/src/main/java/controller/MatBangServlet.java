package controller;

import model.LoaiMatBang;
import model.MatBang;
import model.TrangThai;
import repository.*;
import service.MatBangService;
import service.MatBangServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "MatBangServlet",urlPatterns = "/servlet")
public class MatBangServlet extends HttpServlet {
    MatBangService matBangService = new MatBangServiceImpl();
    TrangThaiRepo trangThaiRepo = new TrangThaiRepoImpl();
    LoaiMatBangRepo loaiMatBangRepo = new LoaiMatBangRepoImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createMatBang(request,response);
                break;
            case "delete":
                delMatBang(request,response);
                break;
        }

    }

    private void delMatBang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String maMatBang = request.getParameter("maMatBangXoa");
        MatBang matBang = matBangService.selectMatBangByMaMatBang(maMatBang);

        if(matBang==null) {
            List<MatBang> matBangList = matBangService.selectAllMatBang();
            List<LoaiMatBang>  loaiMatBangList = loaiMatBangRepo.selectAllLoaiMatBang();
            List<TrangThai> trangThaiList = trangThaiRepo.selectAllTrangThai();

            request.setAttribute("matBangList",matBangList);
            request.setAttribute("loaiMatBangList",loaiMatBangList);
            request.setAttribute("trangThaiList",trangThaiList);
            request.setAttribute("mess","Mã mặt bằng không tồn tại");
            request.getRequestDispatcher("list.jsp").forward(request,response);
        }else {
            matBangService.deleteMatBang(maMatBang);
            try {
                response.sendRedirect("/servlet");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createMatBang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String maMatBang = request.getParameter("maMatBang");
        int trangThai = Integer.parseInt(request.getParameter("trangThai"));
        double dienTich = Double.parseDouble(request.getParameter("dienTich"));
        int soTang = Integer.parseInt(request.getParameter("soTang"));
        int loaiMatBang = Integer.parseInt(request.getParameter("loaiMatBang"));
        double giaTien = Double.parseDouble(request.getParameter("giaTien"));
        String ngayBatDau = request.getParameter("ngayBatDau");
        String ngayKetThuc = request.getParameter("ngayKetThuc");
        MatBang matBang = new MatBang(maMatBang,trangThai,dienTich,soTang,loaiMatBang,giaTien,ngayBatDau,ngayKetThuc);

        Map<String,String> errors = matBangService.createNewMatBang(matBang);
        if(errors.isEmpty()) {
            matBangService.createNewMatBang(matBang);
            response.sendRedirect("/servlet");
        }else{
            List<LoaiMatBang>  loaiMatBangList = loaiMatBangRepo.selectAllLoaiMatBang();
            List<TrangThai> trangThaiList = trangThaiRepo.selectAllTrangThai();

            request.setAttribute("matBang",matBang);
            request.setAttribute("loaiMatBangList",loaiMatBangList);
            request.setAttribute("trangThaiList",trangThaiList);
            request.setAttribute("errors", errors);
            request.getRequestDispatcher("create.jsp").forward(request,response);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }

            switch (action) {
                case "create":
                    createForm(request,response);
                    break;
                case "search":
                    searchMatBang(request,response);
                    break;
                default:
                    matBangList(request,response);
            }
        }

    private void searchMatBang(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String giaTien = request.getParameter("giaTien");
        String soTang = request.getParameter("soTang");
        String maLoaiMatBang = request.getParameter("loaiMatBang");

        List<MatBang> matBangList = matBangService.search(giaTien,soTang,maLoaiMatBang);
        List<LoaiMatBang>  loaiMatBangList = loaiMatBangRepo.selectAllLoaiMatBang();
        List<TrangThai> trangThaiList = trangThaiRepo.selectAllTrangThai();

        request.setAttribute("matBangList",matBangList);
        request.setAttribute("loaiMatBangList",loaiMatBangList);
        request.setAttribute("trangThaiList",trangThaiList);

        request.getRequestDispatcher("list.jsp").forward(request,response);
    }

    private void createForm(HttpServletRequest request, HttpServletResponse response) {
        List<LoaiMatBang>  loaiMatBangList = loaiMatBangRepo.selectAllLoaiMatBang();
        List<TrangThai> trangThaiList = trangThaiRepo.selectAllTrangThai();

        request.setAttribute("loaiMatBangList",loaiMatBangList);
        request.setAttribute("trangThaiList",trangThaiList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("create.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void matBangList(HttpServletRequest request, HttpServletResponse response) {
        List<MatBang> matBangList = matBangService.selectAllMatBang();
        List<LoaiMatBang>  loaiMatBangList = loaiMatBangRepo.selectAllLoaiMatBang();
        List<TrangThai> trangThaiList = trangThaiRepo.selectAllTrangThai();

        request.setAttribute("matBangList",matBangList);
        request.setAttribute("loaiMatBangList",loaiMatBangList);
        request.setAttribute("trangThaiList",trangThaiList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("list.jsp");
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
