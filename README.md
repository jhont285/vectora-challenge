# vectora-challenge

El proyecto esta hecho con arquitectura hexagonal

```
src/main/java/com/miempresa/miapp
├── MiAppApplication.java          # Clase principal
│
├── domain/                        # Núcleo del dominio (puro, sin frameworks)
│   ├── model/                     # Entidades y Value Objects
│   │   └── Product.java
│   └── port/                      # Interfaces (puertos)
│       ├── in/                   # Puertos de entrada (casos de uso)
│       │   └── GetProductUseCase.java
│       └── out/                  # Puertos de salida (interfaces técnicas)
│           └── LoadProductPort.java
│
├── application/                   # Implementaciones de casos de uso
│   └── service/
│       └── GetProductService.java
│
├── adapter/                       # Adaptadores concretos (entrada y salida)
│   ├── in/                       # Interfaces de entrada
│   │   └── web/                 # Controladores REST
│   │       └── ProductController.java
│   └── out/                      # Interfaces de salida
│       └── persistence/         # Adaptadores hacia DB, otros sistemas
│           ├── ProductJpaRepository.java
│           ├── ProductEntity.java
│           └── ProductPersistenceAdapter.java
│
├── config/                        # Configuración de beans, seguridad, etc.
│   └── BeanConfig.java
│
└── exception/                     # Excepciones personalizadas
    └── ProductNotFoundException.java
```

# Account Service

Se agregarón las siguientes validaciones en el endpoint de creación
- Saldo mayor o igual a cero
- Que el nombre no venga vacio ni nulo

Que plus tiene:

- Cache en el endpoint de optener saldo.

Que falto: 
- JWT

# Transaction Service

Se agregarón las siguientes validaciones en el endpoint de creación de cuenta.
- Numeros de cuenta iguales o mayor a cero
- Monto mayor a cero
- Numero de cuenta existan en el account service

Que plus tiene:

- Cache en el endpoint de optener saldo con su respectivo refresco si se crea una nueva cuenta
- Circuit Breaker al consultar account service

Que falto: 
- JWT

# Como correr el proyecto

Requisitos

* Docker
* Java 17

Commandos

```shh
cd account_service

./gradlew bootRun  # En macOS/Linux
gradlew.bat bootRun  # En Windows

# Abrir otra consola

cd transaction_service
./gradlew bootRun  # En macOS/Linux
gradlew.bat bootRun  # En Windows
```

Abrir la colleción de postman y explorar

