(ns logica-computacional-ep2.core
  (:gen-class)
  (:require
    ;; [logica-computacional-ep2.functions :refer [extract-data] :as functions]
    [logica-computacional-ep2.functions :refer :all]
  )
)

(defn -main
  [& args]

  (def data-splitted (extract-data (nth args 0)))
  (def terminal-symbols (get-terminal-symbols data-splitted ))
  (def non-terminal-symbols (get-non-terminal-symbols data-splitted))
  (def initial-symbol (get-inital-symbol data-splitted ))
  (def rule-hash-map (build-language-hash-map data-splitted non-terminal-symbols ))
  (def language-chains (generate-language-chains 0 rule-hash-map (count (nth args 1)) terminal-symbols non-terminal-symbols [initial-symbol]))

  (print "Cadeias de tamanho maximo ")
  (print (count (nth args 1)))
  (print ": ")
  (println language-chains)

  (println (type (first language-chains)))
  (println (type (nth args 1)))

  (if (some #(= % (nth args 1)) language-chains)
    (println "Cadeia foi aceita")
    (println "Cadeia nao foi aceita")))
