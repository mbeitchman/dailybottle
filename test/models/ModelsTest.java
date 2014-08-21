package models;

import models.*;
import org.junit.*;
import static org.junit.Assert.*;
import play.test.WithApplication;
import static play.test.Helpers.*;

public class ModelsTest extends WithApplication{
	@Before
	public void setUp(){
		start(fakeApplication(inMemoryDatabase()));
	}

	@Test
	public void createAndRetrieveUser(){
		new User("foo@bar.com", "Foo", "secret").save();
		User foo = User.find.where().eq("email", "foo@bar.com").findUnique();
		assertNotNull(foo);
		assertEquals("Foo", foo.name);
	}

	@Test
	public void authenticateUser(){
		new User("foo@bar.com", "Foo", "secret").save();

		assertNotNull(User.authenticate("foo@bar.com", "secret"));
		assertNull(User.authenticate("foo@bar.com", ""));
		assertNull(User.authenticate("bar@bar.com", "secret"));
	}
}