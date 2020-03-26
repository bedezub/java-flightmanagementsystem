// Prescribed by zubquzaini
import java.text.DecimalFormat;

public class Client extends User {
   
    private String code;
    private int numOfTicket;
    private String promoCode;
    private double payment;
    DecimalFormat df = new DecimalFormat("#,###,##0.00");
    
    public Client() {
        
        code = null;
        numOfTicket = 0;
        promoCode = null;
        payment = 0.0;
    }
    
    public void setData(String u, String pn) {
        super.setData(u, pn);
    }
    
    public void setBook(String c, int num) {
        code=c;
        numOfTicket = num;
    }
    
    public void setPromo(String p) {
        promoCode = p;
    }
    
    public void setPayment(double p) {
        payment = p;
    }
    
    public String getCodes() {
        return code;
    }
    
    public int getNumOfTicket ()
    {
        return numOfTicket;
    }

    public String getPromo()
    {
        return promoCode;
    }
    
    public double getPayment()
    {
        return payment;
    }
    
    public double getPrices() {
        double prices [] = super.getPrice();
        return prices[getKey(code)];
    }
    
    public int getTimes() {
        int times [] = super.getTime();
        return times[super.getKey(code)];
    }
    
    public String getDates() {
        String date [] = super.getDate();
        return date[super.getKey(code)];        
    }
    
    public String getDepart() {
        String depart[] = super.getDeparture();
        return depart[super.getKey(code)];  
    }
    
    public String getArrivals() {
        String arrival[] = super.getArrival();
        return arrival[super.getKey(code)];      
    }
    
    public double calcTotal()
    {
        double total=0.0;
        double prices [] = super.getPrice();
        
        total=prices[super.getKey(code)] * numOfTicket;
        return total;
    }
    
    public double calcDiscount()
    {
        double priceAfterDiscount=0.0;
        String promoCode = getPromo(); 
        
        if(promoCode.equalsIgnoreCase("Superfly"))
        {
            priceAfterDiscount=calcTotal() * 0.95;
        } else {
            priceAfterDiscount=calcTotal();
        }
 
        return priceAfterDiscount; 
    }
    
    public double calcBalance() {
        double balance = 0.00;
        balance=payment-calcDiscount();
        return balance;
    }
    
    public String saveReceipt() {
        
        String display = "";
        display = display + ("******************************\n");
        display = display + ("Fly Malaya Booking Receipt\n");
        display = display + ("******************************\n");
        display = display + ("Name:" + getUsername() + "\n");
        display = display + ("Phone Number:" + getPhoneNum() + "\n");
        display = display + ("Code:" + getCodes() + "\n");
        display = display + ("Number of ticket:" + getNumOfTicket() + "\n");
        display = display + ("Price:RM" + getPrices() + "\n");
        display = display + ("Date:" + getDates() + "\n");
        display = display + ("Time:" + getTimes() + "\n");
        display = display + ("Depart:" + getDepart() + "\n");
        display = display + ("Arrival:" + getArrivals() + "\n");
        display = display + ("******************************\n");
        display = display + ("Price: RM" + calcTotal() + "\n");
        display = display + ("Price after discount:RM" +  calcDiscount() + "\n");
        display = display + ("Payment:RM" + getPayment() + "\n");
        display = display + ("Balance:RM" + df.format(calcBalance()) + "\n");
        display = display + ("******************************\n");
        display = display + ("THANK YOU PLEASE COME AGAIN\n\n");
        
        return display;
    }
    
    public String printReceipt() {
        
        String display = "<html>";
        display = display + ("******************************<br>");
        display = display + ("Fly Malaya Booking Receipt<br>");
        display = display + ("******************************<br>");
        display = display + ("Name:" + getUsername() + "<br>");
        display = display + ("Phone Number:" + getPhoneNum() + "<br>");
        display = display + ("Code:" + getCodes() + "<br>");
        display = display + ("Number of ticket:" + getNumOfTicket() + "<br>");
        display = display + ("Price:RM" + getPrices() + "<br>");
        display = display + ("Date:" + getDates() + "<br>");
        display = display + ("Time:" + getTimes() + "<br>");
        display = display + ("Depart:" + getDepart() + "<br>");
        display = display + ("Arrival:" + getArrivals() + "<br>");
        display = display + ("******************************<br>");
        display = display + ("Price: RM" + calcTotal() + "<br>");
        display = display + ("Price after discount:RM" +  calcDiscount() + "<br>");
        display = display + ("Payment:RM" + getPayment() + "<br>");
        display = display + ("Balance:RM" + df.format(calcBalance()) + "<br>");
        display = display + ("******************************<br>");
        display = display + ("THANK YOU PLEASE COME AGAIN<br>");
        display = display + ("</html>");
        return display;
    }
}