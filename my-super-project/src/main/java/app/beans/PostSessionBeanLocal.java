package app.beans;

import app.entities.Post;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface PostSessionBeanLocal {
//    void addBook(String bookName);
    String getStr();
    ArrayList<Post> getBeans();
}
