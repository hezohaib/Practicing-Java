package learning_oop;
public class phone {
    private String Brand ;
    private String Color ;
    private int Price ;

    public void setBrand ( String Brand ) {
        this.Brand = Brand ;
    }
    public void setColor ( String Color ) {
        this.Color = Color ;
    }
    public void setPrice ( int Price ) {
        this.Price = Price ;
    }
    public String getBrand () {
        return Brand ;
    }
    public String getColor (){
        return Color ;
    }
    public int getPrice () {
        return Price ;
    }
    phone ( String Brand , String Color , int Price ) {
        this.Brand = Brand ;
        this.Color = Color ;
        this.Price = Price ;
    }
    public static void main(String[] args) {
        phone p = new phone ( "Iphone 14 Pro Max", "Midnight Black", 1999);

        System.out.println("Brand:" + p.getBrand());
        System.out.println("Color:" + p.getColor());
        System.out.println("Price:$" + p.getPrice());
    }

}
