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

Se agregarón las siguientes validaciones
- saldo mayor o igual a cero
- que el nombre no venga vacio

Se agrego cache en el endpoint para obtener la cuenta

# Transaction Service


# Como correr el proyecto

