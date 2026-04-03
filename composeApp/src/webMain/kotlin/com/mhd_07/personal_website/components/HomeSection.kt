package com.mhd_07.personal_website.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.rememberAsyncImagePainter
import com.github.panpf.sketch.request.ComposableImageRequest
import com.mhd_07.personal_website.LocalTheme
import com.mhd_07.personal_website.model.Hero
import com.mhd_07.personal_website.openUrl
import com.mhd_07.personal_website.util.ScreenType

@Composable
fun HomeSection(modifier: Modifier = Modifier, data: Hero) {
    val theme = LocalTheme.current

    if (theme.screenType == ScreenType.MOBILE)
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + data.image)),
                contentDescription = "My Profile Picture",
                modifier = Modifier.fillMaxWidth(0.5f).aspectRatio(1f).clip(CircleShape),
            )
            GText(
                text = data.greeting,
                style = theme.typography.titleLarge,
                color = theme.colors.primary,
                align = TextAlign.Center
            )
            TypewriterTextEffect(data.summary) { displayedText ->
                GText(
                    text = displayedText,
                    style = theme.typography.display,
                    color = theme.colors.text,
                    align = TextAlign.Center,
                )
            }
            Button(
                onClick = { openUrl(BASE_URL + data.resume) },
                colors = ButtonDefaults.buttonColors(containerColor = theme.colors.primary)
            ) {
                GText(
                    text = "Download My Resume",
                    style = theme.typography.display,
                    color = theme.colors.onPrimary,
                    align = TextAlign.Center
                )
            }
        }
    else
        Row(
            modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(0.6f),
                verticalArrangement = Arrangement.spacedBy(theme.dimensions.titleContentSpacing),
                horizontalAlignment = Alignment.Start
            ) {
                GText(
                    text = data.greeting,
                    style = theme.typography.titleLarge,
                    color = theme.colors.primary,
                )
                TypewriterTextEffect(data.summary) { displayedText ->
                    GText(
                        text = displayedText,
                        style = theme.typography.display,
                        color = theme.colors.text,
                        align = TextAlign.Start
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + data.image)),
                    contentDescription = "My Profile Picture",
                    modifier = Modifier.fillMaxWidth(0.5f).aspectRatio(1f).clip(CircleShape),
                )
                Button(
                    onClick = { openUrl(BASE_URL + data.resume) },
                    colors = ButtonDefaults.buttonColors(containerColor = theme.colors.primary)
                ) {
                    GText(
                        text = "Download My Resume",
                        style = theme.typography.display,
                        color = theme.colors.onPrimary,
                        align = TextAlign.Center
                    )
                }
            }
        }
}
