# Recipe

Aplicación para ver recetas y su país de origen.
La aplicación tiene 3 pantallas principales:
- Lista y buscador de las recetas
- Detalles de la receta seleccionada 
- Mapa geolocalización de origen de la receta 

Arquitectura!

![Screenshot_3](https://user-images.githubusercontent.com/2738131/227078583-d8149535-9b33-41a3-b4da-c9f4853eade3.png)

Arquitectura MVVM: elegida porque es una de las arquitecturas más utilizadas en el mercado actual y también porque elimina el fuerte acoplamiento entre cada componente. Más importante aún, en esta arquitectura los hijos no tienen referencia directa al padre, solo son referenciados por observables.

Hilt: proporciona una forma estándar de hacer DI en la aplicación, proporcionando contenedores para cada componente de Android en el proyecto y administrando el ciclo de vida del contenedor automáticamente. Para su uso también se utilizó la Daga.

Retrofit: además de permitir una implementación simple y ser uno de los marcos más utilizados en el mercado, con OkHttp es fácil, por ejemplo, interceptar la solicitud y cambiarla de la manera que lo necesite.

Compose: se usó porque tiene una gran reducción de código, la biblioteca de Compose no está acoplada al sistema operativo, como con los componentes actuales, es compatible con los componentes heredados (xml) y compose fue diseñado de manera que podemos construir nuestro interactúa con un sistema de bloques de código pequeños, reutilizables y autónomos.

Coroutines -  las corrutinas ayudan a administrar tareas de larga duración que, de lo contrario, podrían bloquear el subproceso principal y hacer que tu app dejara de responder. Más del 50% de los desarrolladores profesionales que usan corrutinas informaron que vieron un aumento en la productividad.

GoogleMaps API - La app de Google Maps te permite ir a cualquier lugar gracias a la navegación sencilla paso a paso. Maps te muestra instrucciones sobre cómo llegar y utiliza información del tráfico en tiempo real para encontrar la mejor ruta que te lleve a tu destino.

AsyncImage: es un componible que ejecuta una solicitud de imagen de forma asíncrona y muestra el resultado. Admite los mismos argumentos que la imagen compuesta estándar y, además, admite la configuración de marcador de posición/error/pintores alternativos y devoluciones de llamada onLoading/onSuccess/onError. Aquí hay un ejemplo que carga una imagen con un recorte circular, fundido cruzado y establece un marcador de posición.

Pruebas unitarias en UseCase y Repository
