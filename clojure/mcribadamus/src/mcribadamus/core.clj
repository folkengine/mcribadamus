(ns mcribadamus.core
  (:require [cheshire.core :refer :all]))

(defn to-symbol [year symbol-entry]
  (str (:symbol symbol-entry) year ".CME"))

(defn symbols-after-month [month symbols-map]
  (drop-while #(> month (:month %)) symbols-map))

(defn symbols-for-year [year month symbols-map]
  (map #(to-symbol year %)
        (symbols-after-month month symbols-map)))

(defn future-symbols [start-year start-month symbols-map]
  (lazy-seq
    (concat (symbols-for-year start-year start-month symbols-map)
      (future-symbols (inc start-year) 1 symbols-map))))

(defn symbol-url [symbol]
  (str "http://finance.yahoo.com/webservice/v1/symbols/"
    symbol "/quote?format=json&view=detail"))

(defn slurp-symbol [symbol]
  (slurp (symbol-url symbol)))

(defn decode-entry [key val]
  (cond
    (some #(= key %) [:day_low :year_high :volume :ts :year_low :day_high
                      :chg_percent :change :price])
        {key (decode val)}
    :else {key val}))

(defn decode-symbol [data]
  (->> data (map #(decode-entry (first %) (second %))) (reduce merge) ))

(defn parse-symbol-data [symbol]
 (let [result (slurp-symbol symbol)]
  (->> (parse-string result true) :list :resources first :resource :fields
          decode-symbol)))
