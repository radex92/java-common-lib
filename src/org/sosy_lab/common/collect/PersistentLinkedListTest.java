/*
 *  CPAchecker is a tool for configurable software verification.
 *  This file is part of CPAchecker.
 *
 *  Copyright (C) 2007-2013  Dirk Beyer
 *  All rights reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 *  CPAchecker web page:
 *    http://cpachecker.sosy-lab.org
 */
package org.sosy_lab.common.collect;

import java.util.Arrays;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.TestStringListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.testers.ListLastIndexOfTester;
import com.google.common.collect.testing.testers.ListListIteratorTester;
import com.google.common.collect.testing.testers.ListSubListTester;

public class PersistentLinkedListTest extends TestCase {

  private static final TestStringListGenerator listGenerator = new TestStringListGenerator() {

    @Override
    protected List<String> create(String[] pElements) {
      return PersistentLinkedList.copyOf(pElements);
    }
  };

  public static junit.framework.Test suite() throws NoSuchMethodException, SecurityException {
    TestSuite suite = new TestSuite();
    suite.addTestSuite(PersistentLinkedListTest.class);

    suite.addTest(ListTestSuiteBuilder.using(listGenerator)
        .named("PersistentLinkedList")
        .withFeatures(CollectionFeature.KNOWN_ORDER,
                      CollectionSize.ANY)

        .suppressing(
            // These tests all rely on a fully implemented ListIterator.
            ListLastIndexOfTester.class.getMethod("testFind_wrongType"),
            ListLastIndexOfTester.class.getMethod("testFind_no"),
            ListLastIndexOfTester.class.getMethod("testFind_yes"),
            ListLastIndexOfTester.class.getMethod("testFind_nullNotContainedAndUnsupported"),
            ListLastIndexOfTester.class.getMethod("testLastIndexOf_duplicate"),
            ListListIteratorTester.class.getMethod("testListIterator_tooLow"),
            ListListIteratorTester.class.getMethod("testListIterator_unmodifiable"),
            ListSubListTester.class.getMethod("testSubList_lastIndexOf"))

        .createTestSuite());

    return suite;
  }

  @Test
  public void testOf1() {
    PersistentLinkedList<String> l1 = PersistentLinkedList.of("a");
    PersistentLinkedList<String> l2 = PersistentLinkedList.copyOf(new String[]{"a"});
    Assert.assertEquals(l2, l1);
  }

  @Test
  public void testOf2() {
    PersistentLinkedList<String> l1 = PersistentLinkedList.of("a", "b");
    PersistentLinkedList<String> l2 = PersistentLinkedList.copyOf(new String[]{"a", "b"});
    Assert.assertEquals(l2, l1);
  }

  @Test
  public void testOf3() {
    PersistentLinkedList<String> l1 = PersistentLinkedList.of("a", "b", "c");
    PersistentLinkedList<String> l2 = PersistentLinkedList.copyOf(new String[]{"a", "b", "c"});
    Assert.assertEquals(l2, l1);
  }

  @Test
  public void testOfVarArgs() {
    PersistentLinkedList<String> l1 = PersistentLinkedList.of("a", "b", "c", "d");
    PersistentLinkedList<String> l2 = PersistentLinkedList.copyOf(new String[]{"a", "b", "c", "d"});
    Assert.assertEquals(l2, l1);
  }

  @Test
  public void testWithAll() {
    PersistentLinkedList<String> l1 = PersistentLinkedList.of("d").withAll(Arrays.asList("a", "b", "c"));
    PersistentLinkedList<String> l2 = PersistentLinkedList.copyOf(new String[]{"a", "b", "c", "d"});
    Assert.assertEquals(l2, l1);
  }

  @Test
  public void testReversed() {
    PersistentLinkedList<String> l1 = PersistentLinkedList.of("a", "b", "c", "d");
    PersistentLinkedList<String> l2 = PersistentLinkedList.copyOf(new String[]{"d", "c", "b", "a"});
    Assert.assertEquals(l2, l1.reversed());
  }
}