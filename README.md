# cardTransactions
 
## Configuration du Port

L'application est configurée pour s'exécuter sur le port 8081 par défaut. Si vous souhaitez modifier le port, vous pouvez le faire en modifiant la propriété `server.port` dans le fichier `src/main/resources/application.properties`.


## APIs

### Obtenir une liste de transactions

**Endpoint:** `/api/transactions`
**Méthode:** GET

**Paramètres:**
- Aucun


### Obtenir une liste de transactions filtrées

**Endpoint:** `/api/transactionsFiltred`
**Méthode:** GET

**Paramètres Optionnels:**
- amount
- merchant
- status


### Obtenir une page de transactions

**Endpoint:** `/api/transactionsByPage`
**Méthode:** GET

**Paramètres:**
- page
- size


### Obtenir une liste de transactions triées par amount

**Endpoint:** `/api/sortedListByAmount`
**Méthode:** GET

**Paramètres:**
- aucun

### Obtenir une liste de transactions triées par merchant et status

**Endpoint:** `/api/sortedListByMerchantAndStatus`
**Méthode:** GET

**Paramètres:**
- aucun
