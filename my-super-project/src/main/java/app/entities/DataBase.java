package app.entities;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType(name = "db")
@XmlRootElement
public class DataBase {
    @XmlElementWrapper(name="posts", nillable = true)
    public List posts = new ArrayList<Post>();

    @XmlElementWrapper(name="employees", nillable = true)
    public List employees = new ArrayList<Employee>();

    @XmlElementWrapper(name="departments", nillable = true)
    public List departments = new ArrayList<Department>();

    @Override
    public String toString() {
        return "DataBase{" +
                "posts=" + posts +
                ", employees=" + employees +
                ", departments=" + departments +
                '}';
    }
}
