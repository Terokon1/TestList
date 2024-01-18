package com.example.testlist.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testlist.R
import com.example.testlist.data.Announcement
import com.example.testlist.ui.common.LoadableContainer
import com.example.testlist.utils.extensions.formatStandard
import org.koin.androidx.compose.koinViewModel

@Composable
fun ListScreen(vm: ListViewModel = koinViewModel()) {
    val uiState by vm.uiState.collectAsState()
    val listState = rememberLazyListState()

    LoadableContainer(modifier = Modifier.fillMaxSize(), isLoading = uiState.loaders > 0) {
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            if (uiState.error && uiState.loaders == 0)
                item {
                    ErrorItem(
                        modifier = Modifier.fillParentMaxHeight()
                    ) {
                        vm.update()
                    }
                }
            items(uiState.announcements) {
                AnnouncementItem(announcement = it)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(2.dp)
                        .background(Color.Gray)
                )
            }
        }
    }
}

@Composable
fun AnnouncementItem(announcement: Announcement) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
    {
        Text(text = announcement.title, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = announcement.description, fontSize = 14.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.tags_text) + announcement.tags.joinToString(", "),
            fontSize = 12.sp,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = announcement.date.formatStandard(),
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun ErrorItem(modifier: Modifier = Modifier, refreshButton: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = stringResource(R.string.error_title))
            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = { refreshButton() }) {
                Text(text = stringResource(R.string.update_button_text))
            }
        }
    }
}