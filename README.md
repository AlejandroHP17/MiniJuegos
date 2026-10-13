# MiniJuegos

App host que lista y ejecuta minijuegos embebidos como AAR.

Los juegos **no** viven en este repo: se desarrollan aparte, se publican a GitHub Packages y aquí solo se consumen + se registran en el catálogo.

## Repos del ecosistema

| Repo | Rol |
|------|-----|
| [MiniJuegosSdk](https://github.com/AlejandroHP17/MiniJuegosSdk) | Contrato `GameModule` |
| [Buscaminas](https://github.com/AlejandroHP17/Buscaminas) | Juego AAR (plantilla) |
| [findout](https://github.com/AlejandroHP17/findout) | Find Out AAR |
| [FlappyBird](https://github.com/AlejandroHP17/FlappyBird) | Flappy Bird AAR |
| [MiniJuegos](https://github.com/AlejandroHP17/MiniJuegos) | Host (este proyecto) |

## Documentación

- [Estructura del proyecto](docs/ESTRUCTURA.md) — hub, `GameVisuals` e iconos vectoriales
- [Tutorial del sistema completo](docs/TUTORIAL.md) — paso a paso de cómo funciona todo junto

## UX del hub

El menú principal usa marca teal/coral, tarjetas por juego e **iconos vectoriales** (`ic_game_*`) mapeados por `GameModule.id` en `GameVisuals` (sin cambiar el SDK).

## Dependencias Maven

```kotlin
implementation("pelkidev.com.mx.minijuegos:sdk:1.0.0")
implementation("pelkidev.com.mx.minijuegos:buscaminas:1.0.1")
implementation("pelkidev.com.mx.minijuegos:findout:1.0.0-SNAPSHOT")
implementation("pelkidev.com.mx.minijuegos:flappybird:1.0.0-SNAPSHOT")
```

Registro en código:

```kotlin
object GameCatalog {
    val games = listOf(
        BuscaminasGameModule,
        FindOutGameModule,
        FlappyBirdGameModule,
    )
}
```

## Credenciales locales (Android Studio)

En `~/.gradle/gradle.properties` (no subir al repo):

```properties
gpr.user=AlejandroHP17
gpr.token=ghp_xxx   # PAT classic con read:packages
```

Luego **Sync Project**. Cada sync reconsulta los SNAPSHOT publicados por CI.

## Build

```bash
./gradlew :app:assembleDebug
```

## CI

Push / `workflow_dispatch` en `main` → `assembleDebug` resolviendo packages remotos. Secret requerido: `GH_PACKAGES_TOKEN`.

## Flujo diario

1. Cambias un juego → push → CI publica AAR  
2. En MiniJuegos (Android Studio) → Sync / Run → ves el cambio  

`publishToMavenLocal` sirve para prototipar sin esperar CI (`mavenLocal()` es fallback en `settings.gradle.kts`).
