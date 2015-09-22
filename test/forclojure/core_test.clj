(ns forclojure.core-test
  (:require [clojure.test :refer :all]
            [forclojure.core :refer :all]))

(deftest a-test
    (is (= (flat '()) '() )))

(deftest test2
    (is (=
           '(1)
           (flat '(1))
       )))

(deftest test3
  (is (=
        '(1 2)
        (flat '(1 2))
        )))


(deftest test3
  (is (=
        '(1 2 3)
        (flat '((1 2) 3))
        )))

(deftest test4
  (is (=
        '(1 2 3)
        (flat '(1 (2 3)))
        )))

(deftest test5
  (is (=
        '(1 2)
        (flat '((1 2)))
        )))


(deftest test6
  (is (=
        '(1 2 3 4)
        (flat '((1 2) (3 4)))
        )))

(deftest test7
  (is (=
        '(1 2 3 4 5 6)
        (flat '((1 2) 3 [4 [5 6]]))
        )))

(deftest test8
  (is (=
        '(4 5 6)
        (flat [4 [5 6]])
        )))

(deftest test9
  (is (=
        '(5 6)
        (flat '([5 6]))
        )))
