package pelkidev.com.mx.minijuegos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import pelkidev.com.mx.minijuegos.presentation.GamesHubScreen
import pelkidev.com.mx.minijuegos.sdk.GameModule
import pelkidev.com.mx.minijuegos.ui.theme.MiniJuegosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MiniJuegosTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var selectedGameId by rememberSaveable { mutableStateOf<String?>(null) }
                    val selectedGame: GameModule? =
                        GameCatalog.games.firstOrNull { it.id == selectedGameId }

                    if (selectedGame == null) {
                        GamesHubScreen(
                            games = GameCatalog.games,
                            onGameSelected = { selectedGameId = it.id },
                        )
                    } else {
                        selectedGame.Entry(onExit = { selectedGameId = null })
                    }
                }
            }
        }
    }
}
