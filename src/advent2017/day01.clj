(ns advent2017.day01
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def INPUT-FILE "day01.txt")

(defn load-input []
  (let [digits-str (string/trim-newline (slurp (io/resource INPUT-FILE)))]
    (mapv (comp read-string str) digits-str)))

(defn captcha [digits]
  (let [shifted-digits (rest (cycle digits))]
    (->> (map vector digits shifted-digits)
         (filter #(apply = %))
         (map first)
         (reduce +))))

(comment
  (captcha [])
  (captcha [0])
  (captcha [0 0])
  (captcha [1 1])
  (captcha [1 1 2 2])
  (captcha (load-input))
  )

(defn run []
  (captcha (load-input)))
