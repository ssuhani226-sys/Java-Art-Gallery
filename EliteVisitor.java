
public class EliteVisitor extends ArtGalleryVisitor{
    protected boolean assignedPersonalArtAdvisor;//new attribute
    protected boolean exclusiveEventAccess;//new attribute
    protected double rewardPoints;  
    protected double discountAmount;
    protected double finalPrice;
    protected double refundableAmount;
    

    public EliteVisitor(int visitorId,String fullName,String gender,String contactNo,String registrationDate,double ticketCost,String ticketType){
        super(visitorId,fullName,gender,contactNo,registrationDate,ticketCost,ticketType);
        this.assignedPersonalArtAdvisor=false;
        this.exclusiveEventAccess=false;
    }
    //each attribute with corresponding accesor method
    public int getvisitorId(){return visitorId;} 
    
    public String getfullName() {return fullName;}

    public String getgender(){return gender;}

    public String getcontactNo(){return contactNo;}

    public String getregistrationDate(){return registrationDate;}

    public double getticketost(){return ticketCost;}

    public String getticketType(){return ticketType;}

    public boolean getexclusiveEventAccess(){return exclusiveEventAccess;}
    //Implementing method boolean getassignedPersonalArtAdvisor()
    public boolean getassignedPersonalArtAdvisor(){
        if (rewardPoints>5000){
            assignedPersonalArtAdvisor=(true);
            return assignedPersonalArtAdvisor;
        }
        else{
            return assignedPersonalArtAdvisor;
        }
    }
    //Implementing boolean exclusiveEventAccess()
    public boolean exclusiveEventAccess(){
        if (assignedPersonalArtAdvisor){
            exclusiveEventAccess=true;}
        else{
            return exclusiveEventAccess;
        }
        return exclusiveEventAccess;
    }

    //Implementing String buyProduct(String artworkName,double artworkPrice method
    @Override
    public String buyProduct(String artworkName,double artworkPrice){
        if (isActive==false){
            return"Please login to activate your account";
        }if(isBought==false){
            this.artworkName=artworkName;
            this.artworkPrice=artworkPrice;
            isBought=true;
            buyCount++;
            return"Succcesful Purchase!";  
        }else{
            return"The artwork has already been purchased.";
        }
    }
    //Implementing double calculateDiscount() method 
    @Override
    public double calculateDiscount(){
        if(isBought==true){ 
            discountAmount=artworkPrice*0.40;
            finalPrice=artworkPrice-discountAmount;
            return discountAmount;
        }else{
            discountAmount=0;
        }
        return discountAmount;
    }
    //Implementing double calculateRewardPoints()
    @Override
    public double calculateRewardPoints(){
        {
            if (isBought==true) {
                calculateDiscount();  
                rewardPoints += finalPrice * 10;
            }
            return rewardPoints;
        }
    }

    //Implementing generateBill() method
    @Override
    public void generateBill(){
        if (isBought==false){
            System.out.println("No purchase made to generate a bill.");
        }else{           
            System.out.println("Customer Bill");
            System.out.println("Visitor ID: " +visitorId);
            System.out.println("Visitor Name: " +fullName);
            System.out.println("Artwork Name: " +artworkName);
            System.out.println("Artwork Price: " +artworkPrice);
            System.out.println("Discount: " +discountAmount);
            System.out.println("Final Price: " +finalPrice);
        }
    }
    //implementing terminateVisitor() method 
    private void terminateVisitor(){ //private access 
        isActive=false;
        assignedPersonalArtAdvisor=false;
        exclusiveEventAccess=false;
        visitCount=0;
        cancelCount=0;
        rewardPoints=0;
    }
    //implementing String cancelProduct(String artworkName,String cancellationReason) method 
    @Override
    public String cancelProduct(String artworkName,String cancellationReason){
        if (cancelCount>=3){
            terminateVisitor();
            return"Account has been terminated due to repeated cancellation";
        }
        if (buyCount==0){
            return"Cancellation is not accepted";
        }
        if(this.artworkName!=null && this.artworkName.equals(artworkName)){
            isBought=false;
            refundableAmount=artworkPrice-(artworkPrice*0.5);
            rewardPoints-=finalPrice*10;
            cancelCount++;
            buyCount--;
            this.cancellationReason=cancellationReason;
            return"Artwork"+artworkName+"has been cancelled with refunded amount of rs. "+refundableAmount;
        }else{return"The name of artwork is incorrect.";}
    }
    //implementing display()method
    public void display(){
        super.display();
        System.out.println("Assigned Personal Advisor:  "+assignedPersonalArtAdvisor);
        System.out.println("Exclusive Event Access    "+exclusiveEventAccess); 
        System.out.println("Reward Points: " + rewardPoints);
        System.out.println("Discount: " + discountAmount);
    }
    //For Activation Output this is used.It can be erased to have not active output.    
    public void activate() {
        this.isActive = true;
    } 

    public void bought(){
        this.isBought=true;
    }
}

        


    
    