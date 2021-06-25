package sample.others;

import Model.Permission;
import okhttp3.*;
import okio.Buffer;
import org.jetbrains.annotations.NotNull;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;

public class AuthenticationClient implements Interceptor {

    private MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request origin = chain.request();
        //Request rewrite = origin;
        Response response = null;

        //check that is user guest?
        String dataInFile = FileWorking.readFromFile();
        boolean isGuest = false;
        if(dataInFile.equals("{}"))
            isGuest = true;

        //check that the method user want to do is CRUD?
        boolean sensitiveMethod = true;
        if(origin.method().equals("GET"))
            sensitiveMethod = false;

        //get url which user want to visit
        String url = origin.url().toString();
        System.out.println(url);
        //if current user is guest but no visit to register and want to make a CRUD
        //change the url to the another resource
//        if(isGuest && !url.contains("register") && crudMethod){
//            System.out.println("redirect");
//            origin.newBuilder().url("http://localhost:8080/LibraryRestAPI/webapi/redirect").get().build();
//            response = chain.proceed(origin);
//            return response;
//        }
//        if(isGuest && !crudMethod) {
//            System.out.println("guest");
//            origin = origin.newBuilder().header("Authorization", "guest").build();
//            response = chain.proceed(origin);
//            return response;
//        }
//        if(isGuest && url.contains("register")){
//            System.out.println("register");
//            origin = origin.newBuilder().header("Authorization", "register").build();
//            response = chain.proceed(origin);
//            return response;
//        }
//        if(url.contains("login")){
//            System.out.println("login");
//            origin = origin.newBuilder().header("Authorization", "login").build();
//            return chain.proceed(origin);
//        }
        if(isGuest){
            if(url.contains("login")){
                System.out.println("login");
                origin = origin.newBuilder().header("Authorization", "login").build();
            }
            else if(url.contains("register")){
                System.out.println("register");
                origin = origin.newBuilder().header("Authorization", "register").build();
            }
            else{
                if(sensitiveMethod){
                    origin = origin.newBuilder().header("Authorization", "dangerous").build();
                }
                else{
                    origin = origin.newBuilder().header("Authorization", "guest").build();
                }
            }
            return chain.proceed(origin);
        }
        JSONObject bodyReRequest = new JSONObject();
        String token = null;
        try {
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(dataInFile);
            token = object.get("token").toString();
            System.out.println(token);

            if(!sensitiveMethod){
                System.out.println("Set token for method GET");
                Request remake = origin.newBuilder().header("Authorization", "Token " + token).build();
                System.out.println("Completely set token for method GET " + remake.url().toString());
                return chain.proceed(remake);
            }

//            System.out.println(bodyToString(origin));

            bodyReRequest = (JSONObject) parser.parse(bodyToString(origin));

            object = (JSONObject) parser.parse(object.get("permission").toString());
            Permission permission = new Permission(
                    Integer.parseInt(object.get("id").toString()),
                    object.get("position").toString()
            );

            bodyReRequest.put("permission", object);
            System.out.println(bodyReRequest);
            //rewrite = origin.newBuilder().header("Authorization", "Token " + token).build();
//            RequestBody newbody = RequestBody.create(bodyReRequest.toString(), jsonType);
//            Request remake = new Request.Builder().header("Authorization", "Token " + token).url(url).method(origin.method(), newbody).build();
//            return chain.proceed(remake);
        }catch (Exception e){
            System.out.println("Error in write to get permission: " + e.getMessage());
            e.printStackTrace();
        }
        RequestBody newBody = RequestBody.create(bodyReRequest.toString(), jsonType);
        Request remake = new Request.Builder().header("Authorization", "Token " + token).url(url).method(origin.method(), newBody).build();
        return chain.proceed(remake);
    }
    private String bodyToString(final Request request){

        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            copy.body().writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }
}
