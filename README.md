# shortener

Desafío

Crear un URL shortener para ser utilizado en promociones enviadas a travès twitter.

Requisitos funcionales

1.- Crear un alias corto para una URL, es decir, pasar de una URL como esta: "https://www.enjoyalgorithms.com/blog/design-a-url-shortening-service-like-tiny-url" a un alias corto como "149a1d3"

2.- Dado un alias, recuperar la URL original

3.- Habilitar/Deshabilitar una URL. Sòlo las URL habilitadas pueden ser recuperadas

4.- Cambiar la URL asignada al alias

Requisitos no funcionales

1.- Los alias generados deben tener una vigencia indefinida

2.- La plataforma debe soportar picos de tráfico alrededor de 1M RPM

3.- Se debe contar con estadísticas de acceso nearly real time

4.- La solución debe maximizar la velocidad y minimizar los costos

5.- La solución debe tener un uptime de 99,98%


Supuestos funcionales

1.- El largo máximo de una URL es de 2048 caracteres

2.- Los usuarios son manejados por otro componente el cual puede ser consultado respecto de la validez de un usuario


Supuestos no funcionales

1.- Se utilizará una base de datos para almacenar la relación entre el alias y la correcpondiente URL

1.- Se considerará como vigencia indefinida 50 años

2.- Se consideraràn los siguientes tramos de tráfico al día:

  a)Tráfico pico: 20% del dìa con 1M RPM
  
  b)Tráfico normal: 60% del día con 500K RPM
  
  c)Tráfico bajo: 20% del día con 100K RPM
  
3.- Con esta estimaciòn de tráfico al día, se tiene un estimado de 748M peticiones al día

4.- Se considerará una relación de 500 lecturas por cada escritura (500:1). Esta relación nos indica que al año se deben guardar 546M registros. En 50 años se requiere guardar 27.300M de registros

5.- Dada la naturaleza del problema, se debe priorizar la velocidad de lectura por sobre las opraciones de inserción y actualización

6.- Se utilizarán los caracteres a-z,0-9 para codificar el alias. Esto nos da 36 carcateres. Debemos cubrir 27.300M de combinaciones diferentes con combinaciones de estos 36 caracteres. Utilizando 7 caracteres obtenemos 36 elevado a 7 (78.300M) combinaciones diferentes, por lo que utilizaremos 7 caracteres

7.- Considerando que la base de datos debe guardar la URL (2048), el alias (7), y el estado (1) => 2056 => 16K por registro. Con esto deberemos tener una capacidad de almacenamiento para los 50 años de al menos 410Tb

8.- Las estadísticas de acceso se obtendrán mediante otro componente, el cual se alimentará mediante mensajes generados en una cola kafka

9.- Se utilizará la base de datos mongodb principalmente debido a su capacidad de escalamiento horizontal


Algotimo de generación de alias

1.- Se utiliza el algoritmo SHA-256 para generar un string de 64 caracteres

2.- Se toman los 7 primeros caracteres y se buscan en la base de datos:

  a)Si no se encuentra al alias se inserta en la base de datos 
  
  b)Si se encuentra el alias, se seleccionan los siguientes 7 caracteres (moviendo la selección en un caracter) y se vuelve a buscar en la base de datos, repitiendo el proceso hasta dar con una combinación no existente
  

Arquitectura


Descripción API



Servidor
  
http://139.144.190.16:8080

