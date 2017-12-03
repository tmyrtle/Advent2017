(ns advent2017.day02
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [clojure.string :refer [trim-newline]]
            [clojure.test :refer [deftest are is run-tests]]))

(def INPUT-FILE "day02.txt")

(defn load-input []
  (with-open [reader (io/reader (io/resource INPUT-FILE))]
    (->> (csv/read-csv reader :separator \tab)
         (mapv #(mapv read-string %)))))

;; Part 1

(defn rowcalc [row]
  (- (apply max row) (apply min row)))

(defn checksum1 [rows]
  (->> (map rowcalc rows)
       (reduce +)))

;; Part 2

(defn find-divisor [row]
  (let [row (reverse (sort row))]
    (loop [row row]
      (let [n (first row)
            res (first (filter #(= 0 (mod n %)) (rest row)))]
        (if res
          (/ n res)
          (recur (rest row)))))))

(defn checksum2 [rows]
  (->> (map find-divisor rows)
       (reduce +)))

;; Misc

(defn run []
  [(checksum1 (load-input))
   (checksum2 (load-input))])
