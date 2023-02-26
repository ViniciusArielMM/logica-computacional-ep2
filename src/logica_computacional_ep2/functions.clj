(ns logica-computacional-ep2.functions
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn extract-data
  [fname]
  (let [data (slurp fname)
        data-splitted (str/split data #"\n")]
    data-splitted))

(defn get-non-terminal-symbols
  [data-splitted]
  (str/split (first data-splitted) #" "))

(defn get-terminal-symbols
  [data-splitted]
  (str/split (second data-splitted) #" "))

(defn get-inital-symbol
  [data-splitted]
  (last data-splitted))

(defn build-language-hash-map
  [data-splitted non-terminal-symbols]
  (reduce (fn [hash [index key]] (assoc hash key  (str/split (nth data-splitted (+ index 2)) #" "))) {} (map-indexed (fn [idx itm] [idx itm]) non-terminal-symbols)))

(defn has-only-terminal-symbols
  [word terminal-symbols]
  (let [pattern (re-pattern (str "[" (reduce str terminal-symbols) "]*"))]
    (re-matches pattern word)))

(defn generate-language-chains
  [index rule-hash-map word-length terminal-symbols language-chains]
  (if (= index (count language-chains))
    language-chains
    nil))