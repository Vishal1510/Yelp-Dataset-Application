/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import jdbcConnectivity.jdbcConnectivity;
import model.business;
import model.review;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import static parser.Business_parser.FileName;

/**
 *
 * @author VISHAL
 */
public class Review_parser {
    
    static String FileName="E:\\MASTERS LECTUR\\Sem 2\\Database Arch\\DB\\YelpDataset\\YelpDataset-CptS451\\yelp_review.json";
    
    public static synchronized List<review> ReadJSON(String Encoding) throws FileNotFoundException, ParseException {
    Scanner scn=new Scanner(new File(FileName),Encoding);
    ArrayList<JSONObject> json=new ArrayList<>();
    int iterate=0;
    List<review> rObjList = new ArrayList<>();
    iterate=0;
    jdbcConnectivity jdbc = new jdbcConnectivity();
    jdbc.connect();
    //Reading and Parsing Strings to Json
    while(scn.hasNext()){
        JSONObject obj2= (JSONObject) new JSONParser().parse(scn.nextLine());
        //json.add(obj2);
       
        review temp = new review();
            
        if(obj2.containsKey("votes"))
        {
                int useful;
                JSONObject usefulVotes= (JSONObject) obj2.get("votes");
                if(usefulVotes.containsKey("useful")){
                    useful= Integer.parseInt(usefulVotes.get("useful").toString());
                }
                else
                {
                    useful=0;
                }
                temp.setUsefulVotes(useful);
        }
                   
        if(obj2.containsKey("user_id")){
            String uid = (String)obj2.get("user_id");
            temp.setUser_id(uid);
        }
            
        if(obj2.containsKey("review_id")){
            String rid = (String)obj2.get("review_id");
            temp.setReview_id(rid);
        }
            
        if(obj2.containsKey("stars")){
            float str = Float.parseFloat(obj2.get("stars").toString());
            temp.setStars(str);
        }
            
        try
           {
            if(obj2.containsKey("date")){
                String dt= obj2.get("date").toString();
                System.out.println("Date to string:"+dt);
                //dt=dt+" ";
                temp.setDat(dt);
            }
            }catch(Exception e){System.out.println("Error:+e.printstacktrace()");}
            
            
        if(obj2.containsKey("text")){
            String txt= (String)obj2.get("text");
            temp.setText(txt);
        }
            
        if(obj2.containsKey("type")){
            String tp = (String)obj2.get("type");
            temp.setType(tp);
        }
            
        if(obj2.containsKey("business_id")){
            String bid = (String)obj2.get("business_id");
            temp.setBusiness_id(bid);
        }
        
        try{               
                String uid = temp.getUser_id();
                String rid = temp.getReview_id();
                float str = temp.getStars();
                String dte = temp.getDat();//ro.rvDate;
                System.out.println("DB Date:"+dte);
                String txt = temp.getText();
                String tp = temp.getType();
                String bid = temp.getBusiness_id();
                int usefulVotes=temp.getUsefulVotes();
                
                PreparedStatement psInsRObj = jdbc.conn.prepareStatement("Insert into Review2 values(?,?,?,?,?,?,?,?)");
                
                psInsRObj.setString(1,uid);
                psInsRObj.setString(2,rid); 
                psInsRObj.setFloat(3,str);
                psInsRObj.setString(4,dte);
                psInsRObj.setString(5,txt);
                psInsRObj.setString(6,tp);
                psInsRObj.setString(7,bid);
                psInsRObj.setInt(8, usefulVotes);
                
                jdbc.insert(psInsRObj);
                
                psInsRObj.getConnection().commit();
                psInsRObj.close();
                rObjList.add(temp);

        } catch(Exception e){System.out.println("Main error:"+e.getMessage());
            e.printStackTrace();
        }
            
            
    }
    jdbc.close();    
    return rObjList;
    }
}
    
