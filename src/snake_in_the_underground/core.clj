(ns snake-in-the-underground.core)

(def app-state (atom {:apple-position [4 3]
                      :snake-position [[1 0]
                                       [1 1]]}))

(defn add-thing [row thing-position character]
  (assoc row thing-position character))

(def board (atom [[ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.],
                  [ \. \. \. \. \. \. \. \. \. \.]]))

(defn snakes-for-row-y [all-snake-parts-in-game row-number]
  (filter #(= row-number (second %)) all-snake-parts-in-game))

(defn handle-row [index row]
  (let [apple-row-number (second
                          (:apple-position @app-state))]
    (map add-thing index (snakes-for-row-y (:snake-position @app-state) index)
    (if (= index apple-row-number)
      (add-thing row (first (:apple-position @app-state)) \A)
      row)))

(map-indexed (comp println handle-row)
             @board)