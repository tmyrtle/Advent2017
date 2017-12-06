(ns advent2017.day03
  (:require [clojure.test :refer [deftest are is run-tests]]))

;; Part 1

(def INPUT 325489)

;; (1 1 2 2 3 3 4 4 5 5 ...)
(defn range2 [start]
  (lazy-seq (cons start (cons start (range2 (inc start))))))

;; Sequence of directions to make a CCW spiral
;; ([1 0] [0 1] [-1 0] [0 -1] [1 0] ...)
(def spiral-dirs (cycle [[1 0] [0 1] [-1 0] [0 -1]]))

;; Legs of the spiral
;; ([dir steps] ...)
(def spiral-legs (map vector spiral-dirs (twice 1)))

;; Sequence of single step moves to make the spiral
(def spiral-steps (for [[dir steps] legs
                        _           (range steps)]
                    dir))

(defn add-vec [a b]
  (mapv + a b))

;; Sequence of positions, from sequence of steps
(defn positions
  ([steps]
   (positions [0 0] steps))
  ([start-pos steps]
   (lazy-seq (cons start-pos (positions (add-vec start-pos (first steps)) (rest steps))))))

;; Calculate position of location#
(defn position [loc] (nth (positions spiral-steps) (dec loc)))

;; distance of position from origin (in moves)
(defn dist [pos] (+ (Math/abs (get pos 0)) (Math/abs (get pos 1))))

(defn run1 []
  (dist (position INPUT)))


;; Part 2

;; mutable memory
(def memory (atom {}))

;; memory initial state
(defn reset-memory []
  (reset! memory {[0 0] 1}))

;; adjacent cells
(def adjacent [[1 0] [1 1] [0 1] [-1 1] [-1 0] [-1 -1] [0 -1] [1 -1]])

;; sum adjacent cells and store them to memory
(defn sum-and-store-adjacent [pos]
  (let [val (reduce (fn [v adj-pos] (+ v (get @memory (add-vec pos adj-pos) 0))) 0 adjacent)]
    (swap! memory assoc pos val)
    val))

;; sequence of adjacent sums
(def adjacent-sums (->> (iterate inc 2)
                        (map position)
                        (map sum-and-store-adjacent)))

;; run simulation forward until adjacent-sum is greater than INPUT
(defn run2 []
  (reset-memory)
  (some #(when (> % INPUT) %) adjacent-sums))
