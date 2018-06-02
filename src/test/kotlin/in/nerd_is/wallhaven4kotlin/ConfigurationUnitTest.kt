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

import `in`.nerd_is.wallhaven4kotlin.model.enums.Category
import `in`.nerd_is.wallhaven4kotlin.model.enums.Order
import `in`.nerd_is.wallhaven4kotlin.model.enums.Purity
import `in`.nerd_is.wallhaven4kotlin.model.enums.Sorting
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Xuqiang ZHENG on 18/4/26.
 */
class ConfigurationUnitTest {

  @Test
  fun testConfigurationEquals_allRight() {
    val config1 = Configuration()
    val config2 = Configuration()
    val config3 = config1.deepCopy()

    assertThat("should equal", config1, equalTo(config3))

    config1.purities.add(Purity.NSFW)
    assertThat("not equal", config1, not(equalTo(config3)))
    assertThat("should not share the same purities", config2.purities, not(equalTo(config1.purities)))

    config3.purities.add(Purity.NSFW)
    assertThat("equals again", config1, equalTo(config3))
  }

  @Test
  fun testConfigurationDeepCopy_allRight() {
    val config1 = Configuration()
    val config2 = config1.deepCopy()

    assertThat("not the same object", config1 === config2, equalTo(false))

    config1.categories.remove(Category.ANIME)
    assertThat("categories is copied", config2.categories.contains(Category.ANIME), equalTo(true))

    config1.purities.add(Purity.NSFW)
    assertThat("purities is copied", config2.purities.contains(Purity.NSFW), equalTo(false))

    config1.sorting = Sorting.RANDOM
    assertThat("default sorting is not random", Configuration.DEFAULT_SORTING, not(equalTo(Sorting.RANDOM)))
    assertThat("sorting is copied", config2.sorting, equalTo(Configuration.DEFAULT_SORTING))

    config1.order = Order.ASCENDING
    assertThat("default order is not ascending", Configuration.DEFAULT_ORDER, not(equalTo(Order.ASCENDING)))
    assertThat("order is copied", config2.order, equalTo(Configuration.DEFAULT_ORDER))
  }
}