/*
 * Copyright (C) 2016 The Android Open Source Project
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
package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.Window;
import com.google.android.exoplayer2.util.Assertions;

/**
 * A {@link Timeline} consisting of a single period and static window.
 */
public final class SinglePeriodTimeline implements Timeline {

  private static final Object ID = new Object();

  private final long durationUs;
  private final Window window;

  /**
   * Creates a timeline with one period of known duration and a window extending from zero to its
   * duration.
   *
   * @param durationUs The duration of the period, in microseconds.
   * @param isSeekable Whether seeking is supported within the period.
   */
  public SinglePeriodTimeline(long durationUs, boolean isSeekable) {
    this(durationUs, Window.createWindowFromZero(durationUs, isSeekable, false /* isDynamic */));
  }

  /**
   * Creates a timeline with one period of known duration and a window extending from zero to its
   * duration.
   *
   * @param durationUs The duration of the period, in microseconds.
   * @param window The available window within the period.
   */
  public SinglePeriodTimeline(long durationUs, Window window) {
    this.durationUs = durationUs;
    this.window = window;
  }

  @Override
  public long getAbsoluteStartTime() {
    return 0;
  }

  @Override
  public int getPeriodCount() {
    return 1;
  }

  @Override
  public long getPeriodDurationMs(int periodIndex) {
    Assertions.checkIndex(periodIndex, 0, 1);
    return durationUs == C.UNSET_TIME_US ? ExoPlayer.UNKNOWN_TIME : (durationUs / 1000);
  }

  @Override
  public long getPeriodDurationUs(int periodIndex) {
    Assertions.checkIndex(periodIndex, 0, 1);
    return durationUs;
  }

  @Override
  public Object getPeriodId(int periodIndex) {
    Assertions.checkIndex(periodIndex, 0, 1);
    return ID;
  }

  @Override
  public Window getPeriodWindow(int periodIndex) {
    Assertions.checkIndex(periodIndex, 0, 1);
    return window;
  }

  @Override
  public int getPeriodWindowIndex(int periodIndex) {
    Assertions.checkIndex(periodIndex, 0, 1);
    return 0;
  }

  @Override
  public int getIndexOfPeriod(Object id) {
    return ID.equals(id) ? 0 : Timeline.NO_PERIOD_INDEX;
  }

  @Override
  public int getWindowCount() {
    return 1;
  }

  @Override
  public Window getWindow(int windowIndex) {
    Assertions.checkIndex(windowIndex, 0, 1);
    return window;
  }

}