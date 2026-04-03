package com.mhd_07.personal_website.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.github.panpf.sketch.rememberAsyncImagePainter
import com.github.panpf.sketch.request.ComposableImageRequest


@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
){
    Image(
        modifier = modifier,
        painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + url)),
        contentDescription = contentDescription,
        contentScale = ContentScale.Fit
    )
}