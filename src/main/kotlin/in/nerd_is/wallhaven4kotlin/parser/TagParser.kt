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

import `in`.nerd_is.wallhaven4kotlin.helper.altTitle
import `in`.nerd_is.wallhaven4kotlin.model.Purity
import `in`.nerd_is.wallhaven4kotlin.model.Tag
import org.jsoup.nodes.Document

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
object TagParser : Parser<List<Tag>> {

  private const val selector = "#tags .tag"

  override fun parseDoc(doc: Document): List<Tag> {
    val elements = doc.select(selector)
    val tags = ArrayList<Tag>(elements.size)

    for (elem in elements) {
      val id = elem.attr("data-tag-id").toLong()
      val purity = elem.className()
        .split(" ")
        .map { it.substringAfter('-', "") }
        .filter { it != "" }
        .map { Purity.valueOf(it.capitalize()) }
        .first()
      val nameElem = elem.selectFirst(".tagname")
      val name = nameElem.text()
      val altText = nameElem.altTitle()

      tags.add(Tag(id, name, purity, altText))
    }

    return tags
  }
}