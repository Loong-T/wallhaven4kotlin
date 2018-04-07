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

import `in`.nerd_is.wallhaven4kotlin.model.enums.Category
import `in`.nerd_is.wallhaven4kotlin.model.enums.Purity
import `in`.nerd_is.wallhaven4kotlin.model.Thumbnail
import `in`.nerd_is.wallhaven4kotlin.util.enumNames
import `in`.nerd_is.wallhaven4kotlin.util.enumValueOfIgnoreCase
import org.jsoup.nodes.Document

/**
 * @author Xuqiang ZHENG on 18/3/14.
 */
object ListParser : Parser<List<Thumbnail>> {

  private const val selector = ".thumb-listing .thumb-listing-page ul > li figure"

  override fun parseDoc(doc: Document): List<Thumbnail> {
    val elemList = doc.select(selector)

    val list = ArrayList<Thumbnail>(elemList.size)
    for (elem in elemList) {
      val id = elem.attr("data-wallpaper-id").toLong()

      val infoElem = elem.selectFirst(".thumb-info")
      val resolution = ResolutionParser.parseString(
        infoElem.selectFirst(".wall-res").text()
      )
      val favCount = elem.selectFirst(".wall-favs").text().toLong()

      lateinit var purity: Purity
      lateinit var category: Category
      val classNames = elem.classNames()
        .map { it.substringAfter('-', "") }
        .filter { it.isNotEmpty() }
      for (item in classNames) {
        val name = item.toUpperCase()
        when (name) {
          in enumNames<Purity>() -> purity = enumValueOfIgnoreCase(name)
          in enumNames<Category>() -> category = enumValueOfIgnoreCase(name)
        }
      }

      list.add(Thumbnail(id, resolution, purity, category, favCount))
    }

    return list
  }
}