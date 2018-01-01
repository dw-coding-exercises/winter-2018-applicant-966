(ns my-exercise.ocd
  (:require [clojure.string :as string]))

; Create identifiers only with city and state for now.
(defn parse-identifiers [data]
  (def state (string/lower-case (data :state)))
  (def city (string/lower-case (data :city)))
  (def state-id (str "ocd-division/country:us/state:" state))
  (def place-id (str state-id "/place:" (string/replace city " " "_")))
  [state-id place-id])
