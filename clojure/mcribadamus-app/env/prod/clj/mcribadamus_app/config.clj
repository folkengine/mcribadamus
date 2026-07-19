(ns mcribadamus-app.config
  (:require [taoensso.timbre :as timbre]))

(def defaults
  {:init
   (fn []
     (timbre/info "\n-=[mcribadamus-app started successfully]=-"))
   :middleware identity})
