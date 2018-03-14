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

import `in`.nerd_is.wallhaven4kotlin.model.Color
import org.jsoup.nodes.Document

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
object ColorParser : Parser<List<Color>> {

  private const val selector = ".color-palette .color a"

  override fun parseDoc(doc: Document): List<Color> {
    val elements = doc.select(selector)

    return elements.map { it.attr("href") }
      .map { it.substringAfter("colors=") }
      .map { Color.new(it) }
  }
}