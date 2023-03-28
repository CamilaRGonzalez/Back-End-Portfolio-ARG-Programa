# Back-End-Portfolio-ARG-Programa
Back end del proyecto final de Argentina Programa (portfolio editable) desarrollado con spring boot

<h2>Requerimientos</h2>
-Java 19</br>
-MariaDB</br>

<h2>Instalación</h2>
- Clona este repositorio en tu computadora.</br>
- Crea una base de datos MariaDB</br>
- Modifica la configuración de la base de datos en el archivo application.properties para que apunte a tu base de datos.</br>
- Abrí el proyecto desde tu IDE y descargá las dependencias declaradas</br>


<h2>Uso</h2>
- Crea un usuario y contraseña en la tabla usuarios de la base de datos. Podes hacerlo de manera directa mediante comandos sql o a través del </br>
endpoint POST /login/nuevo (Request Parameters: usuario, pass)
- Una vez creado el usuario puedes usar el front-end para ingresar coo administrador y cargar los datos del portfolio de manera gráfica o puedes usar los </br>
endpoints de la aplicación a través de postman.

La API tiene los siguientes endpoints:

Login:
GET /login: Retorna usuario si se proporcionó usuario y contraseña correctas. </br>
POST /login/nuevo : Crea un usuario nuevo (Request Parameters: usuario, pass) </br>

Información personal:
GET /personas/{id}: Retorna la información personal de la persona buscada.</br>
PUT /personas/editar: Actualiza información personal </br>
(Request Body: {"id" : 1, "nombreyapellido": "nombre", "titulo":"titulo","sobremi":"sobremi","foto":""}).</br>
PUT /personas/editarfoto: Actualiza la foto de perfil (Request parameter: archivo jpg) </br>

Educación:
GET /educacion/: Retorna la información de la formación educativa del usuario</br>
POST /educacion/nueva : Agrega un nuevo registro de formación educativa </br>
(Request Body: {"id" : 0, "titulo": "titulo", "institucion":"institucion","fechainicio":"fechainicio","estado":"estado","foto":""}).</br>
PUT /educacion/editar: Actualiza información de formación educativa </br>
(Request Body: {"id" : 1, "titulo": "titulo", "institucion":"institucion","fechainicio":"fechainicio","estado":"estado","foto":""}).</br>
PUT /educacion/editarfoto: Actualiza la foto del registro seleccionado (Request parameters: archivo (jpg),id(número) )  </br>
DELETE /educacion/eliminar/{id}: Elimina el registro con el id especificado.</br>

Habilidades:
GET /habilidades/: Retorna las habilidades del usuario</br>
POST /habilidades/nueva : Agrega una nueva habilidad </br>
(Request Body: {"id" : 0, "nombre": "nombre", "tipo":"tipo","porcentaje": 80,"foto":""}).</br>
PUT /habilidades/editar: Actualiza una habilidad </br>
(Request Body: {"id" : 1, "nombre": "nombre", "tipo":"tipo","porcentaje": 80,"foto":""}).</br>
PUT /habilidades/editarfoto: Actualiza la foto del registro seleccionado (Request parameters: archivo (jpg),id(número) ) </br>
DELETE /habilidades/eliminar/{id}: Elimina el registro con el id especificado.</br>

Proyectos:
GET /proyectos/: Retorna los proyectos del usuario</br>
POST /proyectos/nueva : Agrega un nuevo proyecto </br>
(Request Body: {"id" : 0, "nombre": "nombre", "tecnologias":"tecnologias","descripcion": "descripcion","foto":"","link":"link"}).</br>
PUT /proyectos/editar: Actualiza un proyecto </br>
(Request Body: {"id" : 1, "nombre": "nombre", "tecnologias":"tecnologias","descripcion": "descripcion","foto":"","link":"link"}).</br>
PUT /proyectos/editarfoto: Actualiza la foto del registro seleccionado (Request parameters: archivo (jpg),id(número) ) </br>
DELETE /proyectos/eliminar/{id}: Elimina el registro con el id especificado.</br>
