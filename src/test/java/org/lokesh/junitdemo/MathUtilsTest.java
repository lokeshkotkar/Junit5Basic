package org.lokesh.junitdemo;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // by using this line only one instatce of MathUtilsTest class is created while running each testcase
												// if we made this then no need to declare method as static which is used in (the method which is annotate with @BeforeAll annotation)	
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)  // this is default behavior of junit means before running of each test cases each time instance of MathUtilsTest is created

@DisplayName("when running MathUtilsTest")
class MathUtilsTest {
	
	MathUtils util;
	
	// this method marked as static because this method need to be executed before the instance of this class (MathUtilsTest) is created
	// static methods are executed without instance 
	@BeforeAll
	public static void beforeAllInit() {
		System.out.println("this method will be executed at start and executed only once");
	}
	
	// before execution of each test case this code will be executed first
	@BeforeEach
	public void initialization() {
		util = new MathUtils();
	}
	
	@AfterEach
	public void cleanUp() {
		System.out.println("clean up code after each method executed");
	}

	// while running each test cases new instance  of this class (MathUtilsTest) is created .
	//means new instatce of MathUtilsTest class is created every time when we run different testcase 
	@Test
	void testAdd() {
		
		int expexted = 2;
		int actual = util.add(1, 1);
		assertEquals(expexted, actual,"add method should add two numbers");
	}
	
	@DisplayName("check multiply functionality")   // @DisplayName is to display test case name 
	@Test
	void testMultiply() {
		
		int expected = 4;
		int actual = util.multiply(2, 2);
		
		assertEquals(expected, actual, "multiply method should multiply 2 numbers");
		
	}
	
	@DisplayName("this test is for division")
	@Test
	@EnabledOnOs(OS.LINUX)  // this test is enable only if os is linux
	void testDivide() {
		
		assertThrows(ArithmeticException.class, () -> util.divide(20, 0), "divide to thrown exception");
		
	}
	
	
	@DisplayName("TDD method should not run")
	@Test
	@Disabled  // by using disabled this test case not run and not shown in failure state, it skip this test 
	public void notRun() {
		fail("this test should be disabled");
	}
	
	@DisplayName("run only when server is up and running")
	@Test
	public void serverTest() {
		boolean serverUp = false;
		assumeTrue(serverUp);    // test case will be run only when condition inside assumeTrue is true 
		int expected = 4;
		int actual = util.multiply(2, 2);
		
		assertEquals(expected, actual, "multiply method should multiply 2 numbers");
		
		
	}
	
	@DisplayName("assert all testcase")
	@Test
	public void assertAllTest() {
		assertAll(               //asserAll will check all assert statement inside it if any assert is fails then entire test case is fails 
				() -> assertEquals(4, util.multiply(2, 2)),
				() -> assertEquals(0, util.multiply(2, 0)),
				() -> assertEquals(6, util.multiply(2, 3))
				);
	}
	
	
	@Nested   // inside one class when we want to run different test cases in one go then we use nested class
	@DisplayName("add method")
	class AddTest{
		@DisplayName("when adding two positive numbers")
		@Test
		public void AddPositive() {
			assertEquals(2, util.add(1, 1),"should return right sum");
		}
		
		@DisplayName("when adding two nwgative numbers")
		@Test
		public void AddNegative() {
			assertEquals(-2, util.add(-1, -1),"should return right sum");
		}
	}
	
	@Test
	public void lamdaAssertMessageTest() {
		int expected = 4;
		int actual = util.add(2, 2);
		assertEquals(expected, actual, () -> "should return sum " +expected +"but actual value is  "+actual);
		// lamda is used in assetequals because the third parameter in assertequals is always regardless of test pass or fails 
		// so by using lamda third parameter is computed only when test fails
	}
	
	@RepeatedTest(3) // this test case will repeat 3 times 
	@DisplayName("repeated test example")
	public void repeatedTestExample(RepetitionInfo repetationInfo) {
		repetationInfo.getCurrentRepetition();
		assertEquals(2, util.add(1, 1),"should return right sum");
		
	}

}
