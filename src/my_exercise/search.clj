(ns my-exercise.search
  (:require [hiccup.page :refer [html5]]))

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
   (results request)))
