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

import `in`.nerd_is.wallhaven4kotlin.model.Category
import `in`.nerd_is.wallhaven4kotlin.model.Wallpaper
import `in`.nerd_is.wallhaven4kotlin.util.Constant.DATE_FORMAT
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat

/**
 * @author Xuqiang ZHENG on 18/2/27.
 */
object WallpaperParser : Parser<Wallpaper> {

//  private const val URL = "/wallpaper/"

  override fun parse(doc: Document): Wallpaper {
    val resolution = ResolutionParser.parse(doc)
    val colors = ColorParser.parse(doc)
    val tags = TagParser.parse(doc)
    val purity = PurityParser.parse(doc)
    val uploader = UserParser.parse(doc)

    val dlElem = doc.selectFirst("[data-storage-id='showcase-info'] > dl")

    val timeStr = dlElem.selectFirst(".showcase-uploader time")
      .attr("datetime")
    val date = SimpleDateFormat(DATE_FORMAT).parse(timeStr)

    lateinit var category: Category
    lateinit var size: String
    var viewCount: Long = 0
    var favCount: Long = 0
    for (child in dlElem.children()) {
      if (child.tagName() != "dt")
        continue

      when (child.text().trim()) {
        "Category" -> {
          val categoryName = child.nextElementSibling().text().capitalize()
          category = Category.valueOf(categoryName)
        }
        "Size" -> {
          size = child.nextElementSibling().text()
        }
        "Views" -> {
          viewCount = child.nextElementSibling().text()
            .replace(",", "").toLong()
        }
        "Favorites" -> {
          favCount = child.nextElementSibling().child(0).text().toLong()
        }
      }
    }

    val paperElem = doc.selectFirst("#wallpaper")
    val fullUrl = paperElem.attr("src")
    val id = paperElem.attr("data-wallpaper-id").toLong()

    return Wallpaper(id, resolution, colors, tags, purity, uploader,
      date, category, size, viewCount, favCount, fullUrl)
  }

//  fun get(id: Long): Wallpaper {
//    val doc = Jsoup.connect(BASE_URL + URL + id).get()
//  }

}