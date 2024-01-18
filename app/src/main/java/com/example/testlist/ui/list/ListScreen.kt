package com.example.testlist.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.koin.androidx.compose.koinViewModel
import org.koin.core.KoinApplication

@Composable
fun ListScreen(vm: ListViewModel = koinViewModel()) {
    vm

    Box(modifier = Modifier.fillMaxSize()) {

    }
}