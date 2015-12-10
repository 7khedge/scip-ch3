(ns sicp-ch3.excercises.3-12)

(defn append [x y]
        (if (empty? x)
          y
          (cons (first x) (append (second x) y))))

(def logger (agent (list)))

(defn log [msg]
  (send logger #(cons %2 %1) msg))

(defn make-pair [x y]
  (agent (list x y)))

(defn append-pair [x y]
  (send y #(cons %2 %1) (second @x))
  (send y #(cons %2 %1) (first @x)))

(defn pop-pair [x]
  (send x rest)
  (send x rest))

(definterface MutableList
  (setcar [newcar])
  (car [])
  (setcdr [newcdr])
  (cdr [])
  (last []))

;;#' access var (meta #'x  )

(deftype Cons [^:unsynchronized-mutable car
               ^:unsynchronized-mutable cdr]
  MutableList
  (setcar [this newcar] (do (set! car newcar) this))
  (car [this] car)
  (setcdr [this newcdr] (do (set! cdr newcdr) this))
  (cdr [this] cdr)
  (last [this]
    (if (nil? (.cdr this))
      this
      (.last (.cdr this))))
  Object
  (equals [this other] (and (= car (.car other)) (= cdr (.cdr other))))
  (hashCode [this] (.hashCode car))
  (toString [this] (str "[" car " . " cdr "]")))

(defn add [x col]
  (conj! (transient col) x)
  (persistent! col))


(defn make-withdraw [init]
  (let [balance (atom init)]
    (fn [amount]
      (if (>= @balance amount)
        (swap! balance #(- % amount))))))