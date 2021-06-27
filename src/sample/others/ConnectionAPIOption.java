package sample.others;

import okhttp3.OkHttpClient;

public class ConnectionAPIOption {

    public static String bookURL = "http://localhost:8080/LibraryRestAPI/webapi/books/";
    public static String authorURL = "http://localhost:8080/LibraryRestAPI/webapi/authors/";
    public static String categoryURL = "http://localhost:8080/LibraryRestAPI/webapi/categories/";
    public static String stocksURL = "http://localhost:8080/LibraryRestAPI/webapi/stockbooks/";
    public static String stocksFilterBookURL = "http://localhost:8080/LibraryRestAPI/webapi/stockbooks/book/";
    public static String borrowURL = "http://localhost:8080/LibraryRestAPI/webapi/borrows/";
    public static String detailBorrowsURL = "http://localhost:8080/LibraryRestAPI/webapi/borrows/details/";
    public static String inputURL = "http://localhost:8080/LibraryRestAPI/webapi/inputs/";
    public static String detailInputURL = "http://localhost:8080/LibraryRestAPI/webapi/inputs/details/";
    public static String borrowRequestURL = "http://localhost:8080/LibraryRestAPI/webapi/borrowrequests/";
    public static String detailBorrowRequestURL = "http://localhost:8080/LibraryRestAPI/webapi/borrowrequests/details/";
    public static String qualityURL = "http://localhost:8080/LibraryRestAPI/webapi/quality/";
    public static String accountURL = "http://localhost:8080/LibraryRestAPI/webapi/accounts/";
    public static String permissionURL = "http://localhost:8080/LibraryRestAPI/webapi/permissions/";

    public static String imageURL = "http://localhost:8080/libraryapi/webapi/images/";

    public static OkHttpClient getClient(){
        return new OkHttpClient.Builder().addInterceptor(new AuthenticationClient()).build();
    }
}
