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
 *
 */

package `in`.nerd_is.wallhaven4kotlin.model;

/**
 * Created by Xuqiang ZHENG on 18/3/9.
 */
data class Color(
  val r: Int,
  val g: Int,
  val b: Int
) {
  companion object {
    fun new(hexStr: String): Color {
      if (hexStr.length != 6) {
        throw IllegalArgumentException("Length of hex string should be 6")
      }

      val r = hexStr.substring(0, 2).toInt(16)
      val g = hexStr.substring(2, 4).toInt(16)
      val b = hexStr.substring(4).toInt(16)
      return Color(r, g, b)
    }
  }
}