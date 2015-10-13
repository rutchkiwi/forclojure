(ns forclojure.maps
  (:require [clojure.test :refer :all]))

(defn zmap
  [l1 l2]
  (apply array-map (interleave l1 l2))
  )

(deftest zmap-test
  (is (= (zmap [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3}))
  )

(defn iter [f initial]
  (lazy-seq
    (cons
      initial
      (iter f (f initial))
      )
    ))

(deftest iter-test
  (is (= (take 5 (iter #(* 2 %) 1)) [1 2 4 8 16])))
