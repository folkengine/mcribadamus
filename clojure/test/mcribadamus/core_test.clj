(ns mcribadamus.core-test
  (:require [midje.sweet :refer :all]
            [mcribadamus.core :refer :all]))

(facts "about determining the correct futures symbols"
  (fact "for a month/year get back at least 9 symbols"
    (count (get-symbols 0 16)) => 9)
  (fact "for Jan 2015 the first symbol back should be LHG16.CME"
    (first (get-symbols 0 16)) => "LHG16.CME")
       )
