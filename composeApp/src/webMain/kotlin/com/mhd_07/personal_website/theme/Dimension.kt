package com.mhd_07.personal_website.theme

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Dimension(
    val value: Dp,
)

data class FontSize(
    val value : TextUnit,
)

sealed class Dimensions {
    abstract val sectionSpacing : Dp
    abstract val inSectionSpacing : Dp
    abstract val titleContentSpacing : Dp
    abstract val titleDescriptionSpacing : Dp
    abstract val cardInnerMargin : Dp
    abstract val smallMargin: Dp

    abstract val iconSize : Dp

    abstract val contactIconSize : Dp

    abstract val contactCardSize : Dp

    object Desktop : Dimensions(){
        override val sectionSpacing: Dp
            get() = 88.dp
        override val inSectionSpacing: Dp
            get() = 48.dp
        override val titleContentSpacing: Dp
            get() = 24.dp
        override val titleDescriptionSpacing: Dp
            get() = 16.dp
        override val cardInnerMargin: Dp
            get() = 32.dp
        override val smallMargin: Dp
            get() = 8.dp
        override val iconSize: Dp
            get() = 32.dp
        override val contactIconSize: Dp
            get() = 32.dp
        override val contactCardSize: Dp
            get() = 64.dp
    }
    object Mobile : Dimensions(){
        override val sectionSpacing: Dp
            get() = 48.dp
        override val inSectionSpacing: Dp
            get() = 24.dp
        override val titleContentSpacing: Dp
            get() = 16.dp
        override val titleDescriptionSpacing: Dp
            get() = 0.dp
        override val cardInnerMargin: Dp
            get() = 16.dp
        override val smallMargin: Dp
            get() = 4.dp
        override val iconSize: Dp
            get() = 24.dp
        override val contactIconSize: Dp
            get() = 24.dp
        override val contactCardSize: Dp
            get() = 48.dp
    }
}

sealed class FontSizes{
    abstract val titleLarge : FontSize
    abstract val description : FontSize
    abstract val titleSmall : FontSize
    abstract val display : FontSize

    data object Desktop: FontSizes(){
        override val titleLarge = FontSize(value = 40.sp)
        override val description = FontSize(value = 18.sp)
        override val titleSmall = FontSize(value = 20.sp)
        override val display = FontSize(value = 24.sp)
    }
    object Mobile : FontSizes(){
        override val titleLarge = FontSize(value = 24.sp)
        override val description = FontSize(value = 16.sp)
        override val titleSmall = FontSize(value = 18.sp)
        override val display = FontSize(value = 20.sp)
    }
}

