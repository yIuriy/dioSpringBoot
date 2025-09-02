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
br.com.iuri.warehouse
├── config
│    ├── AMPQConfig
│    ├── OpenAPIConfig
│    └── WarehouseClientConfig
│   
├── controller
│   ├── request
│   │    └── ProductSaveRequest
│   │
│   ├── response
│   │    ├── ProductAvailableResponse
│   │    ├── ProductDetailResponse
│   │    └── ProductSavedResponse
│   │
│   └── ProductController
│   
├── dto
│    ├── ProductDetailDTO
│    ├── ProductInfoDTO
│    └── StockStatusMessage
│   
├── entity
│    └── ProductEntity
│   
├── exceptions
│    └── ProductNotFoundException
│   
├── mapper
│    └── IProductMapper
│   
├── repository
│    └── ProductRepositor
│   
├── service
│   ├── impl
│   │    ├── IProductChangeAvailabilityConsumer
│   │    └── IProductService
│   │
│   ├── IProductChangeAvailabilityConsumer
│   └── IProductService
│   
└── StorefrontApplication
```
### Endpoints do Storfront
#### GET /products - retorna uma lista com todos os produtos cadastrados 
- Exemplo de Response
  ```
  [
    {
      "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "name": "string"
    }
  ]
  ```

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
#### POST /products/{id}/purchase - realiza a compra de um produto que esteja com status ativo
- Exemplo de Request
  ```
  {
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
  }
  ```
#### GET /products/{id} - retorna um produto com base no id
- Exemplo de Response
  ```
  {
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "price": 0
  }
  ```
## Warehouse - API destinada à ser o estoque do e-commerce
### Estrutura do Warehouse
```
br.com.iuri.warehouse
├── config
│   ├── AMQPConfig
│   ├── OpenAPIConfig
│   └── StorefrontClientConfig
│
├── controller
│   ├── request
│   │   ├── ProductSaveRequest
│   │   └── StockSaveRequest
│   │
│   ├── response
│   │   ├── ProductDetailResponse
│   │   ├── ProductSaveResponse
│   │   └── StockSavedResponse
│   │
│   ├── ProductController
│   └── StockController
│
├── dto
│   ├── ProductStorefrontSaveDTO
│   └── StockStatusMessage
│
├── entity
│   ├── ProductEntity
│   ├── StockEntity
│   └── StockStatus
│
├── exceptions
│   ├── ProductNotFoundException
│   └── StockNotFoundException
│
├── mapper
│   ├── IProductMapper
│   └── IStockMapper
│
├── repository
│   ├── ProductRepository
│   └── StockRepository
│
├── service
│   ├── impl
│   │   ├── ProductChangeAvailabilityProducerImpl
│   │   ├── ProductQueryServiceImpl
│   │   ├── ProductServiceImpl
│   │   └── StockServiceImpl
│   │
│   ├── IProductChangeAvailabilityProducer
│   ├── IProductQueryService
│   ├── IProductService
│   └── IStockService
│
└── WarehouseApplication

```
### Endpoints do Storfront
#### PUT /stocks/{id}/release - altera o status de um produto para AVAILABLE no Warehouse para TRUE no Storefront 
#### DELETE /stocks/{id}/release - altera o status de um produto para UNAVAILABLE no Warehouse para FALSE no Storefront  
#### POST /stocks - insere um produto no estoque
- Exemplo de Request
  ```
  {
  "amount": 9007199254740991,
  "boughtPrice": 0,
  "soldPrice": 0,
  "productId": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
  }
  ```
- Exemplo de Response
  ```
  {
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "amount": 9007199254740991,
  "boughtPrice": 0,
  "status": "IN_CONFERENCE",
  "soldPrice": 0,
  "productId": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "productName": "string"
  }
  ```
#### POST /products - insere um produto no sistema, para posteriormente ser inserido no Stok
- Exemplo de Request
  ```
  {
  "name": "string"
  }
  ```
- Exemplo de Response
  ```
  {
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string"
  }
  ```
#### POST /products/{id}/purchase - realiza a compra de um produto que esteja com status ativo
#### GET /products/{id} - retorna um produto com base no id
- Exemplo de Response
  ```
  {
  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
  "name": "string",
  "price": 0
  }
  ```


