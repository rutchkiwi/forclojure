(ns forclojure.seqred
  (:require [clojure.test :refer :all]))

(defn seqreds
  ([f seq]
   (seqreds f (first seq) (rest seq))
    )

  ([f acc seq]
   (lazy-seq
     (if (empty? seq) (list acc)
       (cons acc (seqreds f (f acc (first seq)) (rest seq)))
       ))
    )
  )

(deftest seqred-tests-with-reductions1
  ;(is (= (seqreds + []) [0]))
  (is (= (seqreds + 1 []) [1]))
  (is (= (seqreds + [1]) [1]))
  (is (= (seqreds + 1 [1]) [1 2]))
  (is (= (seqreds + [1 2]) [1 3]))
  (is (= (seqreds + 1 [2 3]) [1 3 6]))
  (is (= (take 5 (seqreds + (range))) [0 1 3 6 10]))
  (is (= (take 5 (seqreds + 10 (range))) [10 10 11 13 16]))
  (is (= (seqreds conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]]))
  (is (= (last (seqreds * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))
  (is (= (seqreds conj [1] []) [[1]]))
  )

;(deftest seqred-tests-with-reductions2
;  (is (= (reductions + []) [0]))
;  (is (= (reductions + 1 []) [1]))
;  (is (= (reductions + [1]) [1]))
;  (is (= (reductions + 1 [1]) [1 2]))
;  (is (= (reductions + [1 2]) [1 3]))
;  (is (= (reductions + 1 [2 3]) [1 3 6]))
;  (is (= (take 5 (reductions + (range))) [0 1 3 6 10]))
;  (is (= (take 5 (reductions + 10 (range))) [10 10 11 13 16]))
;  (is (= (reductions conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]]))
;  (is (= (last (reductions * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))
;  )
;
;(deftest seqred-tests-with-reductions3
;  (is (= (reductions * []) [1]))
;  (is (= (reductions * 1 []) [1]))
;  (is (= (reductions * [1]) [1]))
;  (is (= (reductions * 1 [1]) [2]))
;  (is (= (reductions * [1 2]) [1 3]))
;  (is (= (reductions * 1 [2 3]) [3 6]))
;  (is (= (take 5 (reductions + (range))) [0 1 3 6 10]))
;  (is (= (take 5 (reductions + 10 (range))) [10 11 13 16 20]))
;  (is (= (reductions conj [1] [2 3 4]) [[1] [1 2] [1 2 3] [1 2 3 4]]))
;  (is (= (last (reductions * 2 [3 4 5])) (reduce * 2 [3 4 5]) 120))
;  )
