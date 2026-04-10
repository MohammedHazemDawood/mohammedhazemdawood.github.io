package com.mhd_07.personal_website.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.mhd_07.personal_website.LocalTheme
import com.mhd_07.personal_website.model.Contact
import com.mhd_07.personal_website.openUrl
import com.mhd_07.personal_website.util.NetworkImage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
    contacts: List<Contact>,
) {
    val theme = LocalTheme.current
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(theme.dimensions.titleDescriptionSpacing)
        ) {
            GText(
                text = "Contact",
                style = theme.typography.titleLarge,
                color = theme.colors.primary,
            )
            GText(
                text = "Feel free to reach out",
                style = theme.typography.description,
                color = theme.colors.text,
            )
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth(0.9f).align(Alignment.End),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing)
        ) {
            contacts.forEach {
                ContactItem(
                    contact = it,
                )
            }
        }
    }
}

@Composable
private fun ContactItem(
    contact: Contact,
) {
    val theme = LocalTheme.current

    Column(
        modifier = Modifier.clickable {
            if (contact.link.isNotBlank())
            openUrl(contact.link)
        }
            .padding(theme.dimensions.smallMargin),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(theme.dimensions.smallMargin)
    ) {
        Card(
            shape = RoundedCornerShape(theme.dimensions.titleContentSpacing),
            colors = CardDefaults.cardColors(containerColor = theme.colors.primary.copy(alpha = 0.1f)),
            modifier = Modifier.size(theme.dimensions.contactCardSize)
        ) {
             Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                 NetworkImage(
                     url = BASE_URL + contact.icon,
                     contentDescription = null,
                     modifier = Modifier.size(theme.dimensions.contactIconSize),
                     colorFilter = ColorFilter.tint(theme.colors.primary)
                 )
             }
        }
        GText(
            text = contact.title,
            style = theme.typography.titleSmall,
            color = theme.colors.text
        )
    }
}


const val BASE_URL =
    "https://raw.githubusercontent.com/MohammedHazemDawood/mohammedhazemdawood.github.io/main/assets"
