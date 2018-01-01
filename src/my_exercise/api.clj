(ns my-exercise.api
  (:require [clojure.string :as string]
            [clj-http.client :as client]))

; Accepts hashmap containing location information.
; Create identifiers only with city and state for now.
; Return identifier strings as sequence for dynamic use.
(defn parse-identifiers [data]
  (def state (string/lower-case (data :state)))
  (def city (string/lower-case (data :city)))
  (def state-id (str "ocd-division/country:us/state:" state))
  (def place-id (str state-id "/place:" (string/replace city " " "_")))
  [state-id place-id])

; Accepts sequence of indentifier strings.
; Returns body of API response. Will be () if invalid data or no elections.
(defn get-elections [identifiers]
  (def identifiers-string (string/join "," identifiers))
  (def api-base-string "https://api.turbovote.org/elections/upcoming?district-divisions=")
  (def response (client/get (str api-base-string identifiers-string)))
  (response :body))
