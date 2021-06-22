package sample.others;

import Model.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ModelParse {
    public static Author getAuthor(String jsonString){
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);

            String name;
            int id, age, numberbook;
            boolean gender;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            name =  object.get("name") == null ? "" : object.get("name").toString();
            age = object.get("age") == null ? 0 : Integer.parseInt(object.get("age").toString());
            numberbook = object.get("numberbook") == null ? 0 : Integer.parseInt(object.get("numberbook").toString());
            gender = object.get("gender") == null ? false : true;

            Author author = new Author(id, name, age, numberbook, gender);
            return author;
        } catch (Exception e) {
            System.out.println("Error in parser author: " + e.getMessage() + e.getCause());
            return null;
        }
    }
    public static Category getCategory(String jsonString){
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);

            String name, description;
            int id;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            name =  object.get("name") == null ? "" : object.get("name").toString();
            description =  object.get("description") == null ? "" : object.get("description").toString();

            Category category = new Category(id, name, description);

            return category;
        } catch (Exception e) {
            System.out.println("Error in parser category: " + e.getMessage() + e.getCause());
            return null;
        }
    }
    public static Book getBook(String jsonString){
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);

            int id, numberPage;
            String name, image;
            Author author;
            Category category;
            boolean available;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            name =  object.get("name") == null ? "" : object.get("name").toString();
            image = object.get("image") == null ? "" : object.get("image").toString();
            author = getAuthor(object.get("author").toString());
            category = getCategory(object.get("category").toString());
            numberPage = object.get("numberpage") == null ? 0 : Integer.parseInt(object.get("id").toString());
            available = object.get("available") == null ? false : true;

            Book book = new Book(id, name, author, category, image, numberPage, available);

            return book;
        }catch (Exception e) {
            System.out.println("Error in parser book: " + e.getMessage() + e.getCause());
            return null;
        }
    }
    public static  Permission getPermissionModel(String jsonString){
        try{
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);
            int id;
            String position;
            boolean addbook, editBk, delBk, validAcc, refreshAcc, authenBr, registerEmpAcc, delEmp, borrow;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            position = object.get("position") == null ? "" : object.get("position").toString();
            addbook = object.get("editBk") == null ? false : true;
            editBk = object.get("delBk") == null ? false : true;
            delBk = object.get("available") == null ? false : true;
            validAcc = object.get("validAcc") == null ? false : true;
            refreshAcc = object.get("refreshAcc") == null ? false : true;
            authenBr = object.get("authenBr") == null ? false : true;
            registerEmpAcc = object.get("registerEmpAcc") == null ? false : true;
            delEmp = object.get("delEmp") == null ? false : true;
            borrow = object.get("borrow") == null ? false : true;

            Permission permission = new Permission(id, position, addbook, editBk, delBk, validAcc, refreshAcc, authenBr, registerEmpAcc, delEmp, borrow);
            return permission;
        }catch (Exception e){
            System.out.println("Error in parser permission: " + e.getMessage() + e.getCause());
            return null;
        }
    }

    public static Account getAccount(String jsonString){
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);

            int id, age, dateLeft;
            String username, password, realname, code, token;
            Permission permission;
            Date register, expiration;
            boolean gender;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            username =  object.get("username") == null ? "" : object.get("username").toString();
            password = object.get("userpassword") == null ? "" : object.get("userpassword").toString();
            permission = getPermissionModel(jsonString);
            realname = object.get("realname") == null ? "" : object.get("realname").toString();
            code = object.get("secretcode") == null ? "" : object.get("secretcode").toString();
            token = object.get("token") == null ? "" : object.get("token").toString();
            register = getDate(object.get("register").toString());
            expiration = getDate(object.get("expiration").toString());
            gender = object.get("gender") == null ? false : true;
            age = object.get("age") == null ? 0 : Integer.parseInt(object.get("age").toString());
            dateLeft = object.get("dateleft") == null ? 0 : Integer.parseInt(object.get("dateleft").toString());

            Account account = new Account(id, permission, username, password, realname, age, gender, code, register, expiration);

            return account;
        }catch (Exception e) {
            System.out.println("Error in parser book: " + e.getMessage() + e.getCause());
            return null;
        }
    }
    public static Date getDate(String date) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(date);
            int year, month, day;

            year = object.get("year") == null ? 0 : Integer.parseInt(object.get("year").toString());
            month = object.get("month") == null ? 0 : Integer.parseInt(object.get("month").toString());
            day = object.get("day") == null ? 0 : Integer.parseInt(object.get("day").toString());

            Date datee = new Date(year, month, day);
            return datee;

        }catch (Exception e) {
            System.out.println("Error in parser book: " + e.getMessage() + e.getCause());
            return null;
        }
    }
}
