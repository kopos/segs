(ns seg.core
  (:require [seg.rules-engine :as r]))

(defn run [opts]
  (println "seg.core init" opts (r/run)))


(defn run2 [opts]
  (println "seg.core init" opts (r/run2)))
