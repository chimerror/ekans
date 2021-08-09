(ns ekans.moves)

(defrecord MoveResult [result-type adjusted-roll stat-modifier bonus-modifier raw-roll])

(defn get-random-dice-roll [] (+ (inc (rand-int 6)) (inc (rand-int 6))))

(defn make-move
  ([] (make-move 0 0 (get-random-dice-roll)))
  ([stat-modifier] (make-move stat-modifier 0 (get-random-dice-roll)))
  ([stat-modifier bonus-modifier] (make-move stat-modifier bonus-modifier (get-random-dice-roll)))
  ([stat-modifier bonus-modifier dice-roll]
    (let [adjusted-roll (+ stat-modifier bonus-modifier dice-roll)
          result-type (cond (<= adjusted-roll 6) :down-beat
                            (<= adjusted-roll 9) :mixed-beat
                            :else :up-beat)]
      (->MoveResult result-type adjusted-roll stat-modifier bonus-modifier dice-roll))))