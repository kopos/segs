(ns seg.rules
  (:require [clara.rules :refer :all]))

(defrecord Segment [id])
(defrecord AntiSegment [id])

(defrecord Player [id is-staff is-active])

(defrecord PlayerRule [id remove-all?])

(defrule all?
  [Player (= is-active true)]
  =>
  (insert! (->Segment 1)))

(defrule inhouse?
  [Player (= is-staff true)]
  =>
  (insert! (->Segment 2)))

(defrule except-some?
  [PlayerRule (= remove-all? true)]
  =>
  (retract! (->Segment 1)))

(defrule not-all?
  [Player (= is-active false)]
  =>
  (insert! (->AntiSegment 1)))


(defquery fetch-segments
  []
  [?segment <- Segment])


(defquery fetch-anti-segments
  []
  [?segment <- AntiSegment])