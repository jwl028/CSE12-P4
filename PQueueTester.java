/**
 * @author Jimmy Li 
 * Tester for PQueue implementation
 */

import junit.framework.*;
import java.util.*;

/**
 * Junit tester
 */

public class PQueueTester extends junit.framework.TestCase 
{

  private PQueue<String> sQueue;
  private PQueue<Integer> iQueue;

  /**
   * Sets Junit to run in a graphical interface.
   */

  public static void main(String args[])
  {
    junit.swingui.TestRunner.main(new String[] {"PQueueTester"});
  }

  /**
   * Tests that the elements are added correctly and the size is incremented
   * accordingly.
   */

  public void testAddRemove()
  {
    //Checks that highest priority gets added to the top
    assertTrue(iQueue.size() == 0);
    iQueue.add(new Integer(2));
    assertEquals(iQueue.peek(),new Integer(2));
    assertTrue(iQueue.size() == 1);
    iQueue.add(new Integer(1));
    assertEquals(iQueue.peek(),new Integer(2));
    assertTrue(iQueue.size() == 2);
    iQueue.add(new Integer(3));
    assertEquals(iQueue.peek(),new Integer(3));
    assertTrue(iQueue.size() == 3);
    //Attempt to add null element
    try {
      iQueue.add(null);
      fail();
    }
    catch(NullPointerException e) {
      assertTrue(true);
    }
    //Check that all elements were added correctly
    iQueue.remove();
    assertTrue(iQueue.size() == 2);
    assertEquals(iQueue.peek(),new Integer(2));
    iQueue.remove();
    assertTrue(iQueue.size() == 1);
    assertEquals(iQueue.peek(),new Integer(1));
  }

  /**
   * Tests that remove returns null if empty
   */

  public void testRemoveNull()
  {
    assertEquals(iQueue.remove(),null);
  }

  /**
   * Tests that remove removes one duplicate
   */

  public void testRemoveDup()
  {
    iQueue.add(new Integer(1));
    iQueue.add(new Integer(2));
    iQueue.add(new Integer(2));
    assertTrue(iQueue.size() == 3); 
    assertEquals(iQueue.peek(), new Integer(2));
    assertEquals(iQueue.remove(), new Integer(2));
    assertTrue(iQueue.size() == 2); 
    assertEquals(iQueue.peek(), new Integer(2));
    assertEquals(iQueue.remove(), new Integer(2));
    assertTrue(iQueue.size() == 1); 
    assertEquals(iQueue.peek(), new Integer(1));
  }

  /**
   * Tests that peek returns null if empty
   */

  public void testPeekNull()
  {
    assertTrue(iQueue.peek()==null);
  }

 /**
  * Ensures that isEmpty returns true if empty
  */

  public void testIsEmpty()
  {
    assertTrue(iQueue.isEmpty());
    assertTrue(iQueue.size() == 0);
    iQueue.add(new Integer(1));
    assertFalse(iQueue.isEmpty());
    assertTrue(iQueue.size() == 1);
    assertEquals(iQueue.peek(), new Integer(1));
  }

  /**
   * Tests that array expands accordingly when many elements are added
   */

  public void testCapacity()
  {
    for(int i = 0; i < 10000; i++) {
      iQueue.add(i);
      assertEquals(iQueue.peek(), new Integer(i));
      assertTrue(iQueue.size() == i+1);
    }
  } 

  /**
   * Tests that highest priority is going to top when random generator is used
   */

  public void testRandomNum()
  {
    Random generator = new Random();
    int biggestNum = 0;
    int randomIndex = 0;
    //Generate 10000 random numbers from 0 to 1000
    for(int i = 0; i < 10000; i++) {
      randomIndex = generator.nextInt(1000);
      if(randomIndex >= biggestNum) {
        //Stores biggest number
        biggestNum = randomIndex;
      }
      iQueue.add(randomIndex);
      assertEquals(iQueue.size(), i+1);
    }
    //Check that peek returns biggest number
    assertTrue(iQueue.peek() == biggestNum);
  }

  /**
   * Makes sure that sort sorts in a nondecreasing order
   */

  public void testSort()
  {
    //Sort array and check that its in a nondecreasing order
    Integer[] arr = {2,4,1,3,7,6};
    Heap12.sort(arr);
    assertEquals(arr[0],new Integer(1));
    assertTrue(arr[1] == 2);
    assertTrue(arr[2] == 3);
    assertTrue(arr[3] == 4);
    assertTrue(arr[4] == 6);
    assertTrue(arr[5] == 7);
    Integer[] arr2 = new Integer[10000];
    for(int i = 9999-1; i >= 0; i++) {
      arr2[i] = i;
    }
    Heap12.sort(arr2);
    for(int i = 0; i < arr2.length; i++) {
      assertTrue(arr[i] == i);
    }
  }

  /**
   * Tests the equality method 
   */

  public void testEqualsCtor()
  {
    //Add the same elements to two Heaps
    PQueue<Integer> iQueue2 = new Heap12<Integer>();
    for(int i = 0; i <= 900; i++) {
      iQueue.add(i);
    }
    for(int j = 0; j <=900 ; j++) {
      iQueue2.add(j);
    }
    assertTrue(iQueue.equals(iQueue2));
    assertFalse(iQueue.equals(sQueue));
    PQueue<Integer> iQueue3 = new Heap12<Integer>((Heap12)iQueue2);
    assertTrue(iQueue3.equals(iQueue2));
    iQueue2.remove();
    assertFalse(iQueue3.equals(iQueue2));
  }

  /**
   * Sets initial conditions before each test  
   */

  protected void setUp()
  {
    sQueue = new Heap12<String>();
    iQueue = new Heap12<Integer>();
  }

  /**
   * Sets post conditions after each test  
   */

  protected void tearDown()
  {
    sQueue = null;
    iQueue = null;
  }

}
