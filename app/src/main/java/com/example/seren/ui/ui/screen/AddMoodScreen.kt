package com.example.seren.ui.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.seren.R
import com.example.seren.domain.model.MoodType
import com.example.seren.ui.viewmodel.MoodViewModel


//Improve UI and continue study Compose
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AddMoodScreen(navController: NavController){
    val viewModel: MoodViewModel = hiltViewModel()
    val uiState = viewModel.uiState.collectAsState()


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

            //Mood selector
            MoodSelector(
                selectedMood = viewModel.uiState.collectAsState()
                    .value.selectedMood
            ) {
                mood ->
                viewModel.onMoodSelected(mood)
            }

            //Period selector
            //Pending
            DropDownMenu()


            //Note textField
            OutlinedTextField(
                value = uiState.value.note,
                onValueChange = {
                    viewModel.onNoteChange(it)
                },
                label = { Text("Note (optional)") }
            )


            //Buttons
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(onClick = { navController.popBackStack()}) {
                    Text("Cancel")
                }

                Button(
                    onClick = { viewModel.addMood()},
                    enabled = uiState.value.selectedMood != null
                            && uiState.value.selectedPeriod != null
                ) {
                    Text("Save")
                }
            }
        }
    }
}


@Composable
//Period selector
fun DropDownMenu() {
    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val itemPosition = remember {
        mutableStateOf(0)
    }

    val usernames = listOf("Morning", "Night")
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.clickable {
                    isDropDownExpanded.value = true
                }
            ) {
                Text(text = usernames[itemPosition.value])
                Image(
                    painter = painterResource(id = R.drawable.dropdown),
                    contentDescription = "DropDown Icon"
                )
            }
            DropdownMenu(
                expanded = isDropDownExpanded.value,
                onDismissRequest = {
                    isDropDownExpanded.value = false
                }) {
                usernames.forEachIndexed { index, username ->
                    DropdownMenuItem(text = {
                        Text(text = username)
                    },
                        onClick = {
                            isDropDownExpanded.value = false
                            itemPosition.value = index
                        })
                }
            }
        }

    }
}

//Mood selector
@Composable
fun MoodSelector(
    selectedMood: MoodType?,
    onMoodSelected: (MoodType) -> Unit
) {
    Row( modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        MoodType.entries.forEach { mood ->
            val emoji = when (mood) {
                MoodType.VERY_SAD -> "😢"
                MoodType.SAD -> "🙁"
                MoodType.NEUTRAL -> "😐"
                MoodType.HAPPY -> "🙂"
                MoodType.VERY_HAPPY -> "😄"
            }

            Text(
                text = emoji,
                fontSize = if (selectedMood == mood) 48.sp else 32.sp,
                modifier = Modifier
                    .clickable { onMoodSelected(mood) }
                    .padding(8.dp)
                    .graphicsLayer {
                        // Add a little scale animation here if you like
                        scaleX = if (selectedMood == mood) 1.2f else 1f
                        scaleY = if (selectedMood == mood) 1.2f else 1f
                    }
            )
        }
    }
}


