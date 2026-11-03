# HU-3c

- Estado: cancelada
- Épica: [epica.md](epica.md)

Como jugador, quiero que al empezar un juego de Nintendo DS el emulador use la BIOS incluida, para que la partida arranque con ella. Requiere: #3

**Cancelación (2026-11-03):** no hay dumps de BIOS disponibles; el producto sigue con FreeBIOS de melonDS. No se implementará en esta épica.

## Criterios de aceptación

- [ ] Dado que empiezo Pokémon HeartGold, cuando arranca la partida, entonces no se me pide elegir un archivo de BIOS.
- [ ] Dado que la instalación trae la BIOS, cuando el juego arranca, entonces veo la partida enseguida y no aparece un menú para elegir el cartucho.
- [ ] Dado que salgo y vuelvo a empezar Pokémon HeartGold, cuando arranca, entonces otra vez no se me pide la BIOS y veo la partida.
- [ ] Dado que la instalación no trae la BIOS, cuando empiezo el juego, entonces la partida igual arranca.

## Plan

- Al crear la consola, si están los assets, se instalan en `NDSArgs.ARM9BIOS` (4096 bytes) y `NDSArgs.ARM7BIOS` (16384 bytes). Un test comprueba que esos bytes son los del asset y no la FreeBIOS de melonDS.
- La pantalla pasa el contenido de `bios9.bin` y `bios7.bin` al crear el núcleo. Se mantiene el arranque directo: elegir el juego sigue abriendo la partida.
- Esos dos archivos se colocan en `emuladornds/src/main/assets/` y se **commitean** (repo privado); los `bios*.bin` sueltos siguen ignorados.
- Si falta uno de los dos, no se instala ninguno y la consola sigue con la FreeBIOS que melonDS ya trae.

Archivos (5): `EmuladorStartScreen.kt`, `MelonDsCore.kt`, `emuladornds_melonds.cpp`, `bios9.bin` y `bios7.bin`.

## Verificación

- `:emuladornds:testDebugUnitTest` (dominio `NdsBootAssets` + suite presentation OK).
- `:emuladornds:assembleRelease`.
- Pendiente: copiar `bios9.bin` y `bios7.bin` a `assets/` y commitear.
- Publicar el AAR en Maven local, instalar MiniJuegos y comprobar los cuatro criterios con HeartGold.

## Fuera de alcance

- El firmware de la consola ([HU-3d](HU-3d.md)).
- Pokémon SoulSilver (historia 3b), salvo que ya esté en la lista: esta historia no lo añade.
- Los juegos de Game Boy Advance.
- Quitar el arranque directo y pedir un archivo de BIOS al jugador.
- Cambiar MiniJuegos, el catálogo o el contrato `GameModule`.
- Controles, guardado, trucos y Bluetooth.

## Decisiones

- Assets: `bios9.bin` (4096 bytes) y `bios7.bin` (16384 bytes), copiados de los archivos locales. No se descargan.
- Se mantienen `SetupDirectBoot` y `Start`, para que la partida siga empezando al elegir el juego.
- Un tamaño distinto de 4096 o 16384 no se instala: cuenta como BIOS ausente.
- La FreeBIOS de melonDS 1.0 queda solo como reserva cuando faltan los archivos.
