# Backend de FlujoClientes Rincón Sur

## Fase 1: estructura ejecutable

Cinco proyectos Spring Boot independientes escritos en Kotlin. Cada carpeta tiene su propio `pom.xml`,
clase de aplicación, configuración y pruebas. No existe un proyecto Maven
agregador ni dependencia del proyecto Android.

| Proyecto | Puerto local | Responsabilidad futura |
|---|---:|---|
| api-gateway | 8080 | Entrada Android y enrutamiento |
| auth-service | 8081 | Login, JWT, BCrypt y contraseñas |
| user-service | 8082 | Usuarios, perfiles, roles y estado activo |
| operations-service | 8083 | Sesiones, registros, accesos, horarios, tienda y dashboard |
| notification-service | 8084 | Correos y notificaciones |

En esta fase solo están implementados el arranque HTTP y los endpoints técnicos de
salud. No hay rutas de negocio, autenticación, persistencia ni envío de correos.
El Gateway incorpora Spring Cloud Gateway Server Web MVC; sus rutas se configurarán
en la fase de integración. Todos los proyectos usan el mismo modelo web Servlet.

## Estructura de cada proyecto

```text
pom.xml
README.md
src/main/kotlin/cl/duocuc/rinconsur/<servicio>/<Aplicacion>.kt
src/main/resources/application.yml
src/main/resources/application-local.yml
src/test/kotlin/cl/duocuc/rinconsur/<servicio>/<Aplicacion>Tests.kt
```

## Herramientas

- JDK 21.
- Kotlin 2.2.21, con compilador Maven y plugin Spring para abrir las clases que requieren proxies.
- Maven 3.9 o superior instalado y disponible como `mvn`.
- Spring Boot 4.0.7.
- Spring Cloud 2025.1.2, únicamente en el Gateway.
- Maven Surefire 3.5.5, fijado para reproducibilidad de las pruebas.

La combinación Boot/Cloud sigue la [matriz oficial de compatibilidad](https://spring.io/projects/spring-cloud/).
No se incluye Maven Wrapper en esta fase; se utiliza Maven instalado.
La primera compilación requiere acceso a Maven Central o una caché completa.

## Compilación y pruebas

Desde la raíz del repositorio, cada proyecto se verifica por separado:

```sh
mvn -B -ntp -f backend/api-gateway/pom.xml clean verify
mvn -B -ntp -f backend/auth-service/pom.xml clean verify
mvn -B -ntp -f backend/user-service/pom.xml clean verify
mvn -B -ntp -f backend/operations-service/pom.xml clean verify
mvn -B -ntp -f backend/notification-service/pom.xml clean verify
```

`verify` compila, ejecuta las pruebas y produce un JAR ejecutable por servicio.
Las pruebas cargan el contexto web completo y usan MockMvc para verificar salud,
disponibilidad y que el endpoint de entorno no esté expuesto. No abren sockets ni
requieren servicios externos. El arranque con puertos reales se comprueba ejecutando
los servicios y consultando sus endpoints de salud en un entorno que permita sockets.

Con dependencias previamente descargadas se puede agregar `-o` para trabajar sin red.
Si Maven selecciona otro JDK, configurar `JAVA_HOME` apuntando a Java 21.

## Ejecución local

Ejecutar cada comando en una terminal independiente desde la raíz:

```sh
mvn -f backend/api-gateway/pom.xml spring-boot:run
mvn -f backend/auth-service/pom.xml spring-boot:run
mvn -f backend/user-service/pom.xml spring-boot:run
mvn -f backend/operations-service/pom.xml spring-boot:run
mvn -f backend/notification-service/pom.xml spring-boot:run
```

También se puede ejecutar cada archivo `target/<servicio>-0.0.1-SNAPSHOT.jar` con `java -jar`.

Cada proceso expone:

- `/actuator/health`.
- `/actuator/health/liveness`.
- `/actuator/health/readiness`.

Por ejemplo, `curl --fail http://localhost:8081/actuator/health` devuelve estado `UP`.
Los detalles internos y los demás endpoints de Actuator no se exponen.

## Configuración

Cada servicio contiene `application.yml` y `application-local.yml`.

| Variable | Valor predeterminado | Uso |
|---|---|---|
| SERVER_PORT | Puerto de la tabla | Cambiar puerto HTTP |
| SERVER_ADDRESS | 127.0.0.1 | Dirección de escucha |
| SPRING_PROFILES_ACTIVE | local | Seleccionar perfil |

La ejecución local escucha solo en la interfaz de bucle local.
No se requiere archivo `.env`. Los secretos futuros se proporcionarán externamente.

## Laragon y base de datos

Los cinco servicios se ejecutan con Java y Maven, sin Docker ni Docker Compose.
Laragon podrá proporcionar el motor de base de datos en la fase de persistencia;
no reemplaza al proceso Java de cada microservicio.

En esta fase no se necesita una base de datos. Antes de agregar persistencia se
confirmará qué motor y versión se utilizarán en Laragon. Cada servicio persistente
tendrá su propia base y credenciales; el Gateway no necesitará base de datos.
El controlador JDBC y las migraciones se incorporarán según ese motor.

## Límites y siguientes fases

Los roles oficiales serán `ADMINISTRADOR`, `SUPERVISOR` y `OBSERVADOR`.
Auth será dueño de las credenciales; Users, del perfil, correo, rol y estado activo.
Operations será dueño de las sesiones y sus registros; Notifications, de los envíos.
Los servicios no compartirán tablas ni entidades persistentes.

La siguiente fase implementará Users y Auth: contratos, persistencia, usuarios,
roles, credenciales, login y JWT. Después seguirán operaciones, rutas y seguridad
del Gateway, notificaciones e integración Android, cada una con sus comprobaciones.
La lógica funcional de Android permanece intacta durante esta fase.

## Validación de la fase 1

El 11 de noviembre de 2026 se ejecutó `mvn -B -ntp -f backend/<servicio>/pom.xml
clean verify` para cada uno de los cinco proyectos desde la terminal local con
JDK 21.0.10 y Maven 3.9.16. Los cinco finalizaron con `BUILD SUCCESS` y generaron
sus JAR ejecutables.

| Proyecto | Pruebas | Fallos | Errores | Omitidas |
|---|---:|---:|---:|---:|
| api-gateway | 3 | 0 | 0 | 0 |
| auth-service | 3 | 0 | 0 | 0 |
| user-service | 3 | 0 | 0 | 0 |
| operations-service | 3 | 0 | 0 | 0 |
| notification-service | 3 | 0 | 0 | 0 |

Las 15 pruebas verifican los endpoints mediante MockMvc. La ejecución simultánea
de los cinco procesos y la comunicación por puertos reales todavía no se han
comprobado. Los avisos de carga dinámica del agente Mockito no produjeron fallos.
La aplicación Android conserva su contenido original.
