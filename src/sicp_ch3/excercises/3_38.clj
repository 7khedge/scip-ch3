(ns sicp-ch3.excercises.3-38)

;; Exercise 3.38: Suppose that Peter, Paul, and Mary share a joint bank account that initially contains $100.
;; Concurrently, Peter deposits $10, Paul withdraws $20, and Mary withdraws half the money in the account,
;; by executing the following commands:

;;Peter: (set! balance (+ balance 10))
;;Paul:  (set! balance (- balance 20))
;;Mary:  (set! balance (- balance (/ balance 2)))

;;a. Listallthedifferentpossiblevaluesforbalanceafterthesethree transactions have been completed,
;; assuming that the banking system forces the three processes to run sequentially in some order.

;;b. What are some other values that could be produced if the sys- tem allows the processes to be interleaved?
;; Draw timing dia- grams like the one in Figure 3.29 to explain how these values can occur.


;; 100
;;Peter first
;; 45

;;Paul first
;; 45

;;Mary frist
;; 40
