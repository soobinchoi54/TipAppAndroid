# TipAppAndroid
A tip application based on Android platform

## Architecture
![architecture](/pics/architecture.png)

### About
The architecture of Tip App is mostly connected by explicit message-based calls from one component to another using Android intents. The app also utilizes a bounded service to support timer functionality, which calculates the amount of time elapsed since the start of a dining experience. The time elapsed is displayed to the user and serves as a value to be factored into the user’s quantification of their dining experience. Apart from explicit method calls, Connectors are also implemented to support some of the key components of the app.</div>

### The Tip App consists of eight major components: 
* Main Menu
* Restaurant List
* Restaurant External Database(RESTful API based)
* Experience Review Page
* Timer
* Tip Result
* Experience SQLite Database
* History List


## Contributors/Distribution:
  * [Duo Chai.](https://github.com/Danny7226) (https://github.com/Danny7226/TipAppAndroid/commits?author=Danny7226)  
  * [Soobin Choi.](https://github.com/soobinchoi54) (https://github.com/Danny7226/TipAppAndroid/commits?author=soobinchoi54)  
  * [Marc Andrada.](https://github.com/marc-andrada) (https://github.com/Danny7226/TipAppAndroid/commits?author=marc-andrada)  
