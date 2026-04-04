package com.mhd_07.personal_website.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import com.mhd_07.personal_website.LocalTheme

sealed class Section {
    abstract val id: String
    abstract val title: String

    data object Home : Section() {
        override val id: String
            get() = "home-section"
        override val title: String
            get() = "Home"
    }

    data object Events : Section() {
        override val id: String
            get() = "events-section"
        override val title: String
            get() = "Events"
    }

    data object Certificates : Section() {
        override val id: String
            get() = "certificates-section"
        override val title: String
            get() = "Certificate"
    }

    data object Projects : Section() {
        override val id: String
            get() = "projects-section"
        override val title: String
            get() = "Projects"
    }

    data object Contact : Section() {
        override val id: String
            get() = "contact-section"
        override val title: String
            get() = "Contact"
    }

    companion object {
        val sections = listOf(Home, Events, Certificates, Projects, Contact)

        fun valueOf(id: String) = sections.first { it.id == id }
    }
}

const val TAB_SELECTOR = "TAB_SELECTOR"

@Composable
fun Header(
    modifier: Modifier = Modifier,
    sections: List<Section> = Section.sections,
    onSectionClick: (Section) -> Unit,
    currentSection: Section = Section.Home
) {
    val theme = LocalTheme.current
    SharedTransitionLayout {
        Card(
            modifier = modifier,
            colors = CardDefaults.cardColors(containerColor = theme.colors.background),
            elevation = CardDefaults.elevatedCardElevation(),
            shape = CircleShape
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(
                    vertical = theme.dimensions.smallMargin,
                    horizontal = theme.dimensions.titleDescriptionSpacing
                ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                sections.forEach {
                    Box(modifier = Modifier.clip(CircleShape).clickable { onSectionClick(it) }) {
                        AnimatedContent(
                            targetState = currentSection == it,
                            label = "tab_${it.id}"
                        ) { isSelected ->
                            if (isSelected)
                                Card(
                                    shape = CircleShape,
                                    colors = CardDefaults.cardColors(containerColor = theme.colors.primary),
                                    modifier = Modifier.sharedBounds(
                                        sharedContentState = rememberSharedContentState(TAB_SELECTOR),
                                        this@AnimatedContent,
                                    )
                                ) {
                                    GText(
                                        text = it.title,
                                        modifier = Modifier.padding(
                                            vertical = theme.dimensions.smallMargin,
                                            horizontal = theme.dimensions.titleContentSpacing
                                        ),
                                        color = theme.colors.onPrimary,
                                        style = theme.typography.display
                                    )//txt
                                }//card
                            else
                                GText(
                                    text = it.title,
                                    modifier = Modifier.padding(
                                        vertical = theme.dimensions.smallMargin,
                                        horizontal = theme.dimensions.titleContentSpacing
                                    ),
                                    color = theme.colors.text,
                                    style = theme.typography.display,
                                )//txt
                        }
                    }
                }
            }
        }
    }
}
