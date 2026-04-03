package com.mhd_07.personal_website.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onPreviewKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.rememberAsyncImagePainter
import com.github.panpf.sketch.request.ComposableImageRequest
import com.mhd_07.personal_website.LocalTheme
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.arrow_left
import personalwebsite.composeapp.generated.resources.arrow_right

@Composable
fun ImagePreviewDialog(
    images: List<String>,
    firstImageIndex: Int = 0,
    onDismiss: () -> Unit,
) {
    val pagerState = rememberPagerState(initialPage = firstImageIndex) { images.size }
    val coroutineScope = rememberCoroutineScope()
    val theme = LocalTheme.current
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.8f))
            .onPreviewKeyEvent {
                if (it.type == KeyEventType.KeyUp && it.key == Key.Escape) {
                    onDismiss()
                    true
                } else false
            }.clickable { onDismiss() },
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable { onDismiss() }
        )
        HorizontalPager(
            modifier = Modifier
                .fillMaxHeight(0.9f)
                .fillMaxWidth(0.6f)
                .align(Alignment.Center),
            state = pagerState,
            userScrollEnabled = false,
        ) { page ->
            Box(modifier = Modifier.fillMaxSize()) {
                Image(
                    modifier = Modifier.fillMaxHeight()
                        .wrapContentWidth().align(Alignment.Center).clickable(false) {},
                    painter = rememberAsyncImagePainter(
                        ComposableImageRequest(
                            run {
                                val url = images[page]
                                BASE_URL + url
                            }
                        )),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
        }

        if (pagerState.pageCount > 1) {
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .fillMaxHeight()
                    .fillMaxWidth(0.1f)
                    .clickable(pagerState.currentPage != 0) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_left),
                    contentDescription = "Previous",
                    modifier = Modifier.size(theme.dimensions.inSectionSpacing),
                    tint = if (pagerState.currentPage != 0) theme.colors.text else Color.DarkGray
                )
            }

            // Right Arrow
            Box(
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .fillMaxHeight()
                    .fillMaxWidth(0.1f)
                    .clickable(pagerState.currentPage != pagerState.pageCount - 1) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(Res.drawable.arrow_right),
                    contentDescription = "Next",
                    modifier = Modifier.size(theme.dimensions.inSectionSpacing),
                    tint = if (pagerState.currentPage != pagerState.pageCount - 1) theme.colors.text else  Color.DarkGray
                )
            }
        }
    }
}
