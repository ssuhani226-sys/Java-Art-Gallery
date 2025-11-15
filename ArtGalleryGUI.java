import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; 
import javax.swing.*;//it calls array list 
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.FlowLayout;
import javax.swing.table.*;
import java.io.*;
import java.awt.event.WindowAdapter;
import java.awt.Font;

public class ArtGalleryGUI extends JFrame {
    private JTextField idField, nameField, contactField, ticketPriceField;
    private JRadioButton male, female, other;
    private ButtonGroup genderGroup;
    private JComboBox<String> dayBox, monthBox, yearBox, ticketTypeBox;
    private JTextField purIdField, artNameField, artPriceField;
    private JButton addBtn, clearBtn, displayBtn, saveBtn;
    private JButton cancelBtn, buyBtn, discountBtn, rewardBtn, generateBillBtn, logBtn, advisorBtn, upgradeBtn;
    private double ArtworkPrice;
    private ArrayList<ArtGalleryVisitor> visitorList = new ArrayList<>();//to connect array list from art gallery

    public ArtGalleryGUI() {
        setTitle("ArtGallerySystem");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 950);
        setLayout(null);
        getContentPane().setBackground(new Color(235, 245, 238));
        

        

        JLabel addVisitorsLabel = new JLabel("Add Visitors");
        addVisitorsLabel.setBounds(30, 20, 200, 30);//(x,y,width,height)
        add(addVisitorsLabel);

        JLabel idLabel = new JLabel("Visitor ID:");
        idLabel.setBounds(30, 60, 100, 25);
        add(idLabel);
        idField = new JTextField();
        idField.setBounds(140, 60, 180, 25);
        add(idField);

        JLabel nameLabel = new JLabel("Full Name:");
        nameLabel.setBounds(30, 100, 100, 25);
        add(nameLabel);
        nameField = new JTextField();
        nameField.setBounds(140, 100, 180, 25);
        add(nameField);

        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(30, 140, 100, 25);
        add(genderLabel);
        male = new JRadioButton("Male");
        male.setBounds(140, 140, 70, 25);
        add(male);
        female = new JRadioButton("Female");
        female.setBounds(220, 140, 80, 25);
        add(female);
        other = new JRadioButton("Other");
        other.setBounds(300, 140, 80, 25);
        add(other);

        genderGroup = new ButtonGroup();
        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        JLabel regLabel = new JLabel("Registration Date:");
        regLabel.setBounds(30, 180, 120, 25);
        add(regLabel);
        dayBox = new JComboBox<>();
        String[] days = new String[31];
        for (int i = 1; i <= 31; i++) days[i-1] = String.valueOf(i);
        dayBox= new JComboBox<>(days);

        monthBox = new JComboBox<>(new String[]{
                "Jan", "Feb", "Mar", "Apr", "May", 
                "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"});

        yearBox= new JComboBox<>();
        String[] years = new String[50];
        for (int i = 0; i < 50; i++) years[i] = String.valueOf(1980 + i);
        yearBox = new JComboBox<>(years);

        //For  panel
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(dayBox);
        datePanel.add(monthBox);
        datePanel.add(yearBox);
        datePanel.setBackground(new Color(191, 216, 210));
        datePanel.setBounds(140, 180, 260, 30);
        datePanel.setOpaque(true);
        add(datePanel);

        JLabel contactLabel = new JLabel("Contact Number:");
        contactLabel.setBounds(30, 220, 120, 25);
        add(contactLabel);
        contactField = new JTextField();
        contactField.setBounds(140, 220, 180, 25);
        add(contactField);

        JLabel typeLabel = new JLabel("Ticket Type:");
        typeLabel.setBounds(30, 260, 100, 25);
        add(typeLabel);
        ticketTypeBox = new JComboBox<>(new String[]{"Select", "Standard", "Elite"});
        ticketTypeBox.setBounds(140, 260, 180, 25);
        add(ticketTypeBox);

        JLabel priceLabel = new JLabel("Ticket Price:");
        priceLabel.setBounds(30, 300, 100, 25);
        add(priceLabel);
        ticketPriceField = new JTextField();
        ticketPriceField.setBounds(140, 300, 180, 25);
        ticketPriceField.setEditable(false);
        add(ticketPriceField);

