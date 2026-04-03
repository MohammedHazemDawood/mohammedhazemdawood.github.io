@file:OptIn(InternalResourceApi::class)

package personalwebsite.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.FontResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceContentHash
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/personalwebsite.composeapp.generated.resources/"

@delegate:ResourceContentHash(803_929_443)
public val Res.font.g_bold: FontResource by lazy {
      FontResource("font:g_bold", setOf(
        ResourceItem(setOf(), "${MD}font/g_bold.ttf", -1, -1),
      ))
    }

@delegate:ResourceContentHash(57_245_069)
public val Res.font.g_extra_bold: FontResource by lazy {
      FontResource("font:g_extra_bold", setOf(
        ResourceItem(setOf(), "${MD}font/g_extra_bold.ttf", -1, -1),
      ))
    }

@delegate:ResourceContentHash(-1_367_266_544)
public val Res.font.g_extra_light: FontResource by lazy {
      FontResource("font:g_extra_light", setOf(
        ResourceItem(setOf(), "${MD}font/g_extra_light.ttf", -1, -1),
      ))
    }

@delegate:ResourceContentHash(766_043_924)
public val Res.font.g_light: FontResource by lazy {
      FontResource("font:g_light", setOf(
        ResourceItem(setOf(), "${MD}font/g_light.ttf", -1, -1),
      ))
    }

@delegate:ResourceContentHash(555_619_594)
public val Res.font.g_medium: FontResource by lazy {
      FontResource("font:g_medium", setOf(
        ResourceItem(setOf(), "${MD}font/g_medium.ttf", -1, -1),
      ))
    }

@delegate:ResourceContentHash(1_952_728_614)
public val Res.font.g_regular: FontResource by lazy {
      FontResource("font:g_regular", setOf(
        ResourceItem(setOf(), "${MD}font/g_regular.ttf", -1, -1),
      ))
    }

@delegate:ResourceContentHash(-1_329_982_008)
public val Res.font.g_semi_bold: FontResource by lazy {
      FontResource("font:g_semi_bold", setOf(
        ResourceItem(setOf(), "${MD}font/g_semi_bold.ttf", -1, -1),
      ))
    }

@delegate:ResourceContentHash(276_800_688)
public val Res.font.g_thin: FontResource by lazy {
      FontResource("font:g_thin", setOf(
        ResourceItem(setOf(), "${MD}font/g_thin.ttf", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectWebMainFont0Resources(map: MutableMap<String, FontResource>) {
  map.put("g_bold", Res.font.g_bold)
  map.put("g_extra_bold", Res.font.g_extra_bold)
  map.put("g_extra_light", Res.font.g_extra_light)
  map.put("g_light", Res.font.g_light)
  map.put("g_medium", Res.font.g_medium)
  map.put("g_regular", Res.font.g_regular)
  map.put("g_semi_bold", Res.font.g_semi_bold)
  map.put("g_thin", Res.font.g_thin)
}
