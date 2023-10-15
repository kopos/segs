(ns seg.rules-engine
  (:require
    [clojure.edn :as edn]
    [clojure.set :refer [difference]]
    [clara.rules :refer [mk-session insert fire-rules query]]
    [seg.rules :refer [->Player ->PlayerRule fetch-segments fetch-anti-segments]]))

(defn calc-segments
  [session]
  (let [segs (query session fetch-segments)
        segs' (query session fetch-anti-segments)]
    (difference (set (map (comp :id :?segment) segs)) (set (map (comp :id :?segment) segs')))))

(defn run []
  (let [session (-> (mk-session 'seg.rules)
                    (insert (->Player 11 true true))
                    (fire-rules)
                    (insert (->PlayerRule 11 true))
                    (fire-rules))]
    (calc-segments session)))


(defn load-rules []
  (edn/read-string (slurp "rules.edn")))

(defn run2 []
  (let [session (-> (mk-session (load-rules))
                    (insert (->Player 11 true true))
                    (fire-rules))]
    (query session "fetch-segments2")))