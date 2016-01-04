(ns sicp-ch3.paragraphs.3-53)


(defn stream-null? [stream]
 (or (empty? stream)))

;(defn interleave [s1 s2]
;        (if (stream-null? s1)
;          s2
;          (cons-stream (stream-car s1)
;                       (interleave s2 (stream-cdr s1)))))