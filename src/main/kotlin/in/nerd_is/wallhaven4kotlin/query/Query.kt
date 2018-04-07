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

package `in`.nerd_is.wallhaven4kotlin.query

import `in`.nerd_is.wallhaven4kotlin.model.enums.Category
import `in`.nerd_is.wallhaven4kotlin.model.enums.Order
import `in`.nerd_is.wallhaven4kotlin.model.enums.Purity
import `in`.nerd_is.wallhaven4kotlin.model.enums.Sorting
import `in`.nerd_is.wallhaven4kotlin.util.allOf
import `in`.nerd_is.wallhaven4kotlin.util.enumSetOf

/**
 * @author Xuqiang ZHENG on 18/3/15.
 */

fun query(init: Query.() -> Unit): Query {
  val query = Query()
  query.init()
  return query
}

class Query {

  var keywords = ""
  var page = 0L
  var categories = DEFAULT_CATEGORY
  var purities = DEFAULT_PURITY
  var sorting = Sorting.RELEVANCE
  var order = Order.DESCENDING

  fun category(vararg categories: Category) {
    this.categories = enumSetOf(*categories)
  }

  fun purity(vararg purities: Purity) {
    this.purities = enumSetOf(*purities)
  }

  class Builder {
    private var keywords = ""
    private var page = 0L
    private var categories = DEFAULT_CATEGORY
    private var purities = DEFAULT_PURITY
    private var sorting = Sorting.RELEVANCE
    private var order = Order.DESCENDING

    fun keywords(keywords: String): Builder {
      this.keywords = keywords
      return this
    }

    fun page(page: Long): Builder {
      this.page = page
      return this
    }

    fun category(vararg categories: Category): Builder {
      this.categories = enumSetOf(*categories)
      return this
    }

    fun purity(vararg purities: Purity): Builder {
      this.purities = enumSetOf(*purities)
      return this
    }

    fun sortring(sorting: Sorting): Builder {
      this.sorting = sorting
      return this
    }

    fun order(order: Order): Builder {
      this.order = order
      return this
    }

    fun build(): Query {
      val query = Query()
      query.keywords = keywords
      query.page = page
      query.categories = categories
      query.purities = purities
      query.sorting = sorting
      query.order = order
      return query
    }
  }

  companion object {
    val DEFAULT_CATEGORY = allOf<Category>()
    val DEFAULT_PURITY = enumSetOf(Purity.SFW, Purity.SKETCHY)
  }
}