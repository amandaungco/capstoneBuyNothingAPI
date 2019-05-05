# BuyNothingAPI

This is a Java/SpringBoot API connected to a Postgres Database.

See https://github.com/amandaungco/BuyNothingMatcherApp for the mobile App/Interface.

## Functionality
These are the API endpoints based on the MVP requirements for the capstone.

### Users

List of all users
```
GET /users
```

Add a new user
```
POST /users
```

Edit an existing user
```
PUT /users/{userId}
```

Delete an existing user
```
DELETE /users/{userId}
```
### Offers

List of all offers
```
GET /offers
```

Post a new offer
```
POST /users/{userId}/offers
```

Edit an existing offer
```
PUT /users/{userId}/offers/{offerId}
```

Delete an existing offer
```
DELETE /users/{userId}/offers/{offerId}
```

### Requests

List of all requests
```
GET /requests
```

Post a new request
```
POST /users/{userId}/requests
```

Edit an existing offer
```
PUT /users/{userId}/requests/{requestId}
```

Delete an existing offer
```
DELETE /users/{userId}/requests/{requestId}
```

### Exchanges

List of all exchanges
```
GET /exchanges
```

Post a new exchange
```
POST /exchanges
```

Edit an existing exchange
```
PUT /exchanges/{exchangeId}
```

Delete an existing exchange
```
DELETE /exchanges/{exchangeId}
```
### Matches

List of all matches
```
GET /matches
```

Post a new match
```
POST /matches
```

Edit an existing match
```
PUT /matches/{matchId}
```

Delete an existing match
```
DELETE /matches/{matchId}
```



## Installation

To download and edit this project: 
1. [Download Android Studio](https://developer.android.com/studio/)
2. Clone this repository:
```
git clone https://github.com/amandaungco/BuyNothingMatcherApp.git
```
3. Open Android Studio and select Import Project, then choose the folder into which you cloned this repo.
Connect an Android phone running Android Nougat (APK 23) or higher to your computer. (You can also use an emulator to run the app on a virtual device).

4. Click the green Run button in the menu bar and select the device you plugged in to build the project and install the app on your phone. The app should open automatically.

5. In order to get full functionality the backend API will need to also be cloned and running, please see [Buy Nothing API](https://github.com/amandaungco/capstoneBuyNothingAPI).

## Credits

 * Swipecards base by https://github.com/Diolor/Swipecards
 * Image Assistance by https://github.com/bumptech/glide
