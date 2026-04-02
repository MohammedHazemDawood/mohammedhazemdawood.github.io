package components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.image.LandscapistImage


const val BASE_URL = "https://MohammedHazemDawood.github.io/PersonalWebsite"

@Composable
fun RemoteImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String? = null,
){
    LandscapistImage(
        modifier = modifier,
        imageModel = { BASE_URL + url },
        imageOptions = ImageOptions(
            contentScale = ContentScale.Fit,
            contentDescription = contentDescription
        ),
    )
}