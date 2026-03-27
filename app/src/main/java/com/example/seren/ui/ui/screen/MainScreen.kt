package com.example.seren.ui.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seren.ui.viewmodel.MoodViewModel

@Composable
fun MainScreen( navController: NavController) {

    Card(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            //Title
            Text(modifier = Modifier.padding(all = 30.dp),
                text = "How do you feel today?")

            //LazyColumn
            LazyColumnMood()

            //Buttons
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        navController.navigate("add_mood")
                    }
                ) {
                    Text("Add Mood")
                }

            }
        }
    }
}

@Composable
fun LazyColumnMood(viewModel: MoodViewModel = hiltViewModel())
{
    val data by viewModel.uiState.collectAsState()

    LazyColumn {
        items(data.moods) { mood ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            )
            Text(text = mood.note ?: "No note")
            Spacer(modifier = Modifier.padding(8.dp))
        }
    }
}