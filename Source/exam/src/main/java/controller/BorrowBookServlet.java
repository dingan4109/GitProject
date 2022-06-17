package controller;

import model.Book;
import model.BorrowCode;
import model.Student;
import service.BorrowCodeService;
import service.BorrowCodeServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ObjectAServlet",urlPatterns = "/servlet")
public class BorrowBookServlet extends HttpServlet {
    BorrowCodeService borrowCodeService = new BorrowCodeServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "borrowBook":
                borrowBook(request,response);
                break;
            case "delete":

                break;
            case "edit":

                break;
        }
    }

    private void borrowBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<Book> bookList = borrowCodeService.selectAllBooks();
        String borrowCode = request.getParameter("borrowCode");
        String bookName = request.getParameter("bookName");
        int bookId = 0;
        int currentQuantity = 0;
        for(int i=0;i<bookList.size();i++) {
            if(bookList.get(i).getBookName().equals(bookName)) {
                bookId = bookList.get(i).getBookId();
                currentQuantity = bookList.get(i).getBookQuantity();
            }
        }

        int studentId = Integer.parseInt(request.getParameter("studentName"));
        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");

        BorrowCode borrowCodeObject = new BorrowCode(borrowCode,bookId,studentId,startDate,endDate,currentQuantity);
        Map<String,String> errors = borrowCodeService.createBorrowCode(borrowCodeObject);
        if(errors.isEmpty()) {
            int newQuantity = currentQuantity -1;
           borrowCodeService.setUpdateQuantity(newQuantity,bookId);
//            request.setAttribute("mess", "Mượn sách thành công");
//            request.setAttribute("bookList",bookList);
//            request.getRequestDispatcher("listBook.jsp").forward(request,response);
            response.sendRedirect("/servlet");
        }else{
            List<Student> studentList = borrowCodeService.selectAllStudents();
            request.setAttribute("studentList",studentList);

            request.setAttribute("bookName",bookName);
            request.setAttribute("mess", "Mượn sách thất bại");
            request.setAttribute("startDate", startDate);
            request.setAttribute("borrowCode", borrowCode);
            request.setAttribute("errors",errors);
            request.getRequestDispatcher("borrowBookForm.jsp").forward(request,response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "borrowBook":
                borrowBookForm(request,response);
                break;
            case "viewBorrowCodeList":
                viewBorrowCodeList(request,response);
            case "edit":

                break;
            default:
                viewBookList(request,response);
                break;
        }
    }

    private void viewBorrowCodeList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BorrowCode> borrowCodes = borrowCodeService.selectAllBorrowCodes();
        List<Student> studentList = borrowCodeService.selectAllStudents();
        List<Book> bookList = borrowCodeService.selectAllBooks();

        request.setAttribute("borrowCodes",borrowCodes);
        request.setAttribute("studentList",studentList);
        request.setAttribute("bookList",bookList);
        request.getRequestDispatcher("listBorrowCode.jsp").forward(request,response);

    }

    private void borrowBookForm(HttpServletRequest request, HttpServletResponse response) {
        List<Student> studentList = borrowCodeService.selectAllStudents();
        String bookName = request.getParameter("bookName");
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        request.setAttribute("studentList",studentList);
        request.setAttribute("bookName",bookName);
        request.setAttribute("today",today);


        try {
            request.getRequestDispatcher("borrowBookForm.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewBookList(HttpServletRequest request, HttpServletResponse response) {
        List<Book> bookList = borrowCodeService.selectAllBooks();

        request.setAttribute("bookList",bookList);

        try {
            request.getRequestDispatcher("listBook.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

