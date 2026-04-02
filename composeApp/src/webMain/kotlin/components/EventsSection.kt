package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mhd_07.personal_website.LocalTheme
import com.mhd_07.personal_website.model.Event
import com.mhd_07.personal_website.util.ScreenType

@Composable
fun EventsSection(
    modifier: Modifier = Modifier,
    data: List<Event>,
    onOpenDialog: (List<String>) -> Unit
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
                text = "Events",
                style = theme.typography.titleLarge,
                color = theme.colors.primary,
            )
            GText(
                text = "Outside Code",
                style = theme.typography.description,
                color = theme.colors.text,
            )
        }
        data.forEach {
            if (theme.screenType == ScreenType.DESKTOP)
                EventCardDesktop(
                    event = it,
                    onOpenDialog = onOpenDialog,
                    modifier = Modifier.fillMaxWidth(0.95f).align(Alignment.End),
                    shape = RoundedCornerShape(theme.dimensions.cardInnerMargin)
                )
            else
                EventCardMobile(
                    event = it,
                    onOpenDialog = onOpenDialog,
                    modifier = Modifier.fillMaxWidth(0.95f).align(Alignment.End),
                    shape = RoundedCornerShape(theme.dimensions.cardInnerMargin)
                )
        }
    }
}