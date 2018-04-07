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

package `in`.nerd_is.wallhaven4kotlin.helper

import `in`.nerd_is.wallhaven4kotlin.model.enums.Order
import `in`.nerd_is.wallhaven4kotlin.model.enums.Purity
import `in`.nerd_is.wallhaven4kotlin.model.enums.Sorting
import `in`.nerd_is.wallhaven4kotlin.query.query
import `in`.nerd_is.wallhaven4kotlin.util.allOf
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Xuqiang ZHENG on 18/3/14.
 */
class UrlHandlerUnitTest {
  @Test
  fun testWallpaperUrl_allRight() {
    val url = UrlHandler.fromWallpaperId(333898)
    assertThat(
      "url equals", url,
      equalTo("https://alpha.wallhaven.cc/wallpaper/333898")
    )
  }

  @Test
  fun testFromQueryToUrl_allRight() {
    val query = query {
      keywords = ""
      page = 2
      categories = allOf()
      purity(Purity.SFW, Purity.SKETCHY)
      sorting = Sorting.RANDOM
      order = Order.DESCENDING
    }
    assertThat(
      "random url matches", UrlHandler.fromQuery(query),
      equalTo("https://alpha.wallhaven.cc/search?q=&categories=111&purity=110&sorting=random&order=desc&page=2")
    )
  }
}