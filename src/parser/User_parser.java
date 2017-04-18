/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

//import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import jdbcConnectivity.jdbcConnectivity;
//import model.review;
import model.user;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author VISHAL
 */
public class User_parser {
    
    static String FileName="E:\\MASTERS LECTUR\\Sem 2\\Database Arch\\DB\\YelpDataset\\YelpDataset-CptS451\\yelp_user.json";
    
    public static synchronized List<user> ReadJSON(String Encoding) throws FileNotFoundException, ParseException {
    Scanner scn=new Scanner(new File(FileName),Encoding);
    ArrayList<JSONObject> json=new ArrayList<>();
    int iterate =0;
    List<user> uObjList = new ArrayList<>();
    jdbcConnectivity jdbc = new jdbcConnectivity();
    jdbc.connect();
    //Reading and Parsing Strings to Json
    while(scn.hasNext()){
        JSONObject obj= (JSONObject) new JSONParser().parse(scn.nextLine());
        json.add(obj);
        user temp = new user();
            
        if(obj.containsKey("yelping_since")){
                
                String ys=(String)obj.get("yelping_since");
                temp.setYelping_since(ys); 
                System.out.print("Parser value:"+obj.get("yelping_since"));
        }
            
        if(obj.containsKey("review_count")){
                int rev_count = Integer.parseInt(obj.get("review_count").toString());
                temp.setReview_count(rev_count);
                System.out.print("Parser value"+rev_count);
        }
            
        if(obj.containsKey("name")){
                String n= (String)obj.get("name");
                temp.setName(n);
                System.out.print("Parser value:"+n);
        }
            
        if(obj.containsKey("user_id")){
                String uid = (String)obj.get("user_id");
                temp.setUser_id(uid);
                System.out.print("Parser value:"+uid);
        }
            
        if(obj.containsKey("fans")){
                int fan = Integer.parseInt(obj.get("fans").toString());
                temp.setFans(fan);
                System.out.print("Parser value:"+fan);
        }
           
        if(obj.containsKey("average_stars")){
                 float avg_str = Float.parseFloat(obj.get("average_stars").toString());
                 temp.setAverage_stars(avg_str);                
                 System.out.print("Parser value:"+avg_str);
        }
            
        if(obj.containsKey("type")){
                String t= (String)obj.get("type");
                temp.setType(t);
                System.out.print("Parser value:"+t);
        }
        
        try{
            String ys = temp.getYelping_since();
            int rev_count = temp.getReview_count();
            String n = temp.getName();
            String uid = temp.getUser_id();
            int fans = temp.getFans();
            float avg_stars = temp.getAverage_stars();
            String t = temp.getType();
                //String c = temp.getCompliments();
                
            PreparedStatement psInsUObj = jdbc.conn.prepareStatement("Insert into YELP_USER2 values(?,?,?,?,?,?,?)");
            System.out.print("Main Class");
            psInsUObj.setString(1,ys);
            psInsUObj.setInt(2,rev_count);
            psInsUObj.setString(3,n);
            psInsUObj.setString(4,uid);
            psInsUObj.setInt(5,fans);
            psInsUObj.setFloat(6,avg_stars);
            psInsUObj.setString(7,t);
               // psInsUObj.setString(8,c);
               
            jdbc.insert(psInsUObj);
            psInsUObj.getConnection().commit();
            psInsUObj.close();
            uObjList.add(temp);
            
        } catch(Exception e){System.out.println("Main error:"+e.getMessage());}
        
    }
    jdbc.close();
    return uObjList;
    }
}

