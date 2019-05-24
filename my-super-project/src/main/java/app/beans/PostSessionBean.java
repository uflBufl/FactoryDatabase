package app.beans;

import app.entities.DataBase;
import app.entities.Department;
import app.entities.Employee;
import app.entities.Post;
import app.scripts.DepartmentScripts;
import app.scripts.EmployeeScripts;
import app.scripts.NewClassScripts;
import app.scripts.PostScripts;

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

    public void editPost(Post post, String action){

        System.out.println("Start Edit");

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = post.getName();
        if (!(nameFilterString.equals(""))) {
            args.add("name");
            params.add(nameFilterString);
        }
        String salaryFilterString = String.valueOf(post.getSalary());
        if (!(salaryFilterString.equals(""))) {
            args.add("salary");
            params.add(salaryFilterString);
        }
        String timeFilterString = String.valueOf(post.getTime());
        if (!(timeFilterString.equals(""))) {
            args.add("time");
            params.add(timeFilterString);
        }


        PostScripts ps = new PostScripts();
        if(action.equals("edit")) {
            String postId = String.valueOf(post.getPostId());
            params.add(postId);
            ps.updatePost(params, args);
        }
        else{
            String idFilterString = String.valueOf(post.getPostId());
            if (!(idFilterString.equals(""))) {
                args.add("postId");
                params.add(idFilterString);
            }
            ps.addPost(params, args);
        }
//        System.out.println(args.get(0));
//        System.out.println(params.get(0));

//        PostScripts ps = new PostScripts();
//        ps.addPost(params, args);
//        ps.updatePost(params, args);
    }




    public void editEmployee(Employee employee, String action){
        System.out.println("Start Edit");

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = employee.getName();
        if (!(nameFilterString.equals(""))) {
            args.add("name");
            params.add(nameFilterString);
        }
        String emailFilterString = employee.getEmail();
        if (!(emailFilterString.equals("") || emailFilterString.equals("null"))) {
            args.add("email");
            params.add(emailFilterString);
        }
        String addressFilterString = employee.getAddress();
        if (!(addressFilterString.equals("") || addressFilterString.equals("null"))) {
            args.add("address");
            params.add(addressFilterString);
        }
        String postIdFilterString = String.valueOf(employee.getPostId());
        if (!(postIdFilterString.equals(""))) {
            args.add("postId");
            params.add(postIdFilterString);
        }
        String departmentIdFilterString = String.valueOf(employee.getDepartmentId());
        if (!(departmentIdFilterString.equals(""))) {
            args.add("departmentId");
            params.add(departmentIdFilterString);
        }
        String headFilterString = String.valueOf(employee.getHead());
        if (!(headFilterString.equals("") || headFilterString.equals("0"))) {
            args.add("head");
            params.add(headFilterString);
        }


        EmployeeScripts es = new EmployeeScripts();
        if(action.equals("edit")) {
            String employeeId = String.valueOf(employee.getEmployeeId());
            params.add(employeeId);
            es.updateEmployee(params, args);
        }
        else{
            String idFilterString = String.valueOf(employee.getEmployeeId());
            if (!(idFilterString.equals(""))) {
                args.add("employeeId");
                params.add(idFilterString);
            }
            es.addEmployee(params, args);
        }
    }




    public void editDepartment(Department department, String action){
        System.out.println("Start Edit");

        ArrayList<String> args = new ArrayList<String>();
        ArrayList<String> params = new ArrayList<String>();

        String nameFilterString = department.getName();
        if (!(nameFilterString.equals(""))) {
            args.add("name");
            params.add(nameFilterString);
        }
        String addressFilterString = department.getAddress();
        if (!(addressFilterString.equals("") || addressFilterString.equals("null"))) {
            args.add("address");
            params.add(addressFilterString);
        }


        DepartmentScripts ds = new DepartmentScripts();
        if(action.equals("edit")) {
            String departmentId = String.valueOf(department.getDepartmentId());
            params.add(departmentId);
            ds.updateDepartment(params, args);
        }
        else{
            String idFilterString = String.valueOf(department.getDepartmentId());
            if (!(idFilterString.equals(""))) {
                args.add("departmentId");
                params.add(idFilterString);
            }
            ds.addDepartment(params, args);
        }
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

    public <T extends Object> File convertToXml(T db){

        File f = new File("test.xml");

        try {
            JAXBContext context = JAXBContext.newInstance( Post.class, Employee.class, Department.class, DataBase.class );
            Marshaller marshaller = context.createMarshaller();
            // устанавливаем флаг для читабельного вывода XML в JAXB
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            // маршаллинг объекта в файл
            marshaller.marshal(db,  f);
//            return f;
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return f;
    }

    public <T extends Object> T importFromXml(String filePath){

        // восстанавливаем объект из XML файла
        T unmarshPost = fromXmlToObject(filePath);
        if (unmarshPost != null) {
            System.out.println("Ноль?");
            System.out.println(unmarshPost.toString());
        }
        return unmarshPost;
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
