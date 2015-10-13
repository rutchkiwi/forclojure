(ns problem65 (:require [clojure.test :refer :all]))


(defn coll-identifier [col]
  :unknown)

(deftest coll-identifier-test
  (is (= :map (coll-identifier {:a 1, :b 2})))
  (is (= :list (coll-identifier (range (rand-int 20)))))
  (is (= :vector (coll-identifier [1 2 3 4 5 6])))
  (is (= :set (coll-identifier #{10 (rand-int 5)})))
  (is (= [:map :set :vector :list] (map coll-identifier [{} #{} [] ()]))))