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

import `in`.nerd_is.wallhaven4kotlin.model.enums.Sorting
import `in`.nerd_is.wallhaven4kotlin.query.Query
import `in`.nerd_is.wallhaven4kotlin.util.bitString
import okhttp3.HttpUrl

/**
 * @author Xuqiang ZHENG on 18/3/14.
 */
object UrlHandler {

  private const val BASE_URL = "https://alpha.wallhaven.cc"
  private const val WALLPAPER_SEGMENT = "wallpaper"
  private const val SEARCH_SEGMENT = "search"

  private const val PARAM_Q = "q"
  private const val PARAM_PAGE = "page"
  private const val PARAM_CATEGORIES = "categories"
  private const val PARAM_PURITY = "purity"
  private const val PARAM_SORTING = "sorting"
  private const val PARAM_ORDER = "order"
  private const val PARAM_TOP_RANGE = "topRange"

  private val base = HttpUrl.parse(BASE_URL)!!

  fun fromWallpaperId(id: Long) =
    base.newBuilder(WALLPAPER_SEGMENT)
      ?.addPathSegment(id.toString())
      ?.build()
      ?.toString()!!

  fun fromQuery(query: Query): String {
    val builder = base.newBuilder(SEARCH_SEGMENT)
    builder?.addQueryParameter(PARAM_Q, query.keywords)
      ?.addQueryParameter(PARAM_CATEGORIES, query.categories.bitString())
      ?.addQueryParameter(PARAM_PURITY, query.purities.bitString())
      ?.addQueryParameter(PARAM_SORTING, query.sorting.value)
      ?.addQueryParameter(PARAM_ORDER, query.order.value)
      ?.addQueryParameter(PARAM_PAGE, query.page.toString())

    if (query.sorting == Sorting.TOPLIST) {
      builder?.addQueryParameter(PARAM_TOP_RANGE, query.topRange.value)
    }

    return builder?.build().toString()
  }

}