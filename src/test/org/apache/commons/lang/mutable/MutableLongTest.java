/*
 * Copyright 2002-2004 The Apache Software Foundation.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.lang.mutable;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.textui.TestRunner;

/**
 * JUnit tests.
 * 
 * @version $Id: MutableLongTest.java,v 1.3 2004/12/26 02:35:47 bayard Exp $
 * @see MutableLong
 */
public class MutableLongTest extends TestCase {

    public MutableLongTest(String testName) {
        super(testName);
    }

    public static void main(String[] args) {
        TestRunner.run(suite());
    }

    public static Test suite() {
        return new TestSuite(MutableLongTest.class);
    }

    // ----------------------------------------------------------------
    public void testConstructors() {
        assertEquals(0, new MutableLong().longValue());
        
        assertEquals(1, new MutableLong(1).longValue());
        
        assertEquals(2, new MutableLong(new Long(2)).longValue());
        assertEquals(3, new MutableLong(new MutableLong(3)).longValue());
        try {
            new MutableLong(null);
            fail();
        } catch (NullPointerException ex) {}
    }

    public void testGetSet() {
        final MutableLong mutNum = new MutableLong(0);
        assertEquals(0, new MutableLong().longValue());
        assertEquals(new Long(0), new MutableLong().getValue());
        
        mutNum.setValue(1);
        assertEquals(1, mutNum.longValue());
        assertEquals(new Long(1), mutNum.getValue());
        
        mutNum.setValue(new Long(2));
        assertEquals(2, mutNum.longValue());
        assertEquals(new Long(2), mutNum.getValue());
        
        mutNum.setValue(new MutableLong(3));
        assertEquals(3, mutNum.longValue());
        assertEquals(new Long(3), mutNum.getValue());
        try {
            mutNum.setValue(null);
            fail();
        } catch (NullPointerException ex) {}
        try {
            mutNum.setValue("0");
            fail();
        } catch (ClassCastException ex) {}
    }

    public void testEquals() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(true, mutNumA.equals(mutNumA));
        assertEquals(true, mutNumA.equals(mutNumB));
        assertEquals(true, mutNumB.equals(mutNumA));
        assertEquals(true, mutNumB.equals(mutNumB));
        assertEquals(false, mutNumA.equals(mutNumC));
        assertEquals(false, mutNumB.equals(mutNumC));
        assertEquals(true, mutNumC.equals(mutNumC));
        assertEquals(false, mutNumA.equals(null));
        assertEquals(false, mutNumA.equals(new Long(0)));
        assertEquals(false, mutNumA.equals("0"));
    }

    public void testHashCode() {
        final MutableLong mutNumA = new MutableLong(0);
        final MutableLong mutNumB = new MutableLong(0);
        final MutableLong mutNumC = new MutableLong(1);

        assertEquals(true, mutNumA.hashCode() == mutNumA.hashCode());
        assertEquals(true, mutNumA.hashCode() == mutNumB.hashCode());
        assertEquals(false, mutNumA.hashCode() == mutNumC.hashCode());
        assertEquals(true, mutNumA.hashCode() == new Long(0).hashCode());
    }

    public void testCompareTo() {
        final MutableLong mutNum = new MutableLong(0);

        assertEquals(0, mutNum.compareTo(new MutableLong(0)));
        assertEquals(+1, mutNum.compareTo(new MutableLong(-1)));
        assertEquals(-1, mutNum.compareTo(new MutableLong(1)));
        try {
            mutNum.compareTo(null);
            fail();
        } catch (NullPointerException ex) {}
        try {
            mutNum.compareTo(new Long(0));
            fail();
        } catch (ClassCastException ex) {}
        try {
            mutNum.compareTo("0");
            fail();
        } catch (ClassCastException ex) {}
    }

    public void testPrimitiveValues() {
        MutableLong mutNum = new MutableLong(1L);

        assertEquals( 1.0F, mutNum.floatValue(), 0 );
        assertEquals( 1.0, mutNum.doubleValue(), 0 );
    }

    public void testToString() {
        assertEquals("0", new MutableLong(0).toString());
        assertEquals("10", new MutableLong(10).toString());
        assertEquals("-123", new MutableLong(-123).toString());
    }

}
