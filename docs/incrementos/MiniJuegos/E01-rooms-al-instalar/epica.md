# Épica: Juegos de Rooms listos en el emulador

- Estado: descartada
- Actualizada: 2026-11-03
- Descarte: el código se revirtió en EmuladorGba y EmuladorNds. Meter las ROM en el AAR hacía la app más pesada y la partida se sentía peor que abriendo el archivo desde el teléfono.
- Para quién: quien abre el emulador de Game Boy Advance o el de Nintendo DS en MiniJuegos.
- Problema: los juegos de Rooms hay que copiarlos a mano y, al abrir el emulador, buscar el archivo en otra carpeta.
- Resultado: al instalar MiniJuegos y abrir un emulador, ahí aparecen sus juegos de Rooms y se puede empezar uno sin abrir otra carpeta. En Game Boy Advance: Crash Bandicoot, Mario Kart Super Circuit, Super Mario World y Pokémon Fire Red. En Nintendo DS: Pokémon HeartGold y SoulSilver. El emulador de DS sigue con FreeBIOS y firmware generado de melonDS (sin dumps de consola en el AAR).
- Repo primario: MiniJuegos/
- Repos secundarios: EmuladorGba/, EmuladorNds/
- Tipo: feature
- Fuera de la épica: otros juegos; BIOS de Game Boy Advance (ese emulador no la lee); elegir archivos de otras carpetas; controles, guardado y trucos.
- Rooms en repo: ya no. Las ROM no van en el AAR; el jugador las abre desde el teléfono.

## Backlog

1. [HU-1](HU-1.md) — revertida. Como jugador, quiero ver Crash Bandicoot en el emulador de Game Boy Advance y empezarlo, para jugar sin abrir otra carpeta.
2. [HU-2](HU-2.md) — revertida. Como jugador, quiero ver también Mario Kart Super Circuit, Super Mario World y Pokémon Fire Red en ese emulador y empezar cualquiera, para elegir entre los juegos de Rooms de Game Boy Advance. Requiere: #1
3. [HU-3](HU-3.md) — revertida. Como jugador, quiero ver Pokémon HeartGold en el emulador de Nintendo DS y empezarlo, para jugar sin abrir otra carpeta.
3b. [HU-3b](HU-3b.md) — revertida. Como jugador, quiero ver también Pokémon SoulSilver en el emulador de Nintendo DS y empezarlo, para elegir entre los dos juegos de Rooms. Requiere: #3
3c. [HU-3c](HU-3c.md) — cancelada (sin consola para dumps de BIOS; se mantiene FreeBIOS de melonDS).
3d. [HU-3d](HU-3d.md) — cancelada (misma razón; firmware generado de melonDS).
