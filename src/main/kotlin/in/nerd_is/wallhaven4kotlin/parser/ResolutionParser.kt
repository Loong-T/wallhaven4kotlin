package `in`.nerd_is.wallhaven4kotlin.parser

import `in`.nerd_is.wallhaven4kotlin.model.Resolution
import org.jsoup.nodes.Document

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
object ResolutionParser : Parser<Resolution> {

  private const val selector = ".showcase-resolution"

  override fun parseDoc(doc: Document): Resolution {
    val element = doc.selectFirst(selector)
    val title = element.attr("original-title")
    val res = parseString(element.text())
    res.altTitle = title
    return res
  }

  fun parseString(str: String): Resolution {
    val sizes = str.split('x').map { it.trim().toInt() }
    return Resolution(sizes[0], sizes[1])
  }
}