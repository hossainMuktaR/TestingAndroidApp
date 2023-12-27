package com.example.testingandroidapp.feature_note.domain.add_edit_note_redux

import com.example.testingandroidapp.feature_note.redux.SideEffect

sealed class AddEditNoteSideEffect: SideEffect{
    data class ShowSnackbar(val message: String): AddEditNoteSideEffect()
    object SaveNote: AddEditNoteSideEffect()
}
