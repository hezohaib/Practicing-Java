class Book {
    private String author;
    private String[] chapterNames = new String[100];
    private int chapterCount;  
    public Book() {
        author = "";
        chapterCount = 0;
    }
    public Book(String a, String[] chapters) {
        author = a;
        chapterCount = chapters.length;
        for(int i = 0; i < chapters.length; i++) {
            chapterNames[i] = chapters[i];
        }
    }
    public void setAuthor(String a) {
        author = a;
    }
    public void setChapterNames(String[] chapters) {
        chapterCount = chapters.length;
        for(int i = 0; i < chapters.length; i++) {
            chapterNames[i] = chapters[i];
        }
    }
    public String getAuthor() {
        return author;
    }
    public int getChapterCount() {
        return chapterCount;
    }
    public boolean compareBooks(Book b) {
        if(this.author.equals(b.author)) {
            return true;
        }
        else {
            return false;
        }
    }
    public Book compareChapterNames(Book b) {
        if(this.chapterCount > b.chapterCount) {
            return this;
        }
        else {
            return b;
        }
    }
}
class Runner {
    public static void main(String[] args) {
        Book b1 = new Book();
        b1.setAuthor("Ali");
        String[] ch1 = {"Intro", "Chapter1", "Chapter2"};
        b1.setChapterNames(ch1);
        String[] ch2 = {"Start", "Middle", "End", "Extra"};
        Book b2 = new Book("Ahmed", ch2);
        boolean sameAuthor = b1.compareBooks(b2);
        System.out.println("Same author? " + sameAuthor);
        Book larger = b1.compareChapterNames(b2);
        System.out.println("Book with greater chapters author: " + larger.getAuthor());
    }
}
