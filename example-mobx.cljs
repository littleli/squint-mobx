(ns counter
  (:require [squint.core :refer [defclass]]
            ["react" :as react]
            ["react-dom" :as rdom]
            ["https://esm.sh/mobx@6.12.0" :refer [makeAutoObservable]]
            ["https://esm.sh/mobx-react-lite@4.0.5" :refer [observer]]))

(defclass Timer
  (field -secondsPassed 0)
  (constructor [this]
               (makeAutoObservable this))
  Object
  (increase-timer [_]
                  (set! -secondsPassed (+ 1 -secondsPassed))))

(def my-timer (new Timer))

(defn TimerView []
  #jsx
   [:<>
    (observer (fn [{timer :timer} comp]
                #jsx
                 [:span "Seconds passed: " (.-secondsPassed timer)]))])

(defonce elt (doto (js/document.createElement "div") (js/document.body.prepend)))
(def root (rdom/createRoot elt))
(.render root #jsx [TimerView {:timer my-timer}])

(js/setInterval (fn [] (.increase-timer my-timer)) 1000)