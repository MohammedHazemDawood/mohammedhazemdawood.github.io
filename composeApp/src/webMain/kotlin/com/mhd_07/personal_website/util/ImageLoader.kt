package com.mhd_07.personal_website.util

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.CircularProgressIndicator
import com.skydoves.landscapist.core.Landscapist
import com.skydoves.landscapist.core.ImageRequest
import com.skydoves.landscapist.core.model.ImageResult
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import com.mhd_07.personal_website.LocalTheme
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.image.LandscapistImage
import com.skydoves.landscapist.image.LandscapistImageState

private val landscapist = Landscapist.getInstance()

@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
    colorFilter: ColorFilter? = null
) {
    val theme = LocalTheme.current
    LandscapistImage(
        modifier =  modifier,
        imageModel = { url },
        imageOptions = ImageOptions(contentScale = contentScale, contentDescription = contentDescription, colorFilter = colorFilter),
        requestBuilder = {
            size(width = Int.MAX_VALUE, height = Int.MAX_VALUE)
        },
        loading = {
            Box(modifier = Modifier.matchParentSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = theme.colors.primary
                )
            }
        }
    )
}