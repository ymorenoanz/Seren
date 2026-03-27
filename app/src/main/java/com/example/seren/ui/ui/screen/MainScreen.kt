package com.example.seren.ui.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seren.domain.model.DayPeriod
import com.example.seren.domain.model.MoodType
import com.example.seren.ui.viewmodel.MoodViewModel
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {

        // Greeting
        Text(
            text = "Hello, Yari 🌊",
            fontSize = 22.sp
        )

        // Today Mood Card
        TodayMoodCard(
            mood = todayMood?.mood,
            period = todayMood?.period,
            note = todayMood?.note
        )

        // Button
        Button(
            onClick = { navController.navigate("add_mood") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add / Update Mood")
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
        elevation = CardDefaults.cardElevation(8.dp),
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
                text = mood?.name ?: "No mood yet",
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

