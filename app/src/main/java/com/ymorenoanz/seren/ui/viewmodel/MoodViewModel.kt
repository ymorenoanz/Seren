package com.ymorenoanz.seren.ui.viewmodel

import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.ymorenoanz.seren.domain.model.DayPeriod
import com.ymorenoanz.seren.domain.model.MoodEntry
import com.ymorenoanz.seren.domain.model.MoodType
import com.ymorenoanz.seren.domain.usecases.AddMoodUseCase
import com.ymorenoanz.seren.domain.usecases.GetMoodEntriesUseCase
import com.ymorenoanz.seren.ui.states.MoodUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class MoodViewModel @Inject constructor
    (private val getMoodEntriesUseCase: GetMoodEntriesUseCase,
     private val addMoodUseCase: AddMoodUseCase): ViewModel(){

        //We use MutableStateFlow only in viewModel
        //We use StateFlow is read-only because UI cannot modify
        //UI can just observe
        //Here we catch the UI state, and based on that we act
        private val _uiState =
            MutableStateFlow(MoodUIState(isLoading = true,))

    val uiState: StateFlow<MoodUIState> = _uiState

    init {
        observeMoods()
    }

    private fun observeMoods(){
        viewModelScope.launch {
            getMoodEntriesUseCase()
                .catch { e->
                _uiState.value = MoodUIState(
                    error = e.message,
                )
            }
                .collect { moods ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            moods = moods
                        )
                    }
                }
        }
    }


    //Add mood
    @RequiresApi(Build.VERSION_CODES.O)
    fun addMood(){
        val state = _uiState.value
        val user = FirebaseAuth.getInstance().currentUser

        if (user == null) {
            _uiState.update {
                it.copy(error = "User not authenticated")
            }
            return
        }

        // 1. Validate inputs
        val moodError = if (state.selectedMood == null) "Select a mood" else null
        val periodError = if (state.selectedPeriod == null) "Select a period" else null

        // 2. Update state with errors
        _uiState.update {
            it.copy(
                moodError = moodError,
                periodError = periodError
            )
        }

        // 3. Stop if there are errors
        if (moodError != null || periodError != null) {
            return
        }

        //Create moodEntry
        val moodEntry = MoodEntry(
            id = 0,
            date = LocalDate.now(),
            period = state.selectedPeriod,
            mood = state.selectedMood,
            note = state.note)

        //Save mood
        viewModelScope.launch {
            addMoodUseCase(moodEntry)
           println("Button clicked")
        }
    }

    //Mood selector validation
    fun onMoodSelected(mood: MoodType) {
        _uiState.update {
            it.copy(selectedMood = mood)
        }
    }

    //Period selector validator
    fun onPeriodSelected(period: DayPeriod) {
        _uiState.update {
            it.copy(selectedPeriod = period)
        }
    }

    //Note validator
    fun onNoteChange(note: String) {
        _uiState.update {
            it.copy(note = note)
        }
    }
}



