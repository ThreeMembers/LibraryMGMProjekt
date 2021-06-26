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
            gender = object.get("gender") == null ? false : Boolean.parseBoolean(object.get("gender").toString());

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
            author = object.get("author") == null ? null : getAuthor(object.get("author").toString());
            category = object.get("category") == null ? null : getCategory(object.get("category").toString());
            numberPage = object.get("numberpage") == null ? 0 : Integer.parseInt(object.get("id").toString());
            available = object.get("available") == null ? false : Boolean.parseBoolean(object.get("available").toString());

            Book book = new Book(id, name, author, category, image, numberPage, available);

            return book;
        }catch (Exception e) {
            e.printStackTrace();
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
            addbook = object.get("addNewBk") == null ? false : Boolean.parseBoolean(object.get("addNewBk").toString());
            editBk = object.get("editBk") == null ? false : Boolean.parseBoolean(object.get("editBk").toString());
            delBk = object.get("delBk") == null ? false : Boolean.parseBoolean(object.get("delBk").toString());
            validAcc = object.get("validAcc") == null ? false : Boolean.parseBoolean(object.get("validAcc").toString());
            refreshAcc = object.get("refreshAcc") == null ? false : Boolean.parseBoolean(object.get("refreshAcc").toString());
            authenBr = object.get("authenBr") == null ? false : Boolean.parseBoolean(object.get("authenBr").toString());
            registerEmpAcc = object.get("registerEmpAcc") == null ? false : Boolean.parseBoolean(object.get("registerEmpAcc").toString());
            delEmp = object.get("delEmp") == null ? false : Boolean.parseBoolean(object.get("delEmp").toString());
            borrow = object.get("borrow") == null ? false : Boolean.parseBoolean(object.get("borrow").toString());

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

//            System.out.println(object);

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            username =  object.get("username") == null ? "" : object.get("username").toString();
            password = object.get("userpassword") == null ? "" : object.get("userpassword").toString();
            permission = getPermissionModel(jsonString);
            realname = object.get("realname") == null ? "" : object.get("realname").toString();
            code = object.get("secretcode") == null ? "" : object.get("secretcode").toString();
            token = object.get("token") == null ? "" : object.get("token").toString();
            register = object.get("register") == null ? null : getDate(object.get("register").toString());
            expiration = object.get("expiration") == null ? null : getDate(object.get("expiration").toString());
            gender = object.get("gender") == null ? false : Boolean.parseBoolean(object.get("gender").toString());
            age = object.get("age") == null ? 0 : Integer.parseInt(object.get("age").toString());
            dateLeft = object.get("dateleft") == null ? 0 : Integer.parseInt(object.get("dateleft").toString());

            Account account = new Account(id, permission, username, password, realname, age, gender, code, register, expiration);

            return account;
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in parser account: " + e.getMessage() + e.getCause());
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
            System.out.println("Error in parser date: " + e.getMessage() + e.getCause());
            return null;
        }
    }
    public static Borrow getBorrowRecord(String content){
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(content);

            int id;
            Date checkDate, returnDate;
            Account employee, reader;
            boolean inprocess;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            checkDate = getDate(object.get("checkdate").toString());
            returnDate = getDate(object.get("returndate").toString());
            employee = getAccount(object.get("employee").toString());
            reader = getAccount(object.get("reader").toString());

            inprocess = object.get("inprocess") == null ? false : Boolean.parseBoolean(object.get("inprocess").toString());

            Borrow borrow = new Borrow(id, reader, employee, checkDate, returnDate, inprocess);
            return borrow;
        }catch (Exception e) {
            System.out.println("Error in parser borrow: " + e.getMessage() + e.getCause());
            return null;
        }
    }

    public static BorrowRequest getBorrowRequest(String jsonString) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);

            int id;
            Date sendDate;
            Account reader;
            boolean isAuthen;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            sendDate = getDate(object.get("senddate").toString());
            reader = getAccount(object.get("reader").toString());
            isAuthen = object.get("authen") == null ? false : Boolean.parseBoolean(object.get("authen").toString());

            BorrowRequest borrow = new BorrowRequest(id, reader, sendDate, isAuthen);
            return borrow;
        }catch (Exception e) {
            System.out.println("Error in parser borrowrequest: " + e.getMessage() + e.getCause());
            return null;
        }
    }

    public static Input getInput(String jsonString) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);

            int id;
            Date inputDate;
            Account employee;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            inputDate = getDate(object.get("inputdate").toString());
            employee = getAccount(object.get("employee").toString());

            Input input = new Input(id, employee, inputDate);
            return input;
        }catch (Exception e) {
            System.out.println("Error in parser Input: " + e.getMessage() + e.getCause());
            return null;
        }
    }

    public static Quality getQuality(String jsonString) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);

            int id;
            String situation, description;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            situation = object.get("situation") == null ? "" : object.get("situation").toString();
            description = object.get("description") == null ? "" : object.get("description").toString();

            Quality quality = new Quality(id, situation, description);
            return quality;
        }catch (Exception e) {
            System.out.println("Error in parser quality: " + e.getMessage() + e.getCause());
            return null;
        }
    }
    public static StockBook getStockBook(String jsonString) {
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(jsonString);

            int id;
            Book collection;
            Quality quality;
            int year;
            boolean isborrow;

            id = object.get("id") == null ? 0 : Integer.parseInt(object.get("id").toString());
            collection = getBook(object.get("collection").toString());
            quality = getQuality(object.get("quality").toString());
            year = object.get("releaseyear") == null ? 0 : Integer.parseInt(object.get("releaseyear").toString());
            isborrow = object.get("isborrow") == null ? false : Boolean.parseBoolean(object.get("isborrow").toString());

            StockBook stockBook = new StockBook(id, collection, quality, year, isborrow);
            return stockBook;
        }catch (Exception e) {
            System.out.println("Error in parser stockbook: " + e.getMessage() + e.getCause());
            return null;
        }
    }
    //sudo apt update
    //sudo apt-get install wireshark
}
