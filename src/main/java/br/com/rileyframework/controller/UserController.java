package br.com.rileyframework.controller;

import br.com.rileyframework.annotations.Get;
import br.com.rileyframework.annotations.Rest;
import br.com.rileyframework.generics.JsonReturn;
import br.com.rileyframework.model.Book;

/**
 * Sample of Test
 */
@Rest
public class UserController {
	
	@Get
	public String getBook() {
		Book book = new Book();
		book.setAuthor("NetoDevel");
		book.setTitle("RileyFramework");
		book.setPrice(0.0d);
		return JsonReturn.toJson(book);
	}
	
	
}