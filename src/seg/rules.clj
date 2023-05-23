(ns seg.rules
  (:require [clara.rules :refer :all]))

(defrecord Segment [id])
(defrecord Player [id is-staff is-active])

(defrule all?
  [Player (= is-active true)]
  =>
  (insert! (->Segment 1)))

(defrule inhouse?
  [Player (= is-staff true)]
  =>
  (insert! (->Segment 2)))


(defquery fetch-segments
  []
  [?segment <- Segment])
