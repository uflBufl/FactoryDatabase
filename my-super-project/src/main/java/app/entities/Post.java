package app.entities;

import javafx.geometry.Pos;

import java.util.Objects;

import javax.xml.bind.annotation.*;

// определяем корневой элемент
@XmlRootElement(name = "Post")
// определяем последовательность тегов в XML
@XmlType(propOrder = {"name", "salary", "time"})
public class Post {
    private int postId;
    private String name;
    private int salary;
    private int time;

    public Post() {
    }

    public Post(int postId, String name, int salary, int time) {
        this.postId = postId;
        this.name = name;
        this.salary = salary;
        this.time = time;
    }

    // указываем, что id должно быть атрибутом
    @XmlAttribute
    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post)) return false;
        Post post = (Post) o;
        return getPostId() == post.getPostId() &&
                getSalary() == post.getSalary() &&
                getTime() == post.getTime() &&
                getName().equals(post.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPostId(), getName(), getSalary(), getTime());
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", time=" + time +
                '}';
    }
}
