(ns pull.core-test
  (:require [clojure.test :refer :all]
            [pull.core :refer :all]))

(deftest pull-test
  (are [m pattern result] (= result (pull m pattern))
    {:a 1 :b 2} [:a] {:a 1}
    {:a 1 :b 2 :c 3} [:a :c] {:a 1 :c 3}
    {:a 1 :b {:a 1 :b 2}} [:a {:b [:a]}] {:a 1 :b {:a 1}}
    
    {:a 1} [:b] nil
    {:a 1} [{:b [:a]}] nil
    {:a {:a 1}} [{:a [:b]}] nil))
