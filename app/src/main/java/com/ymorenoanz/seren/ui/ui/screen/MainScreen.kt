package com.ymorenoanz.seren.ui.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ymorenoanz.seren.data.mapper.toEmoji
import com.ymorenoanz.seren.domain.model.DayPeriod
import com.ymorenoanz.seren.domain.model.MoodEntry
import com.ymorenoanz.seren.domain.model.MoodType
import com.ymorenoanz.seren.ui.viewmodel.MoodViewModel
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen( navController: NavController,
                viewModel: MoodViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    val today = LocalDate.now()
    val todayMood = uiState.moods.find {
        it.date == today
    }

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { navController.navigate("add_mood") },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Add Mood/Update mood 🌙")
                }
            }
        }
    ) { padding ->

        LazyColumn(
            contentPadding = padding,
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text("Hello, Yari 🌙")
            }

            item {
                // Today Mood Card
                TodayMoodCard(
                    mood = todayMood?.mood,
                    period = todayMood?.period,
                    note = todayMood?.note
                )

            }

            item {
                Text(
                    text = "Your History 🌙",
                    fontSize = 18.sp
                )
            }

            items(uiState.moods.sortedByDescending { it.date }) { moodEntry ->
                MoodHistoryItem(moodEntry)
            }
        }
    }

}


@Composable
fun TodayMoodCard(
    mood: MoodType?,
    period: DayPeriod?,
    note: String?
) {
    Card(
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            Text(
                text = "Today",
                fontSize = 14.sp
            )

            Text(
                //text = mood?.toEmoji()  ?: "No mood yet",
                text = "${mood?.toEmoji()} ${mood?.name} ${if (mood != null) "🌙" 
                else "No mood yet 🌙"}",
                fontSize = 26.sp

            )

            Text(
                text = period?.name ?: "",
                fontSize = 14.sp
            )

            if (!note.isNullOrEmpty()) {
                Text(text = note)
            }
        }
    }
}

@Composable
fun MoodHistoryItem(entry: MoodEntry) {

    Card(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {

            // Top row (emoji + period)
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${entry.mood?.toEmoji()} ${entry.mood?.name}",
                    fontSize = 16.sp
                )

                Text(
                    text = entry.period?.name ?: "",
                    fontSize = 12.sp
                )

            }

            // Date
            Text(
                text = entry.date.toString(),
                fontSize = 12.sp
            )

            // Note
            if (!entry.note.isNullOrEmpty()) {
                Text(
                    text = entry.note,
                    fontSize = 14.sp
                )
            }
        }
    }
}

