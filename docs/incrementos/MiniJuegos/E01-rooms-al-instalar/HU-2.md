# HU-2

- Estado: hecha
- Épica: [epica.md](epica.md)

Como jugador, quiero ver también Mario Kart Super Circuit, Super Mario World y Pokémon Fire Red en ese emulador y empezar cualquiera, para elegir entre los juegos de Rooms de Game Boy Advance. Requiere: #1

## Criterios de aceptación

- [x] Dado que abro el emulador de Game Boy Advance, cuando llega la pantalla inicial, entonces veo Mario Kart Super Circuit, Super Mario World y Pokémon Fire Red, y Crash Bandicoot sigue ahí, sin abrir una carpeta.
- [x] Dado que veo Mario Kart Super Circuit, cuando lo elijo, entonces empieza ese juego y veo la partida, no la pantalla de elegir archivo.
- [x] Dado que veo Super Mario World, cuando lo elijo, entonces empieza ese juego y veo la partida, no la pantalla de elegir archivo.
- [x] Dado que veo Pokémon Fire Red, cuando lo elijo, entonces empieza ese juego y veo la partida, no la pantalla de elegir archivo.
- [x] Dado que salgo del emulador después de haber jugado uno de esos tres, cuando lo vuelvo a abrir, entonces los tres siguen en la pantalla inicial.

## Plan

- El estado ofrece, además de Crash Bandicoot, «Mario Kart Super Circuit» (`mario-kart-super-circuit.gba`), «Super Mario World» (`super-mario-world.gba`) y «Pokémon Fire Red» (`pokemon-fire-red.gba`). Un test comprueba ese orden y que elegir cada uno carga sus propios bytes por `onFileChosen`.
- La pantalla no se modifica: ya pinta un control por cada juego que ofrece el estado.
- Los tres ROM se copian desde Descargas a `emuladorgba/src/main/assets/` con esos nombres. `*.gba` ya está en `.gitignore`.
- Si falta el asset de uno, ese control no se muestra y los demás sí.

Archivos (5): `EmuladorStartViewModel.kt`, `EmuladorStartViewModelTest.kt`, `mario-kart-super-circuit.gba`, `super-mario-world.gba` y `pokemon-fire-red.gba`.

## Verificación

- `:emuladorgba:testDebugUnitTest` (paso 1: lista de 4 juegos y carga por asset OK).
- `:emuladorgba:assembleRelease` (paso 3: assets mario-kart, super-mario-world, pokemon-fire-red).
- Paso 2: sin cambios en pantalla. Paso 4: asset ausente ya cubierto en HU-1.
- Dispositivo físico: los cuatro juegos visibles y jugables (2026-11-03).
- CI EmuladorGba verde tras push paso 3.

## Fuera de alcance

- HeartGold, SoulSilver y la BIOS de DS (historia 3).
- Quitar «Elegir .gba» y BIOS de Game Boy Advance.
- Cambiar MiniJuegos, el catálogo o el contrato `GameModule`.
- Controles, guardado y trucos.

## Decisiones

- Títulos visibles: «Mario Kart Super Circuit», «Super Mario World» y «Pokémon Fire Red».
- Assets: `mario-kart-super-circuit.gba`, `super-mario-world.gba` y `pokemon-fire-red.gba`, copiados de Descargas y **commiteados** en `emuladorgba/src/main/assets/` para que CI publique el AAR con ellos (repo privado).
- Arrancar reutiliza `onFileChosen`. Crash Bandicoot no cambia de título ni de asset.
- La pantalla de la historia 1 recorre la lista del estado, así esta historia no la toca.
- Sin un asset, solo desaparece el control de ese juego.
