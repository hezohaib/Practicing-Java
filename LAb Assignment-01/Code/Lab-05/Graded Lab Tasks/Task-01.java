class Address {
    private int street;
    private int house;
    private String city;
    private int code;
    public void setStreet(int s) {
        street = s;
    }
    public void setHouse(int h) {
        house = h;
    }
    public void setCity(String c) {
        city = c;
    }
 public void setCode(int c) {
        code = c;
    }
    public int getStreet() {
        return street;
    }
    public int getHouse() {
        return house;
    }
    public String getCity() {
        return city;
    }
    public int getCode() {
        return code;
    }
    public void display() {
        System.out.println("Street: " + street + ", House: " + house + ", City: " + city + ", Code: " + code);
    }
}
class Person {
    private String name;
    private Address address;
    public Person() {
        address = new Address();
    }
    public void setName(String n) {
        name = n;
    }
 public void setAddress(Address a) {
        address = a;
    }
    public String getName() {
        return name;
    }
    public Address getAddress() {
        return address;
    }
    public void display() {
        System.out.println("Name: " + name);
        address.display();
    }
}
class Runner {
    public static void main(String[] args) {
        Person p = new Person();
        p.setName("Ali");
        Address a = new Address();
        a.setStreet(5);
        a.setHouse(12);
        a.setCity("Lahore");
        a.setCode(54000);
        p.setAddress(a);
        p.display();
    }
}
