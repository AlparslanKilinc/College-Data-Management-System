package Object_Classes;

import java.io.Serializable;


public class Textbook implements Serializable  {
	
	private String title;
	private String isbn;
	private Name author;
	private double price;
	

	
	public Textbook(Name author,String title, String isbn,  double price) {
		super();
		this.author=author;
		this.title = title;
		this.isbn = isbn;
		this.price = price;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIsbn() {
		return isbn;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public Name getAuthor() {
		return author;
	}
	public void setAuthor(Name author) {
		this.author = author;
	}
	@Override
	public String toString() {
		return "Title:" + title + ", Isbn:" + isbn + ", Author:"  + author+", Price:" + price ;
	}
	
	
}
