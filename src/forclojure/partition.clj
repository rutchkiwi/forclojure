(ns forclojure.partition
  (:require [clojure.test :refer :all]))


;(defn part [n l]
;  (filter #(= (count %) n)
;    (map (partial take n)
;      (take-while (complement empty?)
;        (iterate (partial drop n) l))))
;  )

(defn part [n list]
  (when (>= (count list) n)
    (cons (take n list) (part n (drop n list)))
    )
  )

(deftest forclojure
  (is (= (part 3 (range 9)) '((0 1 2) (3 4 5) (6 7 8))))
  (is (= (part 2 (range 8)) '((0 1) (2 3) (4 5) (6 7))))
  (is (= (part 3 (range 8)) '((0 1 2) (3 4 5))))
  )
