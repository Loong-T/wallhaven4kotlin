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

import `in`.nerd_is.wallhaven4kotlin.model.Wallpaper
import `in`.nerd_is.wallhaven4kotlin.model.enums.Category
import `in`.nerd_is.wallhaven4kotlin.util.Constant.DATE_FORMAT
import `in`.nerd_is.wallhaven4kotlin.util.enumValueOfIgnoreCase
import org.jsoup.nodes.Document
import java.text.SimpleDateFormat

/**
 * @author Xuqiang ZHENG on 18/2/27.
 */
object WallpaperParser : Parser<Wallpaper> {

  private const val WALLPAPER_SELECTOR = "#wallpaper"

  fun isNotValid(doc: Document): Boolean {
    return doc.selectFirst(WALLPAPER_SELECTOR) == null
  }

  override fun parseDoc(doc: Document): Wallpaper {

    val paperElem = doc.selectFirst(WALLPAPER_SELECTOR)
    val id = paperElem.attr("data-wallpaper-id").toLong()
    val fullUrl = "https:" + paperElem.attr("src")

    val resolution = ResolutionParser.parseDoc(doc)
    val colors = ColorParser.parseDoc(doc)
    val tags = TagParser.parseDoc(doc)
    val purity = PurityParser.parseDoc(doc)
    val uploader = UserParser.parseDoc(doc)

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
          val categoryName = child.nextElementSibling().text()
          category = enumValueOfIgnoreCase(categoryName)
        }
        "Size" -> {
          size = child.nextElementSibling().text()
        }
        "Views" -> {
          viewCount = child.nextElementSibling().text()
            .replace(",", "").toLong()
        }
        "Favorites" -> {
          val sibling = child.nextElementSibling()
          favCount = if (sibling.children().isEmpty()) {
            sibling.text().toLong()
          } else {
            sibling.child(0).text().toLong()
          }
        }
      }
    }

    return Wallpaper(id, resolution, colors, tags, purity, uploader,
      date, category, size, viewCount, favCount, fullUrl)
  }

}