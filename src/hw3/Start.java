package hw3;

import static com.sun.jndi.toolkit.dir.DirSearch.search;
import static com.sun.jndi.toolkit.dir.DirSearch.search;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import jdbcConnectivity.jdbcConnectivity;
import static jdk.nashorn.internal.objects.NativeString.search;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author VISHAL
 */
public class Start extends javax.swing.JFrame {

    /**
     * Creates new form Start
     */
    ArrayList<String> main_Category = new ArrayList<>();
    ArrayList<String> subCategory = new ArrayList<>();
    ArrayList<String> attribute = new ArrayList<>();
    ArrayList<String> businessIDs = new ArrayList<>();
    String currBusiness="";
    String search_criteria="", search_state="", search_city="", search_zip="", search_day="" ;
    DefaultTableModel dmTable = new DefaultTableModel();
    public Start() {
        initComponents();
        choice1.addItem(" "); 
        choice1.addItem("Sunday"); 
        choice1.addItem("Monday"); 
        choice1.addItem("Tuesday"); 
        choice1.addItem("Wednesday");
        choice1.addItem("Thursday");
        choice1.addItem("Friday");
        choice1.addItem("Saturday");
        
        choice2.addItem(" ");
        choice2.addItem("1:00");
        choice2.addItem("2:00");
        choice2.addItem("3:00");
        choice2.addItem("4:00");
        choice2.addItem("5:00");
        choice2.addItem("6:00");
        choice2.addItem("7:00");
        choice2.addItem("8:00");
        choice2.addItem("9:00");
        choice2.addItem("10:00");
        choice2.addItem("11:00");
        choice2.addItem("12:00");
        choice2.addItem("13:00");
        choice2.addItem("14:00");
        choice2.addItem("15:00");
        choice2.addItem("16:00");
        choice2.addItem("17:00");
        choice2.addItem("18:00");
        choice2.addItem("19:00");
        choice2.addItem("20:00");
        choice2.addItem("21:00");
        choice2.addItem("22:00");
        choice2.addItem("23:00");
        choice2.addItem("00:00");
        
        
        choice3.addItem(" "); 
        choice3.addItem("1:00");
        choice3.addItem("2:00");
        choice3.addItem("3:00");
        choice3.addItem("4:00");
        choice3.addItem("5:00");
        choice3.addItem("6:00");
        choice3.addItem("7:00");
        choice3.addItem("8:00");
        choice3.addItem("9:00");
        choice3.addItem("10:00");
        choice3.addItem("11:00");
        choice3.addItem("12:00");
        choice3.addItem("13:00");
        choice3.addItem("14:00");
        choice3.addItem("15:00");
        choice3.addItem("16:00");
        choice3.addItem("17:00");
        choice3.addItem("18:00");
        choice3.addItem("19:00");
        choice3.addItem("20:00");
        choice3.addItem("21:00");
        choice3.addItem("22:00");
        choice3.addItem("23:00");
        choice3.addItem("00:00");
       
        choice4.addItem("All");
        choice4.addItem("Any Attribute");
        choice4.addItem("City");
        choice4.addItem("State");
        choice4.addItem("Zip");
        choice4.addItem("Days of Week"); 
        
       jList1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //if(choice4.getSelectedItem().equals("All"))
                    String main_Cat;
                    if(e.isControlDown()){
                        List<String> mainCategory= jList1.getSelectedValuesList();
                        Iterator irt = mainCategory.iterator();
                        while(irt.hasNext()){
                        main_Cat = (String) irt.next();
                        System.out.println("Category:"+main_Cat);
                        if(!main_Category.contains(main_Cat))
                            main_Category.add(main_Cat);
                        }
                    } else{     
                        main_Category.clear();
                        jList2.removeAll();
                        jList2.revalidate();
                        jList2.repaint();
                        
                        jList3.removeAll();
                        jList3.revalidate();
                        jList3.repaint();
                        
                        jList4.removeAll();
                        jList4.revalidate();
                        jList4.repaint();
                        
                        main_Cat = jList1.getSelectedValue().toString();
                        main_Category.add(main_Cat);
                        System.out.println("Refresh Category");
                    }
                System.out.println("Main Category");
                String resultCat = "";
                Iterator itrSub = main_Category.iterator();
                while(itrSub.hasNext()){
                    if(resultCat.equals(""))
                        resultCat+=itrSub.next();
                    else
                        resultCat+="'"+" OR c.c_category ='"+itrSub.next();
                    
                }
                    
                    
                     
                                
                    Object[] str2 = new Object[5000];
                    String populateSubCategory = "select distinct s.c_subcategory from BUSINESS_SUBCATEGORY s,BUSINESS_CATEGORY c " +
                                                 "where (c.c_category= '"+resultCat+"')"+" and " +
                                                 "c.bid = s.bid";
                    jdbcConnectivity jdbc = new jdbcConnectivity();
                    jdbc.connect();
                    int i=0;
                    try{
                        PreparedStatement psSelSubCat = jdbc.conn.prepareStatement(populateSubCategory);
                        ResultSet rs = jdbc.select(psSelSubCat);
                        while(rs.next()){
                            String result = rs.getString(1);
                            str2[i] = result;
                            i++;
                        }
                        jList2.setListData(str2);
                        jList2.revalidate();
                        jList2.repaint();
                    }catch(Exception exp){}
                    finally{
                        jdbc.close();
                    }
                 
                
            }

