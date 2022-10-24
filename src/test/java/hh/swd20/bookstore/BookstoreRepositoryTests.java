package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.bind.annotation.PathVariable;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookstoreRepositoryTests {
	
	@Autowired
	private BookRepository brepository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@Autowired
	private UserRepository urepository;
	
	@Test	// Testing the functionality of the findByTitle() -method
	public void findByTitleShouldReturnBook() {
		List<Book> books = brepository.findByTitle("Opetuslapsi");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Kari Hotakainen");
	}
	
	@Test	// Testing the functionality of the save() -method of BookRepository
	public void createNewBook() {
		Book book = new Book("Book title", "Book author", 2022, "123456789", 10.00, null);
		brepository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	
	@Test	// Testing the functionality of the findByName() -method
	public void findByNameShouldReturnCategory() {
		List<Category> categories = crepository.findByName("Comic");
		assertThat(categories).hasSize(1);
		assertThat(categories.get(0).getCategoryId()).isEqualTo(3);
	}
	
	@Test	// Testing the functionality of the save() -method of CategoryRepository
	public void createNewCategory() {
		Category category = new Category("School");
		crepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
	
	
	@Test	// Testing the functionality of the save() -method of UserRepository
	public void createNewUser() {
		User user = new User("user2", "123", "email@email.com", "USER");
		urepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	
}
