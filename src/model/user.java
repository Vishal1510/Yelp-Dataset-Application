/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author VISHAL
 */
public class user {
    
    private String yelping_since;
    //String votes[] ={"useful","funny","cool"} 
    private int review_count;
    private String name;
    private String user_id;
    //friends;
    private int fans;
    private float average_stars;
    private String type;
    private String compliments;
    //elite;

    /**
     * @return the yelping_since
     */
    public String getYelping_since() {
        return yelping_since;
    }

    /**
     * @param yelping_since the yelping_since to set
     */
    public void setYelping_since(String yelping_since) {
        this.yelping_since = yelping_since;
    }

    /**
     * @return the review_count
     */
    public int getReview_count() {
        return review_count;
    }

    /**
     * @param review_count the review_count to set
     */
    public void setReview_count(int review_count) {
        this.review_count = review_count;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the fans
     */
    public int getFans() {
      //  System.out.print("Getter Value value"+fans);
        return fans;
    }

    /**
     * @param fans the fans to set
     */
    public void setFans(int fans) {
        this.fans = fans;
        //System.out.print("Setter Value value"+fans);
    }

    /**
     * @return the average_stars
     */
    public float getAverage_stars() {
        //System.out.print("Getter Value value"+average_stars);
        return average_stars;
    }

    /**
     * @param average_stars the average_stars to set
     */
    public void setAverage_stars(float average_stars) {
        this.average_stars = average_stars;
       // System.out.print("Setter Value value"+average_stars);
    }

    /**
     * @return the type
     */
    public String getType() {
        //System.out.print("Getter Value value"+type);
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
        //System.out.print("Setter Value value"+type);
    }

    /**
     * @return the compliments
     
    public String getCompliments() {
        //System.out.print("Getter Value value"+compliments);
        return compliments;
    }

   /**
     * @param compliments the compliments to set
     
    public void setCompliments(String compliments) {
        this.compliments = compliments;
        //System.out.print("Setter Value value"+compliments);
    }
*/    
}
