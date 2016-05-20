(ns sieru.bash
  (require [clojure.java.shell :as sh])
  (:require [clojure.string :as str]))

(sh/sh "pwd")
(sh/sh "ls" "-aul")

(:out (sh/sh "ls" "-aul"))
(:out (sh/sh "pwd"))



(str/split-lines (:out (sh/sh "ls")))


(def cd-ls-aul-str (for [line-str  (drop 1 (str/split-lines (:out (sh/sh "ls" "-aul"))))]
  line-str))


(defn display-cd-ls-aul
  "cdのファイル名を表示
  別段意味のある処理ではないが勉強用ということで"
  []
  (->> cd-ls-aul-str
       (map (fn [c] (last (str/split c #" "))))
       (filter #(not (or (= "." %) (= ".." %))))))

(display-cd-ls-aul)




(for [line-str (drop 1 (str/split-lines (:out (sh/sh "ls" "-aul"))))
      s (str/split line-str #" ")]
  (list s))


(str/split "Clojure is awesome!" #" ")





