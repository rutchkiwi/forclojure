(ns forclojure.distinct
  (:require
    [clojure.test :refer :all]))

(defn lazy-contains? [col key]
  (some #{key} col))

(defn dist
  [seq]
  (reduce
    (fn [dist-list, e]
      (if (some #{e} dist-list)
        dist-list
        (concat dist-list [e])
      ))
    []
    seq
    )
  )

(deftest tests
  (is (= (dist [:a :a :b :b :c :c]) [:a :b :c]))
  (is (= (dist [:b :b :c :c :a :a ]) [:b :c :a]))
  (is (= (dist '([2 4] [1 2] [1 3] [1 3])) '([2 4] [1 2] [1 3])))
  )
