class Pizza {
    private String size;          
    private int cheeseToppings;
    private int pepperoniToppings;
    private int hamToppings;
    public Pizza(String s, int cheese, int pepperoni, int ham) {
        size = s;
        cheeseToppings = cheese;
        pepperoniToppings = pepperoni;
        hamToppings = ham;
    }
    public void setSize(String s) {
        size = s;
    }
    public void setCheeseToppings(int c) {
        cheeseToppings = c;
    }
    public void setPepperoniToppings(int p) {
        pepperoniToppings = p;
    }
    public void setHamToppings(int h) {
        hamToppings = h;
    }
    public String getSize() {
        return size;
    }
    public int getCheeseToppings() {
        return cheeseToppings;
    }
    public int getPepperoniToppings() {
        return pepperoniToppings;
    }
 public int getHamToppings() {
        return hamToppings;
    }
    public double calcCost() {
        double cost = 0;
        int totalToppings = cheeseToppings + pepperoniToppings + hamToppings;
        if(size.equalsIgnoreCase("small")) {
            cost = 10 + (2 * totalToppings);
        }
        else if(size.equalsIgnoreCase("medium")) {
            cost = 12 + (2 * totalToppings);
        }
        else if(size.equalsIgnoreCase("large")) {
            cost = 14 + (2 * totalToppings);
        }
        return cost;
    }
    public String getDescription() {
        return size + " pizza with " + cheeseToppings + " cheese, " + 
               pepperoniToppings + " pepperoni, " + hamToppings + " ham toppings";
    }
}
class PizzaOrder {
    private Pizza pizza1;
    private Pizza pizza2;
    private Pizza pizza3;
    private int count;
    public PizzaOrder() {
        count = 0;
    }
    public void addPizza(Pizza p) {
        if(count == 0) {
            pizza1 = p;
            count++;
        }
        else if(count == 1) {
            pizza2 = p;
            count++;
        }
        else if(count == 2) {
            pizza3 = p;
            count++;
        }
        else {
            System.out.println("Order is full (max 3 pizzas)");
        }
    }
    public double calcTotal() {
        double total = 0;
        if(count >= 1) total = total + pizza1.calcCost();
        if(count >= 2) total = total + pizza2.calcCost();
        if(count >= 3) total = total + pizza3.calcCost();
        return total;
    }
}
class Runner {
    public static void main(String[] args) {
        Pizza p1 = new Pizza("large", 1, 1, 2);   // should cost 22
        System.out.println(p1.getDescription());
        System.out.println("Cost: $" + p1.calcCost());

        Pizza p2 = new Pizza("medium", 2, 0, 1);
        System.out.println(p2.getDescription());
        System.out.println("Cost: $" + p2.calcCost());
        PizzaOrder order = new PizzaOrder();
        order.addPizza(p1);
        order.addPizza(p2);

        System.out.println("Total order cost: $" + order.calcTotal());
    }
}
