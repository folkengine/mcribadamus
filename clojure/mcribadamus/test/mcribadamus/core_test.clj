(ns mcribadamus.core-test
  (:use midje.sweet)
  (:require [clojure.test :refer :all]
            [mcribadamus.core :refer :all]))

(def symbols [{:symbol "ABC" :month 1} {:symbol "ABD" :month 2}
  {:symbol "ABE" :month 4} {:symbol "ABF" :month 6}])

(fact "given a symbol and a year it returns a symbol entry"
    (to-symbol 16 (first symbols)) => "ABC16.CME")

(fact "given a year, month and map of symbols it returns all symbols
       for the year after the given month"
    (symbols-for-year 16 2 symbols) => ["ABD16.CME" "ABE16.CME" "ABF16.CME"])

(fact "returns symbols after given year and given month"
 (take 5 (future-symbols 15 4 symbols)) => ["ABE15.CME" "ABF15.CME" "ABC16.CME"
                                            "ABD16.CME" "ABE16.CME"])

(fact "future-url takes a symbol and returns a stock URL"
  (symbol-url "ABC16.CME") => "http://finance.yahoo.com/webservice/v1/symbols/ABC16.CME/quote?format=json&view=detail")

(fact "slurp-symbol returns a string"
  (first (slurp-symbol "LHG16.CME")) => \{)

(facts "parse-symbol-data "
  (fact "returns market data for that symbol"
    (:symbol (parse-symbol-data "LHG16.CME")) =>  "LHG16.CME")

  (fact "returns nil for an invalid symbol"
    (:symbol (parse-symbol-data "ABC.CME")) => nil))
