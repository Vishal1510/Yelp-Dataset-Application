/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;


import model.business;
import model.businessCategory;
//import org.json.simple.JSONObject; 
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
//import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdbcConnectivity.jdbcConnectivity;
import org.json.simple.JSONArray;
/**
 *
 * @author VISHAL
 */
public class Business_parser {
    
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




    
    static String FileName="E:\\MASTERS LECTUR\\Sem 2\\Database Arch\\DB\\YelpDataset\\YelpDataset-CptS451\\yelp_business.json";
    static Map<String,Set<String>> categoMap;
    static jdbcConnectivity jdbc = new jdbcConnectivity();
    
    
public static synchronized List<business> ReadJSON(String Encoding) throws FileNotFoundException, ParseException {
    Scanner scn=new Scanner(new File(FileName),Encoding);
    ArrayList<JSONObject> json=new ArrayList<>();

    List<business> bObjList = new ArrayList<>();
    List<businessCategory> bCategoList = new ArrayList<>();
    //List<BusinessSubcategory> bSubcategoList = new ArrayList<>();
    ArrayList<String> attribute_List = new ArrayList<>();
    Set<String> subCategoList = new HashSet<>();
    
    ArrayList<String> categoList = new ArrayList<>();
    
    categoList.add("Active Life");
    categoList.add("Arts & Entertainment");
    categoList.add("Automotive"); 
    categoList.add("Car Rental");
    categoList.add("Cafes");
    categoList.add("Beauty & Spas");
    categoList.add("Convenience Stores");
    categoList.add("Dentists");
    categoList.add("Doctors");
    categoList.add("Drugstores");
    categoList.add("Department Stores");
    categoList.add("Education");
    categoList.add("Event Planning & Services");
    categoList.add("Flowers & Gifts");
    categoList.add("Food");
    categoList.add("Health & Medical");
    categoList.add("Home Services");
    categoList.add("Home & Garden");
    categoList.add("Hospitals");
    categoList.add("Hotels & Travel");
    categoList.add("Hardware Stores");
    categoList.add("Grocery");
    categoList.add("Medical Centers");
    categoList.add("Nurseries & Gardening");
    categoList.add("Nightlife");
    categoList.add("Restaurants");
    categoList.add("Shopping");
    categoList.add("Transportation");
    
    attribute_List.add("Goodfor_Desert");
    attribute_List.add("Goodfor_latenight");
    attribute_List.add("Goodfor_lunch");
    attribute_List.add("Goodfor_dinner");
    attribute_List.add("Goodfor_breakfast");
    attribute_List.add("Goodfor_brunch");
    attribute_List.add("Goodfor_caters");
    attribute_List.add("park_garage");
    attribute_List.add("park_street");
    attribute_List.add("park_validated");
    attribute_List.add("park_lot");
    attribute_List.add("park_valet");
    attribute_List.add("diet_dairyfree");
    attribute_List.add("diet_glutenfree");
    attribute_List.add("diet_vegan");
    attribute_List.add("diet_soyafree");
    attribute_List.add("diet_vegeterian");
    attribute_List.add("ambience_romantic");
    attribute_List.add("ambience_intimate");
    attribute_List.add("ambience_touristy");
    attribute_List.add("ambience_hipster");
    attribute_List.add("ambience_divey");
    attribute_List.add("ambience_classy");
    attribute_List.add("ambience_trendy");
    attribute_List.add("ambience_upscale");
    attribute_List.add("ambience_casual");
    attribute_List.add("music_DJ");
    attribute_List.add("music_background");
    attribute_List.add("music_jukebox");
    attribute_List.add("music_live");
    attribute_List.add("music_video");
    attribute_List.add("music_karaoke");
    attribute_List.add("By appointment only_false");
    attribute_List.add("By appointment only");
    attribute_List.add("Take out");
    attribute_List.add("Take out_false");
    attribute_List.add("order at counter");
    attribute_List.add("order at counter_false");
    attribute_List.add("caters");
    attribute_List.add("caters_false");
    attribute_List.add("noiselevel_average");
    attribute_List.add("noiselevel_quiet");
    attribute_List.add("noiselevel_loud");
    attribute_List.add("noiselevel_veryloud");
    attribute_List.add("Takes reservations");
    attribute_List.add("Takes reservations_false");
    attribute_List.add("Delivery");
    attribute_List.add("Delivery_false");
    attribute_List.add("HasTV");
    attribute_List.add("HasTV_false");
    attribute_List.add("OutdoorSeating");
    attribute_List.add("OutdoorSeating_false");
    attribute_List.add("Attire_casual");
    attribute_List.add("Alcohol_none");
    attribute_List.add("Alcohol_fullbar");
    attribute_List.add("Alcohol_Beer&Wine");
    attribute_List.add("WaiterService");
    attribute_List.add("WaiterService_false");
    attribute_List.add("Accepts Credit Cards");
    attribute_List.add("Accepts Credit Cards_false");
    attribute_List.add("Good for kids");
    attribute_List.add("Good for kids_false");
    attribute_List.add("Good for groups");
    attribute_List.add("Good for groups_false");
    attribute_List.add("price range_1");
    attribute_List.add("price range_2");
    attribute_List.add("price range_3");
    attribute_List.add("price range_4");
    attribute_List.add("Dogs allowed");
    attribute_List.add("Dogs allowed_false");
    attribute_List.add("Wifi_free");
    attribute_List.add("Wifi_none");
    attribute_List.add("Wheelchair accessible");
    attribute_List.add("Wheelchair accessible_false");
    attribute_List.add("Good for dancing");
    attribute_List.add("Good for dancing_false");
    attribute_List.add("Smoking_outdoor");
    attribute_List.add("Coat check");
    attribute_List.add("Coat check_false");
    attribute_List.add("Happy Hour");
    attribute_List.add("Happy Hour_false");
    attribute_List.add("BYOB");
    attribute_List.add("BYOB_false");
    attribute_List.add("Corkage");
    attribute_List.add("Corkage_false");
    attribute_List.add("BYOB/Corkage_no");
    attribute_List.add("BYOB/Corkage_yes_free");
    attribute_List.add("BYOB/Corkage_yes_corkage");
    attribute_List.add("payment_mastercard");
    attribute_List.add("payment_visa");
    attribute_List.add("payment_amex");
    attribute_List.add("payment_discover");
    attribute_List.add("payment_cashonly");
    attribute_List.add("Open 24hrs");
    attribute_List.add("Open 24hrs_false");

    
    categoMap = new HashMap();
    categoMap.put("Active Life", new HashSet<String>());
    categoMap.put("Arts & Entertainment", new HashSet<String>());
    categoMap.put("Automotive", new HashSet<String>());
    categoMap.put("Car Rental", new HashSet<String>());
    categoMap.put("Cafes", new HashSet<String>());
    categoMap.put("Beauty & Spas", new HashSet<String>());
    categoMap.put("Convenience Stores", new HashSet<String>());
    categoMap.put("Dentists", new HashSet<String>());
    categoMap.put("Drugstores", new HashSet<String>());
    categoMap.put("Department Stores", new HashSet<String>());
    categoMap.put("Education", new HashSet<String>());
    categoMap.put("Event Planning & Services", new HashSet<String>());
    categoMap.put("Flowers & Gifts", new HashSet<String>());
    categoMap.put("Doctors", new HashSet<String>());
    categoMap.put("Food", new HashSet<String>());
    categoMap.put("Health & Medical", new HashSet<String>());
    categoMap.put("Home Services", new HashSet<String>());
    categoMap.put("Home & Garden", new HashSet<String>());
    categoMap.put("Hospitals", new HashSet<String>());
    categoMap.put("Hotels & Travel", new HashSet<String>());
    categoMap.put("Hardware Stores", new HashSet<String>());
    categoMap.put("Grocery", new HashSet<String>());
    categoMap.put("Medical Centers", new HashSet<String>());
    categoMap.put("Nurseries & Gardening", new HashSet<String>());
    categoMap.put("Nightlife", new HashSet<String>());
    categoMap.put("Restaurants", new HashSet<String>());
    categoMap.put("Shopping", new HashSet<String>());
    categoMap.put("Transportation", new HashSet<String>());
   
     jdbc.connect();
     //Reading and Parsing Strings to Json
    while(scn.hasNext()) {
        JSONObject obj= (JSONObject) new JSONParser().parse(scn.nextLine());
        json.add(obj);
        business temp = new business();
        int iterate =0;

        
        
        if(obj.containsKey("business_id")) 
        {
             String bid = (String) obj.get("business_id");
             temp.setBusiness_id(bid); 
             
             System.out.println("Businees ID:"+bid);
             //getHours(obj,bid);
             //getCategories(obj, bid);
             //getAttribute(obj, bid);
            
        }
 
        if(obj.containsKey("full_address"))
        {
            String Address = obj.get("full_address").toString();
            temp.setFull_address(Address); 
            //System.out.println("Address:"+obj.get("full_address"));
        }
        
        if(obj.containsKey("city"))
        {
            String city = obj.get("city").toString();
            temp.setCity(city); 
            //System.out.println("city:"+obj.get("city"));
        }
                
        if(obj.containsKey("attributes")){
        }
                
        if(obj.containsKey("open"))
        {
            String status = obj.get("open").toString();
            if(status.equals("true"))
                temp.setOpen(true);
            else if(status.equals("false"))
                temp.setOpen(false); 
            //System.out.println("open:"+obj.get("open"));
        }    
                
        if(obj.containsKey("review_count"))
        {
            int rCnt = Integer.parseInt(obj.get("review_count").toString());
            temp.setReview_count(rCnt);
            //System.out.println("review_count:"+obj.get("review_count"));
        }
        if(obj.containsKey("name"))
        {
            String name = obj.get("name").toString();
            temp.setName(name); 
            //System.out.println("name:"+obj.get("name"));
         }
        if(obj.containsKey("longitude"))
        {
            float longitude = Float.parseFloat(obj.get("longitude").toString());
            temp.setLongitude(longitude); 
            //System.out.println("longitude:"+obj.get("longitude"));
        }
        if(obj.containsKey("latitude"))
        {
            float latitude = Float.parseFloat(obj.get("latitude").toString());
            temp.setLatitude(latitude);
            //System.out.println("latitude:"+obj.get("latitude"));
        }
        if(obj.containsKey("state"))
        {
            String state = obj.get("state").toString();
            temp.setState(state); 
            //System.out.println("state:"+obj.get("state"));
        }
        if(obj.containsKey("stars"))
        {
            float stars = Float.parseFloat(obj.get("stars").toString());
            temp.setStars(stars); 
            //System.out.println("stars:"+obj.get("stars"));
        }
        if(obj.containsKey("type"))
        { 
            String type = obj.get("type").toString();
            temp.setBusiness_type(type); 
            //System.out.println("type:"+obj.get("type"));
        }
        
        try {
            
                String bid = temp.getBusiness_id();
                String addr = temp.getFull_address();
                String city = temp.getCity();
                String state = temp.getState();
                String bName = temp.getName();
                String bType = temp.getBusiness_type();
                String open = "";
                        boolean status = temp.isOpen();
                        if(status)
                            open= "true";
                        else
                            open = "false";
                        
                
                float latitude = temp.getLatitude();
                float longitude = temp.getLongitude();
                float stars = temp.getStars();
                int revCount = temp.getReview_count();
                
                PreparedStatement psInsBObj = jdbc.conn.prepareStatement("Insert into Business2 values(?,?,?,?,?,?,?,?,?,?,?)");
                psInsBObj.setString(1,bid);
                psInsBObj.setString(2,addr);
                psInsBObj.setString(3,open);
                psInsBObj.setString(4,city);
                psInsBObj.setString(5,state);
                psInsBObj.setFloat(6,latitude);
                psInsBObj.setFloat(7,longitude);
                psInsBObj.setInt(8,revCount);
                psInsBObj.setString(9,bName);
                psInsBObj.setFloat(10,stars);
                psInsBObj.setString(11,bType);
                
                jdbc.insert(psInsBObj);
                
                psInsBObj.getConnection().commit();
                psInsBObj.close();
                bObjList.add(temp);
                
        }catch(Exception e)
        {
              System.out.println("Main error:"+e.getMessage());
              e.printStackTrace();
        }
    }
     /*   for(String key: categoMap.keySet())
        {
            System.out.println("-------------"+key+"----------------");
            for(String value: categoMap.get(key)){
            System.out.println(""+value);
            }
        }*/
               
        try
        {
            jdbc.close();
        } 
        catch(Exception e){ }
        return bObjList;
}

private static synchronized void getHours(JSONObject j,String businessID){
    String close,open;
    if(j.containsKey("hours")){
        JSONObject joHours = (JSONObject) j.get("hours");
        if(joHours.containsKey("Monday")){
            JSONObject joMonday = (JSONObject) joHours.get("Monday");
            if(joMonday.containsKey("close")){
               close = joMonday.get("close").toString();
               open = joMonday.get("open").toString();
               
                
                
                try{
                    PreparedStatement psInsBHoursObj = jdbc.conn.prepareStatement("Insert into Business_Hours values(?,?,?,?)");
                    psInsBHoursObj.setString(1,businessID);
                    psInsBHoursObj.setString(2,"Monday");
                    psInsBHoursObj.setString(3,open);
                    psInsBHoursObj.setString(4,close);
                    jdbc.insert(psInsBHoursObj);
                    psInsBHoursObj.close();
                }catch(SQLException se){}
            }
        }
        if(joHours.containsKey("Tuesday")){
            JSONObject joTuesday = (JSONObject) joHours.get("Tuesday");
            if(joTuesday.containsKey("close")){
               close = joTuesday.get("close").toString();
               open = joTuesday.get("open").toString();
               
                
                try{
                    PreparedStatement psInsBHoursObj = jdbc.conn.prepareStatement("Insert into Business_Hours values(?,?,?,?)");
                    psInsBHoursObj.setString(1,businessID);
                    psInsBHoursObj.setString(2,"Tuesday");
                    psInsBHoursObj.setString(3,open);
                    psInsBHoursObj.setString(4,close);
                    jdbc.insert(psInsBHoursObj);
                    psInsBHoursObj.close();
                }catch(SQLException se){}
            }
        }
        if(joHours.containsKey("Wednesday")){
            JSONObject joWednesday = (JSONObject) joHours.get("Wednesday");
            if(joWednesday.containsKey("close")){
               close = joWednesday.get("close").toString();
               open = joWednesday.get("open").toString();
               
                
                try{
                    PreparedStatement psInsBHoursObj = jdbc.conn.prepareStatement("Insert into Business_Hours values(?,?,?,?)");
                    psInsBHoursObj.setString(1,businessID);
                    psInsBHoursObj.setString(2,"Wednesday");
                    psInsBHoursObj.setString(3,open);
                    psInsBHoursObj.setString(4,close);
                    jdbc.insert(psInsBHoursObj);
                    psInsBHoursObj.close();
                
                }catch(SQLException se){}
            }
        }
        if(joHours.containsKey("Thursday")){
            JSONObject joThursday = (JSONObject) joHours.get("Thursday");
            if(joThursday.containsKey("close")){
               close = joThursday.get("close").toString();
               open = joThursday.get("open").toString();
               
                
                try{
                    PreparedStatement psInsBHoursObj = jdbc.conn.prepareStatement("Insert into Business_Hours values(?,?,?,?)");
                    psInsBHoursObj.setString(1,businessID);
                    psInsBHoursObj.setString(2,"Thursday");
                    psInsBHoursObj.setString(3,open);
                    psInsBHoursObj.setString(4,close);
                    jdbc.insert(psInsBHoursObj);
                    psInsBHoursObj.close();
                
                }catch(SQLException se){}
            }
        }
        if(joHours.containsKey("Friday")){
            JSONObject joFriday = (JSONObject) joHours.get("Friday");
            if(joFriday.containsKey("close")){
               close = joFriday.get("close").toString();
               open = joFriday.get("open").toString();
               
                
                try{
                    PreparedStatement psInsBHoursObj = jdbc.conn.prepareStatement("Insert into Business_Hours values(?,?,?,?)");
                    psInsBHoursObj.setString(1,businessID);
                    psInsBHoursObj.setString(2,"Friday");
                    psInsBHoursObj.setString(3,open);
                    psInsBHoursObj.setString(4,close);
                    jdbc.insert(psInsBHoursObj);
                    psInsBHoursObj.close();
                
                }catch(SQLException se){}
            }
        }
        if(joHours.containsKey("Saturday")){
            JSONObject joSaturday = (JSONObject) joHours.get("Saturday");
            if(joSaturday.containsKey("close")){
               close = joSaturday.get("close").toString();
               open = joSaturday.get("open").toString();
               
                
                try{
                    PreparedStatement psInsBHoursObj = jdbc.conn.prepareStatement("Insert into Business_Hours values(?,?,?,?)");
                    psInsBHoursObj.setString(1,businessID);
                    psInsBHoursObj.setString(2,"Saturday");
                    psInsBHoursObj.setString(3,open);
                    psInsBHoursObj.setString(4,close);
                    jdbc.insert(psInsBHoursObj);
                    psInsBHoursObj.close();
                
                }catch(SQLException se){}
            }
        }
        if(joHours.containsKey("Sunday")){
            JSONObject joSunday = (JSONObject) joHours.get("Sunday");
            if(joSunday.containsKey("close")){
               close = joSunday.get("close").toString();
               open = joSunday.get("open").toString();

                try{
                    PreparedStatement psInsBHoursObj = jdbc.conn.prepareStatement("Insert into Business_Hours values(?,?,?,?)");
                    psInsBHoursObj.setString(1,businessID);
                    psInsBHoursObj.setString(2,"Sunday");
                    psInsBHoursObj.setString(3,open);
                    psInsBHoursObj.setString(4,close);
                    jdbc.insert(psInsBHoursObj);
                    psInsBHoursObj.close();
                }catch(SQLException se){}
            }
        }
    }
}

private synchronized static void getCategories(JSONObject j,String bid){
              if(j.containsKey("categories")){
                    JSONArray categoArray = (JSONArray) j.get("categories");
                    ArrayList<String> tempCategoryList = new ArrayList<String>();
                    ArrayList<String> tempSubCategoryList = new ArrayList<String>();
                    for(int i=0;i<categoArray.size();i++){
                        String array_Category = categoArray.get(i).toString();
                        if (categoMap.containsKey(array_Category)){
                            tempCategoryList.add(array_Category);
               
                            try{
                                    PreparedStatement psInsBusienss_CategoryObj = jdbc.conn.prepareStatement("Insert into Business_Category values(?,?)");
                                    psInsBusienss_CategoryObj.setString(1,bid);
                                    psInsBusienss_CategoryObj.setString(2,array_Category);
                                    jdbc.insert(psInsBusienss_CategoryObj);
                                    psInsBusienss_CategoryObj.close();
                            }catch(SQLException se){
                                System.out.println(se.toString());
                            }
                        }else{
                            
                            tempSubCategoryList.add(array_Category);
                            
                            try{
                            PreparedStatement psInsBusiness_SubCategoryObj = jdbc.conn.prepareStatement("Insert into Busienss_SubCategory values(?,?)");
                                    psInsBusiness_SubCategoryObj.setString(1,bid);
                                    psInsBusiness_SubCategoryObj.setString(2,array_Category);
                                    jdbc.insert(psInsBusiness_SubCategoryObj);
                                    psInsBusiness_SubCategoryObj.close();
                            }catch(SQLException se){
                            System.out.println(se.toString());
                            }
                                    
                        }
                    } 
                    for(String key: tempCategoryList)
                                categoMap.get(key).addAll(tempSubCategoryList);
               }
}
 private static void getAttribute(JSONObject joAttr,String bid){
        String newAttribute="";
        JSONObject attribute = (JSONObject) joAttr.get("attributes");
        Set<String> keys = attribute.keySet();
        Iterator<String> itration =keys.iterator();
        while(itration.hasNext())
        {
            newAttribute = itration.next();
                        
            String subAttribute="",result="",r="";
        if(newAttribute != null){
            if(newAttribute.equals("Dietary Restrictions") || newAttribute.equals("Ambience")  || newAttribute.equals("Parking")  ||  newAttribute.equals("Music") || newAttribute.equals("Payment Types")|| newAttribute.equals("Good For")){
            switch(newAttribute){
                case "Dietary Restrictions":
                     JSONObject dietary_Restriction = (JSONObject) attribute.get("Dietary Restriction");
                     if(dietary_Restriction != null){
                         if(dietary_Restriction.size()>0){
                     Set<String> restrictions = dietary_Restriction.keySet();
                     Iterator<String> iteration2 =restrictions.iterator();
                     while(iteration2.hasNext()){
                         r = iteration2.next();
                         switch(r){
                           case "dairy-free":
                            subAttribute = dietary_Restriction.get("dairy-free").toString();
                            if(subAttribute.equals("true")){
                                result="dairy-free";
                            } else if(subAttribute.equals("false")){
                                result="dairy-free_false";
                            }
                            break;
                         case "gluten-free":
                            subAttribute = dietary_Restriction.get("gluten-free").toString();
                            if(subAttribute.equals("true"))
                            {
                                result="gluten-free";
                            } else if(subAttribute.equals("false"))
                            {
                                result="gluten-free_false";
                            }
                            break;
                         case "soy-free":
                             subAttribute = dietary_Restriction.get("soy_free").toString();
                            if(subAttribute.equals("true"))
                            {
                                result="soy-free";
                            } else if(subAttribute.equals("false"))
                            {
                                result="soy-free_false";
                            }
                            break; 
                         case "vegetarian":
                             subAttribute = dietary_Restriction.get("vegetarian").toString();
                            if(subAttribute.equals("true"))
                            {
                                result="vegetarian";
                            } else if(subAttribute.equals("false"))
                            {
                                result="vegetarian_false";
                            }
                            break; 
                         case "vegan":
                             subAttribute = dietary_Restriction.get("vegan").toString();
                            if(subAttribute.equals("true"))
                            {
                                result="vegan";
                            } else if(subAttribute.equals("false"))
                            {
                                result="vegan_false";
                            }
                            break; 
                         case "kosher":
                             subAttribute = dietary_Restriction.get("kosher").toString();
                            if(subAttribute.equals("true"))
                            {
                                result="kosher";
                            } else if(subAttribute.equals("false"))
                            {
                                result="kosher_false";
                            }
                            break; 
                         case "halal":
                             subAttribute = dietary_Restriction.get("halal").toString();
                            if(subAttribute.equals("true"))
                            {
                                result="halal";
                            } else if(subAttribute.equals("false"))
                            {
                                result="halal_false";
                            }
                            break;     
                         }
                         try{
                                PreparedStatement psInsertBusinessAttributeObj = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                                psInsertBusinessAttributeObj.setString(1,bid);
                                psInsertBusinessAttributeObj.setString(2,"Dietary Restriction");
                                psInsertBusinessAttributeObj.setString(3,r);
                                psInsertBusinessAttributeObj.setString(4,result);
                                jdbc.insert(psInsertBusinessAttributeObj);
                                psInsertBusinessAttributeObj.close();
                        } catch(SQLException se){
                        }
                     }
                     } 
                }
                    break;
                case "Ambience": 
                    JSONObject ambience = (JSONObject) attribute.get("Ambience");
                    if(ambience != null){
                    if(ambience.size()>0){
                     Set<String> amb = ambience.keySet();
                     Iterator<String> itration3 =amb.iterator();
                     while(itration3.hasNext()){
                         r = itration3.next();
                         switch(r){
                         case "romantic":
                            subAttribute = ambience.get("romantic").toString();
                            if(subAttribute.equals("true")){
                                result="romantic";
                            } else if(subAttribute.equals("false")){
                                result="romantic_false";
                            }
                            break;    
                           case "intimate":
                            subAttribute = ambience.get("intimate").toString();
                            if(subAttribute.equals("true")){
                                result="intimate";
                            } else if(subAttribute.equals("false")){
                                result="intimate_false";
                            }
                            break;  
                            case "touristy":
                            subAttribute = ambience.get("touristy").toString();
                            if(subAttribute.equals("true")){
                                result="touristy";
                            } else if(subAttribute.equals("false")){
                                result="touristy_false";
                            }
                            break;
                            case "hipster":
                            subAttribute = ambience.get("hipster").toString();
                            if(subAttribute.equals("true")){
                                result="hipster";
                            } else if(subAttribute.equals("false")){
                                result="hipster_false";
                            }
                            break;
                            case "divey":
                            subAttribute = ambience.get("divey").toString();
                            if(subAttribute.equals("true")){
                                result="divey";
                            } else if(subAttribute.equals("false")){
                                result="divey_false";
                            }
                            break;
                            case "classy":
                            subAttribute = ambience.get("classy").toString();
                            if(subAttribute.equals("true")){
                                result="classy";
                            } else if(subAttribute.equals("false")){
                                result="classy_false";
                            }
                            break;
                            case "trendy":
                            subAttribute = ambience.get("trendy").toString();
                            if(subAttribute.equals("true")){
                                result="trendy";
                            } else if(subAttribute.equals("false")){
                                result="trendy_false";
                            }
                            break;

                            case "upscale":
                            subAttribute = ambience.get("upscale").toString();
                            if(subAttribute.equals("true")){
                                result="upscale";
                            } else if(subAttribute.equals("false")){
                                result="upscale_false";
                            }
                            break;
                            case "casual":
                            subAttribute = ambience.get("casual").toString();
                            if(subAttribute.equals("true")){
                                result="casual";
                            } else if(subAttribute.equals("false")){
                                result="casual_false";
                            }
                            break;         
                         }
                        try{
                                PreparedStatement psInsertBusinessAttributeObj = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                                psInsertBusinessAttributeObj.setString(1,bid);
                                psInsertBusinessAttributeObj.setString(2,"Ambience");
                                psInsertBusinessAttributeObj.setString(3,r);
                                psInsertBusinessAttributeObj.setString(4,result);

                                jdbc.insert(psInsertBusinessAttributeObj);
                                psInsertBusinessAttributeObj.close();
                        } catch(SQLException se){
                        }

                     }
                     }
                    }
                    break;
                case "Parking":                                                 
                     JSONObject parking = (JSONObject) attribute.get("Parking");
                     if(parking != null){
                     if(parking.size()>0){
                     Set<String> park = parking.keySet();
                     Iterator<String> itration4 = park.iterator();
                     while(itration4.hasNext()){
                         r = itration4.next();
                         switch(r){

                         case "garage":
                            subAttribute = parking.get("garage").toString();
                            if(subAttribute.equals("true")){
                                result="garage";
                            } else if(subAttribute.equals("false")){
                                result="garage_false";
                            }
                            break;
                         case "street":
                            subAttribute = parking.get("street").toString();
                            if(subAttribute.equals("true")){
                                result="street";
                            } else if(subAttribute.equals("false")){
                                result="street_false";
                            }
                            break;                                         
                         case "validated":
                            subAttribute = parking.get("validated").toString();
                            if(subAttribute.equals("true")){
                                result="validated";
                            } else if(subAttribute.equals("false")){
                                result="validated_false";
                            }
                            break;  
                         case "lot":
                            subAttribute = parking.get("lot").toString();
                            if(subAttribute.equals("true")){
                                result="lot";
                            } else if(subAttribute.equals("false")){
                                result="lot_false";
                            }
                            break;  
                         case "valet":
                            subAttribute = parking.get("valet").toString();
                            if(subAttribute.equals("true")){
                                result="valet";
                            } else if(subAttribute.equals("false")){
                                result="valet_false";
                            }
                            break;
                         }
                         try
                         {
                                PreparedStatement psInsertBusinessAttributeObj = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                                psInsertBusinessAttributeObj.setString(1,bid);
                                psInsertBusinessAttributeObj.setString(2,"Parking");
                                psInsertBusinessAttributeObj.setString(3,r);
                                psInsertBusinessAttributeObj.setString(4,result);

                                jdbc.insert(psInsertBusinessAttributeObj);
                                psInsertBusinessAttributeObj.close();
                        } catch(SQLException se){
                        }

                     }
                     }
                     }
                    break; 
                    case "Music":
                     JSONObject music = (JSONObject) attribute.get("Music");
                     if(music != null){
                     if(music.size()>0){
                     Set<String> mus = music.keySet();

                     Iterator<String> itration5 = mus.iterator();
                     while(itration5.hasNext()){
                         r = itration5.next();
                         switch(r){
                            case "dj":
                            subAttribute = music.get("dj").toString();
                            if(subAttribute.equals("true")){
                                result="dj";
                            } else if(subAttribute.equals("false")){
                                result="dj_false";
                            }
                            break;
                            case "background_music":
                            subAttribute = music.get("background_music").toString();
                            if(subAttribute.equals("true")){
                                result="background_music";
                            } else if(subAttribute.equals("false")){
                                result="backgound_music_false";
                            }
                            break;
                        case "jukebox":
                            subAttribute = music.get("jukebox").toString();
                            if(subAttribute.equals("true")){
                                result="jukebox";
                            } else if(subAttribute.equals("false")){
                                result="jukebox_false";
                            }
                            break;
                            case "live":
                            subAttribute = music.get("live").toString();
                            if(subAttribute.equals("true")){
                                result="live";
                            } else if(subAttribute.equals("false")){
                                result="live_false";
                            }
                            break;
                            case "video":
                            subAttribute = music.get("video").toString();
                            if(subAttribute.equals("true")){
                                result="video";
                            } else if(subAttribute.equals("false")){
                                result="video_false";
                            }
                            break;
                            case "karaoke":
                            subAttribute = music.get("karaoke").toString();
                            if(subAttribute.equals("true")){
                                result="karaoke";
                            } else if(subAttribute.equals("false")){
                                result="karaoke_false";
                            }
                            break;
                         }
                         try
                         {
                                PreparedStatement psInsertBusinessAttributeObj = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                                psInsertBusinessAttributeObj.setString(1,bid);
                                psInsertBusinessAttributeObj.setString(2,"Music");
                                psInsertBusinessAttributeObj.setString(3,r);
                                psInsertBusinessAttributeObj.setString(4,result);
                                
                                jdbc.insert(psInsertBusinessAttributeObj);
                                psInsertBusinessAttributeObj.close();
                        } catch(SQLException se){
                        }

                    }
                }
            }
                     break;
                case "Payment Types":
                     JSONObject payment_Type = (JSONObject) attribute.get("Payment Types");
                     if(payment_Type != null){
                     if(payment_Type.size()>0){
                     Set<String> payment_Types = payment_Type.keySet();
                     Iterator<String> iterator_Payment =payment_Types.iterator();
                     while(iterator_Payment.hasNext()){
                         r = iterator_Payment.next();
                         switch(r){
                           case "amex":
                            subAttribute = payment_Type.get("amex").toString();
                            if(subAttribute.equals("true")){
                                result="amex";
                            } else if(subAttribute.equals("false")){
                                result="amex_false";
                            }
                            break;
                         case "discover":
                            subAttribute = payment_Type.get("discover").toString();
                            if(subAttribute.equals("true")){
                                result="discover";
                            } else if(subAttribute.equals("false")){
                                result="discover_false";
                            }
                            break;
                         case "visa":
                            subAttribute = payment_Type.get("visa").toString();
                            if(subAttribute.equals("true")){
                                result="visa";
                            } else if(subAttribute.equals("false")){
                                result="visa_false";
                            }
                             break;
                         case "mastercard":
                             subAttribute = payment_Type.get("mastercard").toString();
                            if(subAttribute.equals("true")){
                                result="mastercard";
                            } else if(subAttribute.equals("false")){
                                result="mastercard_false";
                            }
                             break;
                         case "cash_only":
                            subAttribute = payment_Type.get("cash_only").toString();
                            if(subAttribute.equals("true")){
                                result="cash_only";
                            } else if(subAttribute.equals("false")){
                                result="cash_only_false";
                            }
                             break;
                         }
                          try
                         {      PreparedStatement psInsertBusinessAttributeObj = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                                psInsertBusinessAttributeObj.setString(1,bid);
                                psInsertBusinessAttributeObj.setString(2,"Payment Types");
                                psInsertBusinessAttributeObj.setString(3,r);
                                psInsertBusinessAttributeObj.setString(4,result);
                                
                                jdbc.insert(psInsertBusinessAttributeObj);
                                psInsertBusinessAttributeObj.close();
                        } catch(SQLException se){
                        }
                     }
                     }
                     }
                    break;
               case "Good For":
                JSONObject good_For = (JSONObject) attribute.get("Good For");
                if(good_For != null){
                if(good_For.size()>0){
                     Set<String> goodFOR = good_For.keySet();
                     Iterator<String> iterator6 = goodFOR.iterator();
                     while(iterator6.hasNext()){
                         r = iterator6.next();
                         switch(r){
                            case "dessert":
                            subAttribute = good_For.get("dessert").toString();
                            if(subAttribute.equals("true")){
                                result="dessert";
                            } else if(subAttribute.equals("false")){
                                result="dessert_false";
                            }
                            break;    
                            case "latenight":
                            subAttribute = good_For.get("latenight").toString();
                            if(subAttribute.equals("true")){
                                result="latenight";
                            } else if(subAttribute.equals("false")){
                                result="latenight_false";
                            }
                            break;
                            case "lunch":
                            subAttribute = good_For.get("lunch").toString();
                            if(subAttribute.equals("true")){
                                result="lunch";
                            } else if(subAttribute.equals("false")){
                                result="lunch_false";
                            }
                            break;
                            case "dinner":
                            subAttribute = good_For.get("dinner").toString();
                            if(subAttribute.equals("true")){
                                result="dinner";
                            } else if(subAttribute.equals("false")){
                                result="dinner_false";
                            }
                            break;
                            case "breakfast":
                            subAttribute = good_For.get("breakfast").toString();
                            if(subAttribute.equals("true")){
                                result="breakfast";
                            } else if(subAttribute.equals("false")){
                                result="breakfast_false";
                            }
                            break;
                            case "brunch":
                            subAttribute = good_For.get("brunch").toString();
                            if(subAttribute.equals("true")){
                                result="brunch";
                            } else if(subAttribute.equals("false")){
                                result="brunch_false";
                            }
                            break;
                           /* case "caters":
                            subAttr = good.get("caters").toString();
                            if(subAttr.equals("true")){
                                result="caters";
                            } else if(subAttr.equals("false")){
                                result="caters_false";
                            }
                            break;*/
                       }
                        try{
                                PreparedStatement psInsertBusinessMultAttributeObj = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                                psInsertBusinessMultAttributeObj.setString(1,bid);
                                psInsertBusinessMultAttributeObj.setString(2,"Good For");
                                psInsertBusinessMultAttributeObj.setString(3,r);
                                psInsertBusinessMultAttributeObj.setString(4,result);
                                
                                jdbc.insert(psInsertBusinessMultAttributeObj);
                                psInsertBusinessMultAttributeObj.close();
                        } catch(SQLException se){}     
             }
            }
           }
           break;     
        } 

        }  else if(newAttribute.equals("Ages Allowed") || newAttribute.equals("Noise Level") || newAttribute.equals("Attire") || newAttribute.equals("Alcohol") || newAttribute.equals("Price Range") || newAttribute.equals("Wi-Fi") || newAttribute.equals("Smoking") || newAttribute.equals("BYOB/Corkage")){
            String nonBoolResult="";
            switch(newAttribute){
                case "Noise Level":
                    nonBoolResult = attribute.get("Noise Level").toString();
                    break;
                case "Wi-Fi":
                    nonBoolResult = attribute.get("Wi-Fi").toString();
                    break;
                case "Attire":
                    nonBoolResult = attribute.get("Attire").toString();
                    break;
                case "Alcohol":
                    nonBoolResult = attribute.get("Alcohol").toString();
                    break;
                case "Price Range":
                    nonBoolResult = attribute.get("Price Range").toString();
                    break;
                case "Smoking":
                    nonBoolResult = attribute.get("Smoking").toString();
                    break;
                case "BYOB/Corkage":
                    nonBoolResult = attribute.get("BYOB/Corkage").toString();
                    break;
                case "Ages Allowed":
                    nonBoolResult = attribute.get("Ages Allowed").toString();
                    break;

            }
            try{
                PreparedStatement psInsertBusinessBoolAttributeObj = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                psInsertBusinessBoolAttributeObj.setString(1,bid);
                psInsertBusinessBoolAttributeObj.setString(2,newAttribute);
                psInsertBusinessBoolAttributeObj.setString(3,"NA");
                psInsertBusinessBoolAttributeObj.setString(4,nonBoolResult);
                
                jdbc.insert(psInsertBusinessBoolAttributeObj);
                psInsertBusinessBoolAttributeObj.close();
            }catch(SQLException se){}
        }  else if(newAttribute.equals("Good for Kids") || newAttribute.equals("Good For Kids")){
            String gfkBool="",gfk="";
            if(newAttribute.equals("Good For Kids")){
                    gfkBool = attribute.get("Good For Kids").toString();
                    if(gfkBool.equals("true"))
                        gfk="Good_For_Kids";
                    else
                        gfk="Good_For_Kids_false";
            } 
            if(newAttribute.equals("Good for Kids")){
                    gfkBool = attribute.get("Good for Kids").toString();
                    if(gfkBool.equals("true"))
                        gfk="Good_For_Kids";
                    else
                        gfk="Good_For_Kids_false";
            } 
            try{
                PreparedStatement psInsBoolAttributeObj1 = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                psInsBoolAttributeObj1.setString(1,bid);
                psInsBoolAttributeObj1.setString(2,newAttribute);
                psInsBoolAttributeObj1.setString(3,"NA");
                psInsBoolAttributeObj1.setString(4,gfk);
                jdbc.insert(psInsBoolAttributeObj1);
                psInsBoolAttributeObj1.close();
            }catch(SQLException se){}
        }  else{
            String boolResult="",bool="";
            switch(newAttribute){
                case "Dogs Allowed":
                    bool = attribute.get("Dogs Allowed").toString();
                    if(bool.equals("true"))
                        boolResult="Dogs_Allowed";
                    else
                        boolResult="Dogs_Allowed_false";
                break;


                    case "By Appointment Only":
                    bool = attribute.get("By Appointment Only").toString();
                    if(bool.equals("true"))
                        boolResult="By_Appointment_Only";
                    else
                        boolResult="By_Appointment_Only_false";
                break;
                    case "Take-out":
                    bool = attribute.get("Take-out").toString();
                    if(bool.equals("true"))
                        boolResult="Take_Out";
                    else
                        boolResult="Take_Out_false";
                break;
                    case "Order at Counter":
                    bool = attribute.get("Order at Counter").toString();
                    if(bool.equals("true"))
                        boolResult="Order_At_Counter";
                    else
                        boolResult="Order_At_Counter_false";
                break;
                    case "Caters":
                    bool = attribute.get("Caters").toString();
                    if(bool.equals("true"))
                        boolResult="Caters";
                    else
                        boolResult="Caters_false";
                    break;

                    case "Drive-Thru":
                    bool = attribute.get("Drive-Thru").toString();
                    if(bool.equals("true"))
                        boolResult="Drive-Thru";
                    else
                        boolResult="Drive-Thru_false";
                break;

                case "Open 24 Hours":
                    bool = attribute.get("Open 24 Hours").toString();
                    if(bool.equals("true"))
                        boolResult="Open_24_Hours";
                    else
                        boolResult="Open_24_Hours_false";
                    break;
                    case "Takes Reservations":
                    bool = attribute.get("Takes Reservations").toString();
                    if(bool.equals("true"))
                        boolResult="Takes_Reservsations";
                    else
                        boolResult="Takes_Reservations_false";
                break;
                    case "Delivery":
                    bool = attribute.get("Delivery").toString();
                    if(bool.equals("true"))
                        boolResult="Delivery";
                    else
                        boolResult="Delivery_false";
                break;
                    case "Has TV":
                    bool = attribute.get("Has TV").toString();
                    if(bool.equals("true"))
                        boolResult="Has_TV";
                    else
                        boolResult="Has_TV_false";
                break;
                    case "Outdoor Seating":
                    bool = attribute.get("Outdoor Seating").toString();
                    if(bool.equals("true"))
                        boolResult="Outdoor Seating";
                    else
                        boolResult="Outdoor_Seating_false";
                break;
                    case "Waiter Service":
                    bool = attribute.get("Waiter Service").toString();
                    if(bool.equals("true"))
                        boolResult="Waiter_Service";
                    else
                        boolResult="Waiter_Service_false";
                break;
                    case "Accepts Credit Cards":
                    bool = attribute.get("Accepts Credit Cards").toString();
                    if(bool.equals("true"))
                        boolResult="Accepts_Credit_Cards";
                    else
                        boolResult="Accepts_Credit_Cards_false";
                break;
                    case "Good For Groups":
                    bool = attribute.get("Good For Groups").toString();
                    if(bool.equals("true"))
                        boolResult="Good_For_Groups";
                    else
                        boolResult="Good_For_Groups_false";
                break;
                case "Wheelchair Accessible":
                    bool = attribute.get("Wheelchair Accessible").toString();
                    if(bool.equals("true"))
                        boolResult="Wheelchair_Accessible";
                    else
                        boolResult="Wheelchair_Accessible_false";
                break;
                case "Good For Dancing":
                    bool = attribute.get("Good For Dancing").toString();
                    if(bool.equals("true"))
                        boolResult="Good_For_Dancing";
                    else
                        boolResult="Good_For_Dancing_false";
                break;
                case "Coat Check":
                    bool = attribute.get("Coat Check").toString();
                    if(bool.equals("true"))
                        boolResult="Coat_Check";
                    else
                        boolResult="Coat_Check_false";
                break;
                case "Happy Hour":
                    bool = attribute.get("Happy Hour").toString();
                    if(bool.equals("true"))
                        boolResult="Happy Hour";
                    else
                        boolResult="Happy_Hour_false";
                break;
                case "BYOB":
                    bool = attribute.get("BYOB").toString();
                    if(bool.equals("true"))
                        boolResult="BYOB";
                    else
                        boolResult="BYOB_false";
                break;
                case "Corkage":
                    bool = attribute.get("Corkage").toString();
                    if(bool.equals("true"))
                        boolResult="Corkage";
                    else
                        boolResult="Corkage_false";
                break;

            }
            try{
                PreparedStatement psInserBoolAttributeObj1 = jdbc.conn.prepareStatement("Insert into Business_Attribute values(?,?,?,?)");
                psInserBoolAttributeObj1.setString(1,bid);
                psInserBoolAttributeObj1.setString(2,newAttribute);
                psInserBoolAttributeObj1.setString(3,"NA");
                psInserBoolAttributeObj1.setString(4,boolResult);
              
                jdbc.insert(psInserBoolAttributeObj1);
                psInserBoolAttributeObj1.close();
            }catch(SQLException se){}
                        }
                       
                        }
         
       }
 }

}







    

