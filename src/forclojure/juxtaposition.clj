(ns forclojure.juxtaposition
  (:require [clojure.test :refer :all]))

(defn jux [& fns]
  (fn [& args]
    (map
      (fn [the-fun] (apply the-fun args))
      fns
      )
    )
  )

(deftest jux-tests
  (is (= [21 6 1] ((jux + max min) 2 3 5 1 6 4)))
  (is (= ["HELLO" 5] ((jux #(.toUpperCase %) count) "hello")))
  (is (= [2 6 4] ((jux :a :c :b) {:a 2, :b 4, :c 6, :d 8 :e 10})))
  (is (= [21 6 1 -17] ((jux + max min -) 2 3 5 1 6 4)))
  )