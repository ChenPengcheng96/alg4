package com.pengcheng.alg4;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

/**
 * BinarySearch Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>二月 25, 2018</pre>
 */
public class BinarySearchTest {

    @Before
    public void before() throws Exception {
        //CreateFilesForTest.createArrayData();
    }

    @After
    public void after() throws Exception {
        //CreateFilesForTest.destroyFile();
    }

    /**
     * Method: find(int key, int[] a)
     */
    @Test
    public void testFindForKeyA() throws Exception {
//TODO: Test goes here...
        BinarySearch bs = new BinarySearch();
        int a[] = {84,48,68,10,18,98,12,23,54,57,33,16,77,11,29};
        int actual1 = bs.find(10,a);
        int actual2 = bs.find(98,a);
        int actual3 = bs.find(84,a);
        int actual4 = bs.find(0,a);
        Assert.assertEquals(0,actual1);
        Assert.assertEquals(14,actual2);
        Assert.assertEquals(13,actual3);
        Assert.assertEquals(-1,actual4);
    }

    /**
     * Method: find(int key, RandomAccessContainer container)
     */
    @Test
    public void testFindForKeyContainer() throws Exception {
//TODO: Test goes here... 
    }


    /**
     * Method: findByRange(int key, int[] a, int low, int high)
     */
    @Test
    public void testFindByRange() throws Exception {
//TODO: Test goes here...
    }

} 
