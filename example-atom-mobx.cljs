(ns user
  (:require ["react" :as react]
            ["react-dom" :as rdom]
            ["https://esm.sh/mobx@6.12.0" :refer [makeAutoObservable]]
            ["https://esm.sh/mobx-react-lite@4.0.5" :refer [observer]]))

(def my-timer
  (makeAutoObservable
   (atom {:counter 0})))

(defn inc-counter []
  (swap! my-timer update :counter inc))

(defn TimerView []
  #jsx [:span "Seconds passed: "
        (:counter @my-timer)])

(def TimerView (observer TimerView))

(defonce elt (doto (js/document.createElement "div") (js/document.body.prepend)))
(def root (rdom/createRoot elt))
(.render root #jsx [TimerView])

(defonce create-interval
  (do
    (js/setInterval inc-counter 1000)
    true))