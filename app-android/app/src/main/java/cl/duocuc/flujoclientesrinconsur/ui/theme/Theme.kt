package cl.duocuc.flujoclientesrinconsur.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val EsquemaColores = lightColorScheme(
    primary = MoradoPrincipal,
    onPrimary = Blanco,

    secondary = MoradoOscuro,
    onSecondary = Blanco,

    background = FondoApp,
    onBackground = TextoPrincipal,

    surface = Blanco,
    onSurface = TextoPrincipal,

    error = RojoError
)

@Composable
fun FlujoClientesRinconSurTheme(
    content: @Composable () -> Unit
) {

    MaterialTheme(
        colorScheme = EsquemaColores,
        typography = Typography,
        content = content
    )
}