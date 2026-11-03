# HU-3

- Estado: hecha
- Épica: [epica.md](epica.md)

Como jugador, quiero ver Pokémon HeartGold en el emulador de Nintendo DS y empezarlo, para jugar sin abrir otra carpeta.

## Criterios de aceptación

- [x] Dado que abro el emulador de Nintendo DS, cuando llega la pantalla inicial, entonces veo Pokémon HeartGold y no se abre una carpeta.
- [x] Dado que veo Pokémon HeartGold, cuando lo elijo, entonces empieza la partida y veo el juego, no la pantalla de elegir archivo.
- [x] Dado que estoy en la pantalla inicial, cuando la miro, entonces no aparece Pokémon SoulSilver.
- [x] Dado que salgo del emulador después de haber jugado, cuando lo vuelvo a abrir, entonces Pokémon HeartGold sigue en la pantalla inicial.

## Plan

- El estado inicial ofrece un solo juego, con título «Pokémon HeartGold» y asset `pokemon-heartgold.nds`. Un test comprueba que la lista es solo ese título y que elegirlo carga esa ruta por `onFileChosen`.
- En la pantalla inicial, un control por cada juego que ofrece el estado. Al pulsarlo copia el asset a un archivo y arranca. No abre el selector de documentos.
- El ROM se coloca en `emuladornds/src/main/assets/pokemon-heartgold.nds`, copiado de Descargas y **commiteado** en EmuladorNds (repo privado); los `.nds` sueltos siguen en `.gitignore`.
- Si ese asset no está en la instalación, el control no se muestra. El botón «Elegir .nds» se queda.

Archivos (4): `EmuladorStartViewModel.kt`, `EmuladorStartViewModelTest.kt`, `EmuladorStartScreen.kt` y el asset `pokemon-heartgold.nds`.

## Verificación

- `:emuladornds:testDebugUnitTest` (paso 1: `bundledRoms_listsOnlyHeartGold`, `bundledRomChosen_loadsRomPath` OK).
- `:emuladornds:compileDebugKotlin` (paso 2: botones Rooms + copia asset a caché).
- `:emuladornds:assembleRelease` (paso 3: asset `pokemon-heartgold.nds`).
- Paso 4: asset ausente ya cubierto en paso 2.
- Push: ROM >100 MB → Git LFS (`.gitattributes` + CI `lfs: true`); push a `main` OK.
- Dispositivo físico: HeartGold visible y jugable (2026-11-03).

## Fuera de alcance

- Pokémon SoulSilver ([HU-3b](HU-3b.md)).
- La BIOS de ARM7 y ARM9 ([HU-3c](HU-3c.md)) y el firmware ([HU-3d](HU-3d.md)).
- Los juegos de Game Boy Advance (historias 1 y 2).
- Quitar «Elegir .nds».
- Cambiar MiniJuegos, el catálogo o el contrato `GameModule`.
- Controles, guardado, trucos y Bluetooth.

## Decisiones

- El título visible es «Pokémon HeartGold». El asset se llama `pokemon-heartgold.nds`.
- `onFileChosen` sigue recibiendo una ruta. La pantalla copia el asset a un archivo de caché y le pasa esa ruta.
- El selector de documentos sigue en la pantalla. HeartGold no lo usa.
- Sin el asset en el repo, no hay control y los tests no leen el ROM real. HeartGold sí va versionado en `emuladornds/src/main/assets/`.
- SoulSilver y la BIOS no caben en estos 4 archivos: quedan en HU-3b y HU-3c.
