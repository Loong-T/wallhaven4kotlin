package `in`.nerd_is.wallhaven4kotlin.model

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

/**
 * @author Xuqiang ZHENG on 18/3/13.
 */
class ColorUnitTest {
  @Test
  fun testNewColorFromHex_allRight() {
    var color = Color.new("000000")
    assertThat("red value is right", color.r, equalTo(0))
    assertThat("green value is right", color.g, equalTo(0))
    assertThat("blue value is right", color.b, equalTo(0))

    color = Color.new("ffffff")
    assertThat("red value is right", color.r, equalTo(255))
    assertThat("green value is right", color.g, equalTo(255))
    assertThat("blue value is right", color.b, equalTo(255))

    val red = 133
    val green = 233
    val blue = 33
    val hexStr = red.toString(16) + green.toString(16) + blue.toString(16)
    color = Color.new(hexStr)
    assertThat("red value is right", color.r, equalTo(red))
    assertThat("green value is right", color.g, equalTo(green))
    assertThat("blue value is right", color.b, equalTo(blue))
  }

  @Test(expected = IllegalArgumentException::class)
  fun testNewColorFromHex_hexLengthTooShort_throwIllegalArgument() {
    Color.new("fff")
  }

  @Test(expected = IllegalArgumentException::class)
  fun testNewColorFromHex_hexLengthTooLong_throwIllegalArgument() {
    Color.new("00ffffff")
  }

  @Test(expected = IllegalArgumentException::class)
  fun testNewColorFromHex_invalidHexChar_throwIllegalArgument() {
    Color.new("ggffgg")
  }
}