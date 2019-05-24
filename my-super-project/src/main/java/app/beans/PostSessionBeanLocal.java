package app.beans;

import app.entities.DataBase;
import app.entities.Department;
import app.entities.Employee;
import app.entities.Post;

import javax.ejb.Local;
import java.io.File;
import java.util.ArrayList;

@Local
public interface PostSessionBeanLocal {
//    void addBook(String bookName);
    String getStr();
    ArrayList<Post> getBeans();
    void editPost(Post post, String action);
    void editEmployee(Employee employee, String action);
    void editDepartment(Department department, String action);
    ArrayList<Employee> getEmployees();
    ArrayList<Department> getDepartments();
    <T extends Object> void exportDB(T db, String s);
    <T extends Object> File convertToXml(T db);
    <T extends Object> T importFromXml(String filePath);
    void exportDBT(DataBase db);
    void exportPost(Post post);
    void exportDepartment(Department department);
    void exportEmployee(Employee employee);
    void exportDataBase(DataBase db);
//    void exportBean(Post post);
//    void exportDB(DataBase db);
}
