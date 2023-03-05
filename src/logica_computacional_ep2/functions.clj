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

(defn find-first-non-terminal-symbol-index
  [language-chain non-terminal-symbols]
  (loop [index 0]
    (if (>= index (count language-chain))
      nil
      (if (some #{(subs language-chain index (inc index))} non-terminal-symbols)
        index
        (recur (inc index))))))

(defn replace-at-index
  [index-to-replace language-chain formations]
  (vec (for [formation formations]
         (apply str (concat (take index-to-replace language-chain) formation (drop (inc index-to-replace) language-chain))))))

(defn replace-language-chain-with-formations
  [language-chains index formations]
  (concat (subvec language-chains 0 index) formations (subvec language-chains (inc index))))


(defn remove-long-chains [language-chains max-length]
  (vec (filter #(<= (count %) max-length) language-chains)))

(defn generate-language-chains
  [index rule-hash-map word-length terminal-symbols non-terminal-symbols language-chains]
  (if (or (= index (count language-chains)) (> index (count language-chains)))
    language-chains
    (if (has-only-terminal-symbols (nth language-chains index) terminal-symbols)
		(generate-language-chains (+ index 1) rule-hash-map word-length terminal-symbols non-terminal-symbols language-chains)
		
    (let [first-non-terminal-symbol-index (find-first-non-terminal-symbol-index (nth language-chains index) non-terminal-symbols)]
      (if first-non-terminal-symbol-index
        (let [possible-formations (remove-long-chains (replace-at-index first-non-terminal-symbol-index (nth language-chains index) (get rule-hash-map (str (nth (nth language-chains index) first-non-terminal-symbol-index)))) word-length)]
        (let [new-language-chains (vec (replace-language-chain-with-formations language-chains index possible-formations))]
          (generate-language-chains index rule-hash-map word-length terminal-symbols non-terminal-symbols new-language-chains)))
        (generate-language-chains (+ index 1) rule-hash-map word-length terminal-symbols non-terminal-symbols language-chains)
      )))))