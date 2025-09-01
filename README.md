# Criando um Microsserviço de Controle de E-commerce - DIO
  Repositório que agrupa as duas APIS feitas Spring Boot para o Desafio de Projeto "Criando um Microsserviço de Controle de E-commerce" da DIO.

## Tecnologias Utilizadas
- Spring Boot 3
- H2
- RabbitMQ
- Swagger API
- Lombok
- MapStruct

## Gerenciador de Dependências
- Gradle
  
## Storefront - API destinada à ser vitrine do e-commerce
### Estrutura do Storefront
```
src
└── main
    └── java
        └── br
            └── com
                └── iuri
                    └── storefront
                        ├── config
                        │    ├── AMPQConfig
                        │    ├── OpenAPIConfig
                        │    └── WarehouseClientConfig
                        ├── controller
                        │   ├── request
                        │   │    └── ProductSaveRequest
                        │   ├── response
                        │   │    ├── ProductAvailableResponse
                        │   │    ├── ProductDetailResponse
                        │   │    └── ProductSavedResponse
                        │   └── ProductController
                        ├── dto
                        │    ├── ProductDetailDTO
                        │    ├── ProductInfoDTO
                        │    └── StockStatusMessage
                        ├── entity
                        │    └── ProductEntity
                        ├── exceptions
                        │    └── ProductNotFoundException
                        ├── mapper
                        │    └── IProductMapper
                        ├── repository
                        │    └── ProductRepository
                        ├── service
                        │   ├── impl
                        │   │    ├── IProductChangeAvailabilityConsumer
                        │   │    └── IProductService
                        │   ├── IProductChangeAvailabilityConsumer
                        │   └── IProductService
                        └── StorefrontApplication
```
### Endpoints do Warehouse
#### GET /products - retorna todos os produtos cadastrados 
#### POST /products - cadastra um novo produto
- Exemplo de Request
  ```
  {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "name": "string"
  }
  ```
- Exemplo de Response
  ```
  {
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "active": true
  }
  ```

## Warehouse - API destinada à ser o estoque do e-commerce
### Estrutura do Warehouse

