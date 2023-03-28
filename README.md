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
- Crea un usuario y contraseña en la tabla usuarios de la base de datos. Podes hacerlo de manera directa mediante comandos sql o a través del endpoint POST/login/nuevo (Request Parameters: usuario, pass)</br>
- Una vez creado el usuario puedes usar el front-end para ingresar como administrador y cargar los datos del portfolio de manera gráfica o puedes usar los endpoints de la aplicación a través de postman.</br></br>

La API tiene los siguientes endpoints: </br>

Login: </br>
- GET /login: Retorna usuario si se proporcionó usuario y contraseña correctas. (Request Parameters: user, pass) </br>
- POST /login/nuevo : Crea un usuario nuevo (Request Parameters: usuario, pass) </br>

Información personal:
- GET /personas/{id}: Retorna la información personal de la persona buscada.</br>
- PUT /personas/editar: Actualiza información personal (REQUEST BODY) </br>
- PUT /personas/editarfoto: Actualiza la foto de perfil (Request parameter: archivo jpg) </br></br>
JSON request body: </br>
{ </br>
  "id" : 1, </br>
  "nombreyapellido": "nombre", </br>
  "titulo": "titulo", </br>
  "sobremi": "sobremi", </br>
  "foto": "" </br>
} </br>

Educación:
- GET /educacion/: Retorna la información de la formación educativa del usuario</br>
- POST /educacion/nueva : Agrega un nuevo registro de formación educativa (REQUEST BODY)</br>
- PUT /educacion/editar: Actualiza información de formación educativa (REQUEST BODY) </br>
- PUT /educacion/editarfoto: Actualiza la foto del registro seleccionado (Request parameters: archivo (jpg),id(número) )  </br>
- DELETE /educacion/eliminar/{id}: Elimina el registro con el id especificado.</br></br>
JSON request body: </br>
{ </br>
  "id" : 0, </br>
  "titulo": "titulo", </br>
  "institucion":"institucion", </br>
  "fechainicio":"fechainicio", </br>
  "estado":"estado", </br>
  "foto":"" </br>
}</br>

Habilidades:
- GET /habilidades/: Retorna las habilidades del usuario</br>
- POST /habilidades/nueva : Agrega una nueva habilidad (REQUEST BODY)</br>
- PUT /habilidades/editar: Actualiza una habilidad (REQUEST BODY)</br>
- PUT /habilidades/editarfoto: Actualiza la foto del registro seleccionado (Request parameters: archivo (jpg),id(número) ) </br>
- DELETE /habilidades/eliminar/{id}: Elimina el registro con el id especificado.</br></br>
JSON request body: </br>
{ </br>
  "id" : 0, </br>
  "nombre": "nombre", </br>
  "tipo":"tipo", </br>
  "porcentaje": 80, </br>
  "foto":"" </br>
} </br>

Proyectos:
- GET /proyectos/: Retorna los proyectos del usuario</br>
- POST /proyectos/nueva : Agrega un nuevo proyecto (REQUEST BODY)</br>
- PUT /proyectos/editar: Actualiza un proyecto (REQUEST BODY) </br>
- PUT /proyectos/editarfoto: Actualiza la foto del registro seleccionado (Request parameters: archivo (jpg),id(número) ) </br>
- DELETE /proyectos/eliminar/{id}: Elimina el registro con el id especificado.</br></br>
JSON request body: </br>
{ </br>
  "id" : 0, </br>
  "nombre": "nombre", </br>
  "tecnologias":"tecnologias", </br>
  "descripcion": "descripcion", </br>
  "foto":"", </br>
  "link":"link" </br>
} </br>




