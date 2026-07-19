(ns mcribadamus-app.config
  (:require [selmer.parser :as parser]
            [taoensso.timbre :as timbre]
            [mcribadamus-app.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (timbre/info "\n-=[mcribadamus-app started successfully using the development profile]=-"))
   :middleware wrap-dev})
