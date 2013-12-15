(defproject org.scheibenkaes/backdoor "0.1.0-SNAPSHOT"
  :description "Friend backend for persona"
  :url "http://scheibenkaes.org"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [cheshire "5.2.0"]
                 [http-kit "2.1.13"]]
  :profiles {:dev {:plugins [[lein-midje "3.1.1"]]
                   :dependencies [[midje "1.5.1"]
                                  [http-kit.fake "0.2.0"]]}})
