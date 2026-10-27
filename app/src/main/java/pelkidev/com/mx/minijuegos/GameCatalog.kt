package pelkidev.com.mx.minijuegos

import pelkidev.com.mx.buscaminas.BuscaminasGameModule
import pelkidev.com.mx.emuladornds.EmuladorNdsGameModule
import pelkidev.com.mx.emuladorgba.EmuladorGbaGameModule
import pelkidev.com.mx.findout.FindOutGameModule
import pelkidev.com.mx.flappybird.FlappyBirdGameModule
import pelkidev.com.mx.minijuegos.sdk.GameModule

object GameCatalog {
    val games: List<GameModule> = listOf(
        BuscaminasGameModule,
        FindOutGameModule,
        FlappyBirdGameModule,
        EmuladorGbaGameModule,
        EmuladorNdsGameModule,
    )
}
