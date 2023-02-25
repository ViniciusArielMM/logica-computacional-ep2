(ns logica-computacional-ep2.functions
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn read-csv
	"Reads a .csv file and returns a LAZY SEQUENCE in which each element is a PERSISTENT VECTOR that represents one of
	the rows of the .csv data. Each element in a row is a STRING."
	[csv-file-name]

	(with-open [reader (io/reader csv-file-name)]
		(doall
			(csv/read-csv reader)
		)
	)
)

;; (defn build-language-rules-matrix
;; 	[csv-data]

;; 	(def language-rules-matrix [
;; 		;; for each element in csv-data (persistent vector)
;; 			;; creates a vector in which the first element of the vector is also the first element of the persistent vector
;; 			;; the second element of the vector should be another vector with the remaining data of the persistent vector
;; 			;; 
;; 	]
;; 	)
;; )