        ticketTypeBox.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String type = (String)ticketTypeBox.getSelectedItem();
                    if (type.equals("Standard")){
                        ticketPriceField.setText("1000");
                    }else if(type.equals("Elite")){
                        ticketPriceField.setText("2000");
                    }else{
                        ticketPriceField.setText("0");
                    }
                }
            }
        );

        addBtn = new JButton("ADD");
        addBtn.setBounds(30, 340, 100, 30);
        add(addBtn);

        addBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        //it returns a string to integer
                        int id = Integer.valueOf(idField.getText());
                        String name = nameField.getText();
                        String fullName;//variable is declared but not assigned

                        if (name.isEmpty()) {
                            JOptionPane.showMessageDialog(null,"Please enter your name first.");}
                        //addressing mutliple if else statement  
                        String gender = male.isSelected() ? "Male" :
                            female.isSelected() ? "Female" :
                            other.isSelected() ? "Other" : "";
                        String contact = contactField.getText();//returns the value in its textfield
                        //gives the value selected
                        String regDate = (String) dayBox.getSelectedItem() + "/" +
                            (String) monthBox.getSelectedItem() + "/" +
                            (String) yearBox.getSelectedItem();
                        String ticketType = (String) ticketTypeBox.getSelectedItem();
                        //converts the value in double
                        double ticketCost = Double.valueOf(ticketPriceField.getText());

                        if (name.isEmpty() || gender.isEmpty() || contact.isEmpty() ||
                        ticketType.equals("Select") || ticketPriceField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Please fill all required fields.");
                            return;
                        }
                        //checks if the id already exists
                        boolean isExist = false;
                        for (ArtGalleryVisitor agv : visitorList) {
                            //loop checks every exisiting id
                            if (agv.getvisitorId() == id) {
                                JOptionPane.showMessageDialog(null, "Visitor Id already exists");
                                isExist = true;
                                break;
                            }
                        }
                        //if the id doesnt exists then this loop is implemented
                        if (!isExist) {
                            ArtGalleryVisitor visitor;
                            if (ticketType.equals("Standard")) {
                                visitor = new StandardVisitor(id, name, gender, contact, regDate, ticketCost, ticketType);
                            } else {
                                visitor = new EliteVisitor(id, name, gender, contact, regDate, ticketCost, ticketType);
                            }
                            visitorList.add(visitor);
                            JOptionPane.showMessageDialog(null, "Visitor Added Successfully!");
                        }

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(null, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(140, 340, 100, 30);
        add(clearBtn);   
        //calling ckearbtn method  
        clearBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    idField.setText("");
                    nameField.setText("");
                    contactField.setText("");
                    ticketPriceField.setText("");
                    male.setSelected(false);
                    female.setSelected(false);
                    other.setSelected(false);
                    ticketTypeBox.setSelectedIndex(0);
                    dayBox.setSelectedIndex(0);
                    monthBox.setSelectedIndex(0);
                    yearBox.setSelectedIndex(0);
                }
            });

        displayBtn = new JButton("Display");
        displayBtn.setBounds(250, 340, 100, 30);
        add(displayBtn);
        //adding method to call
        displayBtn.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    String[] column ={"Visitor Id","Full Name", "Contact","Gender","RegDate","Ticket Type", "Ticket Cost"};
                    DefaultTableModel model= new DefaultTableModel(column,0);
                    JTable table=new JTable(model);
                    model.setRowCount(0);
                    //from art gallery visitor
                    for(ArtGalleryVisitor details: visitorList){
                        model.addRow(new Object[]{details.getvisitorId(),
                                details.getfullName(), details.getcontactNo(),
                                details.getgender(),details.getregistrationDate(),
                                details.getticketType(),
                    details.getticketCost()
                  
                            });}
                    JDialog dialog = new JDialog();
                    dialog.setSize(500, 500);
                    JScrollPane scrollPane = new JScrollPane(table);
                    dialog.add(scrollPane);
                    dialog.setVisible(true);
                }

            });

        saveBtn = new JButton("Save");
        saveBtn.setBounds(30, 380, 100, 30);
        add(saveBtn);

        saveBtn.addActionListener(e -> {
                    try {
                        File f = new File("Visitors.txt");
                        BufferedWriter bw = new BufferedWriter(new FileWriter(f));

                        String header = String.format("%-10s %-15s %-10s %-15s %-12s %-12s %-12s %-10s\n",
                                "ID", "Name", "Gender", "Contact", "RegDate","TicketType", "TicketCost", "VisitCount");
                        bw.write(header);
                        bw.write("-".repeat(100) + "\n");

                        for (ArtGalleryVisitor v : visitorList) {
                            String row = String.format("%-10d %-15s %-10s %-15s %-12s %-12s %-12.2f %-10d\n",
                                    v.getvisitorId(), v.getfullName(),
                                    v.getgender(), v.getcontactNo(),
                                    v.getregistrationDate(),v.getticketType(),
                    v.getticketCost(),
                    v.getvisitCount());
                            bw.write(row);
                        }

                        bw.close();
                        JOptionPane.showMessageDialog(null, "File written successfully: " + f.getAbsolutePath());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error while writing to file");
                    }
            });

        JButton readBtn = new JButton("Read");
        readBtn.setBounds(140, 380, 100, 30); 
        add(readBtn);

        
            readBtn.addActionListener(
    new ActionListener(){
        public void actionPerformed(ActionEvent e){
            try {
                File f = new File("Visitors.txt");
                BufferedReader br = new BufferedReader(new FileReader(f));
                String line;
                StringBuilder sb = new StringBuilder();

                while((line = br.readLine()) != null){
                    sb.append(line).append("\n");
                }

                JTextArea txt = new JTextArea();
                txt.setEditable(false);
                txt.setText(sb.toString());
                txt.setFont(new Font("Monospaced", Font.PLAIN, 16)); 

                JScrollPane pane = new JScrollPane(txt);
                pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
 pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                JDialog jd = new JDialog();
                jd.setTitle("Reading from file");
                jd.add(pane);
                jd.setSize(800, 600); 
                jd.setLocationRelativeTo(null);
                jd.setVisible(true);

                br.close();
            } catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });


        JLabel purchaseLabel = new JLabel("Purchase Section");
        purchaseLabel.setBounds(30,440, 200, 25);
        add(purchaseLabel);

        JLabel purchaseIdLabel = new JLabel("Visitor ID:");
        purchaseIdLabel.setBounds(30, 470, 100, 25);
        add(purchaseIdLabel);
        purIdField = new 
        JTextField();
        purIdField.setBounds(140, 470, 180, 25);
        add(purIdField);

        JLabel artNameLabel = new JLabel("Artwork Name:");
        artNameLabel.setBounds(30, 510, 120, 25);
        add(artNameLabel);
        artNameField = new 
        JTextField();
        artNameField.setBounds(140, 510, 180, 25);
        add(artNameField);

        JLabel artPriceLabel = new JLabel("Artwork Price:");
        artPriceLabel.setBounds(30, 550, 120, 25);
        add(artPriceLabel);
        artPriceField = new 
        JTextField();
        artPriceField.setBounds(140, 550, 180, 25);
        add(artPriceField);

        logBtn = new JButton("Log Visitor");
        logBtn.setBounds(250, 380, 120, 30);
        add(logBtn);
        logBtn.addActionListener(new 
            ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        int visitorId= Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter visitor id"));
                        for(ArtGalleryVisitor v: visitorList){
                            if (v.getvisitorId() == visitorId){
                                v.logVisit();
                                JOptionPane.showMessageDialog(null,"You have Logged In Succesfully.");
                                return;
                            }

                        }
                        JOptionPane.showMessageDialog(null,"ID not found","Error",JOptionPane.ERROR_MESSAGE);}
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null,"Please enter your visitor Id.");}

                }
            });

        buyBtn = new JButton("Buy Product");
        buyBtn.setBounds(30, 590, 150, 30);
        add(buyBtn);
        buyBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Buy button clicked");
                    

                    try {   
                        if (artNameField.getText().isEmpty() || artPriceField.getText().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Fill the field.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        int visitorId = Integer.parseInt(purIdField.getText());
                        String artworkName = artNameField.getText();
                        double artworkPrice = Double.parseDouble(artPriceField.getText());

                        boolean found = false;

                        for (ArtGalleryVisitor v : visitorList) {
                            if (v.getvisitorId() == visitorId) {
                                String result= v.buyProduct(artworkName, artworkPrice);                
                                JOptionPane.showMessageDialog(null,result);
                                found=true;
                                break;
                            }
                        }

                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found.");
                        }

                    } catch (NumberFormatException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Invalid information. Please check the ID and price once again.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch(Exception ex){
                        JOptionPane.showMessageDialog(null, "Unexpected error: " , "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }});

        cancelBtn = new JButton("Cancel Product");
        cancelBtn.setBounds(200, 590, 150, 30);
        add(cancelBtn);

        cancelBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int visitorId = Integer.parseInt(purIdField.getText());
                        String artworkName = artNameField.getText();
                        String ticketCost = artPriceField.getText();
                        String cancellationReason = JOptionPane.showInputDialog("Enter your cancellation reason:");
                        for(ArtGalleryVisitor v: visitorList){
                            if(v.getvisitorId()==visitorId){
                                String msg=v.cancelProduct(artworkName, cancellationReason);
                                JOptionPane.showMessageDialog(null,msg);
                                return;
                            }
                        }
                        JOptionPane.showMessageDialog(null,"Id not found.");
                    }
                    catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Id not found.Please try again!","Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        JButton discountBtn = new JButton("Discount");
        discountBtn.setBounds(30, 630, 150, 30);
        add(discountBtn);discountBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        int visitorId = Integer.parseInt(purIdField.getText());
                        boolean visitorFound = false;

                        for (ArtGalleryVisitor v : visitorList) {
                            if (v.getvisitorId() == visitorId) {
                                System.out.println("Visitor found: isBought = " + v.getisBought());
                                if (!v.getisBought()) {
                                    JOptionPane.showMessageDialog(null, "Please purchase an artwork first.");
                                    return;
                                }
                                double discount = v.calculateDiscount();
                                double originalPrice = v.getartworkPrice();
                                double finalPrice = originalPrice - discount;
                                JOptionPane.showMessageDialog(null, "Discounted Price is Rs. " + finalPrice);
                                return;
                            }
                        }

                        if (!visitorFound) {
                            JOptionPane.showMessageDialog(null, "Visitor ID not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid visitor ID. Please try again!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

        JButton rewardBtn = new JButton("Reward");
        rewardBtn.setBounds(200, 630, 150, 30);
        add(rewardBtn);

        rewardBtn.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    try{
                        int visitorId= Integer.parseInt(purIdField.getText());
                        for(ArtGalleryVisitor v: visitorList){
                            if(v.getvisitorId()== visitorId){
                                double reward= v.calculateRewardPoints();
                                JOptionPane.showMessageDialog(null,"Your Reward Points is:   "+reward);
                                return;}}
                        JOptionPane.showMessageDialog(null," Id not found.");}
                    catch(NumberFormatException ex){
                        JOptionPane.showMessageDialog(null, "Id not found.Please try again!","Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

            });

        JButton generateBillBtn = new JButton("Generate Bill");
        generateBillBtn.setBounds(30, 670, 230, 30);
        generateBillBtn.setBackground(new Color(180, 220, 190));
        add(generateBillBtn);

       generateBillBtn.addActionListener(
    new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                String visitorIdStr = purIdField.getText(); // use purchase section ID field

                if(visitorIdStr.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter Visitor ID", "Warning", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int visitorId = Integer.parseInt(visitorIdStr);
                boolean found = false;

                for(ArtGalleryVisitor visitor : visitorList) {
                    if(visitor.getvisitorId() == visitorId) {
                        found = true;

                        // Check purchase
                        if(!visitor.getisBought()) {
                            JOptionPane.showMessageDialog(null, "No purchase made to generate bill", "Warning", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
    
                        // Make sure discount is calculated
                        visitor.calculateDiscount();

                        //Generate bill content
                        StringBuilder billContent = new StringBuilder();
                        billContent.append("===== ART GALLERY BILL =====\n");
                        billContent.append(String.format("%-15s: %d\n", "Visitor ID", visitor.getvisitorId()));
                        billContent.append(String.format("%-15s: %s\n", "Name", visitor.getfullName()));
                        billContent.append(String.format("%-15s: %s\n", "Artwork", visitor.getartworkName()));
billContent.append(String.format("%-15s: Rs. %.2f\n", "Original Price", visitor.getartworkPrice()));

                        billContent.append(String.format("%-15s: %s\n", "Artwork", visitor.getartworkName()));
                        billContent.append(String.format("%-15s: Rs. %.2f\n", "Original Price", visitor.getartworkPrice()));
                        billContent.append(String.format("%-15s: Rs. %.2f\n", "Discount", visitor.getdiscountAmount()));
                        billContent.append(String.format("%-15s: Rs. %.2f\n", "Final Price", visitor.getfinalPrice()));
                        billContent.append("=============================");

                        billContent.append(String.format("%-15s: %d\n", "Visit Count", visitor.getvisitCount()));

                        // Display in dialog
                        JTextArea txt = new JTextArea();
                        txt.setEditable(false);
                        txt.setText(billContent.toString());
                        txt.setFont(new Font("Monospaced", Font.PLAIN, 14));

                        JDialog jd = new JDialog();
                        jd.setTitle("Art Purchase Bill");
                        jd.setSize(400, 300);
                        jd.add(new JScrollPane(txt));
                        jd.setVisible(true);
  

                        // Save bill to file
                        try {
                            File billFile = new File("Bill_Visitor_" + visitorId + ".txt");
                            BufferedWriter bw = new BufferedWriter(new FileWriter(billFile));
                            bw.write(billContent.toString());
                            bw.close();
                            JOptionPane.showMessageDialog(null, "Bill saved to: " + billFile.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
                        } catch(IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error saving bill file", "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        break;
                    }
                }

                if(!found) {
                    JOptionPane.showMessageDialog(null, "Visitor ID not found", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch(NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numeric Visitor ID", "Error", JOptionPane.ERROR_MESSAGE);
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
);

        JLabel manageLabel = new JLabel("Manage Visitors");
        manageLabel.setBounds(420, 440, 200, 25);
        add(manageLabel);

        JButton advisorBtn = new JButton("Assign Personal Advisor");
        advisorBtn.setBounds(420, 470, 200, 30);
        add(advisorBtn);

        advisorBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        String id = JOptionPane.showInputDialog(null, "Please enter visitor ID");
                        if (id == null || id.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Visitor ID cannot be empty");
                            return;
                        }

                        int id1 = Integer.parseInt(id);
                        boolean found = false;

                        for (ArtGalleryVisitor a : visitorList) {
                            if (id1 == a.getvisitorId()) {
                                found = true;

                                if (a instanceof EliteVisitor) {
                                    EliteVisitor elite = (EliteVisitor) a;
                                    elite.getassignedPersonalArtAdvisor();
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "Personal Art Advisor assigned to Elite Visitor: " + a.getfullName()
                                    );
                                } else {
                                    JOptionPane.showMessageDialog(
                                        null,
                                        "This visitor is not an Elite visitor. Advisor cannot be assigned."
                                    );
                                }
                                break;
                            }
                        }

                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Visitor not found");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a valid numeric Visitor ID");
                    }
                }
            });

        upgradeBtn = new JButton("Check Discount Upgrade");
        upgradeBtn.setBounds(420, 510, 200, 30);
        add(upgradeBtn);
        upgradeBtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    try {
                        String idInput = JOptionPane.showInputDialog(null, "Enter Visitor ID:");
                        if (idInput == null || idInput.trim().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Visitor ID cannot be empty.");
                            return;
                        }

                        int visitorId = Integer.parseInt(idInput);
                        boolean found = false;

                        for (ArtGalleryVisitor v : visitorList) {
                            if (v.getvisitorId() == visitorId) {
                                found=true;
                                // Check if visitor is a Standard visitor
                                if (v instanceof StandardVisitor) {
                                    StandardVisitor standard = (StandardVisitor) v;
                                    if (standard.isEligibleForDiscountUpgrade()) {
                                        JOptionPane.showMessageDialog(null,
                                            "Visitor " + standard.getfullName() + " is eligible for upgrade .");
                                    } else {
                                        JOptionPane.showMessageDialog(null,
                                            "Visitor " + standard.getfullName() + " is NOT eligible for upgrade yet.");
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                        "This visitor is already Elite. Upgrade not required.");
                                }
                                break;
                            }
                        }

                        if (!found) {
                            JOptionPane.showMessageDialog(null, "Visitor not found.");
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid visitor ID entered.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Unexpected error: " + ex.getMessage());
                    }
                }
            });

        addWindowListener(
            new WindowAdapter(){
                @Override    
                public void  windowClosing(WindowEvent w){
                    int choice=JOptionPane.showConfirmDialog(null,"Do you want to save before exit?","Closing",JOptionPane.YES_NO_OPTION);
                    if(choice==JOptionPane.YES_OPTION){
                        System.exit(0);
                    }
                    else if(choice==JOptionPane.NO_OPTION){
                        System.exit(0);
                    }
                }
            });
        setVisible(true);
        //to locate in the center of the screen
        setLocationRelativeTo(null);

    }
}
