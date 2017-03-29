package bk.devoxx17.ui;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;

import bk.devoxx17.emulators.InjectionMethod;
import bk.devoxx17.test.MaliciousUserInputDictionnary2;
import bk.devoxx17.test.MaliciousUserInputDictionnary2.UserInput;

public class TestController {

	private static MaliciousUserInputDictionnary2 dictionnary = new MaliciousUserInputDictionnary2();
	
	@Before
	public void init() throws SQLException {
		Controller.init();
	}

	@After
	public void terminate() {
		Controller.terminateDb();
	}

	@Test
	public void TestControllerCheck() {
		ArrayListMultimap<InjectionMethod, UserInput> lstEntry = dictionnary.newCopy();
		for (Map.Entry<InjectionMethod, UserInput> entry : lstEntry.entries()) {
			dictionnary.check(
					true, 
					"Controller", 
					(x, y) -> Controller.check(x, y), 
					(x, y) -> assertTrue(x, y), 
					entry, lstEntry);
		}
	}
	
	@Test
	public void TestControllerCheck1() {
		ArrayListMultimap<InjectionMethod, UserInput> lstEntry = dictionnary.newCopy();
		List<Map.Entry<InjectionMethod, UserInput>> lstEntries = Lists.newArrayList(lstEntry.entries());
		check(lstEntries.get(2), lstEntry);
		check(lstEntries.get(6), lstEntry);
		check(lstEntries.get(0), lstEntry);
		check(lstEntries.get(4), lstEntry);
		check(lstEntries.get(5), lstEntry);
		check(lstEntries.get(1), lstEntry);
		check(lstEntries.get(3), lstEntry);
	}
	
	private void check(Map.Entry<InjectionMethod, UserInput> entry, ArrayListMultimap<InjectionMethod, UserInput> lstEntry) {
		dictionnary.check(
				true, 
				"Controller", 
				(x, y) -> Controller.check(x, y), 
				(x, y) -> assertTrue(x, y), 
				entry, lstEntry);
	}
}