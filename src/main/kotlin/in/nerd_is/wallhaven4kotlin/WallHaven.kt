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

import `in`.nerd_is.wallhaven4kotlin.Configuration.Companion.DEFAULT_TOP_RANGE
import `in`.nerd_is.wallhaven4kotlin.helper.UrlHandler.fromQuery
import `in`.nerd_is.wallhaven4kotlin.helper.UrlHandler.fromWallpaperId
import `in`.nerd_is.wallhaven4kotlin.model.Thumbnail
import `in`.nerd_is.wallhaven4kotlin.model.Wallpaper
import `in`.nerd_is.wallhaven4kotlin.model.enums.Sorting
import `in`.nerd_is.wallhaven4kotlin.model.enums.TopRange
import `in`.nerd_is.wallhaven4kotlin.query.Query
import `in`.nerd_is.wallhaven4kotlin.query.query
import `in`.nerd_is.wallhaven4kotlin.scrape.Scrape.scrapeList
import `in`.nerd_is.wallhaven4kotlin.scrape.Scrape.scrapeWallpaper

/**
 * @author Xuqiang ZHENG on 18/2/27.
 */
object WallHaven {

  var configuration = Configuration.DEFAULT

  fun getWallpaper(id: Long): Wallpaper {
    return scrapeWallpaper(fromWallpaperId(id))
  }

  fun random(keywords: String = ""): List<Thumbnail> {
    val query = query {
      this.keywords = keywords
      sorting = Sorting.RANDOM
      categories = configuration.categories
      purities = configuration.purities
      order = configuration.order
    }
    return search(query)
  }

  fun latest(keywords: String = "", page: Long = 1L): List<Thumbnail> {
    val query = query {
      this.keywords = keywords
      this.page = page
      sorting = Sorting.DATE_ADDED
      categories = configuration.categories
      purities = configuration.purities
      order = configuration.order
    }
    return search(query)
  }

  fun toplist(
    keywords: String = "",
    page: Long = 1L,
    topRange: TopRange = DEFAULT_TOP_RANGE
  ): List<Thumbnail> {
    val query = query {
      this.keywords = keywords
      this.page = page
      sorting = Sorting.TOPLIST
      this.topRange = topRange
      categories = configuration.categories
      purities = configuration.purities
      order = configuration.order
    }
    return search(query)
  }

  fun search(query: Query): List<Thumbnail> {
    return scrapeList(fromQuery(query))
  }
}