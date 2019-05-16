package app.beans;

import app.entities.DataBase;
import app.entities.Department;
import app.entities.Employee;
import app.entities.Post;
import app.scripts.NewClassScripts;

import javax.ejb.Stateless;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

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

    public ArrayList<Employee> getEmployees(){
//        ArrayList<BeanPost> beanPosts = new ArrayList<BeanPost>();

        ArrayList<Employee> employees = new ArrayList<Employee>();
        NewClassScripts nc = new NewClassScripts();
        employees = nc.selectEmployees();

        return employees;
    }

    public ArrayList<Department> getDepartments(){
//        ArrayList<BeanPost> beanPosts = new ArrayList<BeanPost>();

        ArrayList<Department> departments = new ArrayList<Department>();
        NewClassScripts nc = new NewClassScripts();
        departments = nc.selectDepartments();

        return departments;
    }

//    public void exportBean(Post post){
//        // определяем название файла, куда будем сохранять
//        String fileName ="C:/Users/gamer/Desktop/xml_файлы_NetCracker/posts.xml";
//
//
//        //создаем объект Student с какими-то данными
//        Post post1 = new Post(1,"Bob",199,10);
////        student.setId(1);
////        student.setAge(21);
////        student.setName("Andrew");
////        student.setLanguage("Java");
////        student.setPassword("simplepassword");
//
//
//        // сохраняем объект в XML файл
//        convertObjectToXml(post, fileName);
//
//        // восстанавливаем объект из XML файла
//        Post unmarshPost = fromXmlToObject(fileName);
//        if (unmarshPost != null) {
//            System.out.println("Ноль?");
//            System.out.println(unmarshPost.toString());
//        }
//
////        File f = File(fileName);
//
////        System.out.println(unmarshPost.getName());
//
//    }

//    public void exportDB(DataBase db){
//        // определяем название файла, куда будем сохранять
//        String fileName ="C:/Users/gamer/Desktop/xml_файлы_NetCracker/db.xml";
//
//        // сохраняем объект в XML файл
//        convertObjectToXml(db, fileName);
//
//        // восстанавливаем объект из XML файла
//        Post unmarshPost = fromXmlToObject(fileName);
//        if (unmarshPost != null) {
//            System.out.println("Ноль?");
//            System.out.println(unmarshPost.toString());
//        }
//
//        //        File f = File(fileName);
//
//        //        System.out.println(unmarshPost.getName());
//
//    }

    public void exportPost(Post post){
        exportDB(post, "post");
    }

    public void exportDepartment(Department department){
        exportDB(department, "department");
    }

    public void exportEmployee(Employee employee){
        exportDB(employee, "employee");
    }

    public void exportDataBase(DataBase db){
        exportDB(db, "database");
    }

    public <T extends Object> void exportDB(T db, String s){
        // определяем название файла, куда будем сохранять
        String fileName ="C:/Users/gamer/Desktop/xml_файлы_NetCracker/"+s+".xml";

        // сохраняем объект в XML файл
        convertObjectToXml(db, fileName);

        // восстанавливаем объект из XML файла
        T unmarshPost = fromXmlToObject(fileName);
        if (unmarshPost != null) {
            System.out.println("Ноль?");
            System.out.println(unmarshPost.toString());
        }
    }

    // восстанавливаем объект из XML файла
    private static <T extends Object> T fromXmlToObject(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(Post.class, Employee.class, Department.class, DataBase.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (T) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // сохраняем объект в XML файл
    private static <T extends Object> void convertObjectToXml(T student, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance( Post.class, Employee.class, Department.class, DataBase.class );
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(student, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }


    ////////////////////////////////////////////////////////////////
    //тут тестят, не лезть!!
    ////////////////////////////////////////////////////////////////

    public void exportDBT(DataBase db){
        // определяем название файла, куда будем сохранять
        String fileName ="C:/Users/gamer/Desktop/xml_файлы_NetCracker/testT.xml";

        // сохраняем объект в XML файл
//        convertObjectToXmlT(db, fileName);

        // восстанавливаем объект из XML файла
//        DataBase unmarshPost = fromXmlToObjectT(fileName);
//        if (unmarshPost != null) {
//            System.out.println("Ноль?");
//            System.out.println(unmarshPost.toString());
//        }

        //писать результат сериализации будем во Writer(StringWriter)
        StringWriter writer = new StringWriter();

        //создание объекта Marshaller, который выполняет сериализацию
        try {


            JAXBContext context = JAXBContext.newInstance(Post.class, Employee.class, Department.class, DataBase.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            // самосериализация
            marshaller.marshal(db, writer);

            //преобразовываем все записанное в StringWriter в строку
            System.out.println(writer.toString());
        }
        catch (Exception e){}

    }

    // восстанавливаем объект из XML файла
    private static DataBase fromXmlToObjectT(String filePath) {
        try {
            // создаем объект JAXBContext - точку входа для JAXB
            JAXBContext jaxbContext = JAXBContext.newInstance(DataBase.class, Post.class, Employee.class, Department.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();

            return (DataBase) un.unmarshal(new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    // сохраняем объект в XML файл
    private static void convertObjectToXmlT(DataBase student, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(DataBase.class, Post.class, Employee.class, Department.class);
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(student, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}
