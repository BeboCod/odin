import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

/**
 * Composable que muestra un cuadro de diálogo de alerta con un icono, un título, un mensaje y un botón de confirmación.
 *
 * @param containerColor Color de fondo del contenedor del diálogo y del botón.
 * @param icon Recurso de imagen del icono que se mostrará en el diálogo.
 * @param title Título que se mostrará en el diálogo.
 * @param msg Mensaje que se mostrará en el diálogo.
 * @param open Función que se invoca para controlar la visibilidad del diálogo.
 */
@Composable
fun Show(
    containerColor: Color,
    icon: Int,
    title: String,
    msg: String,
    open: (Boolean) -> Unit
) {
    AlertDialog(
        onDismissRequest = { open(false) },
        confirmButton = {
            Button(
                onClick = { open(false) },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White)
            ) {
                Text(
                    text = "Ok",
                    fontWeight = FontWeight.Bold,
                    color = containerColor
                )
            }
        },
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = colorScheme.secondary
                )
            }
        },
        text = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = msg,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = colorScheme.secondary
                )
            }
        },
        icon = {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = colorScheme.secondary
            )
        },
        containerColor = containerColor,
        textContentColor = Color.White,
        titleContentColor = Color.White,
        iconContentColor = Color.White
    )
}
