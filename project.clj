(defproject advent2017 "0.1.0-SNAPSHOT"
  :description "Solutions to Avent of Code 2017"
  :url "https://github.com/tmyrtle/Advent2017"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-RC2"]
                 [org.clojure/data.csv "0.1.4"]]
  :main ^:skip-aot advent2017.core
  :target-path "target/%s"
  :test-paths ["src"]
  :profiles {:uberjar {:aot :all}})
