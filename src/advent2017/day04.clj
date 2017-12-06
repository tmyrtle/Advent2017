(ns advent2017.day04
  (:require [clojure.java.io :as io]
            [clojure.string :refer [split-lines split]]
            [clojure.test :refer [deftest are is run-tests]]))

(def INPUT-FILE "day04.txt")

(defn load-input []
  (let [lines (slurp (io/resource INPUT-FILE))]
    (split-lines lines)))

;; Part 1

(defn valid1? [phrase]
  (let [words (split phrase #"\s+")
        word-set (set words)]
    (= (count word-set) (count words))))

(defn run1 []
  (count (filter valid1? (load-input))))

;; Part 2

(defn valid2? [phrase]
  (let [words (map set (split phrase #"\s+"))
        word-set (set words)]
    (= (count word-set) (count words))))

(defn run2 []
  (count (filter valid2? (load-input))))
