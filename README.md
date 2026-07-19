# McRibadamus Kata
![McRibadamus](mcribadamus.jpg)

## The McRib Availability Prediction Engine

For centuries people have looked to the wisdom of Nostradamus in order to see into the future. In modern times few problems have vexed mankind more than when the McRib(tm) sandwich will be available. Now, thanks to the power of McRibadamus, they can know the fate of the value meal they love so much.

Chris Baker ([@folkengine](https://twitter.com/folkengine))

## A Word From History

The original 2015 version of this kata pulled quotes from the Yahoo Finance
`webservice/v1` API using the CME's `LH` lean hog symbols. Both are gone: Yahoo
killed that API in 2017, and the CME renamed lean hog futures from `LH` to
`HE`. Let this be the kata's first lesson — **APIs die, and your code outlives
them**. The `clojure/mcribadamus/` library and `clojure/mcribadamus-app/` web
app preserve the original 2015 solution, dead endpoint and all, as a
historical exhibit.

This version of the kata is language-agnostic: implement it in whatever
language you like.

# Requirements

In this exercise you will create an application that attempts to predict the
availability of the McRib based upon the price of Lean Hog Futures (Pork)
traded on the Chicago Mercantile Exchange.

Lean Hog Futures trade for the months of February, April, May, June, July,
August, October, and December. Each month has a standard futures month code.
A contract symbol is built as `HE` + month code + two-digit year + `.CME`,
and Yahoo Finance's v8 chart API will return current market data for it as
JSON:

```
https://query1.finance.yahoo.com/v8/finance/chart/<SYMBOL>
```

| Month    | Code | Example (next offering as of July 2026) |
| -------- |:----:| --------------------------------------- |
| February | G    | [HEG27.CME](https://query1.finance.yahoo.com/v8/finance/chart/HEG27.CME) |
| April    | J    | [HEJ27.CME](https://query1.finance.yahoo.com/v8/finance/chart/HEJ27.CME) |
| May      | K    | [HEK27.CME](https://query1.finance.yahoo.com/v8/finance/chart/HEK27.CME) |
| June     | M    | [HEM27.CME](https://query1.finance.yahoo.com/v8/finance/chart/HEM27.CME) |
| July     | N    | [HEN27.CME](https://query1.finance.yahoo.com/v8/finance/chart/HEN27.CME) |
| August   | Q    | [HEQ26.CME](https://query1.finance.yahoo.com/v8/finance/chart/HEQ26.CME) |
| October  | V    | [HEV26.CME](https://query1.finance.yahoo.com/v8/finance/chart/HEV26.CME) |
| December | Z    | [HEZ26.CME](https://query1.finance.yahoo.com/v8/finance/chart/HEZ26.CME) |

The continuous front-month contract is also available as
[`HE=F`](https://query1.finance.yahoo.com/v8/finance/chart/HE=F).

Notes on the endpoint:

- Prices are quoted in US cents per pound (the payload reports currency
  `USX`).
- The current price is buried in the response at
  `chart.result[0].meta.regularMarketPrice` — digging it out of the
  surrounding noise is part of the exercise.
- Send a browser-ish `User-Agent` header; some environments get a 429/403
  without one.
- This is Yahoo's unofficial-but-public API — the same one its own site uses.
  It has broken before (see A Word From History). Consider capturing real
  responses as local fixtures so your tests run offline and your kata
  outlives the endpoint.

## Iteration 1 - The Prediction Engine

This iteration covers the core functionality: computing which contracts to
look at, grabbing the remote futures market data, and analyzing it to report
on the likelihood that the McRib sandwich will be available during that
period.

### Feature: Determine the next offerings

> As a user I want to be able to retrieve the next markets for lean hog
> futures. At least nine markets should be computed. Since they are offered
> only on certain months, I will need to determine what the current month is
> and which markets are available.

- Determine the current month
- Determine which future markets will be available, rolling across year
  boundaries (nine offerings always spans at least two calendar years)

### Feature: Determine Commodity Symbols and Data Links

> As a user I will want to know the Chicago Mercantile Exchange symbols for
> the lean hog futures that I am looking up, and the Yahoo Finance URLs that
> will provide the JSON data for those futures.

- Construct CME symbols (`HE` + month code + two-digit year + `.CME`)
- Construct Yahoo Finance v8 chart URLs

### Feature: Analyze Futures Market Data

> As a user I want to take the latest futures data and report whether the
> McRib is likely to be available during each contract period.

- Retrieve and parse the JSON data, extracting the market price
- Compare the price to a threshold (default: 90.00 cents per pound)
- A price **below** the threshold means pork is cheap, and the McRib is
  **likely**; at or above it, **unlikely**

Why below? The [McRib arbitrage theory](https://web.archive.org/web/2020/http://www.theawl.com/2011/11/a-conspiracy-of-hogs-the-mcrib-as-arbitrage)
holds that McDonald's brings back the McRib precisely when pork is cheap.
(The 2015 kata had this backwards. Nostradamus nods knowingly.)

Example verdicts, using real prices captured 2026-07-19 with the default
threshold of 90.00:

| Symbol    | Price  | McRib?   |
| --------- | ------ | -------- |
| HEV26.CME | 87.95  | likely   |
| HEZ26.CME | 78.775 | likely   |
| HEG27.CME | 81.725 | likely   |
| HEM27.CME | 97.5   | unlikely |

## Iteration 2 - Web Application

> As a user I would like the McRibadamus predictions to be displayed on the
> web.

- Serve the engine's predictions as a JSON endpoint
- Serve a simple page that displays each upcoming contract and its verdict

Links
=====

* [Wikipedia > McRib #Limited availability](https://en.wikipedia.org/wiki/McRib#Limited_availability)
* [A Conspiracy of Hogs: The McRib as Arbitrage](https://web.archive.org/web/2020/http://www.theawl.com/2011/11/a-conspiracy-of-hogs-the-mcrib-as-arbitrage) (archived — the Awl died too)
* [CME Group > Lean Hog Futures](https://www.cmegroup.com/markets/agriculture/livestock/lean-hogs.html)
* [Futures contract month codes](https://en.wikipedia.org/wiki/Futures_contract#Codes)
