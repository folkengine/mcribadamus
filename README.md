# McRibadamus Kata
![McRibadamus](mcribadamus.jpg)

## The McRib Availability Prediction Engine

For centuries people have looked to the wisdom of Nostradamus in order to see into the future. In modern times few problems have vexed mankind more than when the McRib(tm) sandwich will be available. Now, thanks to the power of McRibadamus, they can know the fate of the value meal they love so much. 

Chris Baker ([@folkengine](https://twitter.com/folkengine))

# Requirements

In this exercise you will be creating an application that we attempt to predict the availability of the McRib based upon the price of Lean Hog Futures (Pork) listed by the Chicago Mercantile Exchange.

Lean Hog Futures are traded on the months of February, April, May, Jun, July, August, October, and December. By prepending the year to the appropriate symbol one can call Yahoo Finance in order to retrieve the current market prices.


| Month         | Symbol        | Example   |
| ------------- |:-------------:| ---------:|
| February      | LHG           | [LHG16.CME](http://finance.yahoo.com/webservice/v1/symbols/LHG16.CME/quote?format=json&view=detail) |
| April         | LHJ           | [LHJ16.CME](http://finance.yahoo.com/webservice/v1/symbols/LHJ16.CME/quote?format=json&view=detail) |
| May           | LHK           | [LHK16.CME](http://finance.yahoo.com/webservice/v1/symbols/LHK16.CME/quote?format=json&view=detail) |
| June          | LHM           | [LHM16.CME](http://finance.yahoo.com/webservice/v1/symbols/LHM16.CME/quote?format=json&view=detail) |
| July          | LHN           | [LHN16.CME](http://finance.yahoo.com/webservice/v1/symbols/LHN16.CME/quote?format=json&view=detail) |
| August        | LHQ           | [LHQ16.CME](http://finance.yahoo.com/webservice/v1/symbols/LHQ16.CME/quote?format=json&view=detail) |
| October       | LHV           | [LHV16.CME](http://finance.yahoo.com/webservice/v1/symbols/LHV16.CME/quote?format=json&view=detail) |
| December      | LHZ           | [LHZ16.CME](http://finance.yahoo.com/webservice/v1/symbols/LHZ16.CME/quote?format=json&view=detail) |

## Iteration 1 - Server Side Functionality

This iteration covers core functionality of grabbing the remote futures market data, and analyzing it to report on the likely hood that the McRib sandwich will be available during that period.

### Feature: Determine the next offerings

> As a user I want to be able to retrieve the next markets for lean hog futures. At least nine markets should be computed. Since they are offered only on certain months, I will need to determine what the current month is and which markets are available.

- Determine current month
- Determine which future markets will be available.

### Feature: Determine Commodity Symbols and Data Links

> As a user I will want to know the Chicago Mercantile Exchange symbols for the lean hog futures that I am looking up and the links to Yahoo! Finance that will provide for me JSON data for those futures. 

- Construct CME symbols
- Construct Yahoo! Finance JSON links

### Feature: Analyze Futures Market Data

> As a user I want to be able to take the latest CME futures data and report if the price is greater than the threshold I have determined to be needed in order for the McRib to be available.

- Retreive JSON data
- Compare price to threshold

## Iteration 2 - Web Application

> As a user I would like the McRibadamus predictions to be displayed on the web.

- Create a website to display the engine's predictions.

## Iteration 3 - Mobile Application

- As a use I would like to view McRibadamus' predictions on my mobile device. 

## Iteration 4 - Advanced Analytics

> As a user that acknowledges the threat of climate change, I would like to take the predicted amount of climate change over time and use is as a basis to predict the impact it will have on the avaialability of my beloved McRib sandwich.


Links
=====

* [Wikipedia > McRib #Limited_availability](https://en.wikipedia.org/wiki/McRib#Limited_availability)
* [McRib Locator](http://mcriblocator.com/map.html)
* [A Conspiracy of Hogs: The McRib as Arbitrage](http://www.theawl.com/2011/11/a-conspiracy-of-hogs-the-mcrib-as-arbitrage) 
* [barchart.com > Lean Hog Futures](http://www.barchart.com/commodityfutures/Lean_Hogs_Futures/HE?search=HE*)
* [Wikinvest > Lean Hog Futures](http://www.wikinvest.com/futures/Lean_Hogs_Futures)
* [@McRibWatch](https://twitter.com/McRibWatch)
* [@McRibSandwich](https://twitter.com/McRibSandwich)
* [@mcriblocator](https://twitter.com/mcriblocator)
