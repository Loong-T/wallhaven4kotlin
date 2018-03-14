package `in`.nerd_is.wallhaven4kotlin

import `in`.nerd_is.wallhaven4kotlin.model.Wallpaper
import `in`.nerd_is.wallhaven4kotlin.scrape.Scrape

/**
 * @author Xuqiang ZHENG on 18/2/27.
 */
object WallHaven {
  fun getWallpaper(id: Long): Wallpaper {
    return Scrape.get(id)
  }
}