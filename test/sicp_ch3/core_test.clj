(ns sicp-ch3.core-test
  (:require [clojure.test :refer :all]
            [sicp-ch3.core :refer :all]))

(deftest a-test
  (testing "FIXME, I fail."
    (is (= 0 1))))


;(def balance (atom 100))
;
;(defn withdraw [amount]
;        (if (>= @balance amount)
;            (reset! balance (- @balance amount) )
;            "Insufficient funds"))

;This does not work
(defn new-withdraw []
  (let [balance (atom 100)]
    (fn [amount]
      (if (>= @balance amount)
        (reset! balance (- @balance amount))
        "Insufficient funds"))))

;This works
;You need to create a account processor
;(def W1 (scip-ch3.core-test/make-withdraw (atom 100))
;(W1 50) => 50
;Note % in the first parameter the function is applied to
(defn make-withdraw [init]
  (let [balance (atom init)]
    (fn [amount]
      (if (>= @balance amount)
        (swap! balance #(- % amount))
        (str "Cannot withdraw amount of [" amount "]: Insufficient funds : Current Balance [" @balance "]")))))

(defn make-withdraw-with-pin [init pin]
  (let [balance (atom init)
        stored-pin pin]
    (fn [amount provided-pin]
      (cond
        (not= stored-pin provided-pin) "Incorrect pin"
        (< @balance amount) (str "Cannot withdraw amount of [" amount "]: Insufficient funds : Current Balance [" @balance "]")
        :else (swap! balance #(- % amount))))))

;Excercie 3.1
(defn make-accumulator [acc]
  (let [sum (atom acc)]
    (fn [amount]
      (swap! sum #(+ % amount)))))


;dispatching on keywords as apposed to strings
(defn make-account [init]
  (let [balance (atom init)]
    (defn withdraw [amount]
      (if (>= @balance amount)
        (swap! balance #(- % amount))
        "Insufficient funds"))
    (defn deposit [amount]
      (swap! balance #(+ % amount)))
    (defn dispatch [m amount]
      (cond (= m :withdraw) (withdraw amount)
            (= m :deposit) (deposit amount)
            :else (str "Unknown request: MAKE-ACCOUNT"
                       m))))

  dispatch)

(defn square [x]
  (* x x))

;Excercise 3.2
(defn make-monitored [function]
  (let [counter (atom 0)]
    (defn dispatch [m]
      (if (= m :count) @counter
                       (do
                         (swap! counter inc)
                         (function m)))))

  dispatch)

;3.2.1 lambda does not exits using fn or #
(defn squarel []
  (fn [x] (* x x)))


;3.3
;Page 318

;Page 399

(defn stream-car [s]
  (first s))
(defn stream-cdr [s]
  (force (second s)))

;
;


(defn stream-ref [ s n]
  (if (= n 0)
    (stream-car s)
    (stream-ref (stream-cdr s)
                (- n 1))))

(defn stream-map [proc & argstreams]
  (lazy-seq
    (if (empty? (first argstreams)) '()
                                    (cons
                                      (apply proc (map first argstreams))
                                      (apply stream-map (cons proc (map rest argstreams)))))))


(defn show [x]
  (println x) x)

;; Exercise 3.52

(def sum (atom 0))

(defn accum [x]
  (swap! sum #(+ x %)))

(def stream (map accum (range 1 20))) ; 1

(def y (filter even? stream)) ; 6

(def z (filter #(= (rem % 5) 0) stream)) ; 10

(nth y 7) ; 136





