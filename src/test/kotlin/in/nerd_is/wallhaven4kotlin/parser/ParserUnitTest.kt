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

package `in`.nerd_is.wallhaven4kotlin.parser

import `in`.nerd_is.wallhaven4kotlin.model.Purity
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.collection.IsEmptyCollection.empty
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.junit.BeforeClass
import org.junit.Test

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
class ParserUnitTest {

  @Test
  fun testParseResolution_allRight() {
    val resolution = ResolutionParser.parse(doc)

    assertThat("not null", resolution, notNullValue())
    assertThat("width is right", resolution.width, equalTo(1920))
    assertThat("height is right", resolution.height, equalTo(1080))
    assertThat("ratio is right", resolution.ratio,
      equalTo(resolution.width.toFloat() / resolution.height))
  }

  @Test
  fun testParseColor_allRight() {
    val colors = ColorParser.parse(doc)

    assertThat("not empty", colors, not(empty()))
  }

  @Test
  fun testParseTag_allRight() {
    val tags = TagParser.parse(doc)

    assertThat("has 8 tags", tags.size, equalTo(8))
    assertThat("tag purity is sfw", tags[0].purity, equalTo(Purity.Sfw))
  }

  @Test
  fun testParsePurity_allRight() {
    val purity = PurityParser.parse(doc)

    assertThat("purity is sfw", purity, equalTo(Purity.Sfw))
  }

  @Test
  fun testParseUser_allRight() {
    val user = UserParser.parse(doc)

    assertThat("name matches", user.name, equalTo("ConsistentHypocrite"))
    assertThat("avatar is valid url", user.avatarThumbnail,
      startsWith("https://"))
  }

  @Test
  fun testParseWallpaper_allRight() {
    val wallpaper = WallpaperParser.parse(doc)

    assertThat("not null", wallpaper, notNullValue())
  }

  companion object {

    lateinit var doc: Document

    @BeforeClass @JvmStatic
    fun init() {
      val res = this::class.java.classLoader.getResource("wallpaper.html")
      doc = Jsoup.parse(res.readText())
    }
  }
}