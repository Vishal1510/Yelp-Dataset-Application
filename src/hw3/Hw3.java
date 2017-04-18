/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hw3;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import jdbcConnectivity.jdbcConnectivity;
import model.business;
import model.review;
import model.user;
import parser.Business_parser;
import parser.Review_parser;
import parser.User_parser;
import javax.swing.JFrame;

/**
 *
 * @author VISHAL
 */
public class Hw3 {
    
    static final int business_count=20544;
    static final int review_count=822898;
    static final int user_count=211002;
    static String mainCategory="",day="",criteria="",zip="",city="",state="";
    static business BusinessObject = null;
    
    
    

    
    public static void main(String[] args) {
        // TODO code application logic here
        
        
        jdbcConnectivity jdbc= new jdbcConnectivity();
        if(args.length == 4){
            
            String arguments= args[0] +" "+ args[1] +" "+ args[2] +" "+ args[3] ;
            System.out.println(arguments);
            
            if(arguments.contains("yelp_business.json") && arguments.contains("yelp_review.json") && arguments.contains("yelp_checkin.json") && arguments.contains("yelp_user.json"))
            {
                System.out.println("Deleting Business data");
                String deleteBusinessdata = " drop table business2";
                String createBusinessTable="CREATE TABLE BUSINESS2 ( BUSINESS_ID VARCHAR2(100), FULL_ADDRESS VARCHAR2(200), OPENCLOSE VARCHAR2(200), CITY VARCHAR2(200), BUSINESSSTATE VARCHAR2(200), LATITUDE FLOAT(126), LONGITUDE FLOAT(126), REVIEW_COUNT INTEGER, BUSINESSNAME VARCHAR2(200), STARS FLOAT(126), BUSINESS_TYPE VARCHAR2(200) ) ";
                PreparedStatement psBusinessObjDel=null;
                
                try{
                     jdbc.connect();
                     psBusinessObjDel = jdbc.conn.prepareStatement(deleteBusinessdata);
                     jdbc.delete(psBusinessObjDel);
                } catch(Exception se){ System.out.println("Business objects deletion failed");
                }     //jdbc.close();
                    
                try
                {
                    jdbc.create(createBusinessTable);
                }catch(Exception e){System.out.println("R failed");
                
                }
                
                System.out.println("Deleting User Data");
                String deletUserdata = " drop table YELP_USER2";
                String createUserTable="CREATE TABLE YELP_USER2 (YELPING_SINCE VARCHAR2(200), REVIEW_COUNT INTEGER, USER_NAME VARCHAR(200), USER_ID VARCHAR(200), FANS INTEGER, AVERAGE_STARS FLOAT(126), USER_TYPE VARCHAR2(200) )";
                PreparedStatement psUserObjDel = null;
                
                try{
                     //jdbc.connect();
                     psUserObjDel = jdbc.conn.prepareStatement(deletUserdata);
                     jdbc.delete(psUserObjDel);
                } catch(Exception se){ System.out.println("user objects deletion failed");
                }     //jdbc.close();
                    
                try
                {
                    jdbc.create(createUserTable);
                }catch(Exception e){System.out.println("R failed");
                
                }
                System.out.println("Deleting Review Data");
                String deletReviewdata = " drop table review2";
                String createReviewTable = "CREATE TABLE REVIEW2 (USER_ID VARCHAR2(200), REVIEW_ID VARCHAR2(200), STARS FLOAT(126), DAT VARCHAR2(200), TEXT VARCHAR2(4000), TYPE VARCHAR2(200), BUSINESS_ID VARCHAR2(200), USEFULVOTES INTEGER )";
                PreparedStatement psReviewObjDel = null;
                
                try{
                     //jdbc.connect();
                     psReviewObjDel = jdbc.conn.prepareStatement(deletReviewdata);
                     jdbc.delete(psReviewObjDel);
                     jdbc.close();
                } catch(Exception se){ System.out.println("Reviews objects deletion failed");
                }     //jdbc.close();
                    
                try
                {
                    jdbc.create(createReviewTable);
                }catch(Exception e){System.out.println("R failed");
                
                }
               
                
            }
        }
        else
        {
            System.out.println("Delete Command Block");
            System.out.println("Arguments:"+args.length);
            /*PreparedStatement psBusinessCount;
            ResultSet rsBusinessCount;
            
            String countBusinessData = "select count(*) from business";
            
            try{
                
                 jdbc.connect();
                 psBusinessCount = jdbc.conn.prepareStatement(countBusinessData);
                 rsBusinessCount = psBusinessCount.executeQuery();
                 rsBusinessCount.next();
                 int businessCnt = rsBusinessCount.getInt(1);
                 if(businessCnt != business_count){
                        System.out.println("Starting business insertion");
                        try{
                            List<business> businessObjList = Business_parser.ReadJSON("UTF-8");
                            System.out.println(businessObjList.size()+" businesses populated");
                        }catch(Exception e){}
                      //populateBusinesses();
                 }else
                     System.out.println("Business objects are populated in database correctly");
            }
            catch(Exception exp){
                System.out.println("Insertion in Business failed");
            }
            finally{
                try{
                    jdbc.close();
                } catch (Exception exp){}
            }
            
            PreparedStatement psReviewCount;
            ResultSet resReviewCount;
            
            String countReviewData = "select count(*) from review";
            
            try
            {
                
                jdbc.connect();
                psReviewCount = jdbc.conn.prepareStatement(countReviewData);
                resReviewCount = psReviewCount.executeQuery();
                resReviewCount.next();
                int reviewcnt = resReviewCount.getInt(1);
                if(reviewcnt != review_count){
                    System.out.println("Starting review insertion");
                    try{
                        List<review> reviewObjList = Review_parser.ReadJSON("UTF-8");
                        System.out.println(reviewObjList.size()+" reviews added");
                    }catch(Exception e){}
                    //populateReviews();
                }else
                    System.out.println("Review objects are populated in database correctly");
            }
            catch(Exception exp){
                
                System.out.println("Insertion in Review Failed");
            }
            finally{
                try{
                    jdbc.close();
                    
                }catch (Exception exp){}
            }
            
            PreparedStatement psUserCount;
            ResultSet resUserCount;
            
            String countUserData = "select count(*) from yelp_user";
            
            try
            {
                jdbc.connect();
                psUserCount = jdbc.conn.prepareStatement(countUserData);
                resUserCount = psUserCount.executeQuery();
                resUserCount.next();
                int reviewcnt = resUserCount.getInt(1);
                if(reviewcnt != user_count){
                    System.out.println("Starting user insertion");
                    try{
                        List<user>userObjList = User_parser.ReadJSON("UTF-8");
                        System.out.println(userObjList.size()+" users added");
                    }catch(Exception e){}
                }else
                    System.out.println("User objects are populated in database correctly");
            }
            catch(Exception exp){
                
                System.out.println("Insertion in User Failed");
            }
            finally{
                try{
                    jdbc.close();
                    
                }catch (Exception exp){}
            }*/
            
        }
        
     Hw3 h = new Hw3();
     h.init();
     new Start().setVisible(true);    
        
    }     
    
    private void init(){
               new populateRows("Businesses").start();
               new populateRows("Reviews").start();
               new populateRows("Users").start();
    }

class populateRows extends Thread{
    String table;
    public populateRows(String t){
        table = t;
    }
    public void run(){
        switch(table){
            case "Reviews":
                System.out.println("Starting review insertion");
                try{
                    List<review> reviewObjList = Review_parser.ReadJSON("UTF-8");
                    System.out.println(reviewObjList.size()+" reviews added");
                }catch(Exception e){}
                break;
            case "Users":
                System.out.println("Starting user insertion");
                try{
                    List<user>userObjList = User_parser.ReadJSON("UTF-8");
                    System.out.println(userObjList.size()+" users added");
                }catch(Exception e){}
                break;
            case "Businesses":
                System.out.println("Starting business insertion");
                try{
                    List<business> businessObjList = Business_parser.ReadJSON("UTF-8");
                    System.out.println(businessObjList.size()+" businesses populated");
                }catch(Exception e){}
                break;
        }
    }
}

}   