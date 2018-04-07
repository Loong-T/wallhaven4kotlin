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

package `in`.nerd_is.wallhaven4kotlin.model

import `in`.nerd_is.wallhaven4kotlin.model.enums.Category
import `in`.nerd_is.wallhaven4kotlin.model.enums.Purity
import java.util.Date

/**
 * @author Xuqiang ZHENG on 18/3/7.
 */
data class Wallpaper(
  val id: Long,
  val resolution: Resolution,
  val colors: List<Color>,
  val tags: List<Tag>,
  val purity: Purity,
  val uploader: User,
  val uploadTime: Date,
  val category: Category,
  val size: String,
  val viewCount: Long,
  val favCount: Long,
  val fullUrl: String
) : PaperBase(id)