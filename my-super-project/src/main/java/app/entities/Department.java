package app.entities;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

// определяем корневой элемент
@XmlRootElement(name = "Department")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"name", "address"})
public class Department {
    private int departmentId;
    private String name;
    private String address;

    public Department(){
    }

    public Department(int departmentId, String name, String address) {
        this.departmentId = departmentId;
        this.name = name;
        this.address = address;
    }

    // указываем, что id должно быть атрибутом
    @XmlAttribute
    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Department)) return false;
        Department that = (Department) o;
        return getDepartmentId() == that.getDepartmentId() &&
                getName().equals(that.getName()) &&
                Objects.equals(getAddress(), that.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDepartmentId(), getName(), getAddress());
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
