(ns logica-computacional-ep2.functions
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
			[clojure.string :as str]))

(defn extract-data
	[fname]
	(let [data (slurp fname)
		data-splitted (str/split data #"\n")
	]
	data-splitted
	)
)

(defn get-non-terminal-symbols
	[data-splitted]
	(str/split (first data-splitted) #" ")
)

(defn get-terminal-symbols
	[data-splitted]
	(str/split (second data-splitted) #" ")
)

(defn get-inital-symbol
	[data-splitted]
	(last data-splitted)
)

(defn build-language-hash-map
	[data-splitted non-terminal-symbols]
	;; (hash-map)
	(let [rule-hash-map {}]
	(for [i (range (count non-terminal-symbols))]
			;; (assoc rule-hash-map (nth non-terminal-symbols i)  (str/split (nth data-splitted (+ i 2)) #" "))
			
	)
	(println rule-hash-map)
	)
)

;; (defn generate-language-chain
;; 	[chain]
;; )