'''
Ejercicio 10: Scrollbar
Crea un Text que contenga un texto largo (varios párrafos) y añade una barra de
desplazamiento vertical (Scrollbar) para que el usuario pueda desplazarse a través del
contenido.
'''

import tkinter as tk

# Creamos la ventana principal

root = tk.Tk()
root.title = "Scrollbar"
root.geometry = ("100x100")


# Creamos el frame para el text y la scrollbar

frame = tk.Frame(root)
frame.pack(fill= 'both', expand = True)


# Scrollbar

scrollbar = tk.Scrollbar(root)
scrollbar.pack(side=tk.RIGHT, fill= tk.Y)


# Text

texto = '''
El término búho es el nombre común de las aves de la familia Strigidae del orden de los estrigiformes o aves rapaces nocturnas. Habitualmente designa especies que, a diferencia de las lechuzas, tienen plumas alzadas que parecen orejas (sus verdaderas orejas se encuentran al costado de la cabeza, a los laterales de los ojos) y presentan una coloración amarilla o naranja en el iris. Debido a que sus ojos carecen de movilidad y solo pueden ver hacia delante, pueden girar la cabeza 270°.1​

El Diccionario de la lengua española, publicado por la Real Academia Española, describe como «búho»2​ una especie que claramente es Bubo bubo, el búho real.3​ Sin embargo también utiliza la palabra «búho» de forma genérica, al aplicarla a otras especies, por ejemplo en los lemas tucúquere4​ y caburé.5​

Existen más de 200 clases de búhos.[cita requerida] Estas aves habitan en casi todos los lugares del planeta, excepto en la Antártida. Son aves de rapiña, porque se alimentan de seres vivos tales como: peces, insectos, ratones, lagartijas y otros animales. Estas aves son nocturnas, y pueden cazar en la oscuridad. Se caracterizan por especial cuidado a no hacer ni el más mínimo ruido al intentar agarrar su presa. Por ser aves nocturnas tienen muy desarrollada su parte visual y auditiva. El tamaño varía según la especie, el más pequeño es el mochuelo que mide 13,5 cm.
El término búho es el nombre común de las aves de la familia Strigidae del orden de los estrigiformes o aves rapaces nocturnas. Habitualmente designa especies que, a diferencia de las lechuzas, tienen plumas alzadas que parecen orejas (sus verdaderas orejas se encuentran al costado de la cabeza, a los laterales de los ojos) y presentan una coloración amarilla o naranja en el iris. Debido a que sus ojos carecen de movilidad y solo pueden ver hacia delante, pueden girar la cabeza 270°.1​

El Diccionario de la lengua española, publicado por la Real Academia Española, describe como «búho»2​ una especie que claramente es Bubo bubo, el búho real.3​ Sin embargo también utiliza la palabra «búho» de forma genérica, al aplicarla a otras especies, por ejemplo en los lemas tucúquere4​ y caburé.5​

Existen más de 200 clases de búhos.[cita requerida] Estas aves habitan en casi todos los lugares del planeta, excepto en la Antártida. Son aves de rapiña, porque se alimentan de seres vivos tales como: peces, insectos, ratones, lagartijas y otros animales. Estas aves son nocturnas, y pueden cazar en la oscuridad. Se caracterizan por especial cuidado a no hacer ni el más mínimo ruido al intentar agarrar su presa. Por ser aves nocturnas tienen muy desarrollada su parte visual y auditiva. El tamaño varía según la especie, el más pequeño es el mochuelo que mide 13,5 cm.
El término búho es el nombre común de las aves de la familia Strigidae del orden de los estrigiformes o aves rapaces nocturnas. Habitualmente designa especies que, a diferencia de las lechuzas, tienen plumas alzadas que parecen orejas (sus verdaderas orejas se encuentran al costado de la cabeza, a los laterales de los ojos) y presentan una coloración amarilla o naranja en el iris. Debido a que sus ojos carecen de movilidad y solo pueden ver hacia delante, pueden girar la cabeza 270°.1​

El Diccionario de la lengua española, publicado por la Real Academia Española, describe como «búho»2​ una especie que claramente es Bubo bubo, el búho real.3​ Sin embargo también utiliza la palabra «búho» de forma genérica, al aplicarla a otras especies, por ejemplo en los lemas tucúquere4​ y caburé.5​

Existen más de 200 clases de búhos.[cita requerida] Estas aves habitan en casi todos los lugares del planeta, excepto en la Antártida. Son aves de rapiña, porque se alimentan de seres vivos tales como: peces, insectos, ratones, lagartijas y otros animales. Estas aves son nocturnas, y pueden cazar en la oscuridad. Se caracterizan por especial cuidado a no hacer ni el más mínimo ruido al intentar agarrar su presa. Por ser aves nocturnas tienen muy desarrollada su parte visual y auditiva. El tamaño varía según la especie, el más pequeño es el mochuelo que mide 13,5 cm.
'''


# Crear el widget Text

text = tk.Text(frame, yscrollcommand=scrollbar.set, wrap=tk.WORD)
text.insert(tk.END, texto)  # Insertar el texto largo en el widget Text
text.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)


# Configurar la scrollbar para el widget Text

scrollbar.config(command=text.yview)


# Iniciar el loop de la ventana

root.mainloop()
