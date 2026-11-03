# HU-3d

- Estado: cancelada
- Épica: [epica.md](epica.md)

Como jugador, quiero que al empezar un juego de Nintendo DS también se use el firmware incluido, para arrancar con el de la consola. Requiere: #3c

**Cancelación (2026-11-03):** cancelada junto con HU-3c; firmware generado de melonDS.

## Criterios de aceptación

- [ ] Dado que empiezo Pokémon HeartGold, cuando arranca la partida, entonces no se me pide elegir un archivo de firmware.
- [ ] Dado que la instalación trae el firmware, cuando el juego arranca, entonces veo la partida enseguida y no aparece un menú para elegir el cartucho.
- [ ] Dado que salgo y vuelvo a empezar Pokémon HeartGold, cuando arranca, entonces otra vez no se me pide el firmware y veo la partida.
- [ ] Dado que falta el firmware o no tiene un tamaño válido, cuando empiezo el juego, entonces la partida igual arranca.
- [ ] Dado que la instalación trae la BIOS de la historia 3c y el firmware, cuando empiezo el juego, entonces sigo viendo la partida sin que se me pida ninguno de esos archivos.

## Plan

- Una función acepta el firmware solo si mide 128 KB, 256 KB o 512 KB. Un test cubre esas tres medidas y un tamaño inválido.
- Al crear la consola, si pasa el filtro, se instala con `Firmware(data, length)`. Si no, queda el firmware generado de melonDS. La BIOS de ARM9 y ARM7 no cambia.
- La pantalla pasa también el asset `firmware.bin`. Se mantiene el arranque directo.
- El archivo se coloca en `emuladornds/src/main/assets/firmware.bin` y se **commitea** (repo privado).

Archivos (5): `MelonDsCore.kt`, su test, `EmuladorStartScreen.kt`, `emuladornds_melonds.cpp` y el asset `firmware.bin`.

## Verificación

- Incluido en la misma entrega que HU-3c (`loadNdsBootAssets` + `firmware.bin` en pantalla).
- Pendiente: copiar `firmware.bin` a `assets/` y commitear.
- Publicar el AAR en Maven local, instalar MiniJuegos y comprobar los cinco criterios con HeartGold.

## Fuera de alcance

- Añadir juegos y quitar el arranque directo.
- Descargar el firmware o aceptar otro tamaño.
- BIOS de DSi, controles, guardado, trucos y Bluetooth.
- Cambiar MiniJuegos, el catálogo o el contrato `GameModule`.

## Decisiones

- El asset se llama `firmware.bin`, copiado de un archivo local. No se descarga.
- Tamaños válidos, los que melonDS trata como imagen entera: 131072, 262144 y 524288 bytes. Otro tamaño no se instala.
- Se mantienen `SetupDirectBoot` y `Start`, así elegir el juego sigue abriendo la partida.
- Sin firmware válido, sigue el firmware generado de melonDS 1.0. La BIOS de la historia 3c no se toca.
