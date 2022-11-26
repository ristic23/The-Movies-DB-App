package com.example.core_ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun FavoriteToggleButton(
    modifier: Modifier,
    checkedState: Boolean,
    favoriteButtonClicked: (Boolean) -> Unit
) {

    IconToggleButton(
        checked = checkedState,
        onCheckedChange = {
//            checkedState.value = !checkedState.value
            favoriteButtonClicked(!checkedState)
        },
        modifier = modifier.aspectRatio(1f)
    ) {
        val transition = updateTransition(checkedState, label = "")

        val tint by transition.animateColor(label = "iconColor") { isChecked ->
            if (isChecked) Color.Red else Color.Black
        }

        Icon(
            imageVector = if (checkedState) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
            contentDescription = "Icon",
            tint = tint,
            modifier = Modifier.fillMaxWidth().aspectRatio(1f)
        )
    }
}