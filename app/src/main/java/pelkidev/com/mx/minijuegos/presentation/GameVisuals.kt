package pelkidev.com.mx.minijuegos.presentation

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color
import pelkidev.com.mx.minijuegos.R
import pelkidev.com.mx.minijuegos.ui.theme.BuscaminasAccent
import pelkidev.com.mx.minijuegos.ui.theme.FindOutAccent
import pelkidev.com.mx.minijuegos.ui.theme.FlappyAccent
import pelkidev.com.mx.minijuegos.ui.theme.TealDeep

/**
 * Metadatos visuales del hub, indexados por [pelkidev.com.mx.minijuegos.sdk.GameModule.id].
 * Los iconos viven en el host para no engordar el contrato SDK.
 */
data class GameVisual(
    @DrawableRes val iconRes: Int,
    val accent: Color,
    val cta: String = "Jugar",
)

object GameVisuals {
    fun forId(gameId: String): GameVisual = when (gameId) {
        "buscaminas" -> GameVisual(
            iconRes = R.drawable.ic_game_buscaminas,
            accent = BuscaminasAccent,
            cta = "Despejar minas",
        )
        "findout" -> GameVisual(
            iconRes = R.drawable.ic_game_findout,
            accent = FindOutAccent,
            cta = "Buscar objetos",
        )
        "flappybird" -> GameVisual(
            iconRes = R.drawable.ic_game_flappybird,
            accent = FlappyAccent,
            cta = "Despegar",
        )
        else -> GameVisual(
            iconRes = R.drawable.ic_game_default,
            accent = TealDeep,
        )
    }
}
