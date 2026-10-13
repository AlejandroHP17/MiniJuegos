package pelkidev.com.mx.minijuegos.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val TealContainer = Color(0xFFB8E0D2)

private val LightColorScheme = lightColorScheme(
    primary = TealDeep,
    onPrimary = Cream,
    primaryContainer = TealContainer,
    onPrimaryContainer = Ink,
    secondary = Coral,
    onSecondary = Cream,
    secondaryContainer = Amber.copy(alpha = 0.35f),
    onSecondaryContainer = Ink,
    tertiary = TealSoft,
    background = Sand,
    onBackground = Ink,
    surface = Cream,
    onSurface = Ink,
    surfaceVariant = SandDark,
    onSurfaceVariant = InkMuted,
    outline = InkMuted.copy(alpha = 0.35f),
)

private val DarkColorScheme = darkColorScheme(
    primary = TealSoft,
    onPrimary = Ink,
    secondary = Amber,
    onSecondary = Ink,
    tertiary = Coral,
    background = Color(0xFF122022),
    onBackground = Cream,
    surface = Color(0xFF1A2C2E),
    onSurface = Cream,
    surfaceVariant = Color(0xFF243A3C),
    onSurfaceVariant = SandDark,
)

@Composable
fun MiniJuegosTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content,
    )
}
