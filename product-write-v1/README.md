# product-write-v1

Responsible for exposing CUD endpoints.

## Create Flow

1. POST /v1/products with a JSON representation of a `Product` (API model)
1. Call `Product Service` create
1. 202 Accepted (async response)

## Update Flow

1. PUT /v1/products/{id} with a JSON representation of a `Product` (API model)
1. Call `Product Service` update
1. 202 Accepted (async response)

## Delete Flow

1. DELETE /v1/products/{id}
1. Call GET /v1/products/{id} to check existence (return 404 on missing)
1. 204 No Content

## Design Considerations

* Can a product be updated with a category that does not exist?
