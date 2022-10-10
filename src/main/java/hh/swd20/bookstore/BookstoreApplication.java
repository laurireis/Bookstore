package hh.swd20.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.domain.Book;
import hh.swd20.bookstore.domain.BookRepository;
import hh.swd20.bookstore.domain.Category;
import hh.swd20.bookstore.domain.CategoryRepository;
import hh.swd20.bookstore.domain.User;
import hh.swd20.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository brepository, CategoryRepository crepository, UserRepository urepository) {
		return (args) -> {
			log.info("Create a few categories");
			Category category1 = new Category("Fiction");
			crepository.save(category1);
			Category category2 = new Category("Scifi");
			crepository.save(category2);
			Category category3 = new Category("Comic");
			crepository.save(category3);
			
			
			log.info("Save some books");
			brepository.save(new Book("Pimeät kuut", "Tommi Kinnunen", 2022, "9789510480991", 28.95, category1));
			brepository.save(new Book("Opetuslapsi", "Kari Hotakainen", 2022, "9789523880603", 25.95, category1));
			
			log.info("Create users");
			User user1 = new User("user", "$2a$10$5Qit9SPnlUvMy4Mt.w820O23qdlh./i6nTCKdn0wYnSw48kI4fOLa", "user@user.com", "USER");
			User user2 = new User("admin", "$2a$10$vmelhoIM5wyHT6c6jermtOuVvZVrdXe0ucj9vNmlwFq9HDgHW4pMe", "admin@admin.com", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("Fetch all the categories");
			for (Category category : crepository.findAll()) {
				log.info(category.toString());
			}
			
			log.info("Fetch all the books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
