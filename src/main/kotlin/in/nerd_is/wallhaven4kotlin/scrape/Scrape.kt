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

package `in`.nerd_is.wallhaven4kotlin.scrape

import `in`.nerd_is.wallhaven4kotlin.model.Thumbnail
import `in`.nerd_is.wallhaven4kotlin.model.Wallpaper
import `in`.nerd_is.wallhaven4kotlin.parser.ListParser
import `in`.nerd_is.wallhaven4kotlin.parser.WallpaperParser
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.lang.IllegalArgumentException

/**
 * @author Xuqiang ZHENG on 18/3/14.
 */
object Scrape {

  private val client = OkHttpClient()

  fun scrapeWallpaper(url: String): Wallpaper {
    return WallpaperParser.parseDoc(fetchWallpaperPage(url))
  }

  fun scrapeList(url: String): List<Thumbnail> {
    return ListParser.parseDoc(fetchListPage(url))
  }

  private fun fetchDoc(url: String): Document {
    val request = Request.Builder().url(url).build()
    val response = client.newCall(request).execute()
    return Jsoup.parse(response.body()?.string())
  }

  private fun fetchWallpaperPage(url: String): Document {
    val document = fetchDoc(url)
    if (WallpaperParser.isNotValid(document))
      throw IllegalArgumentException("Wallpaper with url $url do not exists")

    return document
  }

  private fun fetchListPage(url: String): Document {
    val document = fetchDoc(url)
    if (ListParser.isNotValid(document))
      throw IllegalArgumentException("List with url $url do not exists")

    return document
  }
}