(ns forclojure.comp
  (:require [clojure.test :refer :all]))

(defn compose
  ([f1] f1)
  ([f1 & remm]
   (fn [& args]
     (println f1 remm args)
      (f1 (apply (apply compose remm ) args))
  )))

(deftest test-things
  (is (= [2 3 4] ((compose rest) [1 2 3 4])))
  (is (= [3 2 1] ((compose rest reverse) [1 2 3 4])))
  )

comp