# CurrencyProject
Backend application to persist a purchase transaction and retrieve the transaction in a specified country’s
currency

## How to Run

### Local
Run the following command-line in project root directory

```
$ mvn clean spring-boot:run
``` 
The application will be available in port 8080.

### Remote
Application deployed in Railway.

RAILWAY_url

#### EndPoints
- Receive and persist a transaction
http://currencyproject.up.railway.app/api/wexinterview/v1/transactions/save  


- Retrieve a transaction and convert to target currency
http://currencyproject.up.railway.app/api/wexinterview/v1/transactions/conversion


#### Docs

Documentation created using swagger

https://currencyproject.up.railway.app/api/wexinterview/swagger-ui/index.html

http://localhost:8080/api/currency/swagger-ui/index.html

## Builded with
*  [Java 17](https://docs.oracle.com/en/java/javase/17/)
*  [Maven](https://maven.apache.org/) 
