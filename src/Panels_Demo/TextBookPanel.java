package Panels_Demo;


import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import Object_Classes.Name;
import Object_Classes.Textbook;
import Utilities.TextbookBag;
import Utilities.utilities;
import Exceptions_Alerts.EnterException;
import Exceptions_Alerts.IsbnException;
import Exceptions_Alerts.NameException;
import Exceptions_Alerts.Popbox;
import Exceptions_Alerts.PriceException;
import Exceptions_Alerts.PriceRangeException;
import Exceptions_Alerts.RangeException;

public class TextBookPanel {
	
	TextbookBag bookbag = utilities.restorebook();
	private VBox root;
	private HBox fieldBox;
	private HBox textBox;
	private HBox buttonBox;
	private HBox emptyBox;
	private VBox isbnfields;
	private TextField title;
	private TextField authorFirstname;
	private TextField authorLastname;
	private TextField price;
	private TextField isbn;
	private TextArea area;
	private Button Search;
	private Button Remove;
	private Button Exit;
	private Button Insert;
	private Button Update;
	


	public TextBookPanel() {
		
		
		isbnfields=new VBox(3);
		root = new VBox(30);
		fieldBox = new HBox(5);
		textBox = new HBox(20);
		buttonBox = new HBox(20);
		emptyBox = new HBox(20);
		title = new TextField();
		title.setPromptText("TITLE");
		isbn = new TextField();
		isbn.setPromptText("ISBN");
		authorFirstname = new TextField();
		authorFirstname.setPromptText("AUTHOR FIRST NAME");
		authorLastname = new TextField();
		authorLastname.setPromptText("AUTHOR LAST NAME");
		price = new TextField();
		price.setPromptText("PRICE");

		area = new TextArea();
		area.setPrefHeight(25);
		area.setMinWidth(600);

	
		
		//// Remove 
		Remove = new Button("REMOVE");
                Remove.setPrefSize(70,30);
		Remove.setOnAction(e -> {
			area.clear();
			
			try {
				String removeisbns=isbn.getText();
				if(removeisbns.isEmpty()) {
					String title3 = "ERROR";
					String message3 = "REMOVE ISBN FIELD IS EMPTY";
					String title4 = "ENTER A STUDENT ID";
					Popbox.errorBox(message3, title3,title4);
				}
				
				Textbook book = bookbag.sequentialSearchByIsbn(removeisbns);
				if(book==null&&!removeisbns.isEmpty()) {
					String title3 = "ERROR";
					String message3 = "THERE IS NO SUCH TEXTBOOK";
					String title4 = "ISBN :"+removeisbns;
					Popbox.errorBox(message3, title3,title4);
				}
			
					bookbag.deleteByIsbn(removeisbns);
				
					
				
			} catch (NullPointerException e1) {
				
			}
			
				
			
		});
		
		////// Exit
		Exit = new Button("EXIT");
		Exit.setPrefSize(70, 30);
		Exit.setOnAction(e -> {
			utilities.saveTextbook(bookbag);
			Platform.exit();
		});
		
		//// Search
		Search = new Button("SEARCH");
                Search.setPrefSize(70,30);
		Search.setOnAction(x -> {
			area.clear();
			
			try {
				String searchisbns = isbn.getText();
				
				if(searchisbns.isEmpty()) {
					String title3 = "ERROR";
					String message3 = " ISBN FIELD IS EMPTY";
					Popbox.errorBox(message3, title3,null);
					
				}
				Textbook found = bookbag.sequentialSearchByIsbn(searchisbns);
				if(found==null&&!searchisbns.isEmpty()) {
					String title3 = "ERROR";
					String message3 = "NO TEXTBOOK FOUND";
					String title4 = "ISBN :"+searchisbns;
					Popbox.errorBox(message3, title3,title4);
				}
				
				else {
					area.appendText(found.toString());
				}
			} catch (NullPointerException e1) {
				
			}
			
		
		
		});
		
		//// Insert
		Insert = new Button("INSERT");
		Insert.setPrefSize(70, 30);
		Insert.setOnAction(x -> {
			area.clear();
			String titles = title.getText();
			String isbnnumber = isbn.getText();
			String getprice = price.getText();
			String fname = authorFirstname.getText();
			String lname = authorLastname.getText();
			try {
				
				if (isbnnumber.length() > 17 || isbnnumber.length() < 17 &&!isbnnumber.isEmpty()) {
					throw new RangeException();
				}
				if (!isbnnumber.matches("^[0-9-;]+$")&&!isbnnumber.isEmpty()) {
					throw new IsbnException();
				}
				if(isbnnumber.isEmpty()) {
					throw new EnterException();
				}
				
				if(titles.isEmpty()) {
					throw new EnterException();
				}
				if (!fname.matches("[a-zA-Z]+")&&!fname.isEmpty() || !lname.matches(("[a-zA-Z]+"))&&!lname.isEmpty()) {
					throw new NameException();
				}
				if(fname.isEmpty()||lname.isEmpty()) {
					throw new EnterException();
				}
				
				Name authorName = new Name(fname, lname);
				
				if(!getprice.matches("^[0-9.;]+$")&&!getprice.isEmpty()) {
					throw new PriceException();
				}
				double pricezt = Double.parseDouble(getprice);
				String sal= String.format("%.2f", pricezt);
				double pricez = Double.parseDouble(sal);
				if (pricez >= 199.99 || pricez <=0.0 && !getprice.isEmpty()) {
					throw new PriceRangeException();
				}
				if(getprice.isEmpty()) {
					throw new EnterException();
				}
				Textbook book = new Textbook(authorName,titles, isbnnumber, pricez);
				bookbag.insert(book);
				area.appendText(book.toString());
				
			} catch (NumberFormatException e1) {
				
			} catch (PriceRangeException e2) {
				
				String title3 = "WARNING";
				String title4 = "PRICE MUST BE BETWEEN 0.0 AND 199.99";
				String message3 = "ENTER THE PRICE AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (NameException t) {
				
				String title3 = "WARNING";
				String title4 = "NAME SHOULD BE ONLY LETTERS";
				String message3 = "ENTER THE NAME AGAIN ";
				Popbox.warningBox(message3, title3,title4);

			} catch (RangeException e3) {
				
				String title3 = "WARNING";
				String title4 = "ISBN NUMBER SHOULD BE LENGTH 17 INCLUDING DASHES";
				String message3 = "ENTER THE ISBN NUMBER AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (IsbnException e1) {
				String title3 = "WARNING";
				String title4 = "ISBN SHOULD ONLY CONTAIN NUMBERS AND DASHES";
				String message3 = "ENTER THE ISBN NUMBER AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (PriceException e1) {
				String title3 = "WARNING";
				String title4 = "PRICE MUST BE NUMBERS";
				String message3 = "ENTER THE PRICE AGAIN ";
				Popbox.warningBox(message3, title3,title4);
			} catch (EnterException e1) {
				String title3 = "ERROR";
				String title4 = "ALL FIELDS MUST BE ENTERED";
				Popbox.errorBox(null, title3,title4);
			}
			
			
		});

		///Update
		Update = new Button("UPDATE");
		Update.setPrefSize(70, 30);

		Update.setOnAction(e -> {
			area.clear();
			String isbnfound = isbn.getText();
			Textbook found = bookbag.sequentialSearchByIsbn(isbnfound);
			String fname = authorFirstname.getText();
			String lname = authorLastname.getText();

			if (found instanceof Textbook) {
				try {
					String titles = title.getText();
					String isbns = isbn.getText();
					String prices = price.getText();
					
					if (fname.matches("[a-zA-Z]+")) {
						Name n = found.getAuthor();
						Name name1 = new Name(fname, n.getLastname());
						found.setAuthor(name1);
					}
					if (lname.matches("[a-zA-Z]+")) {
						Name n = found.getAuthor();
						Name name2 = new Name(n.getFirstname(), lname);
						found.setAuthor(name2);
					}
					if (fname.matches("[a-zA-Z]+") && lname.matches(("[a-zA-Z]+"))) {
						Name finalname = new Name(fname, lname);
						found.setAuthor(finalname);
					}
					if (!fname.matches("[a-zA-Z]+") && !fname.isEmpty()
							|| !lname.matches(("[a-zA-Z]+")) && !lname.isEmpty()) {
						throw new NameException();
					}
					if(!titles.isEmpty()) {
						((Textbook) found).setTitle(titles);
					}
						
					
					if (!isbns.matches("^[0-9-;]+$") && !isbns.isEmpty()) {
						throw new IsbnException();
					} else if (!(isbns.length() == 17) && !isbns.isEmpty()) {
						throw new RangeException();
					}
					if (isbns.matches("^[0-9-;]+$") && isbns.length() == 17) {
						((Textbook) found).setIsbn(isbns);
					}
					double dprices = Double.parseDouble(prices);
					String sal= String.format("%.2f", dprices);
					double pricez = Double.parseDouble(sal);
					if (pricez >= 199.99 || pricez <= 0) {
						throw new PriceRangeException();
					}
					if (!sal.matches("^[0-9.;]+$") && !sal.isEmpty()) {
						throw new PriceException();
					}
			
					if (sal.matches("^[0-9.;]+$") && pricez <= 199.99 && pricez >=0) {
						((Textbook) found).setPrice(pricez);
					}

				} catch (NumberFormatException e1) {

				} catch (PriceRangeException e2) {
					String title3 = "WARNING";
					String title4 = "PRICE MUST BE BETWEEN 0.0 AND 199.99";
					Popbox.warningBox(null, title3,title4);
				} catch (NameException t) {
					String title3 = "WARNING";
					String title4 = "NAME SHOULD ONLY BE LETTERS";
					Popbox.warningBox(null, title3,title4);

				} catch (RangeException e3) {
					String title3 = "WARNING";
					String title4 = "ISBN NUMBER SHOULD BE LENGTH 17 INCULUDING DASHES";
					Popbox.warningBox(null, title3,title4);
				} catch (IsbnException e1) {
					
					String title3 = "WARNING";
					String title4 = "ISBN SHOULD NOT CONTAIN LETTERS";
					Popbox.warningBox(null, title3,title4);
				}
			
				catch (PriceException e1) {
					String title3 = "WARNING";
					String title4 = "PRICE MUST BE NUMBERS";
					Popbox.warningBox(null, title3,title4);
				} 

			}else {
				if (isbnfound.isEmpty()) {
					String title3 = "EROR";
					String title4 = "ENTER A  ISBN NUMBER TO UPDATE";
					String message3 = "UPDATE ISBN FIELD IS EMPTY";
					Popbox.errorBox(message3, title3,title4);
				}
				if (!isbnfound.isEmpty()) {
					String title3 = "WARNING";
					String title4 = "ENTER A ISBN NUMBER TO UPDATE";
					String message3 = "THERE IS NO SUCH BOOK WITH ISBN: "+isbnfound;
					Popbox.errorBox(message3, title3,title4);
				}
			}

			try {
				area.appendText(found.toString());
			} catch (Exception e1) {

			}
			
			

		});
		isbnfields.getChildren().addAll(isbn);
		fieldBox.getChildren().addAll(title,  authorFirstname, authorLastname, price, isbnfields);
		buttonBox.getChildren().addAll(Search, Remove,  Exit, Insert, Update);
		textBox.getChildren().add(area);
		root.getChildren().addAll(emptyBox, fieldBox, buttonBox, textBox);
		setAlignments();
	}

	public TextField getTitle() {
		return title;
	}

	public void setTitle(TextField title) {
		this.title = title;
	}

	public TextField getIsbn() {
		return isbn;
	}

	public void setIsbn(TextField isbn) {
		this.isbn = isbn;
	}

	public TextField getAuthorFirstname() {
		return authorFirstname;
	}

	public void setAuthorFirstname(TextField authorFirstname) {
		this.authorFirstname = authorFirstname;
	}

	public TextField getAuthorLastname() {
		return authorLastname;
	}

	public void setAuthorLastname(TextField authorLastname) {
		this.authorLastname = authorLastname;
	}

	public TextField getPrice() {
		return price;
	}

	public void setPrice(TextField price) {
		this.price = price;
	}

	public VBox getRoot() {
		return root;
	}

	private void setAlignments() {
		root.setAlignment(Pos.CENTER);
		fieldBox.setAlignment(Pos.CENTER);
		textBox.setAlignment(Pos.CENTER);
		emptyBox.setAlignment(Pos.CENTER);
		buttonBox.setAlignment(Pos.CENTER);
		isbnfields.setAlignment(Pos.CENTER);
	}

}
