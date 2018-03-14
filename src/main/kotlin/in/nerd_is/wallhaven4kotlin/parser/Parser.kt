package `in`.nerd_is.wallhaven4kotlin.parser

import org.jsoup.nodes.Document

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
interface Parser<out T> {
  fun parseDoc(doc: Document): T
}