            @Override
            public void mousePressed(MouseEvent e) {           }
            @Override
            public void mouseReleased(MouseEvent e) {            }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        
        
        jList2.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //if(choice4.getSelectedItem().equals("All")){
                String sub;
                if(e.isControlDown()){
                    System.out.println("Multiple Subcategory"); 
                    List<String> subcat = jList2.getSelectedValuesList();
                    Iterator itr = subcat.iterator();
                    while(itr.hasNext()){
                        sub = (String) itr.next();
                        System.out.println("Subcategory:"+sub);
                        if(!subCategory.contains(sub))
                            subCategory.add(sub);
                    }
                    //sub = jList2.getSelectedValue().toString();
                } else{     
                    subCategory.clear();
                    sub = jList2.getSelectedValue().toString();
                    subCategory.add(sub);
                    System.out.println("Refresh Subcategory");
                }
                
                String resultCat = "";
                Iterator itrCat = main_Category.iterator();
                while(itrCat.hasNext()){
                    if(resultCat.equals(""))
                        resultCat+=itrCat.next();
                    else
                        resultCat+="'"+" OR cat.c_category ='"+itrCat.next();
                    //jList1.removeAll();   
                }
                
                String resultSubCat = "";
                Iterator itrSub = subCategory.iterator();
                while(itrSub.hasNext()){
                    if(resultSubCat.equals(""))
                        resultSubCat+=itrSub.next();
                    else
                        resultSubCat+="'"+" OR subcat.c_subcategory ='"+itrSub.next();
                    //jList3.removeAll();   
                }
                Object[] Attr = new Object[5000];
                int j=0;
                jdbcConnectivity jdbc = new jdbcConnectivity();
                jdbc.connect();
                try{
                    String rtvAttr = "select distinct attr.business_attribute from business_category cat,business_subcategory subcat, business_attribute attr " +
                    "where cat.BID = subcat.bid " +
                    "and cat.BID = attr.business_id " +
                    "and subcat.BID = attr.business_id " +
                    "and (cat.c_category = '"+resultCat+"') " +
                    "and (subcat.c_subcategory= '"+resultSubCat+"')";
                    System.out.println("Attr q:"+rtvAttr);
                    PreparedStatement psSelAObj = jdbc.conn.prepareStatement(rtvAttr);
                    ResultSet rsAttr = jdbc.select(psSelAObj);
                    if(rsAttr != null){
                        while(rsAttr.next()){
                               String attribute = rsAttr.getString(1);
                               Attr[j] = attribute;
                               j++;
                        }
                        jList3.setListData(Attr);
                        jList3.revalidate();
                        jList3.repaint();
                    }
                }catch(Exception exp){}
                finally{
                    jdbc.close();
                }
                //}
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) {  }
            @Override
            public void mouseEntered(MouseEvent e) {   }
            @Override
            public void mouseExited(MouseEvent e) {    }
        });
        
        jList3.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) { 
            
                String att;
                //if(choice4.getSelectedItem().equals("All")){

                if(e.isControlDown()){
                    System.out.println("Multiple Attrubute"); 
                    List<String> att_selected = jList3.getSelectedValuesList();
                    Iterator itr = att_selected.iterator();
                    while(itr.hasNext()){
                        att = (String) itr.next();
                        System.out.println("Attribute:"+att);
                        if(!attribute.contains(att))
                            attribute.add(att);
                    }
                    //sub = jList2.getSelectedValue().toString();
                }
                 else{     
                    attribute.clear();
                    att = jList3.getSelectedValue().toString();
                    attribute.add(att);
                    System.out.println("Refresh Subcategory");
                
                }  
             
               
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });
        
        jList4.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                   if(choice4.getSelectedItem().equals("City")){
                    
                    
                   search_city = jList4.getSelectedValue().toString();
                   System.out.println("City got from list:"+search_city);
                   /*jList4.removeAll();
                   jList4.revalidate();
                   jList4.repaint();*/
               } else if(choice4.getSelectedItem().equals("State")){
                    if(jList4 == null)
                          System.out.println("JLIst 4 is null");
                else{
                        try{
                   search_state = jList4.getSelectedValue().toString();
                   System.out.println("State from list:"+search_state);
                         }catch(Exception exp) { System.out.print("Stack trace:"+exp.getMessage());}
                    }
                   /*jList4.removeAll();
                   jList4.revalidate();
                   jList4.repaint();*/
               } else if(choice4.getSelectedItem().equals("Zip")){
                   search_zip = jList4.getSelectedValue().toString();
                   System.out.println("Zip from list:"+search_zip);
                   /*jList4.removeAll();
                   jList4.revalidate();
                   jList4.repaint();*/
               }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
        /*choice1.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) { 
            String day = choice1.getItem(WIDTH);
            
            
            }

            @Override
            public void mousePressed(MouseEvent e) { }

            @Override
            public void mouseReleased(MouseEvent e) { }

            @Override
            public void mouseEntered(MouseEvent e) { }

            @Override
            public void mouseExited(MouseEvent e) { }
        });*/
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
//    @SuppressWarnings("unchecked");
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList();
        jScrollPane6 = new javax.swing.JScrollPane();
        jList4 = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        choice1 = new java.awt.Choice();
        choice2 = new java.awt.Choice();
        choice3 = new java.awt.Choice();
        choice4 = new java.awt.Choice();
        jButton3 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jList1.setBackground(new java.awt.Color(102, 255, 255));
        jList1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jList1.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Active Life", "Arts & Entertainment", "Automotive", "Car Rental", "Cafes", "Beauty & Spas", "Convenience Stores", "Dentists", "Doctors", "Drugstores", "Department Stores", "Education", "Event Planning & Services", "Flowers & Gifts", "Food", "Health & Medical", "Home Services", "Home & Garden", "Hospitals", "Hotels & Travel", "Hardware Stores", "Grocery", "Medical Centers", "Nurseries & Gardening", "Nightlife", "Restaurants", "Shopping", "Transportation" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jList2.setBackground(new java.awt.Color(51, 255, 255));
        jList2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jScrollPane3.setViewportView(jList2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3)
        );

        jList3.setBackground(new java.awt.Color(51, 255, 255));
        jList3.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jScrollPane4.setViewportView(jList3);

        jList4.setBackground(new java.awt.Color(0, 255, 255));
        jScrollPane6.setViewportView(jList4);

        jLabel5.setText("CITY/STATE/ZIP");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane6)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 10, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Business", "City", "State", "Stars"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTable1MousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("Search");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Close");

        jLabel1.setText("Day of the Week");

        jLabel2.setText("From");

        jLabel3.setText("To");

        jLabel4.setText("Search For");

        choice1.setName(""); // NOI18N
        choice1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice1ItemStateChanged(evt);
            }
        });

        choice4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                choice4ItemStateChanged(evt);
            }
        });

        jButton3.setText("Start");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Review Date", "Stars", "Review Text", "User Id", "Useful Votes"
            }
        ));
        jScrollPane5.setViewportView(jTable2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(choice1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(choice3, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(choice4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 18, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton3)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(94, 94, 94))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1)
                        .addComponent(jButton2)
                        .addComponent(jButton3))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(choice1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choice2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choice3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(choice4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(63, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MousePressed
        // TODO add your handling code here:
        int index = jTable1.getSelectedRow();
        currBusiness = businessIDs.get(index);
    }//GEN-LAST:event_jTable1MousePressed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(!currBusiness.equals("")){
             //new Reviews(currBusiness).setVisible(true); 
             
             if(currBusiness != ""){
            
            System.out.println("BID:"+currBusiness);
            
            
            jdbcConnectivity jdbc = new jdbcConnectivity();
            String reviewSql = " select distinct dat, stars, text, user_id, usefulvotes from Review where business_id='"+currBusiness+"'";
            
            try{
                
                int count=0;
                //jTable1 = new javax.swing.JTable();
                DefaultTableModel Rtable = new DefaultTableModel();
                Rtable.addColumn("Review Date");
                Rtable.addColumn("Stars");
                Rtable.addColumn("Review Text");
                Rtable.addColumn("User ID");
                Rtable.addColumn("Useful Votes");
                
                jdbc.connect();
                
                PreparedStatement psReview = jdbc.conn.prepareStatement(reviewSql);
                ResultSet resReview= jdbc.select(psReview);
                
                while( resReview.next()){
                    
                    Vector<String> row= new Vector<>();
                    row.add(resReview.getString(1));
                    row.add(""+resReview.getFloat(2));
                    row.add(resReview.getString(3));
                    row.add(resReview.getString(4));
                    row.add(""+resReview.getFloat(5));
                    
                    Rtable.insertRow(count++, row);
                    System.out.println(count+" Row:"+row.toString());
                }
                
                    jTable2.setModel(Rtable);
                    jTable2.revalidate();
                    jTable2.repaint();
                
                
                
                //jTable1.setRowSelectionAllowed(true);
                //jTable1.setColumnSelectionAllowed(false);
                
            } catch (Exception exp){
            
                System.out.println("Insertion failed"+exp.getMessage());
                exp.printStackTrace();
            }finally{
                
                try{
                    jdbc.close();
                }catch(Exception exp){}
            }
            currBusiness = "";
        }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int j=0;
                dmTable = new DefaultTableModel();
                dmTable.addColumn("Business"); 
                dmTable.addColumn("City");
                dmTable.addColumn("State");
                dmTable.addColumn("Stars");
                jdbcConnectivity jdbc = new jdbcConnectivity();
                jdbc.connect();
                String rtvAttr = "";
        if(choice4.getSelectedItem().equals("All")){
            if(attribute.size() > 0 && subCategory.size() > 0){
                String resultAttribute = "";
                Iterator itrATT = attribute.iterator();
                while(itrATT.hasNext()){
                    if(resultAttribute.equals(""))
                        resultAttribute+=itrATT.next();
                    else
                        resultAttribute+="'"+" OR ba.business_attribute ='"+itrATT.next();
                    jList3.removeAll();   
                }
                
                String resultSubCat = "";
                Iterator itrSub = subCategory.iterator();
                while(itrSub.hasNext()){
                    if(resultSubCat.equals(""))
                        resultSubCat+=itrSub.next();
                    else
                        resultSubCat+="'"+" OR bsc.c_subcategory ='"+itrSub.next();
                    jList3.removeAll();   
                }
                
                String resultCat = "";
                Iterator itrCat = main_Category.iterator();
                while(itrCat.hasNext()){
                    if(resultCat.equals(""))
                        resultCat+=itrCat.next();
                    else
                        resultCat+="'"+" OR bc.c_category ='"+itrCat.next();
                    jList1.removeAll();   
                }
                
                
                
                    rtvAttr = "select distinct b.business_id,b.businessname, b.city, b.businessstate, b.stars from business b, business_category bc, business_subcategory bsc, business_attribute ba " +
                    "where b.business_id = bc.bid " +
                    "and b.business_id = bsc.BID " +
                    "and ba.Business_ID = b.business_id " +
                    "and (bc.c_category = '"+resultCat+"') " +
                    "and (bsc.c_subcategory= '"+resultSubCat+"') " +
                    "and (ba.business_attribute= '"+resultAttribute+"') ";
                    
                
        }
        } else if(choice4.getSelectedItem().equals("City")){
            System.out.println("City choice input");
            System.out.println("City:"+search_city);
            if(!search_city.equals("")){
                if(attribute.size() > 0 && subCategory.size() > 0){
                String resultAttribute = "";
                Iterator itrATT = attribute.iterator();
                while(itrATT.hasNext()){
                    if(resultAttribute.equals(""))
                        resultAttribute+=itrATT.next();
                    else
                        resultAttribute+="'"+" OR ba.business_attribute ='"+itrATT.next();
                    jList3.removeAll();   
                }
                
                String resultSubCat = "";
                Iterator itrSub = subCategory.iterator();
                while(itrSub.hasNext()){
                    if(resultSubCat.equals(""))
                        resultSubCat+=itrSub.next();
                    else
                        resultSubCat+="'"+" OR bsc.c_subcategory ='"+itrSub.next();
                    jList3.removeAll();   
                }
                
                String resultCat = "";
                Iterator itrCat = main_Category.iterator();
                while(itrCat.hasNext()){
                    if(resultCat.equals(""))
                        resultCat+=itrCat.next();
                    else
                        resultCat+="'"+" OR bc.c_category ='"+itrCat.next();
                    jList1.removeAll();   
                }
                rtvAttr =  "select distinct b.business_id,b.businessname, b.city, b.businessstate, b.stars from business b, business_category bc, business_subcategory bsc, business_attribute ba " +
                    "where b.business_id = bc.bid " +
                    "and b.city='"+search_city+"'"+
                    "and b.business_id = bsc.BID " +
                    "and ba.Business_ID = b.business_id " +
                    "and (bc.c_category = '"+resultCat+"') " +
                    "and (bsc.c_subcategory= '"+resultSubCat+"') " +
                    "and (ba.business_attribute= '"+resultAttribute+"') ";
                }        
            }
        } else if(choice4.getSelectedItem().equals("State")){
            System.out.println("State choice input");
            System.out.println("State:"+search_state);
            if(!search_state.equals("")){
                if(attribute.size() > 0 && subCategory.size() > 0){
                String resultAttribute = "";
                Iterator itrATT = attribute.iterator();
                while(itrATT.hasNext()){
                    if(resultAttribute.equals(""))
                        resultAttribute+=itrATT.next();
                    else
                        resultAttribute+="'"+" OR ba.business_attribute ='"+itrATT.next();
                    jList3.removeAll();   
                }
                
                String resultSubCat = "";
                Iterator itrSub = subCategory.iterator();
                while(itrSub.hasNext()){
                    if(resultSubCat.equals(""))
                        resultSubCat+=itrSub.next();
                    else
                        resultSubCat+="'"+" OR bsc.c_subcategory ='"+itrSub.next();
                    jList3.removeAll();   
                }
                
                String resultCat = "";
                Iterator itrCat = main_Category.iterator();
                while(itrCat.hasNext()){
                    if(resultCat.equals(""))
                        resultCat+=itrCat.next();
                    else
                        resultCat+="'"+" OR bc.c_category ='"+itrCat.next();
                    jList1.removeAll();   
                }
                
                rtvAttr = "select distinct b.business_id,b.businessname, b.city, b.businessstate, b.stars from business b, business_category bc, business_subcategory bsc, business_attribute ba " +
                    "where b.business_id = bc.bid " +
                    "and b.businessstate='"+search_state+"'"+
                    "and b.business_id = bsc.BID " +
                    "and ba.Business_ID = b.business_id " +
                    "and (bc.c_category = '"+resultCat+"') " +
                    "and (bsc.c_subcategory= '"+resultSubCat+"') " +
                    "and (ba.business_attribute= '"+resultAttribute+"') ";
            }    } 
        } else if(choice4.getSelectedItem().equals("Zip")){
            System.out.println("Zip choice input");
            System.out.println("Zip:"+search_zip);
            if(!search_zip.equals("")){
                
                if(attribute.size() > 0 && subCategory.size() > 0){
                String resultAttribute = "";
                Iterator itrATT = attribute.iterator();
                while(itrATT.hasNext()){
                    if(resultAttribute.equals(""))
                        resultAttribute+=itrATT.next();
                    else
                        resultAttribute+="'"+" OR ba.business_attribute ='"+itrATT.next();
                    jList3.removeAll();   
                }
                
                String resultSubCat = "";
                Iterator itrSub = subCategory.iterator();
                while(itrSub.hasNext()){
                    if(resultSubCat.equals(""))
                        resultSubCat+=itrSub.next();
                    else
                        resultSubCat+="'"+" OR bsc.c_subcategory ='"+itrSub.next();
                    jList3.removeAll();   
                }
                
                String resultCat = "";
                Iterator itrCat = main_Category.iterator();
                while(itrCat.hasNext()){
                    if(resultCat.equals(""))
                        resultCat+=itrCat.next();
                    else
                        resultCat+="'"+" OR bc.c_category ='"+itrCat.next();
                    jList1.removeAll();   
                }
                
                rtvAttr = "select distinct b.business_id,b.businessname, b.city, b.businessstate, b.stars from business b, business_category bc, business_subcategory bsc, business_attribute ba " +
                    "where b.business_id = bc.bid " +
                    "and b.full_address like '%"+search_zip+"'"+
                    "and b.business_id = bsc.BID " +
                    "and ba.Business_ID = b.business_id " +
                    "and (bc.c_category = '"+resultCat+"') " +
                    "and (bsc.c_subcategory= '"+resultSubCat+"') " +
                    "and (ba.business_attribute= '"+resultAttribute+"') ";
                }
            }     
        } else if(choice4.getSelectedItem().equals("Days of Week")){
            
            if(!search_day.equals("")){
                dmTable = new DefaultTableModel();
                dmTable.addColumn("Business"); 
                dmTable.addColumn("City");
                dmTable.addColumn("State");
                dmTable.addColumn("Stars");
                PreparedStatement psSearchOpt;
                ResultSet resSearchOpt;
                
                    try{
                        
                        String day= "select distinct b.businessname,b.city,b.businessstate,b.stars from business_hours bh, business b where bh.business_day='"+search_day+"'"
                                + "  and bh.business_id = b.business_id";
                        System.out.println("search day:"+day);
                        psSearchOpt = jdbc.conn.prepareStatement(day);
                        resSearchOpt = jdbc.select(psSearchOpt);
                        int itr=0;
                        
                        while(resSearchOpt.next()){
                                String name = resSearchOpt.getString(1);
                                String city = resSearchOpt.getString(2);
                                String state = resSearchOpt.getString(3);
                                float stars = resSearchOpt.getFloat(4);
                                
                               Vector<String> row=new Vector<>();   
                               row.add(name);
                               row.add(city);
                               row.add(state);
                               row.add(""+stars);
                               dmTable.insertRow(itr++,row); 
                        }
                            jTable1.setModel(dmTable);
                            jTable1.setRowSelectionAllowed(true);
                            jTable1.setColumnSelectionAllowed(false);
                    }catch(Exception exp) { exp.printStackTrace();}
            } 
        }
        else
            JOptionPane.showMessageDialog(rootPane, "Please select search criteria");
        try{    
                if(!choice4.getSelectedItem().equals("Days of Week")){
                        System.out.println("Business:"+rtvAttr);
        PreparedStatement psSelBObj = jdbc.conn.prepareStatement(rtvAttr);
        ResultSet rsBObj = jdbc.select(psSelBObj);
        if(rsBObj != null){
            while(rsBObj.next()){
                   /*String attribute = rsBObj.getString(1);
                   Attr[j] = attribute;
                   j++;*/
                    String businessID = rsBObj.getString(1);
                    businessIDs.add(businessID);
                   Vector<String> row=new Vector<>();
                   row.add(rsBObj.getString(2));
                   row.add(rsBObj.getString(3));
                   row.add(rsBObj.getString(4));
                   row.add(""+rsBObj.getFloat(5));
                   dmTable.insertRow(j++,row); 
            }
            /*jList3.setListData(Attr);
            jList3.revalidate();
            jList3.repaint();*/
            jTable1.setModel(dmTable);
            jTable1.setRowSelectionAllowed(true);
            jTable1.setColumnSelectionAllowed(false);
                }
        
        }
    }
    catch (Exception exp){}
    finally{
        jdbc.close();}
        search_city = "";
        search_state="";
        search_zip="";
        search_day="";
    }//GEN-LAST:event_jButton1ActionPerformed

    private void choice4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice4ItemStateChanged
        // TODO add your handling code here:
        search_criteria = evt.getItem().toString();
        System.out.println("Event ouptput:"+search_criteria);
        jdbcConnectivity jdbc = new jdbcConnectivity();
        jdbc.connect();
        
        PreparedStatement psSearchOpt;
        ResultSet resSearchOpt;
        jTable1.removeAll();
        jTable2.removeAll();
        
        if(search_criteria.equals("City") || search_criteria.equals("State") || search_criteria.equals("Zip") || search_criteria.equals("Any Attribute") || search_criteria.equals("Days of Week")){
            
            jList4.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION); 
            Object []obj = new Object[5000];
            int itr = 0;
            switch(search_criteria){
                
                case "City":{
                        search_zip=""; search_state=""; search_day="";
                        System.out.println("City criteria mode");
                        try{
                           
                            String CityQuery = "select distinct city from business";
                            psSearchOpt = jdbc.conn.prepareStatement(CityQuery);
                            resSearchOpt = jdbc.select(psSearchOpt);
                            
                            while (resSearchOpt.next()){
                                String city = resSearchOpt.getString(1);
                                obj[itr] = city;
                                itr++;
                            }
                            System.out.println("Distinct cities:"+itr);
                            
                            jLabel5.setText("CITY");
                            jList4.setListData(obj); 
                            //search_city=jList4.getSelectedValue().toString();
                            //System.out.println("City slected"+search_city);
                            jList4.revalidate();
                            jList4.repaint();
                        }catch(Exception ex){}   
                    break;
                }
                case "State":{
                    search_zip=""; search_city="";search_day="";
                    System.out.println("State Criteria Mode");
                    try{
                        
                            String StateQuery = "select distinct businessstate from business";
                            psSearchOpt = jdbc.conn.prepareStatement(StateQuery);
                            resSearchOpt = jdbc.select(psSearchOpt);

                            while(resSearchOpt.next()){
                                String State = resSearchOpt.getString(1);
                                obj[itr]= State;
                                itr++;
                            }
                            jLabel5.setText("STATE");
                            jList4.setListData(obj);
                            jList4.revalidate();
                            jList4.repaint();

                        }
                        catch(Exception exp) { }
                    break;
                }
                
                case "Zip":{
                    search_city=""; search_state=""; search_day="";
                    System.out.println("Zip Criteria Mode");
                    try{
                        
                        String address = "select distinct full_address from business";
                        psSearchOpt = jdbc.conn.prepareStatement(address);
                        resSearchOpt = jdbc.select(psSearchOpt);
                        
                        while(resSearchOpt.next()){
                                String result = resSearchOpt.getString(1);
                                String []c= result.split(" ");
                                int length= c.length;
                                String zip= c[length-1];
                                obj[itr]=zip;
                                itr++;
                                
                        }
                    }catch(Exception exp) { }
                    jLabel5.setText("ZIP");
                    jList4.setListData(obj);
                    jList4.revalidate();
                    jList4.repaint();
                    break;
                }
                
                case "Days of Week":{
                    search_city=""; search_state=""; search_zip="";
                    System.out.println("Day of week criteria code");
                    break;
                }
                    
            }
        } else if(search_criteria.equals("All")){
                jList2.removeAll();
                jList2.revalidate();
                jList2.repaint();
                jList3.removeAll();
                jList3.revalidate();
                //jList4.repaint();
                //jList4.removeAll();
                //jList4.revalidate();
                //jList4.repaint();
                jList3.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
        } else 
            JOptionPane.showMessageDialog(rootPane, "Please select search criteria");
        
    }//GEN-LAST:event_choice4ItemStateChanged

    private void choice1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_choice1ItemStateChanged
        // TODO add your handling code here:
        
        search_day= choice1.getSelectedItem();
    }//GEN-LAST:event_choice1ItemStateChanged

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Start.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Start().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Choice choice1;
    private java.awt.Choice choice2;
    private java.awt.Choice choice3;
    private java.awt.Choice choice4;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jList1;
    private javax.swing.JList jList2;
    private javax.swing.JList jList3;
    private javax.swing.JList jList4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    // End of variables declaration//GEN-END:variables
}
