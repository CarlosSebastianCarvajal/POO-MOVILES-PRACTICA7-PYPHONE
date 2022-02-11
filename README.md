# POO-MOVILES-PRACTICA8-PYPHONE
Desarrollo de una aplicación móvil nativa Android que implementa los servicios de la Pasarela de Pago PayPhone (https://www.payphone.app/)  para poder realizar cobros con tarjetas de créditos simulando una aplicación comercial. La Interfaz  de la aplicación debe mostrar los campos requeridos para hacer una solicitud de Cobro

CAPTURAS DE FUNCIONAMIENTO DE LA APLICACIÓN:
A continuación se muestra la captura de la Activity que se muestra inmediatamente al abrir la aplicacion.

Como se puede apreciar. Se muestra una pantalla con varios campos para relizar un cobro mediante PyPhone

![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/Screenshot_1644572070.png)


Se ha ingresado Datos para relizar un cobro:

![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/Screenshot_1644572258.png)

Al presionar el boton se emite el cobro y nos redirige a otra pantalla que muestra el estado de la transacción:

![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/Screenshot_1644572290.png)

Al mismo tiempo PyPhone Notifica al usuario el cobro que se esta relizando

![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/msg1520977477-4186.jpg)

En este caso se ha rechazado la transacción:
![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/msg1520977477-4183.jpg)

Y por lo tanto en nuestra aplicación nos refleja el estado de la transacción en cancelado:

![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/Screenshot_1644572345.png)


AHORA SE MUESTRA UN EJEMPLO DE USO CON UNA TRANSACCION ACEPTADA:

![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/Screenshot_1644572371.png)

Inmediatamente Pyphone notifica el cobro, el cual se ha aceptado:

![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/msg1520977477-4184.jpg)

y Podemos ver el estado en nuestra aplicación:

![alt text](https://github.com/CarlosSebastianCarvajal/POO-MOVILES-PRACTICA8-PYPHONE/blob/main/Capturas/Screenshot_1644572442.png)


