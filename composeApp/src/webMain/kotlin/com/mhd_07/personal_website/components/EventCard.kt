package com.mhd_07.personal_website.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.rememberAsyncImagePainter
import com.github.panpf.sketch.request.ComposableImageRequest
import com.mhd_07.personal_website.LocalTheme
import com.mhd_07.personal_website.model.Event
import org.jetbrains.compose.resources.painterResource
import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.calendar
import personalwebsite.composeapp.generated.resources.location

@Composable
fun EventCardDesktop(
    modifier: Modifier = Modifier,
    event: Event,
    onOpenDialog: (List<String>, Int) -> Unit,
    shape: RoundedCornerShape
) {
    val theme = LocalTheme.current
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = theme.colors.card)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(theme.dimensions.cardInnerMargin),
            horizontalArrangement = Arrangement.spacedBy(theme.dimensions.cardInnerMargin)
        ) {
            //Start:
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.padding(bottom = theme.dimensions.titleDescriptionSpacing),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.titleContentSpacing)
                ) {
                    GText(
                        text = event.title,
                        style = theme.typography.titleLarge,
                        color = theme.colors.primary,
                        modifier = Modifier.weight(1f)
                    )
                    GText(
                        text = event.myRole,
                        style = theme.typography.titleSmall,
                        color = theme.colors.text,
                        modifier = Modifier.weight(0.3f)
                    )
                }
                GText(
                    text = event.description,
                    style = theme.typography.description,
                    color = theme.colors.text,
                    modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
                )
                GText(
                    text = event.expandedRole,
                    style = theme.typography.display,
                    color = theme.colors.text,
                    modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin),
                    modifier = Modifier.padding(bottom = theme.dimensions.smallMargin)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.location),
                        contentDescription = null,
                        tint = theme.colors.text,
                        modifier = Modifier.size(theme.dimensions.iconSize)
                    )
                    GText(
                        text = event.location,
                        style = theme.typography.description,
                        color = theme.colors.text,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.calendar),
                        contentDescription = null,
                        tint = theme.colors.text,
                        modifier = Modifier.size(theme.dimensions.iconSize)
                    )
                    GText(
                        text = event.date,
                        style = theme.typography.description,
                        color = theme.colors.text,
                    )
                }
            }

            //End
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing)
            ) {
                if (event.images.isNotEmpty())
                    Row(horizontalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin)) {
                        Image(
                            modifier = Modifier.clip(
                                RoundedCornerShape(
                                    topStart = theme.dimensions.cardInnerMargin,
                                    bottomStart = theme.dimensions.cardInnerMargin,
                                    topEnd = if (event.images.size == 1) theme.dimensions.cardInnerMargin else 0.dp,
                                    bottomEnd = if (event.images.size == 1) theme.dimensions.cardInnerMargin else 0.dp
                                )
                            ).aspectRatio(2 / 3f).clickable { onOpenDialog(event.images, 0) }
                                .weight(if (event.images.size == 1) 2f else 1f),
                            painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + event.images.first())),
                            contentDescription = null,
                            contentScale = ContentScale.Crop
                        )
                        if (event.images.size > 1)
                            Box(
                                modifier = Modifier.weight(1f)
                                    .clickable { onOpenDialog(event.images, 1) },
                                contentAlignment = Alignment.Center
                            ) {
                                Image(
                                    modifier = Modifier.clip(
                                        RoundedCornerShape(
                                            topEnd = theme.dimensions.cardInnerMargin,
                                            bottomEnd = theme.dimensions.cardInnerMargin,
                                            topStart = 0.dp,
                                            bottomStart = 0.dp
                                        )
                                    ).aspectRatio(2 / 3f),
                                    painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + event.images[1])),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop
                                )

                                if (event.images.size >= 3)
                                    Box(
                                        modifier = Modifier
                                            .matchParentSize()
                                            .clip(
                                                RoundedCornerShape(
                                                    topEnd = theme.dimensions.cardInnerMargin,
                                                    bottomEnd = theme.dimensions.cardInnerMargin,
                                                    topStart = 0.dp,
                                                    bottomStart = 0.dp
                                                )
                                            )
                                            .background(Color.Black.copy(alpha = 0.6f)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        GText(
                                            text = "+${event.images.size - 1}",
                                            style = theme.typography.description,
                                            color = theme.colors.text,
                                            modifier = Modifier.align(Alignment.Center)
                                        )
                                    }
                            }
                    }
                GText(
                    text = event.takeaways,
                    style = theme.typography.display,
                    color = theme.colors.text,
                )
            }
        }
    }
}


