# Estructura — MiniJuegos (host)

## Rol

```mermaid
flowchart TB
  user[Usuario]
  hub[GamesHubScreen]
  catalog[GameCatalog]
  entry[game.Entry]
  aars[AARs sdk + buscaminas + findout + flappybird]

  user --> hub
  hub --> catalog
  catalog --> entry
  entry --> aars
```

MiniJuegos solo se ocupa de:

1. Hub / catálogo  
2. Navegación a `GameModule.Entry`  
3. Tema de marca  
4. Declarar dependencias Maven  

La lógica de cada juego **no** está en este repo.

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
├── settings.gradle.kts              # Repos GitHub Packages (SDK + cada juego)
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
  participant Game as GameModule

  User->>Main: abre app
  Main->>Hub: games = GameCatalog.games
  User->>Hub: tap un juego
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
  gprBus[Packages Buscaminas]
  gprFind[Packages findout]
  gprFlap[Packages FlappyBird]
  local[mavenLocal fallback]

  studio --> gprSdk
  studio --> gprBus
  studio --> gprFind
  studio --> gprFlap
  studio -.-> local
```

Orden en `settings.gradle.kts`:

1. Google / Maven Central  
2. GitHub Packages (SDK)  
3. GitHub Packages (Buscaminas, findout, FlappyBird)  
4. `mavenLocal()` (fallback)

`cacheChangingModulesFor(0)` hace que cada sync pida de nuevo los SNAPSHOT.

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
  class FindOutGameModule
  class FlappyBirdGameModule
  GameCatalog --> GameModule
  BuscaminasGameModule ..|> GameModule
  FindOutGameModule ..|> GameModule
  FlappyBirdGameModule ..|> GameModule
  GameCatalog --> BuscaminasGameModule
  GameCatalog --> FindOutGameModule
  GameCatalog --> FlappyBirdGameModule
```

Para un juego nuevo: dependencia Maven + una línea en `GameCatalog.games`.

## Qué no vive aquí

- Motor / UI interna de cada juego  
- Publicación de AARs de juegos  
- Definición de `GameModule` (está en el SDK)
