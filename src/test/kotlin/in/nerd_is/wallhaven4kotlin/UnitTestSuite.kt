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

import `in`.nerd_is.wallhaven4kotlin.helper.UrlHandlerUnitTest
import `in`.nerd_is.wallhaven4kotlin.model.BitFieldEnumUnitTest
import `in`.nerd_is.wallhaven4kotlin.model.ColorUnitTest
import `in`.nerd_is.wallhaven4kotlin.parser.ListPaperUnitTest
import `in`.nerd_is.wallhaven4kotlin.parser.WallpaperParserUnitTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * @author Xuqiang ZHENG on 18/3/14.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
  UrlHandlerUnitTest::class,
  BitFieldEnumUnitTest::class,
  ColorUnitTest::class,
  WallpaperParserUnitTest::class,
  ListPaperUnitTest::class
)
class UnitTestSuite