@Composable
fun EventCardMobile(
    modifier: Modifier = Modifier,
    event: Event,
    onOpenDialog: (List<String>, Int) -> Unit,
    shape: RoundedCornerShape
) {
    val theme = LocalTheme.current
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = theme.colors.card)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(theme.dimensions.cardInnerMargin),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            GText(
                text = event.title,
                style = theme.typography.titleLarge,
                color = theme.colors.primary,
                modifier = Modifier.padding(bottom = theme.dimensions.titleDescriptionSpacing),
                align = TextAlign.Center
            )
            GText(
                text = event.description,
                style = theme.typography.description,
                color = theme.colors.text,
                modifier = Modifier.padding(bottom = theme.dimensions.titleContentSpacing),
                align = TextAlign.Center
            )
            GText(
                text = event.myRole,
                style = theme.typography.titleSmall,
                color = theme.colors.text,
                modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing),
                align = TextAlign.Center
            )
            if (event.images.isNotEmpty())
                Row(
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin),
                    modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
                ) {
                    Image(
                        modifier = Modifier.clip(
                            RoundedCornerShape(
                                topStart = theme.dimensions.cardInnerMargin,
                                bottomStart = theme.dimensions.cardInnerMargin,
                                topEnd = if (event.images.size == 1) theme.dimensions.cardInnerMargin else 0.dp,
                                bottomEnd = if (event.images.size == 1) theme.dimensions.cardInnerMargin else 0.dp
                            )
                        ).aspectRatio(2 / 3f).clickable { onOpenDialog(event.images, 0) }
                            .weight(if (event.images.size == 1) 2f else 1f),
                        painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + event.images.first())),
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    if (event.images.size > 1)
                        Box(
                            modifier = Modifier.weight(1f).clip(
                                RoundedCornerShape(
                                    topEnd = theme.dimensions.cardInnerMargin,
                                    bottomEnd = theme.dimensions.cardInnerMargin,
                                    topStart = 0.dp,
                                    bottomStart = 0.dp
                                )
                            ).clickable { onOpenDialog(event.images, 1) },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                modifier = Modifier.clip(
                                    RoundedCornerShape(
                                        topEnd = theme.dimensions.cardInnerMargin,
                                        bottomEnd = theme.dimensions.cardInnerMargin,
                                        topStart = 0.dp,
                                        bottomStart = 0.dp
                                    )
                                ).aspectRatio(2 / 3f),
                                painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + event.images[1])),
                                contentDescription = null,
                                contentScale = ContentScale.Crop
                            )

                            if (event.images.size >= 3)
                                Box(
                                    modifier = Modifier
                                        .matchParentSize()
                                        .background(Color.Black.copy(alpha = 0.6f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    GText(
                                        text = "+${event.images.size - 1}",
                                        style = theme.typography.description,
                                        color = theme.colors.text,
                                        modifier = Modifier.align(Alignment.Center)
                                    )
                                }
                        }
                }
            GText(
                text = event.expandedRole,
                style = theme.typography.display,
                color = theme.colors.text,
                modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing),
                align = TextAlign.Center
            )
            GText(
                text = event.takeaways,
                style = theme.typography.display,
                color = theme.colors.text,
                modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing),
                align = TextAlign.Center
            )
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin),
                    modifier = Modifier.padding(bottom = theme.dimensions.smallMargin)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.location),
                        contentDescription = null,
                        tint = theme.colors.text,
                        modifier = Modifier.size(theme.dimensions.iconSize)
                    )
                    GText(
                        text = event.location,
                        style = theme.typography.description,
                        color = theme.colors.text,
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.calendar),
                        contentDescription = null,
                        tint = theme.colors.text,
                        modifier = Modifier.size(theme.dimensions.iconSize)
                    )
                    GText(
                        text = event.date,
                        style = theme.typography.description,
                        color = theme.colors.text,
                    )
                }
            }
        }
    }
}
