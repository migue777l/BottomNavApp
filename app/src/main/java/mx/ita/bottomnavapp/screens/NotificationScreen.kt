package mx.ita.bottomnavapp.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp

@Composable
fun NotificationScreen(modifier: Modifier = Modifier) {


    var count by rememberSaveable { mutableStateOf(0) }
    var textValue by rememberSaveable { mutableStateOf("") }

    val configuration = LocalConfiguration.current
    val isLandscape =
        configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(text = "Contador: $count")

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { count++ }) {
            Text("Incrementar")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = textValue,
            onValueChange = { textValue = it },
            label = { Text("Escribe algo aquí") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = if (isLandscape) "Modo Horizontal" else "Modo Vertical"
        )
    }
}