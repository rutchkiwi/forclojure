(ns forclojure.thing
  (:require [clojure.test :refer :all]))

(defn maketuples [l] (map vector (drop-last 1 (into [-1] l)) l))


(with-test
  (defn longest-subseq [l]
    (apply max-key count
      (map (fn [li] (map last li))
        (split-with (fn [[a b]] (<= a b)) (maketuples l)))))

  (is (= [0 1 2 3] (longest-subseq [1 0 1 2 3 0 4 5])))
  (is (= [0 1 2 3] (longest-subseq [1 0 1 2 3])))
  )


(def pred (fn [[a b]] (<= a b)))

(defn get-orig [l]
  (map last l))

(defn drop-until-decreasing
  [l]
  (println "- " l)
  (if (empty? l)
    '()
    (get-orig (drop-while pred (maketuples (rest l))))
    )
  )

(defn take-until-decreasing
  [li]
  (get-orig (take-while pred (maketuples li)))
  )

(defn susbseqs [li]
  (map take-until-decreasing
    (take-while (complement empty?) (iterate drop-until-decreasing li)))
  )

;(take 10 (iterate drop-until-decreasing ll))


(def ll  [0 1 2 0 1 2 0 1 2 3 4])
(def l2  [1 5 10 2 3])

(deftest dud
  (is (= '(1 2 3 4 5)  (drop-until-decreasing [11 12 13 1 2 3 4 5])))
  (is (= '(11 12 13)  (take-until-decreasing [11 12 13 1 2 3 4 5])))
  (is (= '(0 1 2)  (take-until-decreasing ll)))
  (is (= '(0 1 2 0 1 2 3 4)  (drop-until-decreasing [0 2 4 0 1 2 0 1 2 3 4])))
  (is (= '(0 1 2 3 4)  (drop-until-decreasing '(0 1 2 0 1 2 3 4))))
  (is (= '()  (drop-until-decreasing '(0 1 2 3 4))))

  (is (= '(2 3)  (drop-until-decreasing l2)))
  (is (= 3 (count (susbseqs ll))))
  (is (= 2 (count (susbseqs l2))))
  (is (= '([1 5 10] [2 3]) (susbseqs l2)))
  ()
  )