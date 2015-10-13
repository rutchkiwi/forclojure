(ns problem63
  (:require [clojure.test :refer :all]))

(defn group [f [first & rest :as seq]]
  (if (empty? seq) {}
    (let [rest-map (group f rest)
          key (f first)]
      (assoc
        rest-map
        key
        (cons first (get rest-map key []))
        )))
  )


(deftest test-cases
  (is (= (group #(> % 5) [1 3 6 8]) {false [1 3], true [6 8]}))
  (is (= (group #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
        {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]}))
  (is (= (group count [[1] [1 2] [3] [1 2 3] [2 3]])
        {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]}))
  )