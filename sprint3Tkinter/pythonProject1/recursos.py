import requests
from PIL import Image, ImageTk
import io

def descargar_imagen(url, size):

    try:
        # Realizar una solicitud GET para descargar la imagen
        response = requests.get(url)
        response.raise_for_status()  # Verificar si la solicitud fue exitosa

        # Abrir la imagen desde el flujo de bytes y redimensionarla
        imagen_bytes = io.BytesIO(response.content)
        imagen = Image.open(imagen_bytes)
        imagen = imagen.resize(size, Image.LANCZOS)  # Redimensionar con alta calidad

        # Convertir la imagen en un formato compatible con Tkinter
        imagen_tk = ImageTk.PhotoImage(imagen)
        return imagen_tk

    except requests.exceptions.RequestException as e:
        print(f"Error al descargar la imagen desde {url}: {e}")
        return None
