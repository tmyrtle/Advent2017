(defproject advent2017 "0.1.0-SNAPSHOT"
  :description "Tim's solutions to 2017 Avent of Code in Clojure"
  :url "https://github.com/tmyrtle"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0-RC2"]]
  :main ^:skip-aot advent2017.core
  :target-path "target/%s"
  :test-paths ["src"]
  :profiles {:uberjar {:aot :all}})
