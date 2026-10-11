class HotDogStand {
    private int id;
    private int sold;
    public HotDogStand(int standId, int soldCount) {
        id = standId;
        sold = soldCount;
    }
    public void justSold() {
        sold = sold + 1;
    }
    public int getSold() {
        return sold;
    }
 public int getId() {
        return id;
    }
}
class Runner {
    public static void main(String[] args) {
        HotDogStand stand1 = new HotDogStand(101, 0);
        HotDogStand stand2 = new HotDogStand(102, 0);
        HotDogStand stand3 = new HotDogStand(103, 0);

        stand1.justSold();
        stand1.justSold();
        stand1.justSold();

        stand2.justSold();
        stand2.justSold();

        stand3.justSold();
        stand3.justSold();
        stand3.justSold();
        stand3.justSold();

        System.out.println("Stand " + stand1.getId() + " sold: " + stand1.getSold());
        System.out.println("Stand " + stand2.getId() + " sold: " + stand2.getSold());
        System.out.println("Stand " + stand3.getId() + " sold: " + stand3.getSold());
    }
}
