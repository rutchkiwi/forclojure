(ns forclojure.occurance
  (:require [clojure.test :refer :all]))

(defn occurances [seq]
  (into {} (map (juxt first count) (partition-by identity (sort seq))))
  )

(defn occurances2 [seq]
  (letfn [
          (reducer [counts e]
            (assoc counts e (inc (get counts e 0))))
          ]
    (reduce reducer {} seq))
  )

(deftest tests
  (is (= (occurances2 [1 1 2 3 2 1 1]) {1 4, 2 2, 3 1}))
  (is (= (occurances2 [:a :b :b]) {:a 1, :b 2}))
  )

