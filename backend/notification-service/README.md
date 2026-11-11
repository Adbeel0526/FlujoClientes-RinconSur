# notification-service

Correos y notificaciones del backend.

## Estado

Fase 1: proyecto Kotlin independiente con arranque HTTP, Actuator y pruebas de salud.
La funcionalidad de negocio se implementará en las siguientes fases.

## Requisitos y ejecución

JDK 21 y Maven 3.9 o superior. Ejecutar desde esta carpeta:

```sh
mvn -B clean verify
mvn spring-boot:run
```

Puerto predeterminado: `8084`. Se puede cambiar mediante `SERVER_PORT`.
El perfil predeterminado es `local`; se puede cambiar con `SPRING_PROFILES_ACTIVE`.
La dirección predeterminada es `127.0.0.1` y se puede cambiar con `SERVER_ADDRESS`.

```sh
curl --fail http://localhost:8084/actuator/health
curl --fail http://localhost:8084/actuator/health/readiness
```

Consultar [la documentación del backend](../README.md) para el alcance y la ejecución conjunta.
