package org.lokesh.junitdemo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)  // by using this line only one instatce of MathUtilsTest class is created while running each testcase
												// if we made this then no need to declare method as static which is used in (the method which is annotate with @BeforeAll annotation)	
//@TestInstance(TestInstance.Lifecycle.PER_METHOD)  // this is default behavior of junit means before running of each test cases each time instance of MathUtilsTest is created
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
	
	@Test
	void testMultiply() {
		
		int expected = 4;
		int actual = util.multiply(2, 2);
		
		assertEquals(expected, actual, "multiply method should multiply 2 numbers");
		
	}
	
	@Test
	void testDivide() {
		
		assertThrows(ArithmeticException.class, () -> util.divide(20, 0), "divide to thrown exception");
		
	}

}
