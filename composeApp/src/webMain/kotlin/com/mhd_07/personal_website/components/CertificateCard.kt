package com.mhd_07.personal_website.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.rememberAsyncImagePainter
import com.github.panpf.sketch.request.ComposableImageRequest
import com.github.panpf.sketch.request.svgBackgroundColor
import com.mhd_07.personal_website.LocalTheme
import com.mhd_07.personal_website.model.Certificate
import com.mhd_07.personal_website.openUrl
import org.jetbrains.compose.resources.painterResource
import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.calendar

@Composable
fun CertificateCardDesktop(
    modifier: Modifier = Modifier,
    certificate: Certificate,
    onOpenDialog: (String) -> Unit,
    shape: RoundedCornerShape
) {
    val theme = LocalTheme.current
    Card(
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(containerColor = theme.colors.card)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)
                .padding(theme.dimensions.cardInnerMargin),
            horizontalArrangement = Arrangement.spacedBy(theme.dimensions.cardInnerMargin)
        ) {
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.padding(bottom = theme.dimensions.titleDescriptionSpacing),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.titleContentSpacing)
                ) {
                    GText(
                        text = certificate.title,
                        style = theme.typography.titleLarge,
                        color = theme.colors.primary,
                        modifier = Modifier.weight(1f)
                    )
                    GText(
                        text = certificate.issuer,
                        style = theme.typography.titleSmall,
                        color = theme.colors.text,
                        modifier = Modifier.weight(0.3f)
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.titleContentSpacing)
                ) {
                    Button(
                        onClick = { openUrl(certificate.url) },
                        colors = ButtonDefaults.buttonColors(containerColor = theme.colors.primary)
                    ) {
                        GText(
                            text = "Check",
                            style = theme.typography.display,
                            color = theme.colors.onPrimary,
                            align = TextAlign.Center
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
                            text = certificate.date,
                            style = theme.typography.description,
                            color = theme.colors.text,
                        )
                    }
                }
            }

            if (certificate.image.isNotEmpty())
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + certificate.image) {
                            svgBackgroundColor(
                                Color.Red.toArgb()
                            )
                        }),
                        contentDescription = null,
                        modifier = Modifier.clip(RoundedCornerShape(theme.dimensions.cardInnerMargin))
                            .aspectRatio(16 / 9f)
                            .clickable { onOpenDialog(certificate.image) },
                        contentScale = ContentScale.Crop
                    )
                }
        }
    }
}

@Composable
fun CertificateCardMobile(
    modifier: Modifier = Modifier,
    certificate: Certificate,
    onOpenDialog: (String) -> Unit,
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
                text = certificate.title,
                style = theme.typography.titleLarge,
                color = theme.colors.primary,
                modifier = Modifier.padding(bottom = theme.dimensions.titleDescriptionSpacing),
                align = TextAlign.Center
            )
            GText(
                text = certificate.issuer,
                style = theme.typography.titleSmall,
                color = theme.colors.text,
                modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing),
                align = TextAlign.Center
            )
            if (certificate.image.isNotEmpty())
                Image(
                    painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + certificate.image)),
                    contentDescription = null,
                    modifier = Modifier.clip(RoundedCornerShape(theme.dimensions.cardInnerMargin))
                        .aspectRatio(16 / 9f)
                        .clickable { onOpenDialog(certificate.image) }
                        .padding(bottom = theme.dimensions.inSectionSpacing),
                    contentScale = ContentScale.Crop
                )
            Button(
                onClick = { openUrl(certificate.url) },
                colors = ButtonDefaults.buttonColors(containerColor = theme.colors.primary),
                modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
            ) {
                GText(
                    text = "Check",
                    style = theme.typography.display,
                    color = theme.colors.onPrimary,
                    align = TextAlign.Center
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
                    text = certificate.date,
                    style = theme.typography.description,
                    color = theme.colors.text,
                )
            }
        }
    }
}
