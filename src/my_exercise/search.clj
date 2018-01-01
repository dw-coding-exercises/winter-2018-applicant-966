(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]
            [my-exercise.ocd :as ocd]))

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

(defn parse-data [request]
  (def params (request :params))
  (def street (params :street))
  (def city (params :city))
  (def state (params :state))
  (def zip (params :zip))
  (zipmap [:street :city :state :zip] [street city state zip]))

(defn get-result [request]
  (def data (parse-data request))
  (def identifiers (ocd/parse-identifiers data))
  (print identifiers))

(defn results [request]
  [:div {:class "results"}
   [:ul
    [:li "Street: " ((request :params) :street)]
    [:li "City: "  ((request :params) :city)]
    [:li "State: "  ((request :params) :state)]
    [:li "Zip: "  ((request :params) :zip)]]])

(defn page [request]
  (html5
   (header request)
   (heading request)
   (get-result request)
   (results request)))
