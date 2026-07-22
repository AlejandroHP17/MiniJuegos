# MiniJuegos

App host que lista y ejecuta minijuegos embebidos como AAR (primer juego: [Buscaminas](https://github.com/AlejandroHP17/Buscaminas)).

Los juegos **no** viven en este repo: se desarrollan aparte, se publican a GitHub Packages y aquí solo se consumen + se registran en el catálogo.

## Repos del ecosistema

| Repo | Rol |
|------|-----|
| [MiniJuegosSdk](https://github.com/AlejandroHP17/MiniJuegosSdk) | Contrato `GameModule` |
| [Buscaminas](https://github.com/AlejandroHP17/Buscaminas) | Primer juego (AAR) |
| [MiniJuegos](https://github.com/AlejandroHP17/MiniJuegos) | Host (este proyecto) |

## Documentación

- [Estructura del proyecto](docs/ESTRUCTURA.md)
- [Tutorial del sistema completo](docs/TUTORIAL.md) — paso a paso de cómo funciona todo junto

## Dependencias Maven

```kotlin
implementation("pelkidev.com.mx.minijuegos:sdk:1.0.0")
implementation("pelkidev.com.mx.minijuegos:buscaminas:1.0.0-SNAPSHOT")
```

Registro en código:

```kotlin
object GameCatalog {
    val games = listOf(BuscaminasGameModule)
}
```

## Credenciales locales (Android Studio)

En `~/.gradle/gradle.properties` (no subir al repo):

```properties
gpr.user=AlejandroHP17
gpr.token=ghp_xxx   # PAT classic con read:packages
```

Luego **Sync Project**. Cada sync reconsulta el SNAPSHOT de Buscaminas publicado por CI.

## Build

```bash
./gradlew :app:assembleDebug
```

## CI

Push / `workflow_dispatch` en `main` → `assembleDebug` resolviendo packages remotos. Secret requerido: `GH_PACKAGES_TOKEN`.

## Flujo diario

1. Cambias Buscaminas → push → CI publica AAR  
2. En MiniJuegos (Android Studio) → Sync / Run → ves el cambio  

No hace falta `publishToMavenLocal` si usas GitHub Packages (sí sirve para prototipar sin esperar CI).
