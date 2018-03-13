package `in`.nerd_is.wallhaven4kotlin.helper

import org.jsoup.nodes.Node

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
fun Node.altText() = this.attr("original-title")!!