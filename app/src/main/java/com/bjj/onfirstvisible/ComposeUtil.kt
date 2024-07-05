package com.bjj.onfirstvisible

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned

@Stable
fun Modifier.onFirstVisible(action: () -> Unit): Modifier = composed {
    var flag by remember {
        mutableStateOf(false)
    }
    onGloballyPositioned {
        if (flag.not() && it.boundsInWindow() != Rect.Zero) {
            flag = true
            action()
        }
    }
}