package service;

import model.Book;
import model.BorrowCode;
import model.Student;
import repository.BorrowCodeRepo;
import repository.BorrowCodeRepoImpl;
import util.Validate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BorrowCodeServiceImpl implements BorrowCodeService{
    BorrowCodeRepo borrowCodeRepo = new BorrowCodeRepoImpl();
    @Override
    public List<BorrowCode> selectAllBorrowCodes() {
        return borrowCodeRepo.selectAllBorrowCodes();
    }

    @Override
    public List<Book> selectAllBooks() {
        return borrowCodeRepo.selectAllBooks();
    }

    @Override
    public List<Student> selectAllStudents() {
        return borrowCodeRepo.selectAllStudents();
    }

    @Override
    public Map<String,String> createBorrowCode(BorrowCode borrowCode) {
        Map<String,String> errors = new HashMap<>();
        if(!Validate.validateBorrowCode(borrowCode.getBorrowCode())) {
            errors.put("borrowCode","Mã mượn sách sai");
        }
        int validateDate = Validate.compare(borrowCode.getBorrowStartDate(),borrowCode.getBorrowEndDate());
        if(validateDate == 1) {
            errors.put("date", "Ngày trả sách phải sau ngày mượn sách");
        }
        if(borrowCode.getCurrentQuantity()<1) {
            errors.put("bookName","Đã hết sách");
        }
        if(errors.isEmpty()) {
            borrowCodeRepo.createBorrowCode(borrowCode);
        }

        return errors;
    }

    @Override
    public void setUpdateQuantity(int newQuantity, int bookId) {
        borrowCodeRepo.setUpdateQuantity(newQuantity,bookId);
    }
}
