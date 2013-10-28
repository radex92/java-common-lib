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

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.SortedMap;
import java.util.SortedSet;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;

/**
 * Extension of {@link SortedMap} that specifies {@link SortedSet} as type
 * of some collection views (instead of {@link java.util.Set}).
 */
interface OurSortedMap<K, V> extends SortedMap<K, V> {

  @Override
  SortedSet<K> keySet();

  @Override
  SortedSet<Map.Entry<K, V>> entrySet();

  static class EmptyImmutableOurSortedMap<K extends Comparable<? super K>, V> extends AbstractImmutableMap<K, V> implements OurSortedMap<K, V> {

    private static final OurSortedMap<?, ?> INSTANCE = new EmptyImmutableOurSortedMap<>();

    @SuppressWarnings("unchecked")
    static <K extends Comparable<? super K>, V> OurSortedMap<K, V> of() {
      return (OurSortedMap<K, V>) INSTANCE;
    }

    @Override
    public Comparator<? super K> comparator() {
      return null;
    }

    @Override
    public OurSortedMap<K, V> subMap(K pFromKey, K pToKey) {
      return this;
    }

    @Override
    public OurSortedMap<K, V> headMap(K pToKey) {
      return this;
    }

    @Override
    public OurSortedMap<K, V> tailMap(K pFromKey) {
      return this;
    }

    @Override
    public K firstKey() {
      throw new NoSuchElementException();
    }

    @Override
    public K lastKey() {
      throw new NoSuchElementException();
    }

    @Override
    public SortedSet<K> keySet() {
      return ImmutableSortedSet.of();
    }

    @Override
    public Collection<V> values() {
      return ImmutableList.of();
    }

    @Override
    public int size() {
      return 0;
    }

    @Override
    public boolean isEmpty() {
      return true;
    }

    @Override
    public boolean equals(Object pObj) {
      return pObj instanceof Map<?, ?> && ((Map<?, ?>)pObj).isEmpty();
    }

    @Override
    public int hashCode() {
      return 0;
    }

    @Override
    public boolean containsKey(Object pKey) {
      return false;
    }

    @Override
    public boolean containsValue(Object pValue) {
      return false;
    }

    @Override
    public V get(Object pKey) {
      return null;
    }

    @Override
    public SortedSet<Map.Entry<K, V>> entrySet() {
      return ImmutableSortedSet.of();
    }

    @Override
    public String toString() {
      return "{}";
    }
  }
}
