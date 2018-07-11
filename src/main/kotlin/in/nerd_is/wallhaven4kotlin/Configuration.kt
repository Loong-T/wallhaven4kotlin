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

package `in`.nerd_is.wallhaven4kotlin

import `in`.nerd_is.wallhaven4kotlin.model.enums.*
import `in`.nerd_is.wallhaven4kotlin.util.allOf
import `in`.nerd_is.wallhaven4kotlin.util.enumSetOf
import java.util.EnumSet

/**
 * @author Xuqiang ZHENG on 18/4/7.
 */
data class Configuration(
  var categories: EnumSet<Category> = DEFAULT_CATEGORY,
  var purities: EnumSet<Purity> = DEFAULT_PURITY,
  var sorting: Sorting = DEFAULT_SORTING,
  var order: Order = DEFAULT_ORDER,
  var topRange: TopRange = DEFAULT_TOP_RANGE
) {

  fun deepCopy(
    categories: EnumSet<Category> = this.categories,
    purities: EnumSet<Purity> = this.purities,
    sorting: Sorting = this.sorting,
    order: Order = this.order,
    topRange: TopRange = this.topRange
  ): Configuration {
    return Configuration(categories.clone(), purities.clone(), sorting, order, topRange)
  }

  companion object {
    val DEFAULT_CATEGORY
      get() = allOf<Category>()
    val DEFAULT_PURITY
      get() = enumSetOf(Purity.SFW, Purity.SKETCHY)
    val DEFAULT_SORTING
      get() = Sorting.RELEVANCE
    val DEFAULT_ORDER
      get() = Order.DESCENDING
    val DEFAULT_TOP_RANGE
      get() = TopRange.LAST_MONTH
  }
}