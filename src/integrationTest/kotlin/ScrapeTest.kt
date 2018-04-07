/*
 * Copyright 2018 Xuqiang ZHENG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import `in`.nerd_is.wallhaven4kotlin.scrape.Scrape
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.notNullValue
import org.jsoup.HttpStatusException
import org.junit.Test

import org.junit.Assert.*

/**
 * @author Xuqiang ZHENG on 18/3/14.
 */
class ScrapeTest {

  @Test
  fun testGetWallpaper_allRight() {
    val id = 333898L
    val wallpaper = Scrape.get(333898)

    assertThat("wallpaper not null", wallpaper, notNullValue())
    assertThat("wallpaper id equals", wallpaper.id, equalTo(id))
  }

  @Test(expected = IllegalArgumentException::class)
  fun testGetWallpaper_badId_illegalArgument() {
    Scrape.get(1)
  }
}