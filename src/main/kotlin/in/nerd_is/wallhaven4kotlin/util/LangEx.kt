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

package `in`.nerd_is.wallhaven4kotlin.util

import `in`.nerd_is.wallhaven4kotlin.model.enums.BitFieldEnum
import java.util.EnumSet

inline fun <reified E : Enum<E>> enumNames() =
  enumValues<E>().map { it.name }

inline fun <reified E : Enum<E>> enumValueOfIgnoreCase(value: String): E {
  for (enum in enumValues<E>()) {
    if (enum.name.compareTo(value, true) == 0)
      return enum
  }

  throw IllegalArgumentException("this enum type has no constant with the specified name")
}

inline fun <reified E : Enum<E>> enumSetOf(vararg args: E): EnumSet<E> {
  val set = EnumSet.noneOf(E::class.java)
  for (item in args) set.add(item)
  return set
}

inline fun <reified E : Enum<E>> allOf() = EnumSet.allOf(E::class.java)!!

fun <E> EnumSet<E>.sumBits(): Long
    where E : Enum<E>,
          E : BitFieldEnum {
  return this.fold(0L) { acc, e -> acc + e.bitValue }
}

inline fun <reified E> Long.enumSet(): EnumSet<E>
    where E : Enum<E>,
          E : BitFieldEnum {
  val enums = enumValues<E>()
  val set = EnumSet.noneOf(E::class.java)
  var ordinal = 0
  while (ordinal < enums.size) {
    val bit = this.shr(ordinal).and(1)
    if (bit == 1L) {
      set.add(enums[ordinal])
    }

    ordinal += 1
  }
  return set
}

inline fun <reified E> EnumSet<E>.bitString(): String
    where E : Enum<E>,
          E : BitFieldEnum {
  val s = this.sumBits().toString(2)
  return s.padStart(allOf<E>().size, '0')
}
