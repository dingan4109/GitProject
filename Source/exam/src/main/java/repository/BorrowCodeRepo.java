package repository;

import model.Book;
import model.BorrowCode;
import model.Student;

import java.util.List;

public interface BorrowCodeRepo {
    List<BorrowCode> selectAllBorrowCodes();
    List<Book> selectAllBooks();
    List<Student> selectAllStudents();
    void createBorrowCode(BorrowCode borrowCode);
    void setUpdateQuantity(int newQuantity,int bookId);

}
