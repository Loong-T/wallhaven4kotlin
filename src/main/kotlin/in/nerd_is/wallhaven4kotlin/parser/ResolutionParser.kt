package `in`.nerd_is.wallhaven4kotlin.parser

import `in`.nerd_is.wallhaven4kotlin.model.Resolution
import org.jsoup.nodes.Document

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
object ResolutionParser : Parser<Resolution> {

  private const val selector = ".showcase-resolution"

  override fun parse(doc: Document): Resolution {
    val element = doc.selectFirst(selector)
    val title = element.attr("original-title")
    val list = element.text().split('x').map(String::trim)
    return Resolution(list[0].toInt(), list[1].toInt(), title)
  }
}