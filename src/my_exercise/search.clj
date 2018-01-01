(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]
            [my-exercise.api :as api]))

(defn header [_]
  [:head
   [:meta {:charset "UTF-8"}]
   [:meta {:name "viewport"
           :content "width=device-width, initial-scale=1.0, maximum-scale=1.0"}]
   [:title "Your next election"]
   [:link {:rel "stylesheet" :href "default.css"}]])

(defn heading [_]
  [:div {:class "heading"}
   [:a {:href "/"} "Home"]
   [:h1 "Your next election"]])

; Form hashmap with only location params to send to API client functions.
(defn parse-data [request]
  (def params (request :params))
  (def street (params :street))
  (def city (params :city))
  (def state (params :state))
  (def zip (params :zip))
  (zipmap [:street :city :state :zip] [street city state zip]))

; Parse form input and collect results from API client functions.
(defn get-result [request]
  (def data (parse-data request))
  (def identifiers (api/parse-identifiers data))
  (api/get-elections identifiers))

(defn input-info [request]
  [:div {:class "input-info"}
   [:h2 "Your input info"]
   [:ul
    [:li "Street: " ((request :params) :street)]
    [:li "City: "  ((request :params) :city)]
    [:li "State: "  ((request :params) :state)]
    [:li "Zip: "  ((request :params) :zip)]]])

(defn results [request]
  [:div {:class "results"}
   [:h2 "Your election info"]
   [:p (get-result request)]])

(defn page [request]
  (html5
   (header request)
   (heading request)
   (input-info request)
   (results request)))
