/*
 *  SoSy-Lab Common is a library of useful utilities.
 *  This file is part of SoSy-Lab Common.
 *
 *  Copyright (C) 2007-2015  Dirk Beyer
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
 */
package org.sosy_lab.common;

import com.google.common.io.ByteStreams;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

import java.io.OutputStream;

import javax.annotation.Nullable;

/**
 * This class is an OutputStream implementation that sends everything to two
 * other OutputStreams.
 *
 * Exceptions thrown by any of the streams will be relayed to the caller.
 * @deprecated use {@link org.sosy_lab.common.io.DuplicateOutputStream}
 */
@Deprecated
@SuppressFBWarnings("NM_SAME_SIMPLE_NAME_AS_SUPERCLASS")
public class DuplicateOutputStream extends org.sosy_lab.common.io.DuplicateOutputStream {

  public DuplicateOutputStream(OutputStream pStream1, OutputStream pStream2) {
    super(pStream1, pStream2);
  }

  /**
   * Create an output stream that forwards to all given output streams,
   * ignoring null parameters.
   */
  public static OutputStream mergeStreams(
      @Nullable OutputStream stream1, @Nullable OutputStream stream2) {

    if (stream1 == null) {
      if (stream2 == null) {
        return ByteStreams.nullOutputStream();
      } else {
        return stream2;
      }

    } else {
      if (stream2 == null) {
        return stream1;
      } else {
        return new DuplicateOutputStream(stream1, stream2);
      }
    }
  }
}
