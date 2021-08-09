(ns ekans.moves-test
  (:require [clojure.test :refer :all]
            [ekans.moves :refer :all]))

(def make-move-test-cases [
  [0 0 4 :down-beat]
  [0 0 7 :mixed-beat]
  [0 0 11 :up-beat]
  [-1 0 7 :down-beat]
  [1 0 9 :up-beat]
  [0 -1 7 :down-beat]
  [0 1 9 :up-beat]
  [-2 -2 10 :down-beat]
  [2 2 6 :up-beat]])

(deftest make-move-is-correct
  (doseq [[expected-stat-modifier expected-bonus-modifier expected-dice-roll expected-result-type] make-move-test-cases
          :let [{:keys [result-type adjusted-roll stat-modifier bonus-modifier raw-roll] :as actual-move-result}
                  (make-move expected-stat-modifier expected-bonus-modifier expected-dice-roll)]]
    (testing "Does the move result match the expected values"
      (is (= result-type expected-result-type))
      (is (= adjusted-roll (+ expected-stat-modifier expected-bonus-modifier expected-dice-roll)))
      (is (= stat-modifier expected-stat-modifier))
      (is (= bonus-modifier expected-bonus-modifier))
      (is (= raw-roll expected-dice-roll)))))
