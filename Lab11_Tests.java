import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.*;
import java.io.*;
import java.net.*;
import java.time.*;

public class Lab11_Tests {
    /*
        Complete the test case below that checks to see that threads A and B have both contributed 100 entries respectively
        to the shared ArrayList when they have both finished running.
    */
    @Test
    public void test1() {
        Lab11_Thread threadA = new Lab11_Thread("A1", 100);
        Lab11_Thread threadB = new Lab11_Thread("B1", 100);

        threadA.start();

        try {
            System.out.println("Joining thread A");
            threadA.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted while joining a thread...");
        }

        int resultA = threadA.getData().size();
        int expected = 100;

        assertEquals(resultA, expected);

        threadB.start();

        try {
            System.out.println("Joining thread B");
            threadB.join();
        } catch (InterruptedException e) {
            System.out.println("Interrupted while joining a thread...");
        }

        
        int resultB = threadB.getData().size();
        expected = 200;

        assertEquals(resultB, expected);

    }

    /*
        Complete the test case below that checks to see if the shared ArrayList has at least 10 entries after 500ms of system time
    */
    @Test
    public void test2() {

        Lab11_Thread threadA = new Lab11_Thread("A2", 500);
        Lab11_Thread threadB = new Lab11_Thread("B2", 500);

        threadA.setData(new ArrayList<>()); // static data still had 200 entries from previous test

        threadA.start();
        threadB.start();
        try {
            Thread.sleep(500);
            boolean result = (threadA.getData().size() > 10); 
            boolean expected = true;
            assertEquals(expected, result);

            threadA.join(); 
            threadB.join(); // ensure threads A and B are complete before moving onto next test
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    /*
        Complete the test case below that checks to see if thread A finishes adding its 10 entries before thread B was allowed to 
        add anything to the shared ArrayList
    */
    @Test
    public void test3() {
        Lab11_Thread threadA = new Lab11_Thread("A3", 10);
        Lab11_Thread threadB = new Lab11_Thread("B3", 10);

        threadA.setData(new ArrayList<>()); // static data still has entries from previous test
        threadA.start();
        
        try {
            threadA.join();
        } catch (Exception e){
            e.printStackTrace();
        }

        int result = threadA.getData().size();
        int expected = 10;

        assertEquals(expected, result);
        
        threadB.start();
    }
}
