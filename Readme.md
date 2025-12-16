# ecom-ii-bdcc-app – Application E-commerce JEE
Ce projet est une plateforme e-commerce moderne construite sur une architecture distribuée de Microservices en Java (Spring Boot), complétée par une interface utilisateur front-end dynamique en Angular. L'objectif est de fournir un système modulaire, évolutif et résilient pour la gestion complète des transactions en ligne.
## Architecture Globale du projet :

![Architecture Globale du Projet](./Screens/ArchitectureGlobale.png)

Notre application est construite sur une architecture distribuée et modulaire reposant sur des Microservices indépendants (développés avec Spring Boot), ainsi qu'une interface utilisateur dynamique développée en Angular.

Cette approche garantit la scalabilité, la résilience et la maintenance aisée de l'ensemble de la plateforme.

### Services Back-end (Microservices Spring Boot)
L'ensemble des fonctionnalités métier est décomposé en services spécialisés :

Discovery Service (Eureka) : Assure l'enregistrement et la découverte dynamique des adresses des microservices, permettant une communication inter-service résiliente.

Config Service : Fournit une gestion centralisée des configurations pour tous les services de l'application, facilitant le déploiement sur différents environnements.

Gateway Service : Agit comme l'API Gateway de l'application. C'est le point d'entrée unique qui gère le routage des requêtes vers les services appropriés ainsi que l'application des mécanismes de sécurité (authentification/autorisation).

Billing Service : Contient toute la logique métier de facturation, y compris la gestion des commandes finalisées, le calcul des montants, et la génération des documents associés.

Inventory Service : Gère le catalogue de produits et est responsable du suivi des stocks et de la mise à jour de la disponibilité des articles.

Customer Service (ou ecom-ii-app) : Ce service est dédié à la gestion du domaine client. 

### Interface Utilisateur (Front-end)
Ecom Web App (Angular) : Il s'agit du client web dynamique. Il communique exclusivement avec le Gateway Service pour consommer les APIs des microservices et offrir l'expérience utilisateur complète de l'e-commerce.

![](./Screens/Eureka.png)

Eureka nous permet de visualiser en temps réel l'enregistrement de tous les microservices.

# discovery-service :
![](./Screens/ArchitectureGlobale.png)

![](./Screens/application propreties.png)

# config-service :
![](./Screens/configserviceMain.png)

![](./Screens/application propreties config.png)

# gateway-service :
![](./Screens/gateway main.png)
![](./Screens/application yml.png)
![](./Screens/application propreties gateway.png)
# billing-service :
![](./Screens/architeccture globale de billing.png)
![](./Screens/BillingServiceMain.png)
![](./Screens/application propreties billing.png)

## Entities:
![](./Screens/billing-entities/Bill.png)

![](./Screens/billing-entities/ProductItem.png)
## Feign :
![](./Screens/billing-feign/CustomerRestClient.png)

![](./Screens/billing-feign/ProductRestClient.png)
## Model :
![](./Screens/billing-model/Customer.png)
![](./Screens/billing-model/Product.png)
## Repository :
![](./Screens/billing-repository/BillRepository.png)
![](./Screens/billing-repository/ProductItemRepository.png)
## Web :
![](./Screens/billing-web/BillRestController.png)
# inventory-service :
![](./Screens/architecture globaleinventory.png)
![](./Screens/app propreties inventory.png)
![](./Screens/inventory-main/part1.png)
![](./Screens/inventory-main/part2.png)
## config
![](./Screens/inventory-config/RestRepositoryConfig.png)

## Entities
![](./Screens/inventory-entities/Product.png)

## Repository

![](./Screens/inventory-repository/ProductRepository.png)


# customer-service :
![](./Screens/architecture globale cust.png)
![](./Screens/application propreties cust.png)
![](./Screens/Main cust.png)

## config 
![](./Screens/customer-config/configTEST.png)

![](./Screens/customer-config/CustomerConfigParams.png)

![](./Screens/customer-config/RestRepositoryConfig.png)


## Entities

![](./Screens/customer-entities/Customer.png)

![](./Screens/customer-entities/CustomerProjection.png)

![](./Screens/customer-entities/CustomerProjectionEmail.png)


## Repository
![](./Screens/customer-repo/CustomerRespository.png)


# Config-repo 
![](./Screens/config-repo/app propreties.png)

![](./Screens/config-repo/archi globale.png)

![](./Screens/config-repo/billing dev.png)

![](./Screens/config-repo/billing prod.png)

![](./Screens/config-repo/billing propreties.png)

![](./Screens/config-repo/customer propreties.png)

![](./Screens/config-repo/customer propreties dev.png)

![](./Screens/config-repo/customer propreties prod.png)

![](./Screens/config-repo/inventory propreties.png)

![](./Screens/config-repo/inventory propreties dev.png)

![](./Screens/config-repo/inventory propreties prod.png)


