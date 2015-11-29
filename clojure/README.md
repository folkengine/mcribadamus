# Testing

This project use [Midje](https://github.com/marick/Midje) for testing.

    $> lein midje :autotest


# Examples
Examples from 11/20's meeting...

Run ```lein repl```

```clojure
; First we created a vector of valid months to have data
(def valid-months [2 4 5 6 7 8 10 12])
; #'user/valid-months

; Decrement the list because Java dates are zero indexed
(def valid-months (map dec valid-months))
; #'user/valid-months

; turn this back into a vector
(def valid-months (vec valid-months))
; #'user/valid-months

; a function that will return a Java Date object representing now
(defn now [] (new java.util.Date))
; #'user/now

; get the month from the return value of this function
(def month (.getMonth (now)))
; #'user/month

; split the valid months into two lists around the current month
(split-with (partial > month) valid-months)
; [(1 3 4 5 6 7 9) (11)]

; this is the same thing; partial returns a new function
(split-with (fn [x] (> month x)) valid-months)
; [(1 3 4 5 6 7 9) (11)]

; see what split-with does
(doc split-with)
; -------------------------
; clojure.core/split-with
; ([pred coll])
;   Returns a vector of [(take-while pred coll) (drop-while pred coll)]

; see what take-while does
(doc take-while)
```
