package app.beans;

import app.entities.Post;
import app.scripts.NewClassScripts;

import javax.ejb.Stateless;
import java.util.ArrayList;

@Stateless
public class PostSessionBean implements PostSessionBeanLocal {

    public PostSessionBean(){};

    public String getStr(){
        return "Примерчик строки";
    }

    public ArrayList<Post> getBeans(){
//        ArrayList<BeanPost> beanPosts = new ArrayList<BeanPost>();

        ArrayList<Post> posts = new ArrayList<Post>();
        NewClassScripts nc = new NewClassScripts();
        posts = nc.selectPosts();

        return posts;
    }
}
