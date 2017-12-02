(ns advent2017.day01
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(def INPUT-FILE "day01.txt")

(defn load-input []
  (let [digits-str (string/trim-newline (slurp (io/resource INPUT-FILE)))]
    (mapv (comp read-string str) digits-str)))

(defn captcha1 [digits]
  (captcha digits 1))

(defn captcha2 [digits]
  (captcha digits (/ (count digits) 2)))

(defn captcha [digits shift]
  (let [shifted-digits (nthrest (cycle digits) shift)]
    (->> (map vector digits shifted-digits)
         (filter #(apply = %))
         (map first)
         (reduce +))))

(defn run []
  [(captcha1 (load-input))
   (captcha2 (load-input))])

(comment
  (run)

  (captcha1 [])
  (captcha1 [0])
  (captcha1 [0 0])
  (captcha1 [1 1])
  (captcha1 [1 1 2 2])
  (captcha1 (load-input))

  (captcha2 [])
  (captcha2 [1 1])
  (captcha2 [1 1 2 2])
  (captcha2 [1 2 1 2])
  (captcha2 (load-input))
  )
