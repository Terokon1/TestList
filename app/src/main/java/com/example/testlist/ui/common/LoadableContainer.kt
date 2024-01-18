package com.example.testlist.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.example.testlist.utils.extensions.gesturesDisabled

@Composable
fun LoadableContainer(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier = modifier) {
        content()
        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize().background(Color.DarkGray.copy(0.5f)).gesturesDisabled(true)) {
                Column(
                    Modifier
                        .align(Alignment.Center)
                        .zIndex(999f)
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}