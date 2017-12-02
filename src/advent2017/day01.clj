(ns advent2017.day01
  (:require [clojure.java.io :as io]
            [clojure.string :refer [trim-newline]]
            [clojure.test :refer [deftest are is run-tests]]))

(def INPUT-FILE "day01.txt")

(defn load-input []
  (let [digits-str (trim-newline (slurp (io/resource INPUT-FILE)))]
    (mapv (comp read-string str) digits-str)))

(defn captcha [digits shift]
  (let [shifted-digits (nthrest (cycle digits) shift)]
    (->> (map vector digits shifted-digits)
         (filter #(apply = %))
         (map first)
         (reduce +))))

(defn captcha1 [digits]
  (captcha digits 1))

(defn captcha2 [digits]
  (captcha digits (/ (count digits) 2)))

(defn run []
  [(captcha1 (load-input))
   (captcha2 (load-input))])

(deftest part1
  (are [x y] (= (captcha1 x) y)
    [] 0
    [1] 1
    [1 2 1 2] 0
    [1 1 2 2 1] 4))

(deftest part2
  (are [x y] (= (captcha2 x) y)
    [] 0
    [0 1] 0
    [1 1] 2
    [1 2 1 2] 6
    [1 1 2 2 3 3] 0))

(comment
  (run)
  (run-tests)
  )
