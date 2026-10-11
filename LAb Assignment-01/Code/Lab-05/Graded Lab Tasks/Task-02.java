// Task 1: Address Class
class Address {
    private int street;
    private int house;
    private String city;
    private int code;

    public void setStreet(int street) {
        this.street = street;
    }

    public void setHouse(int house) {
        this.house = house;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCode(int code) {
        this.code = code;
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
        System.out.println("Street: " + street);
        System.out.println("House: " + house);
        System.out.println("City: " + city);
        System.out.println("Postal Code: " + code);
    }
}
class Person {
    private String name;
    private Address address;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void display() {
        System.out.println("Name: " + name);
        if (address != null) {
            System.out.println("Address details:");
            address.display();
        }
    }
}

class Book {
    private Person author;
    private String bookName;
    private String publisher;

    public Book() {
        author = new Person();
    }

    public void setAuthor(Person p) {
        author = p;
    }

    public void setBookName(String n) {
        bookName = n;
    }

    public void setPublisher(String p) {
        publisher = p;
    }

    public Person getAuthor() {
        return author;
    }

    public String getBookName() {
        return bookName;
    }

    public String getPublisher() {
        return publisher;
    }

    public void display() {
        System.out.println("Book Name: " + bookName);
        System.out.println("Publisher: " + publisher);
        System.out.println("Author details:");
        author.display();
    }
}


class Runner {
    public static void main(String[] args) {
        Person author = new Person();
        author.setName("Ahmed");

        Address a = new Address();
        a.setStreet(10);
        a.setHouse(20);
        a.setCity("Karachi");
        a.setCode(75000);
        author.setAddress(a);

        Book b = new Book();
        b.setAuthor(author);
        b.setBookName("Java Basics");
        b.setPublisher("ABC Publishers");

        System.out.println("Before modifying address:");
        b.display();

        Address newAddr = new Address();
        newAddr.setStreet(15);
        newAddr.setHouse(30);
        newAddr.setCity("Islamabad");
        newAddr.setCode(44000);
        
        b.getAuthor().setAddress(newAddr);

        System.out.println("\nAfter modifying address:");
        b.display();
    }
}