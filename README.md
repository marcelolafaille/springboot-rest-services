# Building REST services with Spring
###### https://spring.io/guides/tutorials/rest/


REST embraces the precepts of the web, including its architecture, benefits, and everything else. This is no surprise given its author, Roy Fielding, was involved in probably a dozen specs which govern how the web operates.

What benefits? The web and its core protocol, HTTP, provide a stack of features:
- Suitable actions (`GET`, `POST`, `PUT`, `DELETE`, …)
- Caching
- Redirection and forwarding
- Security (encryption and authentication)


### Generar proyecto
[Spring Initializr](https://start.spring.io/)

Dependencias:
 * Spring Web (WEB)
 * Spring Data JPA (SQL)
 * H2 Database (SQL)
 * Spring HATEOAS (WEB)

### Cuando se ejecuta la aplicación
`2020-08-14 10:51:33.709  INFO 13752 --- [           main] DeferredRepositoryInitializationListener : Triggering deferred initialization of Spring Data repositories…`

```sql
Hibernate:    
drop table if exists employee CASCADE
 
Hibernate: 
drop table if exists orders CASCADE
 
Hibernate: 
drop sequence if exists hibernate_sequence

Hibernate: create sequence id start with 1001 increment by 50

Hibernate:
    create table employee (
       id bigint not null,
        first_name varchar(255),
        last_name varchar(255),
        role varchar(255),
        primary key (id)
    )

Hibernate:   
    create table orders (
       id bigint not null,
        description varchar(255),
        status integer,
        primary key (id)
    )
```

### Pruebas
#### employees
| Método | URL | Resultado |
| --- | :---: | ---: |
| GET | http://localhost:8080/employees | Muestra de todos los empleados |
| GET | http://localhost:8080/employees/99 | Muestra empleado 99 |
| GET | http://localhost:8080/employees/1001 | Error. Muestra mensaje de error |
| POST | http://localhost:8080/employees | Crea un registro **(1)** |
| PUT | http://localhost:8080/employees/520 | Modifica registro 520 **(2)** |
| DELETE | http://localhost:8080/employees/1001 | Borra registro 1001 |


**(1)**
```json
{
    "firstName": "Roberto",
    "lastName": "Robertone",
    "role": "Help Desk Operator"
}
```
```json
{
    "id": 1001,
    "firstName": "Roberto",
    "lastName": "Robertone",
    "role": "Help Desk Operator",
    "name": "Roberto Robertone",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/1001"
        },
        "employees": {
            "href": "http://localhost:8080/employees"
        }
    }
}
```

**(2)**
```json
{
    "id": 520,
    "firstName": "Constantine",
    "lastName": "Predohl",
    "role": "Account Representative II",
    "name": "Constantine Predohl",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/520"
        },
        "employees": {
            "href": "http://localhost:8080/employees"
        }
    }
}
```
```json
{
    "firstName": "Roberto",
    "lastName": "Robertone",
    "role": "Help Desk Operator"
}
```
```json
{
    "id": 520,
    "firstName": "Roberto",
    "lastName": "Robertone",
    "role": "Help Desk Operator",
    "name": "Roberto Robertone",
    "_links": {
        "self": {
            "href": "http://localhost:8080/employees/520"
        },
        "employees": {
            "href": "http://localhost:8080/employees"
        }
    }
}
```

#### orders
| Método | URL | Resultado |
| --- | :---: | ---: |
| GET | http://localhost:8080/orders | Muestra de todas las ordenes |
| GET | http://localhost:8080/orders/99 | Muestra orden 99 |
| POST | http://localhost:8080/employees | Crea un registro **(1)** |
| PUT | http://localhost:8080/employees/520 | Modifica registro 520 **(2)** |
| DELETE | http://localhost:8080/employees/1001 | Borra registro 1001 |


###### NOTA
- Las órdenes en estado **CANCELLED** o **COMPLETED** solo tienen los links **self** y **root**
Los links para transacciones no se muestran.

```json
{
    "id": 4,
    "description": "Rice - 7 Grain Blend",
    "status": "CANCELLED",
    "_links": {
        "self": {
            "href": "http://localhost:8080/orders/4"
        },
        "orders": {
            "href": "http://localhost:8080/orders"
        }
    }
}
```
```json
{
    "id": 5,
    "description": "Split Peas - Yellow, Dry",
    "status": "COMPLETED",
    "_links": {
        "self": {
            "href": "http://localhost:8080/orders/5"
        },
        "orders": {
            "href": "http://localhost:8080/orders"
        }
    }
}
```

- Las órdenes en estado **IN_PROGRESS**, además de **self** y **root** tienen los links para completar y cancelar.
```json
{
    "id": 577,
    "description": "Wine - Semi Dry Riesling Vineland",
    "status": "IN_PROGRESS",
    "_links": {
        "self": {
            "href": "http://localhost:8080/orders/577"
        },
        "orders": {
            "href": "http://localhost:8080/orders"
        },
        "cancel": {
            "href": "http://localhost:8080/orders/577/cancel"
        },
        "complete": {
            "href": "http://localhost:8080/orders/577/complete"
        }
    }
}
```


