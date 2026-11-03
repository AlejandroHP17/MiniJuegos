# HU-3b

- Estado: hecha
- Épica: [epica.md](epica.md)

Como jugador, quiero ver también Pokémon SoulSilver en el emulador de Nintendo DS y empezarlo, para elegir entre los dos juegos de Rooms. Requiere: #3

## Criterios de aceptación

- [x] Dado que abro el emulador de Nintendo DS, cuando llega la pantalla inicial, entonces veo Pokémon SoulSilver, y Pokémon HeartGold sigue ahí, sin abrir una carpeta.
- [x] Dado que veo Pokémon SoulSilver, cuando lo elijo, entonces empieza ese juego y veo la partida, no la pantalla de elegir archivo.
- [x] Dado que elijo Pokémon HeartGold, cuando empieza la partida, entonces el juego es HeartGold.
- [x] Dado que salgo del emulador después de haber jugado SoulSilver, cuando lo vuelvo a abrir, entonces Pokémon SoulSilver sigue en la pantalla inicial.

## Plan

- El estado ofrece, además de Pokémon HeartGold, «Pokémon SoulSilver» con asset `pokemon-soulsilver.nds`. Un test comprueba ese orden y que elegir SoulSilver carga su propia ruta por `onFileChosen`.
- La pantalla no se modifica: ya pinta un control por cada juego que ofrece el estado.
- El ROM se copia desde Descargas a `emuladornds/src/main/assets/pokemon-soulsilver.nds` y se **commitea** (misma regla que HU-3).
- Si falta ese asset, ese control no se muestra y HeartGold sí.

Archivos (3): `EmuladorStartViewModel.kt`, `EmuladorStartViewModelTest.kt` y el asset `pokemon-soulsilver.nds`.

## Verificación

- `:emuladornds:testDebugUnitTest` (paso 1: lista HeartGold + SoulSilver y rutas OK).
- `:emuladornds:assembleRelease` (paso 3: asset `pokemon-soulsilver.nds` vía LFS).
- Paso 2: sin cambios en pantalla. Paso 4: asset ausente ya cubierto en HU-3.
- Dispositivo físico: HeartGold y SoulSilver visibles y jugables (2026-11-03).

## Fuera de alcance

- La BIOS de ARM7 y ARM9 (historia 3c) y el firmware (historia 3d).
- Los juegos de Game Boy Advance.
- Quitar «Elegir .nds».
- Cambiar MiniJuegos, el catálogo o el contrato `GameModule`.
- Controles, guardado, trucos y Bluetooth.

## Decisiones

- El título visible es «Pokémon SoulSilver». El asset se llama `pokemon-soulsilver.nds`.
- Arrancar reutiliza `onFileChosen`. HeartGold no cambia de título ni de asset.
- La pantalla de la historia 3 recorre la lista del estado, así esta historia no la toca.
- Sin el asset, solo desaparece el control de SoulSilver.
