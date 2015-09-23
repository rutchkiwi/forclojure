(ns forclojure.thing
  (:require [clojure.test :refer :all]))

(defn maketuples [l] (map vector (drop-last 1 (into [-1] l)) l))

(def pred (fn [[a b]] (< a b)))

(defn get-orig [l]
  (map last l))

(defn drop-until-decreasing
  [l]
  (if (empty? l)
    '()
    (get-orig (drop-while pred (maketuples l)))
    )
  )

(defn take-until-decreasing
  [li]
  (get-orig (take-while pred (maketuples li))))

(defn susbseqs [li]
  (map take-until-decreasing
    (take-while (complement empty?) (iterate drop-until-decreasing li))))

(with-test
  (defn longest-subseq [l]
      (let [longest (filter #(> (count %) 1) (susbseqs l))]
        (if (empty? longest)
          []
          (apply max-key count longest)
          ))
      )

  (is (= [0 1 2 3] (longest-subseq [1 0 1 2 3 0 4 5])))
  (is (= [5 6] (longest-subseq [5 6 1 3 2 7])))
  (is (= [3 4 5] (longest-subseq [2 3 3 4 5])))
  (is (= [] (longest-subseq [7 6 5 4])))                    ;[7 6 5 4]) [])

  )


(def ll  [0 1 2 0 1 2 0 1 2 3 4])
(def l2  [1 5 10 2 3])
(def l3 [1 0 1 2 3 0 4 5])
(def l4 [4 1 2])

(deftest dud
  (is (= '(1 2 3 4 5)  (drop-until-decreasing [11 12 13 1 2 3 4 5])))
  (is (= '(11 12 13)  (take-until-decreasing [11 12 13 1 2 3 4 5])))
  (is (= '(0 1 2 3 0 4 5)  (drop-until-decreasing l3)))
  (is (= '(1 2)  (drop-until-decreasing l4)))
  (is (= '(0 1 2 0 1 2 3 4)  (drop-until-decreasing [0 2 4 0 1 2 0 1 2 3 4])))
  (is (= '(0 1 2 3 4)  (drop-until-decreasing '(0 1 2 0 1 2 3 4))))
  (is (= '(0 1 2 3 4)  (drop-until-decreasing '(0 1 2 0 1 2 3 4))))
  (is (= '()  (drop-until-decreasing '(0 1 2 3 4))))
  (is (= '(2 3)  (drop-until-decreasing l2)))
  (is (= 3 (count (susbseqs ll))))
  (is (= 2 (count (susbseqs l2))))
  (is (= '([1 5 10] [2 3]) (susbseqs l2)))
  (is (= '([0 1 2] [0 1 2] [0 1 2 3 4]) (susbseqs ll)))
  (is (= '([1] [0 1 2 3] [0 4 5]) (susbseqs l3)))
  (is (= [] (filter seq? '())))
  (is (= [] (filter seq? [])))
  (is (= [] (filter seq? '(1))))
  (is (= [] (filter seq? [1])))
  )