(ns heroes.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(println "This text is printed from src/heroes/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text "Hello world!"}))

(defmulti main-view (fn [data owner] (number? (:text data))))

(defmethod main-view true [data owner]
  (reify om/IRender
    (render [_]
      (dom/h1 nil (str "Hello number " (:text data))))))

(defmethod main-view false [data owner]
  (reify om/IRender
    (render [_]
      (dom/h1 nil (str "Hello " (:text data) "!")))))

(om/root
  main-view
  app-state
  {:target (. js/document (getElementById "app"))})

(defn on-js-reload []
  ;; optionally touch your app-state to force rerendering depending on
  ;; your application
  ;; (swap! app-state update-in [:__figwheel_counter] inc)
)
