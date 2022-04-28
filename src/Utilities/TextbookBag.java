package Utilities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.function.Predicate;

import Object_Classes.Textbook;

public class TextbookBag implements Serializable {
	private Textbook[]bookArr;
	
	private int nElms;
	



public TextbookBag(int Maxsize) {
	  bookArr= new Textbook[Maxsize];
	  
	  
	  nElms=0;
  }

  public void insert(Textbook textbook) {
	  try {
		bookArr[nElms++] = textbook;
	} catch (ArrayIndexOutOfBoundsException e) {
		Textbook[] book = new Textbook[bookArr.length+1];
		for(int i=0; i<bookArr.length; i++) {
			book[i]=bookArr[i];
		}
		book[bookArr.length]=textbook;
		setBookArr(book);
	}
	 
	
  }

  public void display() {
	  for (int i = 0; i < nElms; i++) {
		    
				System.out.println(bookArr[i]);
			
			
		}
		System.out.println();
	}
	 
		  
	  
  public Textbook sequentialSearchByIsbn(String isbn) {
	  
	  for( int i=0; i<nElms; i++) {
		  if(( bookArr[i]).getIsbn().equals(isbn)) {
			  
			  return bookArr[i];
		  }
			  
	  }
	return null;
	 
  }
  public Textbook deleteByIsbn(String isbn) {
	  int i;
	  for( i =0; i<nElms; i++) {
		  if(bookArr[i].getIsbn().contentEquals(isbn)){
			  break;
		  }
	  }
	  if(i==nElms) {
		return null;
		 
	  }else {
		  Textbook temperory=bookArr[i];
		  for(int j=i; j<nElms-1; j++) {
			  bookArr[j]=bookArr[j+1];
		  }
			nElms--;
		  return temperory;
	  }
  }
	public Textbook[] search(Predicate<Textbook> predicate) {
		Textbook[] tempArr = new Textbook[nElms];
		int matchCount =0;
		for(int i = 0; i < nElms; i++) {
			if(predicate.test(bookArr[i])) {
				tempArr[matchCount++] = bookArr[i];
			}
		}
		
		return Arrays.copyOf(tempArr, matchCount);
	}
  public Textbook[] getBookArr() {
		return bookArr;
	}

	public void setBookArr(Textbook[] bookArr) {
		this.bookArr = bookArr;
	}
}
