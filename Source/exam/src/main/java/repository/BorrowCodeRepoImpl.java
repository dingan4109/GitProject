package repository;

import model.Book;
import model.BorrowCode;
import model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BorrowCodeRepoImpl implements BorrowCodeRepo {
    private static final String SELECT_ALL_BORROW_CODES = " SELECT * FROM borrow_code ";
    private static final String SELECT_ALL_BOOK = " SELECT * FROM book ";
    private static final String SELECT_ALL_STUDENT = " SELECT * FROM student ";
    private static final String CREATE_BORROW_CODE = " INSERT INTO borrow_code (borrow_code,book_id,student_id," +
            "borrow_start_date,borrow_end_date)" +
            " " +
            "VALUES(?,?,?,?,?) ";
    private static final String UPDATE_QUANTITY = " UPDATE book SET book_quantity = ? WHERE book_id = ? ";
    @Override
    public List<BorrowCode> selectAllBorrowCodes() {
        List<BorrowCode> borrowCodeList = new ArrayList<>();

        try (Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BORROW_CODES);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String borrowCode = rs.getString(1);
                int bookId = rs.getInt(2);
                int studentId = rs.getInt(3);
                int status = rs.getInt(4);
                String borrowStartDate = rs.getString(5);
                String borrowEndDate = rs.getString(6);

                BorrowCode borrowCodeObject = new BorrowCode(borrowCode, bookId, studentId, status, borrowStartDate,
                        borrowEndDate);
                borrowCodeList.add(borrowCodeObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowCodeList;
    }

    @Override
    public List<Book> selectAllBooks() {
        List<Book> bookList = new ArrayList<>();

        try (Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOK);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int bookId = rs.getInt(1);
                String bookName = rs.getString(2);
                String bookAuthor = rs.getString(3);
                String description = rs.getString(4);
                int bookQuantity = rs.getShort(5);

                Book book = new Book(bookId, bookName, bookAuthor, description, bookQuantity);
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public List<Student> selectAllStudents() {
        List<Student> studentList = new ArrayList<>();

        try (Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENT);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int studentId = rs.getInt(1);
                String studentName = rs.getString(2);
                String className = rs.getString(3);

                Student student = new Student(studentId,studentName,className);
                studentList.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return studentList;
    }

    @Override
    public void createBorrowCode(BorrowCode borrowCode) {
        try(Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_BORROW_CODE);
            preparedStatement.setString(1,borrowCode.getBorrowCode());
            preparedStatement.setInt(2,borrowCode.getBookId());
            preparedStatement.setInt(3,borrowCode.getStudentId());
            preparedStatement.setString(4,borrowCode.getBorrowStartDate());
            preparedStatement.setString(5,borrowCode.getBorrowEndDate());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setUpdateQuantity(int newQuantity,int bookId) {
        try (Connection connection = ConnectionObject.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUANTITY);
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, bookId);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
