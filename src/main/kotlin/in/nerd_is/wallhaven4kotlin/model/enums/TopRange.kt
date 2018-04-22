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

package `in`.nerd_is.wallhaven4kotlin.model.enums

/**
 * @author Xuqiang ZHENG on 18/4/22.
 */
enum class TopRange(val value: String) {
  LAST_DAY("1d"),
  LAST_3_DAYS("3d"),
  LAST_WEEK("1w"),
  LAST_MONTH("1M"),
  LAST_3_MONTH("3M"),
  LAST_6_MONTH("6M"),
  LAST_YEAR("1y")
}