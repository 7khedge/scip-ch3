(ns sicp-ch3.paragraphs.3-51)

(defn stream-car [s]
  (first s))

(defn stream-cdr [s]
  (rest s))

(defn stream-ref [s n]
  (if (= n 0)
    (stream-car s)
    (stream-ref (stream-cdr s) (- n 1))))

(defmacro cons-stream [el coll]
  (list 'lazy-seq (list cons el coll)))


;cdr next
;car first