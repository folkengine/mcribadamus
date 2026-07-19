(ns mcribadamus-app.routes.home
  (:require [mcribadamus-app.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :refer [ok]]
            [clojure.java.io :as io]
            [mcribadamus.core :refer :all]))

(def symbols [{:symbol "ABC" :month 1} {:symbol "ABD" :month 2}
  {:symbol "ABE" :month 4} {:symbol "ABF" :month 6}])

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)
                 :my-symbols symbols}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/about" [] (about-page)))

