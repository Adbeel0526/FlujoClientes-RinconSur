# FlujoClientesRinconSur

Aplicación móvil desarrollada para el caso de la Tienda Departamental Rincón Sur, orientada al registro y segmentación del flujo de clientes por acceso.

## Descripción

La aplicación permite programar sesiones de observación, registrar el comportamiento de las personas que ingresan por los accesos de la tienda y visualizar indicadores generales del flujo.

El objetivo es diferenciar a las personas que transitan por la tienda, quienes realizan una visita y aquellos casos que no pueden determinarse.

## Funcionalidades principales

- Programación de sesiones de observación.
- Validación de acceso, fecha, horario y observador.
- Accesos permitidos del 1 al 4.
- Control de duración de las sesiones.
- Visualización de sesiones programadas.
- Registro de personas por categoría:
  - Tránsito.
  - Visita.
  - No determinado.
- Finalización de sesiones.
- Persistencia local de datos.
- Dashboard con indicadores y porcentajes.
- Vibración al registrar observaciones.
- Notificación al finalizar una observación.
- Diseño adaptado a dispositivos Android.
- Ícono personalizado de la aplicación y de notificaciones.

## Tecnologías utilizadas

- Kotlin
- Android Studio
- Jetpack Compose
- Material Design 3
- MVVM
- Room
- SQLite
- Navigation Compose
- StateFlow
- Coroutines

## Arquitectura

El proyecto utiliza arquitectura MVVM.

### Model

Contiene los estados y entidades utilizados por la aplicación.

### ViewModel

Gestiona la lógica de negocio y el estado de cada pantalla.

### Repository

Actúa como intermediario entre los ViewModel y la capa de persistencia.

### Room

La persistencia local se implementa utilizando Room sobre SQLite.

La base de datos almacena:

- Sesiones de observación.
- Registros asociados a cada sesión.

### Screens

Las interfaces fueron desarrolladas con Jetpack Compose.

Pantallas principales:

- Panel Supervisor.
- Programar sesión.
- Panel Observador.
- Registro de observación.
- Dashboard.

## Flujo general

1. El Supervisor programa una sesión.
2. La sesión queda disponible para el Observador.
3. El Observador selecciona una sesión.
4. Se registran las personas según su clasificación.
5. La observación se finaliza y se guarda.
6. La sesión pasa a estado FINALIZADA.
7. El Dashboard actualiza automáticamente sus indicadores.

## Recursos nativos utilizados

### Vibración

La aplicación utiliza la vibración del dispositivo para entregar retroalimentación al registrar una persona.

### Notificaciones

Al finalizar correctamente una observación, la aplicación genera una notificación indicando que el registro fue guardado.

## Validaciones

El formulario de programación incluye validaciones como:

- Acceso permitido entre 1 y 4.
- Fecha válida.
- La fecha no puede ser posterior a la fecha actual.
- Hora válida en formato HH:mm.
- Duración de sesión entre 30 y 60 minutos.
- Observador obligatorio.

## Ejecución

1. Abrir el proyecto en Android Studio.
2. Sincronizar las dependencias de Gradle.
3. Ejecutar en un emulador Android o dispositivo físico.
4. La aplicación utiliza persistencia local, por lo que no requiere conexión externa para las funciones implementadas.

## APK

Se generó una versión APK de prueba y una versión release firmada para instalación en dispositivos Android.

## Integrantes

- Adbeel Rodríguez
- Jose Martinez

## Asignatura

DSY1105 - Desarrollo de Aplicaciones Móviles

Duoc UC
