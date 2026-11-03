# HU-1

- Estado: hecha
- Épica: [epica.md](epica.md)

Como jugador, quiero ver Crash Bandicoot en el emulador de Game Boy Advance y empezarlo, para jugar sin abrir otra carpeta.

## Criterios de aceptación

- [x] Dado que abro el emulador de Game Boy Advance, cuando llega la pantalla inicial, entonces veo Crash Bandicoot y no se abre una carpeta.
- [x] Dado que veo Crash Bandicoot, cuando lo elijo, entonces empieza la partida y veo el juego, no la pantalla de elegir archivo.
- [x] Dado que estoy en la pantalla inicial, cuando la miro, entonces no aparecen Mario Kart Super Circuit, Super Mario World ni Pokémon Fire Red.
- [x] Dado que salgo del emulador después de haber jugado, cuando lo vuelvo a abrir, entonces Crash Bandicoot sigue en la pantalla inicial.

## Plan

- El estado inicial ofrece un solo juego, con título «Crash Bandicoot» y asset `crash-bandicoot.gba`. Un test comprueba que la lista es solo ese título y que elegirlo carga esos bytes por `onFileChosen`.
- En la pantalla inicial, un control por cada juego que ofrece el estado. En esta historia es solo Crash Bandicoot: al pulsarlo lee su asset y arranca. No abre el selector de documentos.
- El ROM se coloca en `emuladorgba/src/main/assets/crash-bandicoot.gba`, copiado del archivo de Descargas. `*.gba` ya está en `.gitignore`.
- Si ese asset no está en la instalación, el control no se muestra. El botón «Elegir .gba» se queda.

Archivos (4): `EmuladorStartViewModel.kt`, `EmuladorStartViewModelTest.kt`, `EmuladorStartScreen.kt` y el asset `crash-bandicoot.gba`.

## Verificación

- `:emuladorgba:testDebugUnitTest` (paso 1: `bundledRoms_listsOnlyCrashBandicoot`, `bundledRomChosen_loadsRomBytes` OK).
- `:emuladorgba:compileDebugKotlin` (paso 2 OK).
- `:emuladorgba:assembleDebug` (paso 3: asset `crash-bandicoot.gba` en el AAR).
- Paso 4 (asset ausente): cubierto en paso 2 con `bundledAssetExists`.
- Publicar el AAR en Maven local, instalar MiniJuegos y comprobar los cuatro criterios en el teléfono.

## Fuera de alcance

- Mario Kart Super Circuit, Super Mario World y Pokémon Fire Red (historia 2).
- HeartGold, SoulSilver y la BIOS de DS (historia 3).
- Quitar «Elegir .gba» y BIOS de Game Boy Advance.
- Cambiar MiniJuegos, el catálogo o el contrato `GameModule`.
- Controles, guardado y trucos.

## Decisiones

- El título visible es «Crash Bandicoot». El asset se llama `crash-bandicoot.gba`.
- Arrancar reutiliza `onFileChosen`. No hay un segundo camino de carga.
- El selector de documentos sigue en la pantalla. Crash no lo usa.
- Sin el asset en el repo, no hay control y los tests no leen el ROM real. Los de Rooms sí se commitean en `emuladorgba/src/main/assets/`.
- El host no se toca: MiniJuegos ya depende del AAR, así que el asset llega al instalar.
