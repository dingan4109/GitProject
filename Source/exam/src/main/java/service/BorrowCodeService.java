package service;

import model.Book;
import model.BorrowCode;
import model.Student;

import java.util.List;
import java.util.Map;

public interface BorrowCodeService {
    List<BorrowCode> selectAllBorrowCodes();
    List<Book> selectAllBooks();
    List<Student> selectAllStudents();
    Map<String,String> createBorrowCode(BorrowCode borrowCode);
    void setUpdateQuantity(int newQuantity,int bookId);
}
