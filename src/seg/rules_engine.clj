(ns seg.rules-engine
  (:require
    [clojure.edn :as edn]
    [clara.rules :refer [mk-session insert fire-rules query]]
    [seg.rules :refer [->Player fetch-segments]]))

(defn run []
  (let [res (-> (mk-session 'seg.rules)
                (insert (->Player 11 true true))
                (fire-rules))]
    (query res fetch-segments)))


(defn load-rules []
  (edn/read-string (slurp "rules.edn")))

(defn run2 []
  (let [res (-> (mk-session (load-rules))
                (insert (->Player 11 true true))
                (fire-rules))]
    (query res "fetch-segments2")))