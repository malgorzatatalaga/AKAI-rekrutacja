package api.book;

class Book {

    private int id;
    private String title;
    private String author;
    private double rating;

    public int getId() {
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getRating() {
        return rating;
    }

    public Book(int id, String title, String author, double rating) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.rating = rating;
    }

}
