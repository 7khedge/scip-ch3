(ns sicp-ch3.excercises.3-52
  (require [sicp-ch3.excercises.3-51 :refer :all]))

(def sum (atom 0))

(defn accum [x]
  (swap! sum #(+ x %)))

(def stream (map accum (range 1 20))) ; 1
(def y (filter even? stream)) ; 6
(def z (filter #(= (rem % 5) 0) stream)) ; 10
;;(nth y 7) ; 136