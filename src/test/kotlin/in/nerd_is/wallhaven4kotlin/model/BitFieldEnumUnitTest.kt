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

package `in`.nerd_is.wallhaven4kotlin.model

import `in`.nerd_is.wallhaven4kotlin.model.enums.Category
import `in`.nerd_is.wallhaven4kotlin.model.enums.Purity
import `in`.nerd_is.wallhaven4kotlin.util.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Xuqiang ZHENG on 18/4/7.
 */
class BitFieldEnumUnitTest {
  @Test
  fun testCategoryBitValue_allRight() {
    assertThat("People is 0b001", Category.PEOPLE.bitValue, equalTo(0b001L))
    assertThat("Anime is 0b010", Category.ANIME.bitValue, equalTo(0b010L))
    assertThat("General is 0b100", Category.GENERAL.bitValue, equalTo(0b100L))
  }

  @Test
  fun testPurityBitValue_allRight() {
    assertThat("NSFW is 0b001", Purity.NSFW.bitValue, equalTo(0b001L))
    assertThat("Sketchy is 0b010", Purity.SKETCHY.bitValue, equalTo(0b010L))
    assertThat("SFW is 0b100", Purity.SFW.bitValue, equalTo(0b100L))
  }

  @Test
  fun testBitString_allRight() {
    var categorySet = allOf<Category>()
    assertThat("bit string is 111", categorySet.bitString(), equalTo("111"))

    categorySet = enumSetOf(Category.ANIME)
    assertThat("bit string is 010", categorySet.bitString(), equalTo("010"))

    val puritySet = enumSetOf(Purity.SFW, Purity.SKETCHY)
    assertThat("bit string is 110", puritySet.bitString(), equalTo("110"))
  }

  @Test
  fun testSumBits_allRight() {
    var set = allOf<Category>()
    var sum = set.sumBits()
    assertThat(set, equalTo(sum.enumSet()))

    set = enumSetOf(Category.ANIME, Category.PEOPLE)
    sum = set.sumBits()
    assertThat(set, equalTo(sum.enumSet()))
  }
}