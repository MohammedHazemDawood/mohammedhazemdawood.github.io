package com.mhd_07.personal_website.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.mhd_07.personal_website.theme.AppFontFamily

@Composable
fun GText(
    text: String,
    style: TextStyle = TextStyle.Default,
    modifier: Modifier = Modifier,
    color: Color,
    align: TextAlign = TextAlign.Start,
    overflow: TextOverflow = TextOverflow.Clip,
    singleLine: Boolean = false
) {
    Text(
        text = text,
        modifier = modifier,
        color = color,
        style = style,
        textAlign = align,
        fontFamily = AppFontFamily,
        overflow = overflow,
        maxLines = if (singleLine) 1 else Int.MAX_VALUE
    )//txt
}