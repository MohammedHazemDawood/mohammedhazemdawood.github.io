package com.mhd_07.personal_website.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.github.panpf.sketch.rememberAsyncImagePainter
import com.github.panpf.sketch.request.ComposableImageRequest
import com.mhd_07.personal_website.LocalTheme
import com.mhd_07.personal_website.model.Project
import com.mhd_07.personal_website.openUrl
import org.jetbrains.compose.resources.painterResource
import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.calendar
import personalwebsite.composeapp.generated.resources.code
import personalwebsite.composeapp.generated.resources.live

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCardDesktop(
    modifier: Modifier = Modifier,
    project: Project,
    onOpenDialog: (List<String>) -> Unit,
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
            Column(modifier = Modifier.weight(1.2f)) {
                Row(
                    modifier = Modifier.padding(bottom = theme.dimensions.titleDescriptionSpacing),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.titleContentSpacing)
                ) {
                    GText(
                        text = project.title,
                        style = theme.typography.titleLarge,
                        color = theme.colors.primary,
                        modifier = Modifier.weight(1f)
                    )
                    Card(
                        shape = RoundedCornerShape(50),
                        colors = CardDefaults.cardColors(
                            containerColor = theme.colors.primary.copy(alpha = 0.1f)
                        )
                    ) {
                        GText(
                            text = project.status,
                            style = theme.typography.display,
                            color = theme.colors.primary,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp)
                        )
                    }
                }

                GText(
                    text = project.description,
                    style = theme.typography.titleSmall,
                    color = theme.colors.text,
                    modifier = Modifier.padding(bottom = theme.dimensions.titleContentSpacing)
                )

                GText(
                    text = project.longDescription,
                    style = theme.typography.description,
                    color = theme.colors.text,
                    modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
                )

                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
                ) {
                    project.techStack.forEach { tech ->
                        SuggestionChip(
                            onClick = {},
                            label = {
                                GText(
                                    text = tech, style = theme.typography.display,
                                    color = theme.colors.text,
                                )
                            },
                            colors = SuggestionChipDefaults.suggestionChipColors(
                                labelColor = theme.colors.text
                            )
                        )
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing),
                    modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
                ) {
                    if (project.githubUrl.isNotEmpty()) {
                        Button(
                            onClick = { openUrl(project.githubUrl) },
                            colors = ButtonDefaults.buttonColors(containerColor = theme.colors.primary, contentColor = theme.colors.onPrimary)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.code),
                                contentDescription = null,
                                modifier = Modifier.size(theme.dimensions.inSectionSpacing)
                            )
                            Spacer(Modifier.width(theme.dimensions.smallMargin))
                            GText(text = "Code", style = theme.typography.display, color = theme.colors.onPrimary)
                        }
                    }
                    if (project.liveUrl.isNotEmpty()) {
                        Button(
                            onClick = { openUrl(project.githubUrl) },
                            colors = ButtonDefaults.buttonColors(containerColor = theme.colors.primary, contentColor = theme.colors.onPrimary)
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.live),
                                contentDescription = null,
                                modifier = Modifier.size(theme.dimensions.inSectionSpacing)
                            )
                            Spacer(Modifier.width(8.dp))
                            GText(text = "Live", style = theme.typography.display, color = theme.colors.onPrimary)
                        }
                    }
                }

                if (project.date.isNotEmpty())
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.calendar),
                            contentDescription = null,
                            tint = theme.colors.text,
                            modifier = Modifier.size(theme.dimensions.inSectionSpacing)
                        )
                        GText(
                            text = project.date,
                            style = theme.typography.description,
                            color = theme.colors.text,
                        )
                    }
            }

            Column(
                modifier = Modifier.weight(0.8f),
                verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing)
            ) {
                if (project.images.isNotEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + project.images.first())),
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(theme.dimensions.cardInnerMargin))
                            .aspectRatio(16 / 10f)
                            .clickable { onOpenDialog(project.images) },
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ProjectCardMobile(
    modifier: Modifier = Modifier,
    project: Project,
    onOpenDialog: (List<String>) -> Unit,
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
                text = project.title,
                style = theme.typography.titleLarge,
                color = theme.colors.primary,
                modifier = Modifier.padding(bottom = theme.dimensions.titleDescriptionSpacing),
                align = TextAlign.Center
            )

            GText(
                text = project.status,
                style = theme.typography.display,
                color = theme.colors.primary,
                modifier = Modifier.padding(bottom = theme.dimensions.titleContentSpacing)
            )

            if (project.images.isNotEmpty()) {
                Image(
                    painter = rememberAsyncImagePainter(ComposableImageRequest(BASE_URL + project.images.first())),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(bottom = theme.dimensions.inSectionSpacing)
                        .clip(RoundedCornerShape(theme.dimensions.cardInnerMargin))
                        .aspectRatio(16 / 10f)
                        .clickable { onOpenDialog(project.images) },
                    contentScale = ContentScale.Crop
                )
            }

            GText(
                text = project.description,
                style = theme.typography.titleSmall,
                color = theme.colors.text,
                modifier = Modifier.padding(bottom = theme.dimensions.titleContentSpacing),
                align = TextAlign.Center
            )

            GText(
                text = project.longDescription,
                style = theme.typography.description,
                color = theme.colors.text,
                modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing),
                align = TextAlign.Center
            )

            FlowRow(
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
            ) {
                project.techStack.forEach { tech ->
                    SuggestionChip(
                        onClick = {},
                        label = {
                            GText(
                                text = tech,
                                style = theme.typography.display,
                                color = theme.colors.text
                            )
                        }
                    )
                }
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing),
                modifier = Modifier.padding(bottom = theme.dimensions.inSectionSpacing)
            ) {
                if (project.githubUrl.isNotEmpty()) {
                    Button(
                        onClick = { openUrl(project.githubUrl) },
                        colors = ButtonDefaults.buttonColors(containerColor = theme.colors.primary, contentColor = theme.colors.onPrimary)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.code),
                            contentDescription = null,
                            modifier = Modifier.size(theme.dimensions.inSectionSpacing)
                        )
                        Spacer(Modifier.width(theme.dimensions.smallMargin))
                        GText(text = "Code", style = theme.typography.display, color = theme.colors.onPrimary)
                    }
                }
                if (project.liveUrl.isNotEmpty()) {
                    Button(
                        onClick = { openUrl(project.githubUrl) },
                        colors = ButtonDefaults.buttonColors(containerColor = theme.colors.primary, contentColor = theme.colors.onPrimary)
                    ) {
                        Icon(
                            painter = painterResource(Res.drawable.live),
                            contentDescription = null,
                            modifier = Modifier.size(theme.dimensions.inSectionSpacing)
                        )
                        Spacer(Modifier.width(8.dp))
                        GText(text = "Live", style = theme.typography.display, color = theme.colors.onPrimary)
                    }
                }
            }

            if (project.date.isNotEmpty())
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.calendar),
                        contentDescription = null,
                        tint = theme.colors.text,
                        modifier = Modifier.size(theme.dimensions.inSectionSpacing)
                    )
                    GText(
                        text = project.date,
                        style = theme.typography.description,
                        color = theme.colors.text,
                    )
                }
        }
    }
}
