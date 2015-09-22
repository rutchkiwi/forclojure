(ns forclojure.core)
  (defn flat [seq]
    (print (str seq " - "))
        (cond
          (empty? seq) '()
          (not (sequential? (first seq))) (cons (first seq) (flat (rest seq)))
          (sequential? (first seq)) (concat (flat (first seq)) (flat (rest seq)))
          )
        )

(fn flat [seq]
  (print (str seq " - "))
  (cond
    (empty? seq) '()
    (not (sequential? (first seq))) (cons (first seq) (flat (rest seq)))
    (sequential? (first seq)) (concat (flat (first seq)) (flat (rest seq)))
    )
