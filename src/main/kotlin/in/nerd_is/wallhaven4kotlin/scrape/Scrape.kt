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

import `in`.nerd_is.wallhaven4kotlin.helper.UrlHandler
import `in`.nerd_is.wallhaven4kotlin.model.Wallpaper
import `in`.nerd_is.wallhaven4kotlin.parser.WallpaperParser
import `in`.nerd_is.wallhaven4kotlin.parser.WallpaperParser.WALLPAPER_SELECTOR
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

/**
 * @author Xuqiang ZHENG on 18/3/14.
 */
object Scrape {

  private val client = OkHttpClient()

  fun get(id: Long): Wallpaper {
    return WallpaperParser.parseDoc(scrapeWallpaperPage(id))
  }

  private fun scrapeWallpaperPage(id: Long): Document {
    val request = Request.Builder().url(UrlHandler.getWallpaperUrl(id)).build()
    val response = client.newCall(request).execute()
    val document = Jsoup.parse(response.body()?.string())
    if (document.selectFirst(WALLPAPER_SELECTOR) == null)
      throw IllegalArgumentException("Wallpaper with id $id do not exists")

    return document
  }
}