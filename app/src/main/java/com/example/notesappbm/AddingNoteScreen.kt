package com.example.notesappbm


import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.notesappbm.database.Note
import com.example.notesappbm.ui.NoteViewModel

@Composable
fun AddingNoteScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    viewModel: NoteViewModel = viewModel(),
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 60.dp, start = 16.dp, end = 16.dp)
    ) {

        val context = LocalContext.current
        var details by remember { mutableStateOf("") }
        var title by remember { mutableStateOf("") }

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = {
                Text(text = "Note Title")
            },
            modifier = modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = details,
            onValueChange = { details = it },
            label = {
                Text(text = "Note Details")
            },
            modifier = modifier.fillMaxWidth()
        )

        OutlinedButton(
            onClick = {
                viewModel.upsertNote(Note(noteDetails = details , noteTitle = title))
                Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                details = ""
                title = ""
            },
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {
            Text(text = "Save")
        }

    }

}

@Preview(showBackground = true)
@Composable
private fun AddingNoteScreenPreview() {
    AddingNoteScreen(navController = rememberNavController())
}