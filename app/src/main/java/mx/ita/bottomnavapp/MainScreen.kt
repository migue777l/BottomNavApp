package mx.ita.bottomnavapp

import android.content.Intent
import android.provider.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import mx.ita.bottomnavapp.screens.*

@Composable
fun MainScreen() {

    val context = LocalContext.current

    val items = listOf(
        NavItem("Home", Icons.Default.Home),
        NavItem("Fav", Icons.Default.Favorite),
        NavItem("Email", Icons.Default.Email),
        NavItem("Profile", Icons.Default.Person),
        NavItem("Notify", Icons.Default.Notifications),
        NavItem("Dialog", Icons.Default.Info),
        NavItem("Ubication", Icons.Default.LocationOn)
    )

    var selectedIndex by remember { mutableIntStateOf(0) }
    var showFabMenu by remember { mutableStateOf(false) }

    Scaffold(

        floatingActionButton = {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {

                if (showFabMenu) {

                    FloatingActionButton(onClick = {
                        val intent = Intent(Intent.ACTION_SEND)
                        intent.type = "text/plain"
                        intent.putExtra(Intent.EXTRA_TEXT, "Hola desde mi app")
                        context.startActivity(Intent.createChooser(intent, "Compartir"))
                    }) {
                        Icon(Icons.Default.Share, contentDescription = "Compartir")
                    }

                    FloatingActionButton(onClick = {
                        val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                        context.startActivity(intent)
                    }) {
                        Icon(Icons.Default.Settings, contentDescription = "Idioma")
                    }
                }

                FloatingActionButton(onClick = {
                    showFabMenu = !showFabMenu
                }) {
                    Icon(Icons.Default.Add, contentDescription = "Menu")
                }
            }
        },

        bottomBar = {
            if (selectedIndex != 6) { // ocultar en Ubication
                NavigationBar {
                    items.forEachIndexed { index, item ->
                        NavigationBarItem(
                            selected = selectedIndex == index,
                            onClick = { selectedIndex = index },
                            icon = {
                                Icon(item.icon, contentDescription = item.label)
                            },
                            label = {
                                Text(item.label)
                            }
                        )
                    }
                }
            }
        }

    ) { padding ->

        when (selectedIndex) {
            0 -> HomeScreen(Modifier.padding(padding))
            1 -> FavoriteScreen(Modifier.padding(padding))
            2 -> EmailScreen(Modifier.padding(padding))
            3 -> ProfileScreen(Modifier.padding(padding))
            4 -> NotificationScreen(Modifier.padding(padding))
            5 -> DialogScreen(Modifier.padding(padding))
            6 -> UbicationScreen(Modifier.padding(padding))
        }
    }
}
