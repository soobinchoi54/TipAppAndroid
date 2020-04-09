# TipAppAndroid
A RESTful API based tip application on Android platform

### 1.1 Motivation
Tipping is often an awkward but necessary part of any dining experience. It can sometimes feel like you are being put on the spot when a tip amount is already predetermined for you (15% 18% 20% etc.) There are tipping apps on the current market; however, most of them are simple calculators. We were driven to make tipping easier through an interactive app that gives customers a way to quantify their dining experiences based on a set of dining-related criteria. Ultimately, the app creates a more personal approach to paying bills and helps calculate a fair tip that reflects the genuine quality of customers’ dining experiences.

### 1.2 Solution 
Instead of using the built-in calculator to calculate an arbitrary number for your tip, diners can use the Tip App, which gamifies the tipping experience. Diners can base their tips on a pre-set review criteria that reflect their dining experiences. The app will then add or subtract tip percentages based on their perceived experience, consolidate a final tip percentage, and apply the value to calculate the final amount. 


## 1. Architecture
![architecture](/pics/architecture.png)

### 1.1 About
The architecture of Tip App is mostly connected by explicit message-based calls from one component to another using Android intents. The app also utilizes a bounded service to support timer functionality, which calculates the amount of time elapsed since the start of a dining experience. The time elapsed is displayed to the user and serves as a value to be factored into the user’s quantification of their dining experience. Apart from explicit method calls, Connectors are also implemented to support some of the key components of the app.</div>

### 1.2 Eight major components: 
* Main Menu
* Restaurant List
* Restaurant External Database
* Experience Review Page
* Timer
* Tip Result
* Experience SQLite Database
* History List



## 2. Selective Features
* Yelp Fusion RESTful API 
* Picasso
* SQLite DB
* Builder Pattern
* CardView Style UI



## 3. Screenshots
![sc1](/pics/sc1.png)
![sc2](/pics/sc2.png)
![sc4](/pics/sc4.png)
![sc5](/pics/sc5.png)


## 4. Contributors/Distribution:
  * [Duo Chai.](https://github.com/Danny7226) (https://github.com/Danny7226/TipAppAndroid/commits?author=Danny7226)  
  * [Soobin Choi.](https://github.com/soobinchoi54) (https://github.com/Danny7226/TipAppAndroid/commits?author=soobinchoi54)  
  * [Marc Andrada.](https://github.com/marc-andrada) (https://github.com/Danny7226/TipAppAndroid/commits?author=marc-andrada)  
