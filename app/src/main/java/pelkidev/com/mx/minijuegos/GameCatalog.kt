package pelkidev.com.mx.minijuegos

import pelkidev.com.mx.buscaminas.BuscaminasGameModule
import pelkidev.com.mx.minijuegos.sdk.GameModule

object GameCatalog {
    val games: List<GameModule> = listOf(
        BuscaminasGameModule,
    )
}
