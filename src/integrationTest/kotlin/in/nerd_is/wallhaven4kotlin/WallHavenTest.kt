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

package `in`.nerd_is.wallhaven4kotlin

import `in`.nerd_is.wallhaven4kotlin.model.enums.Sorting
import `in`.nerd_is.wallhaven4kotlin.query.query
import org.hamcrest.CoreMatchers.*
import org.hamcrest.collection.IsEmptyCollection.empty
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * @author Xuqiang ZHENG on 18/3/14.
 */
class WallHavenTest {

  @Test
  fun testGetWallpaper_allRight() {
    val id = 657384L
    val wallpaper = WallHaven.getWallpaper(id)

    assertThat("wallpaper not null", wallpaper, notNullValue())
    assertThat("wallpaper id equals", wallpaper.id, equalTo(id))
  }

  @Test(expected = IllegalArgumentException::class)
  fun testGetWallpaper_badId_illegalArgument() {
    WallHaven.getWallpaper(1) // invalid wallpaper id
  }

  @Test
  fun testGetWallpaperList_randomQuery_allRight() {
    val query = query {
      sorting = Sorting.RANDOM
      keywords = ""
    }
    val list = WallHaven.search(query)

    assertThat("list not empty", list, not(empty()))
  }

  @Test
  fun testGetRandomList_allRight() {
    val list = WallHaven.random()
    assertThat("list not empty", list, not(empty()))
  }
}