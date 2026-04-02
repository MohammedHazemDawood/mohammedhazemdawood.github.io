package components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import com.mhd_07.personal_website.LocalTheme
import com.mhd_07.personal_website.util.ScreenType
import org.jetbrains.compose.resources.painterResource
import personalwebsite.composeapp.generated.resources.Res
import personalwebsite.composeapp.generated.resources.me

@Composable
fun HomeSection(modifier: Modifier = Modifier) {
    val theme = LocalTheme.current

    if (theme.screenType == ScreenType.MOBILE)
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(Res.drawable.me),
                contentDescription = "My Profile Picture",
                modifier = Modifier.fillMaxWidth(0.5f).aspectRatio(1f).clip(CircleShape)
            )
            GText(
                text = "Hi, I’m Mohammed Hazem",
                style = theme.typography.titleLarge,
                color = theme.colors.primary,
            )
            TypewriterTextEffect("Obsessed with everything computing --software, hardware, graphics-- you name it.\nFast learner. Currently diving deep into Android Development. Studying Physics, Mathematics, and Computer Science at Al-Azhar Science Faculty. Always excited about what’s next.") { displayedText ->
                GText(
                    text = displayedText,
                    style = theme.typography.display,
                    color = theme.colors.text,
                    align = TextAlign.Center
                )
            }
            Button(
                onClick = {/**TODO: Add Resume Link`**/},
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
                    text = "Hi, I’m Mohammed Hazem",
                    style = theme.typography.titleLarge,
                    color = theme.colors.primary,
                )
                TypewriterTextEffect("Obsessed with everything computing --software, hardware, graphics-- you name it.\nFast learner. Currently diving deep into Android Development. Studying Physics, Mathematics, and Computer Science at Al-Azhar Science Faculty. Always excited about what’s next.") { displayedText ->
                    GText(
                        text = displayedText,
                        style = theme.typography.display,
                        color = theme.colors.text,
                        align = TextAlign.Start
                    )
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(theme.dimensions.inSectionSpacing), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(Res.drawable.me),
                    contentDescription = "My Profile Picture",
                    modifier = Modifier.fillMaxWidth(0.5f).aspectRatio(1f).clip(CircleShape)
                )
                Button(
                    onClick = {/**TODO: Add Resume Link`**/},
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