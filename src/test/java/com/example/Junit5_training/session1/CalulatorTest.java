package com.example.Junit5_training.session1;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.util.AssertionErrors.fail;


@DisplayName("Test Math Operations in Calculator class")
public class CalulatorTest {
//Now here testIntegerDivisionTest_WhenFourIsDividedByTwo_ShouldReturnTwo() &integerSubtractionTest()
    //Both methods create a new Object of Calculator Class so,we can use
    //@BeforeEach method to create instance of Calculator class.we can remove instance of Calculator class from All TestMethods
Calculator calculator;

    //They are Four Types of LifeCycleMethods[Concept]
//    @BeforeAll
//    @BeforeEach
//    @AfterAll
//    @AfterEach

    @BeforeAll //This annotation makes your method to execute one time only Before allTest Methods executes.It need to be static.Example if you need a database to be created  in setUp method,then you can use CleanUp method to delete the Database
    static void setup(){
        System.out.println("Executing @BeforeAll Method.");
}

@AfterAll // This Annotation make your method execute one time only after all Test methods complete.It need to be static. we will use this for cleanUp purpose
static void cleanUp(){
    System.out.println("Executing @AfterAll Method.");
}

@BeforeEach //This Annotation is used for a method you want to run before each test method
void beforeEachTestMethod(){
        calculator = new Calculator();
    System.out.println("Executing @BeforeEach Method.");
}

@AfterEach //
void afterEachTestMethod(){ //If your running Integration test for example and your test method made some changes in database,then you can use this method to delete all those records.
    System.out.println("Executing @AfterEach Method.");
}

    //test<System Under Test>_<Condition or state Change>_<Expected Result>
    @DisplayName("Test 4/2 =2")
    @Test
    void testIntegerDivisionTest_WhenFourIsDividedByTwo_ShouldReturnTwo(){
      System.out.println("Test 4/2 =2");

        //Structure pattern of Unit Test 1. Arrange   //Given
        //create Instance of Calculator class
       // Calculator calculator = new Calculator(); //we used @ BeforeEach class

        //2.ACT //to invoke the methodtest            //When
       //Invoking the class using Object calling method and storing result in res variable
        int res = calculator.integerDivision(4,2);

        //3.Assert //validate return value             //Then
        //verify check if two values are equal
assertEquals(2,res,"4/2 didn't produce 4");

    }


    //Annotation that will help you to disable your unit test ,Usually its not a Good Idea to disable Unit test
    //and If unit tst is Failing for some reason ,and your not sure why it is failing,mostly dont disable it and try to figure out
    //why it is failing but if we do need to disable unit test case means
    //first way comment the test Annotation{//} it wont be exceuted when you run
    //and it will not be included in Unit run or TestReport.
    //Second way is to disbale  Unit test case use @Disabled("message")
@Disabled("To do :Still need to work out")
    @DisplayName("Division By Zero")
    @Test
    void testIntegerDivision_WhenDividendIsDividedByZero_ShouldThrowArithemeticException(){
    System.out.println("Running division By zero");
      fail("Not implemented Yet");

    }


    //Here we learn about how to verify the method your testing throws Exception,we use IntegerDivsion method
    //we use assertThrows assertion.
    @DisplayName("Division By Zero")
    @Test
    void testIntegerDivision_WhenDividendIsDividedByZero1_ShouldThrowArithemeticException(){
        System.out.println("Running division By zero");
       //Arrange //no need to create Calculator Object already Created in @BeforeEach method
        int dividend = 4;
        int divisor = 0;
        String expectedExceptionMessage = "/ by zero";

        //The First Parameter that assertThrows asssertion  accepts is
        //type or className of Exception we are expecting ,Second Parameter is executable
        //here we will provide lambda function that will invoke my our method under test
//Third Parameter that we can include in this assert after lambda function parameter(Second) is
//OptionalMessage that we can include it in optional Message thatwill be printed if this assertion fails
        //Act & Assert//to invoke method
        ArithmeticException actualException =
                assertThrows(ArithmeticException.class,()->{
            //Act
            calculator.integerDivision(dividend,divisor);
        },"Division by Zero should have thrown an Arithemetic Exception");


        //Assert
        //we can check  with exception message that was returned  is whatwe are expecting
        //we will assign the result of assertion to a local Varaiable called
        // actualException and we can verify with assertEquals method.
assertEquals(expectedExceptionMessage,actualException.getMessage(),"Unexpected Exception Messsage");

    }


    @DisplayName("Test 4-2 =2")
    @Test
    void integerSubtractionTest(){

        System.out.println("Test 4/2 =2");
        //create Instance of Calculator class

       // Calculator calculator = new Calculator();//we used @ BeforeEach class
        //Invoking the class using Object calling method and storing result in res variable
        int res = calculator.integerSubtraction(4,2);
        //verify check if two values are equal
        assertEquals(2,res,"4-2 didn't produce output 2");
    }
    @DisplayName("Test 33-23 =10")
    @Test
    void integerSubtractionTest1(){

        System.out.println("Test 33-23 =10");
        //Structure pattern 1. Arrange
        //create Instance of Calculator class

       // Calculator calculator = new Calculator();//we used @ BeforeEach class
        //Invoking the class using Object calling method and storing result in res variable

        int minuend = 33;
           int subtrahed =23;
           int expectedResult = 10;
        int actualResult = calculator.integerSubtraction(minuend ,subtrahed);
        //verify check if two values are equal
        assertEquals(expectedResult,actualResult,
                () -> minuend + "-" + subtrahed+" "+"didn't produce output:"+expectedResult);
        //in assertion method each method we have dynamically computed message,this might
        // slow down your test methods a little bit in a small application,you will not even notice the difference but in much
        // larger application. with Many test methods,an assertion message is used .
        // Developers like to optimze it,and the reason it might slow down your tests alittle bit
        // is because this message will computed every time you test methods,grants whether it passes or fails.
        // this message will always be computed.It gets executed even though it might never be used.
        //So to optimize performance of unit Test,Developers like to convert this test Message into
        //Lambda and it can be converted to Lambda this way.
        //Now this message is lambda function that will be executed when this assertion fails the testMethod.
        //otheriwse ,if the test is passing ,this lambda function will never get executed and no Resourses will be spent
        //to compute this error message.

    }
}
