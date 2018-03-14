package `in`.nerd_is.wallhaven4kotlin.helper

import org.jsoup.nodes.Node

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
fun Node.altTitle() = this.attr("original-title")!!