@file:OptIn(InternalResourceApi::class)

package personalwebsite.composeapp.generated.resources

import kotlin.OptIn
import kotlin.String
import kotlin.collections.MutableMap
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.InternalResourceApi
import org.jetbrains.compose.resources.ResourceContentHash
import org.jetbrains.compose.resources.ResourceItem

private const val MD: String = "composeResources/personalwebsite.composeapp.generated.resources/"

@delegate:ResourceContentHash(-698_043_462)
public val Res.drawable.arrow_left: DrawableResource by lazy {
      DrawableResource("drawable:arrow_left", setOf(
        ResourceItem(setOf(), "${MD}drawable/arrow_left.svg", -1, -1),
      ))
    }

@delegate:ResourceContentHash(-1_676_596_130)
public val Res.drawable.arrow_right: DrawableResource by lazy {
      DrawableResource("drawable:arrow_right", setOf(
        ResourceItem(setOf(), "${MD}drawable/arrow_right.svg", -1, -1),
      ))
    }

@delegate:ResourceContentHash(94_355_038)
public val Res.drawable.calendar: DrawableResource by lazy {
      DrawableResource("drawable:calendar", setOf(
        ResourceItem(setOf(), "${MD}drawable/calendar.svg", -1, -1),
      ))
    }

@delegate:ResourceContentHash(1_689_379_621)
public val Res.drawable.code: DrawableResource by lazy {
      DrawableResource("drawable:code", setOf(
        ResourceItem(setOf(), "${MD}drawable/code.svg", -1, -1),
      ))
    }

@delegate:ResourceContentHash(379_089_144)
public val Res.drawable.compose_multiplatform: DrawableResource by lazy {
      DrawableResource("drawable:compose_multiplatform", setOf(
        ResourceItem(setOf(), "${MD}drawable/compose-multiplatform.xml", -1, -1),
      ))
    }

@delegate:ResourceContentHash(-354_338_102)
public val Res.drawable.live: DrawableResource by lazy {
      DrawableResource("drawable:live", setOf(
        ResourceItem(setOf(), "${MD}drawable/live.svg", -1, -1),
      ))
    }

@delegate:ResourceContentHash(1_058_408_479)
public val Res.drawable.location: DrawableResource by lazy {
      DrawableResource("drawable:location", setOf(
        ResourceItem(setOf(), "${MD}drawable/location.svg", -1, -1),
      ))
    }

@InternalResourceApi
internal fun _collectWebMainDrawable0Resources(map: MutableMap<String, DrawableResource>) {
  map.put("arrow_left", Res.drawable.arrow_left)
  map.put("arrow_right", Res.drawable.arrow_right)
  map.put("calendar", Res.drawable.calendar)
  map.put("code", Res.drawable.code)
  map.put("compose_multiplatform", Res.drawable.compose_multiplatform)
  map.put("live", Res.drawable.live)
  map.put("location", Res.drawable.location)
}
