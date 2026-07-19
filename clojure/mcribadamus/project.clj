(defproject mcribadamus "0.2.0-SNAPSHOT"
  :description "For centuries people have looked to the wisdom of Nostradamus in order to see into the future. In modern times few problems have vexed mankind more than when the McRib(tm) sandwich will be available. Now, thanks to the power of McRibadamus, they can know the fate of the value meal they love so much"
  :url "https://github.com/ColumbusCodeClub/mcribadamus"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                  [cheshire "5.5.0"]]
  :profiles {:dev {:dependencies [[midje "1.7.0"]]} :midje {}})
