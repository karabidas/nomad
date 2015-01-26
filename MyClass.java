import static org.junit.Assert.*;

import org.junit.Test;
import cust.cust;
import boss.admin;
import boss.insert;
import firstlook.Login;

public class MyClass {

	@Test
	public void testinsert() {

		insert mytest = new insert();

		assertEquals("Result", 100, mytest.getX());
	}

	@Test
	public void testAdmin() {

		admin mytest = new admin();

		assertEquals("Result", 100, mytest.getX());

	}

	@Test
	public void testLogin() {
		Login mytest = new Login();

		assertEquals("Result", 100, mytest.getX());
	}

	@Test
	public void testcust() {

		cust mytest = new cust();

		assertEquals("Result", 100, mytest.getX());
	}

}
