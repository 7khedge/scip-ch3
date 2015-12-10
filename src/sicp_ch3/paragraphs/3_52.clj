(ns sicp-ch3.paragraphs.3-52
  (:require [sicp-ch3.paragraphs.3-51 :refer :all]))


(defn integers-starting-from [n]
        (cons-stream n (integers-starting-from (inc n))))


(defn divisible? [x y]
 (= (rem x y) 0))

(def integers (integers-starting-from 1))

(defn no-sevens []
          (filter #(not (divisible? % 7)) integers))