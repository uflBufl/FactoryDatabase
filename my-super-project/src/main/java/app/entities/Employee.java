package app.entities;

import java.util.Objects;

public class Employee {
    private int employeeId;
    private String name;
    private String email;
    private String address;
    private int postId;
    private int departmentId;
    private int head;

    public Employee(int employeeId, String name, String email, String address, int postId, int departmentId, int head) {
        this.employeeId = employeeId;
        this.name = name;
        this.email = email;
        this.address = address;
        this.postId = postId;
        this.departmentId = departmentId;
        this.head = head;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return getEmployeeId() == employee.getEmployeeId() &&
                getPostId() == employee.getPostId() &&
                getDepartmentId() == employee.getDepartmentId() &&
                getHead() == employee.getHead() &&
                getName().equals(employee.getName()) &&
                Objects.equals(getEmail(), employee.getEmail()) &&
                Objects.equals(getAddress(), employee.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmployeeId(), getName(), getEmail(), getAddress(), getPostId(), getDepartmentId(), getHead());
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", postId=" + postId +
                ", departmentId=" + departmentId +
                ", head=" + head +
                '}';
    }
}
