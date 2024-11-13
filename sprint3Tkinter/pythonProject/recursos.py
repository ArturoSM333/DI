import requests
from PIL import Image, ImageTk
import io

def descargar_imagen(url, size):
    try:
        response = requests.get(url)
        response.raise_for_status()
        image_data = io.BytesIO(response.content)
        image = Image.open(image_data).resize(size, Image.LANCZOS)
        return ImageTk.PhotoImage(image)
    except requests.RequestException as e:
        print(f"Error al descargar la imagen desde {url}: {e}")
        return None
