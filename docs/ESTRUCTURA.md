# Estructura — MiniJuegos (host)

## Rol

```mermaid
flowchart TB
  user[Usuario]
  hub[GamesHubScreen]
  catalog[GameCatalog]
  entry[game.Entry]
  aar[AARs sdk + buscaminas]

  user --> hub
  hub --> catalog
  catalog --> entry
  entry --> aar
```

MiniJuegos solo se ocupa de:

1. Hub / catálogo  
2. Navegación a `GameModule.Entry`  
3. Tema de marca  
4. Declarar dependencias Maven  

La lógica del buscaminas **no** está en este repo.

## Árbol del proyecto

```
MiniJuegos/
├── .github/workflows/ci.yml
├── app/
│   └── src/main/java/pelkidev/com/mx/minijuegos/
│       ├── MainActivity.kt          # Hub ↔ Entry
│       ├── GameCatalog.kt           # Lista de GameModule
│       ├── presentation/
│       │   └── GamesHubScreen.kt
│       └── ui/theme/
├── settings.gradle.kts              # Repos GitHub Packages
├── build.gradle.kts                 # Sin caché de SNAPSHOT
└── docs/
    ├── ESTRUCTURA.md
    └── TUTORIAL.md
```

## Navegación host

```mermaid
stateDiagram-v2
  [*] --> Hub
  Hub --> Juego: onGameSelected
  Juego --> Hub: onExit
```

Implementación actual (sin Navigation Compose): estado `selectedGameId` en `MainActivity`.

```mermaid
sequenceDiagram
  participant User
  participant Main as MainActivity
  participant Hub as GamesHubScreen
  participant Game as BuscaminasGameModule

  User->>Main: abre app
  Main->>Hub: games = GameCatalog.games
  User->>Hub: tap Buscaminas
  Hub->>Main: onGameSelected
  Main->>Game: Entry onExit
  User->>Game: jugar / salir
  Game->>Main: onExit
  Main->>Hub: selectedGameId = null
```

## Resolución de dependencias

```mermaid
flowchart LR
  studio[Android_Studio_Sync]
  gprSdk[Packages MiniJuegosSdk]
  gprGame[Packages Buscaminas]
  local[mavenLocal fallback]

  studio --> gprSdk
  studio --> gprGame
  studio -.-> local
```

Orden en `settings.gradle.kts`:

1. Google / Maven Central  
2. GitHub Packages (SDK)  
3. GitHub Packages (Buscaminas)  
4. `mavenLocal()` (fallback)

`cacheChangingModulesFor(0)` hace que cada sync pida de nuevo `1.0.0-SNAPSHOT`.

## Catálogo

```mermaid
classDiagram
  class GameCatalog {
    +games: List~GameModule~
  }
  class GameModule {
    <<interface>>
  }
  class BuscaminasGameModule
  GameCatalog --> GameModule
  BuscaminasGameModule ..|> GameModule
  GameCatalog --> BuscaminasGameModule : listOf
```

Para un juego nuevo: dependencia Maven + una línea en `GameCatalog.games`.

## Qué no vive aquí

- Motor / UI interna de cada juego  
- Publicación de AARs de juegos  
- Definición de `GameModule` (está en el SDK)
